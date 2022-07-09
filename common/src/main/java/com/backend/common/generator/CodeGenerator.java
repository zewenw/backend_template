package com.backend.common.generator;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.*;

;

/**
 * 代码生成
 */
public class CodeGenerator {

    /**
     * 入口
     */
    public static void main(String[] args) {
        geenerate(args);
    }

    //datasource information
    private static final String DATASOURCE_URL = "jdbc:mysql://localhost:3306/service_one?useUnicode=true&serverTimezone=GMT&useSSL=false&characterEncoding=utf8";
    private static final String DATASOURCE_DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    private static final String DATASOURCE_USERNAME = "root";
    private static final String DATASOURCE_PASSWORD = "123";
    //对应模块代码生成开关
    private static final boolean CONTROLLER_FLAG = false;
    private static final boolean SERVICE_FLAG = false;
    private static final boolean SERVICE_IMPL_FLAG = false;
    private static final boolean ENTITY_FLAG = true;
    private static final boolean MAPPER_FLAG = true;
    private static final boolean MAPPER_XML_FLAG = true;
    private static final boolean VO_FLAG = false;

    //文件存放前缀
    private static final String URL_PREFIX = "/service-one/service-one-dao";
    //模块名称
    private static final String MODULE_NAME = "service";
    //组名
    private static final String GROUP_NAME = "backend";

    //package path information
    private static final String CONTROLLER_PATH = URL_PREFIX + "/src/main/java/com/" + GROUP_NAME + "/" + MODULE_NAME + "/controller/";
    private static final String SERVICE_PATH = URL_PREFIX + "/src/main/java/com/" + GROUP_NAME + "/" + MODULE_NAME + "/service/";
    private static final String SERVICE_IMPL_PATH = URL_PREFIX + "/src/main/java/com/" + GROUP_NAME + "/" + MODULE_NAME + "/service/impl/";
    private static final String ENTITY_PATH = URL_PREFIX + "/src/main/java/com/" + GROUP_NAME + "/" + MODULE_NAME + "/dao/entity/";
    private static final String MAPPER_PATH = URL_PREFIX + "/src/main/java/com/" + GROUP_NAME + "/" + MODULE_NAME + "/dao/mapper/";
    private static final String MAPPER_XML_PATH = URL_PREFIX + "/src/main/resources/mappers/" + MODULE_NAME + "/";
    private static final String VO_PATH = URL_PREFIX + "/src/main/java/com/" + GROUP_NAME + "/" + MODULE_NAME + "/dao/";
    /**
     * 读取控制台内容
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入").append(tip).append("：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StrUtil.isNotBlank(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }
    /**
     * RUN THIS TO GENERATE CODE
     */
    public static void geenerate(String[] args) {
        String projectPath = System.getProperty("user.dir");
        // 代码生成器
        CustomGenerator autoGenerator = new CustomGenerator();

        // 全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setSwagger2(false);
        globalConfig.setFileOverride(true);
        globalConfig.setOpen(false);
        globalConfig.setBaseResultMap(true);
        globalConfig.setBaseColumnList(true);
        globalConfig.setEnableCache(false);
        globalConfig.setIdType(IdType.AUTO);
        globalConfig.setDateType(DateType.ONLY_DATE);
        globalConfig.setAuthor("mybatis-plus");
        autoGenerator.setGlobalConfig(globalConfig);
        // 包名
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent("com.".concat(GROUP_NAME).concat(".").concat(MODULE_NAME));
        packageConfig.setEntity("dao.entity");
        packageConfig.setMapper("dao.mapper");
        packageConfig.setService("service");
        packageConfig.setServiceImpl("service.impl");
        packageConfig.setXml("");
        packageConfig.setController("controller");
        packageConfig.setPathInfo(new HashMap<>());
        autoGenerator.setPackageInfo(packageConfig);

        // 数据源配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setUrl(DATASOURCE_URL);
        // dataSourceConfig.setSchemaName("public");
        dataSourceConfig.setDriverName(DATASOURCE_DRIVER_NAME);
        dataSourceConfig.setUsername(DATASOURCE_USERNAME);
        dataSourceConfig.setPassword(DATASOURCE_PASSWORD);
        autoGenerator.setDataSource(dataSourceConfig);

        // 自定义配置
        InjectionConfig injectionConfig = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<>();
                this.setMap(map);
            }
        };

        List<FileOutConfig> focList = new ArrayList<>();
        //Controller file path
        if(CONTROLLER_FLAG){
            focList.add(new FileOutConfig("/templates/generate/controller.java.ftl") {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    // 自定义输入文件名称
                    String filePath = projectPath + CONTROLLER_PATH + tableInfo.getEntityName() + "Controller" + StringPool.DOT_JAVA;
                    return filePath;
                }
            });
        }
        //entity file path
        if(ENTITY_FLAG){
            focList.add(new FileOutConfig("/templates/generate/entity.java.ftl") {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    // 自定义输入文件名称
                    String filePath = projectPath + ENTITY_PATH + tableInfo.getEntityName() + StringPool.DOT_JAVA;
                    return filePath;
                }
            });
        }
        //vo file path
        if(VO_FLAG){
            focList.add(new FileOutConfig("/templates/generate/vo.java.ftl") {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    // 自定义输入文件名称
                    String filePath = projectPath + VO_PATH + tableInfo.getEntityName() + "Dto" + StringPool.DOT_JAVA;
                    return filePath;
                }
            });
        }
        //Mapper.java file path
        if(MAPPER_FLAG){
            focList.add(new FileOutConfig("/templates/generate/mapper.java.ftl") {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    // 自定义输入文件名称
                    String filePath = projectPath + MAPPER_PATH + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_JAVA;
                    return filePath;
                }
            });
        }
        //Mapper.xml file path
        if(MAPPER_XML_FLAG){
            focList.add(new FileOutConfig("/templates/generate/mapper.xml.ftl") {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    // 自定义输入文件名称
                    String filePath = projectPath + MAPPER_XML_PATH + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
                    return filePath;
                }
            });
        }
        //Service file path
        if(SERVICE_FLAG){
            focList.add(new FileOutConfig("/templates/generate/service.java.ftl") {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    // 自定义输入文件名称
                    String filePath = projectPath + SERVICE_PATH + tableInfo.getEntityName() + "Service" + StringPool.DOT_JAVA;
                    return filePath;
                }
            });
        }
        //serviceImpl file path
        if(SERVICE_IMPL_FLAG){
            focList.add(new FileOutConfig("/templates/generate/serviceImpl.java.ftl") {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    // 自定义输入文件名称
                    String filePath = projectPath + SERVICE_IMPL_PATH + tableInfo.getEntityName() + "ServiceImpl" + StringPool.DOT_JAVA;
                    return filePath;
                }
            });
        }
        injectionConfig.setFileOutConfigList(focList);
        autoGenerator.setCfg(injectionConfig);

        //自定义模板
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setController("templates/generate/controller.java");
        templateConfig.setEntity("templates/generate/entity.java");
        templateConfig.setMapper("templates/generate/mapper.java");
        templateConfig.setService("templates/generate/service.java");
        templateConfig.setServiceImpl("templates/generate/serviceImpl.java");
        templateConfig.setXml("templates/generate/mapper.xml");
        templateConfig.setXml("templates/generate/vo.java");
        autoGenerator.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setInclude(scanner("表名"));
        // strategy.setSuperEntityColumns("id");
        strategy.setControllerMappingHyphenStyle(true);
//        strategy.setTablePrefix(pc.getModuleName() + "_");
        autoGenerator.setStrategy(strategy);
        // 选择 freemarker 引擎需要指定如下加，注意 pom 依赖必须有！
        autoGenerator.setTemplateEngine(new FreemarkerTemplateEngine());
        autoGenerator.execute();
    }
}
