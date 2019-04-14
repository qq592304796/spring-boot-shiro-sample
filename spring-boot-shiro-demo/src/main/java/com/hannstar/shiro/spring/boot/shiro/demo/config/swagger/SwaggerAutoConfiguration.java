package com.hannstar.shiro.spring.boot.shiro.demo.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

import io.swagger.annotations.ApiOperation;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerAutoConfiguration {

        /**
         * 是否审核
         */
        private Boolean audit = true;

        /**
         * 访问地址
         */
        private String host;

        public Boolean getAudit() {
            return audit;
        }

        public void setAudit(Boolean audit) {
            this.audit = audit;
        }

        public String getHost() {
            return host;
        }

        public void setHost(String host) {
            this.host = host;
        }

        @Bean
        public Docket api() {
            return new Docket(DocumentationType.SWAGGER_2)
                    .groupName("API")
                    .forCodeGeneration(true)
                    .pathMapping("/")
                    .host(host)
                    .select()
                    .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                    .paths(paths())
                    .build()
                    .apiInfo(apiInfo())
                    .useDefaultResponseMessages(false);
        }

        private ApiInfo apiInfo() {
            return new ApiInfoBuilder()
                    .title("API")
                    .description("API调试工具")
                    .version("1.0")
                    .build();
        }

        private Predicate<String> paths() {
            return Predicates.and(PathSelectors.regex("/.*"), Predicates.not(PathSelectors.regex("/error")));
        }

    }