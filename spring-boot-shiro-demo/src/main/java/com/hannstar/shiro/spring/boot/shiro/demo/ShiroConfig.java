package com.hannstar.shiro.spring.boot.shiro.demo;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.DefaultSessionManager;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * 
 * @author jiangxinjun
 * @date 2019-04-02
 */
@Configuration
public class ShiroConfig implements ApplicationListener<ContextRefreshedEvent> {
    
    @Autowired
    private ApplicationContext applicationContext;
    
    public static class CorsAuthenticationFilter extends FormAuthenticationFilter {
        @Override
        protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
            if (request instanceof HttpServletRequest && ((HttpServletRequest) request).getMethod().equalsIgnoreCase("OPTIONS")) {
                return true;
            }
            return super.isAccessAllowed(request, response, mappedValue);
        }
        
        @Override
        protected boolean onAccessDenied(ServletRequest request, ServletResponse response)
            throws Exception {
                WebUtils.toHttp(response).sendError(HttpServletResponse.SC_UNAUTHORIZED);
                return false;
        }
    }
    
    /**
     * ShiroFilterFactoryBean 处理拦截资源文件问题。
     * 注意：单独一个ShiroFilterFactoryBean配置是或报错的，因为在
     * 初始化ShiroFilterFactoryBean的时候需要注入：SecurityManager
     *
     Filter Chain定义说明
     1、一个URL可以配置多个Filter，使用逗号分隔
     2、当设置多个过滤器时，全部验证通过，才视为通过
     3、部分过滤器可指定参数，如perms，roles
     *
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean  = new ShiroFilterFactoryBean();

        //自定义过滤器
        Map<String, Filter> filters = new LinkedHashMap<>();
        filters.put("authc", new CorsAuthenticationFilter());
        shiroFilterFactoryBean.setFilters(filters);
        // 必须设置 SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        shiroFilterFactoryBean.setLoginUrl("/unauthorized");
        // 登录成功后要跳转的链接
        shiroFilterFactoryBean.setSuccessUrl("/");
        //未授权界面;
        shiroFilterFactoryBean.setUnauthorizedUrl("/unauthorized");
        //拦截器.
        Map<String,String> filterChainDefinitionMap = new LinkedHashMap<>();

        //配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了
        // rememberMe可以访问主页
        filterChainDefinitionMap.put("/", "user");
        filterChainDefinitionMap.put("/user/login", "anon");
        filterChainDefinitionMap.put("/user/logout", "logout");
        /*
        静态资源
        filterChainDefinitionMap.put("/css/**","anon");
        filterChainDefinitionMap.put("/js/**","anon");
        filterChainDefinitionMap.put("/img/**","anon");
        filterChainDefinitionMap.put("/font-awesome/**","anon");
        */
        //filterChainDefinitionMap.put("/**", "user");
        //<!-- 过滤链定义，从上向下顺序执行，一般将 /**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
        //<!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
        filterChainDefinitionMap.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }
    
    /**
    EhCacheManager
    @Bean
    public EhCacheManager ehCacheManager(CacheManager cacheManager) {
        EhCacheManager em = new EhCacheManager();
        //将ehcacheManager转换成shiro包装后的ehcacheManager对象
        em.setCacheManager(cacheManager);
        //em.setCacheManagerConfigFile("classpath:ehcache.xml");
        return em;
    }
    
    @Bean
    protected SessionDAO sessionDAO(EhCacheManager cacheManager) {
        EnterpriseCacheSessionDAO sessionDAO = new EnterpriseCacheSessionDAO();
        sessionDAO.setCacheManager(cacheManager);
        return sessionDAO;
    }
    */
    
    /**
     * Redis
     * @param cacheManager
     * @return
     */
    @Bean
    protected SessionDAO sessionDAO() {
        return new RedisSessionDao();
    }
    
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        DefaultSessionManager defaultSessionManager = (DefaultSessionManager) applicationContext.getBean("sessionManager");
        // 如果session交给redis管理，不需要验证session是否失效，交给redis管理
        defaultSessionManager.setSessionValidationSchedulerEnabled(false);
        
        CookieRememberMeManager cookieRememberMeManager = applicationContext.getBean(CookieRememberMeManager.class);
        //rememberMe cookie加密的密钥 建议每个项目都不一样 默认AES算法 密钥长度(128 256 512 位)
        //KeyGenerator keygen = KeyGenerator.getInstance("AES");
        //SecretKey deskey = keygen.generateKey();
        //System.out.println(Base64.encodeToString(deskey.getEncoded()));
        cookieRememberMeManager.setCipherKey(Base64.decode("2AvVhdsgUs0FSA3SDFAdag=="));          
    }
    
    @Bean("authorizer")
    public ShiroRealm shiroRealm(){
        ShiroRealm myShiroRealm = new ShiroRealm();
        //myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return myShiroRealm;
    }
    
    /**
    @PostConstruct
    public void initDefaultSessionManager() {
        System.out.println("initDefaultSessionManager");
        DefaultSessionManager defaultSessionManager = (DefaultSessionManager) applicationContext.getBean("sessionManager");
        // 如果session交给redis管理，不需要验证session是否失效，交给redis管理
        defaultSessionManager.setSessionValidationSchedulerEnabled(false);
    }
    */
    
    /**
     * 凭证匹配器
     * （由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理了
     *  所以我们需要修改下doGetAuthenticationInfo中的代码;
     * ）
     * @return
     */
    /**
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");//散列算法:这里使用MD5算法;
        hashedCredentialsMatcher.setHashIterations(2);//散列的次数，比如散列两次，相当于 md5(md5(""));
        return hashedCredentialsMatcher;
    }
     */
    
    /**
     * 配置shiro redisManager
     * 使用的是shiro-redis开源插件
     * @return
     */
//    public RedisManager redisManager() {
//        RedisManager redisManager = new RedisManager();
//        redisManager.setHost(host);
//        redisManager.setPort(port);
//        redisManager.setExpire(1800);// 配置缓存过期时间
//        redisManager.setTimeout(timeout);
//        // redisManager.setPassword(password);
//        return redisManager;
//    }

    /**
     * cacheManager 缓存 redis实现
     * 使用的是shiro-redis开源插件
     * @return
     */
//    public RedisCacheManager cacheManager() {
//        RedisCacheManager redisCacheManager = new RedisCacheManager();
//        redisCacheManager.setRedisManager(redisManager());
//        return redisCacheManager;
//    }


    /**
     * RedisSessionDAO shiro sessionDao层的实现 通过redis
     * 使用的是shiro-redis开源插件
     */
//    @Bean
//    public RedisSessionDAO redisSessionDAO() {
//        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
//        redisSessionDAO.setRedisManager(redisManager());
//        return redisSessionDAO;
//    }

}
