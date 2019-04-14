package com.hannstar.shiro.spring.boot.shiro.demo;
//package spring.boot.shiro.demo;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import com.baomidou.mybatisplus.annotation.DbType;
//import com.baomidou.mybatisplus.core.toolkit.StringPool;
//import com.baomidou.mybatisplus.generator.AutoGenerator;
//import com.baomidou.mybatisplus.generator.InjectionConfig;
//import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
//import com.baomidou.mybatisplus.generator.config.FileOutConfig;
//import com.baomidou.mybatisplus.generator.config.GlobalConfig;
//import com.baomidou.mybatisplus.generator.config.PackageConfig;
//import com.baomidou.mybatisplus.generator.config.StrategyConfig;
//import com.baomidou.mybatisplus.generator.config.TemplateConfig;
//import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
//import com.baomidou.mybatisplus.generator.config.po.TableInfo;
//import com.baomidou.mybatisplus.generator.config.rules.DateType;
//import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
//import com.baomidou.mybatisplus.generator.config.rules.IColumnType;
//import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
//import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
//
///**
// * 演示例子，执行 main 方法控制台输入模块表名回车自动生成对应项目目录中
// * 
// * @author jiangxinjun
// * @date 2019-04-02
// */
//public class CodeGenerator {
//
//    public static void main(String[] args) {
//        String moduleName = "";
//
//        String tableName = "";
//
//        // 代码生成器
//        AutoGenerator mpg = new AutoGenerator();
//        // 全局配置
//        GlobalConfig gc = new GlobalConfig();
//        // gc.setFileOverride(true);
//        gc.setDateType(DateType.ONLY_DATE);
//        String projectPath = System.getProperty("user.dir");
//        gc.setOutputDir(projectPath + "/src/main/java");
//        gc.setAuthor("jiangxinjun");
//        gc.setOpen(false);
//        mpg.setGlobalConfig(gc);
//
//        // 数据源配置
//        DataSourceConfig dsc = new DataSourceConfig();
//        dsc.setDbType(DbType.MYSQL);
//        dsc.setTypeConvert(new MySqlTypeConvert() {
//            // 自定义数据库表字段类型转换【可选】
//            @Override
//            public IColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
//                DbColumnType type = (DbColumnType) super.processTypeConvert(globalConfig, fieldType);
//                if (DbColumnType.INTEGER == type) {
//                    String t = fieldType.toLowerCase();
//                    if (t.contains("tinyint")) {
//                        return DbColumnType.BYTE;
//                    }
//                }
//                return type;
//            }
//        });
//        dsc.setUrl("jdbc:mysql://192.168.1.11:3306/test?useUnicode=true&useSSL=false&characterEncoding=utf8");
//        // dsc.setSchemaName("public");
//        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
//        dsc.setUsername("root");
//        dsc.setPassword("123456");
//        mpg.setDataSource(dsc);
//
//        // 包配置
//        PackageConfig pc = new PackageConfig();
//        pc.setModuleName(moduleName);
//        pc.setParent("spring.boot.shiro.demo");
//        mpg.setPackageInfo(pc);
//
//        // 自定义配置
//        InjectionConfig cfg = new InjectionConfig() {
//            @Override
//            public void initMap() {
//                // to do nothing
//            }
//        };
//        List<FileOutConfig> focList = new ArrayList<>();
//        focList.add(new FileOutConfig("/templates/mapper.xml.ftl") {
//            @Override
//            public String outputFile(TableInfo tableInfo) {
//                // 自定义输入文件名称
//                return projectPath + "/src/main/resources/mapper/" + pc.getModuleName() + "/"
//                        + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
//            }
//        });
//        cfg.setFileOutConfigList(focList);
//        mpg.setCfg(cfg);
//        mpg.setTemplate(new TemplateConfig().setXml(null));
//
//        // 策略配置
//        StrategyConfig strategy = new StrategyConfig();
//        strategy.setNaming(NamingStrategy.underline_to_camel);
//        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
//        // strategy.setSuperEntityClass("com.example.demo.common.BaseEntity");
//        strategy.setEntityLombokModel(true);
//        strategy.setRestControllerStyle(true);
//        // strategy.setSuperControllerClass("com.example.demo.common.BaseController");
//        // strategy.setInclude(tableName);
//        // strategy.setSuperEntityColumns("id");
//        strategy.setControllerMappingHyphenStyle(true);
//        strategy.setTablePrefix(pc.getModuleName() + "_");
//        mpg.setStrategy(strategy);
//        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
//        mpg.execute();
//    }
//
//}