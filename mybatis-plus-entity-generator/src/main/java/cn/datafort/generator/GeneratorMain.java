package cn.datafort.generator;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GeneratorMain {
    
    private static final String url = "jdbc:mysql://127.0.0.1:3306/authorization_management?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=utf8&useSSL=false&allowMultiQueries=true&tinyInt1isBit=false&zeroDateTimeBehavior=CONVERT_TO_NULL&autoReconnect=true&failOverReadOnly=false";
    private static final String driverName = "com.mysql.cj.jdbc.Driver";
    private static final String username = "root";
    private static final String password = "DF*c3000sdf";
    //需要生成的表名
    private static final String[] table = new String[]{"am_product", "am_supplier"};
    //生成的包名前缀
    private static final String packageName = "cn.datafort";
    
    public static void main(String[] args) {
        
        AutoGenerator autoGenerator = new AutoGenerator();
        
        // 全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        String projectPath = System.getProperty("user.dir") + "/template/";
        boolean mkdir = new File(projectPath).mkdirs();
        globalConfig.setOutputDir(projectPath);
        globalConfig.setAuthor("cn.datafort");
        globalConfig.setOpen(false);
        globalConfig.setServiceName("%sService");
        globalConfig.setMapperName("%sDao");
        globalConfig.setSwagger2(true);
        autoGenerator.setGlobalConfig(globalConfig);
        
        // 数据源配置 需配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setUrl(url);
        dataSourceConfig.setDriverName(driverName);
        dataSourceConfig.setUsername(username);
        dataSourceConfig.setPassword(password);
        autoGenerator.setDataSource(dataSourceConfig);
        
        // 生成包配置
        PackageConfig packageConfig = new PackageConfig();
        
        packageConfig.setParent(packageName);
        packageConfig.setMapper("dao");
        packageConfig.setEntity("model.entity");
        autoGenerator.setPackageInfo(packageConfig);
        
        // 自定义配置
        InjectionConfig injectionConfig = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };
        
        // 如果模板引擎是 freemarker
        String templatePath = "/templates/mapper.xml.ftl";
        
        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                
                // 自定义输出文件名
                return projectPath + "/src/main/resources/mapper/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        
        injectionConfig.setFileOutConfigList(focList);
        autoGenerator.setCfg(injectionConfig);
        
        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setXml(null);
        templateConfig.setController("");
        templateConfig.setService("");
        templateConfig.setServiceImpl("");
        autoGenerator.setTemplate(templateConfig);
        
        // 策略配置
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setEntityLombokModel(true);
        strategyConfig.setRestControllerStyle(true);
        strategyConfig.setInclude(table);
        strategyConfig.setControllerMappingHyphenStyle(true);
        strategyConfig.setEntityTableFieldAnnotationEnable(true);
        // 忽略表名前缀
        strategyConfig.setTablePrefix("");
        
        autoGenerator.setStrategy(strategyConfig);
        autoGenerator.setTemplateEngine(new FreemarkerTemplateEngine());
        System.out.println("===================== MyBatis Plus Generator ==================");
        
        autoGenerator.execute();
        
        System.out.println("================= MyBatis Plus Generator Execute Complete ==================");
    }
}
