package cn.datafort.generator;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.io.File;
import java.util.Collections;

public class GeneratorMain {
    /**
     * TODO 数据库url
     */
    private static final String url = "jdbc:mysql://172.16.8.40:3306/datafort?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=utf8&useSSL=false&allowMultiQueries=true&tinyInt1isBit=false&zeroDateTimeBehavior=CONVERT_TO_NULL&autoReconnect=true&failOverReadOnly=false";
    /**
     * TODO 数据库用户名
     */
    private static final String username = "root";
    /**
     * TODO 数据库密码
     */
    private static final String password = "DF*c3000";
    /**
     * TODO 数据库表名 空格分割
     */
    private static final String tables = "advance_report audit_agentInfo";
    /**
     * TODO 生成的包名前缀
     */
    private static final String packageName = "cn.datafort";

    public static void main(String[] args) {

        final String[] s = tables.split(" ", -1);
        System.out.println("生成的数据库表数：" + s.length);

        String projectPath = System.getProperty("user.dir") + "/template/";
        new File(projectPath).mkdirs();

        // 数据源配置 需配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig.Builder(url, username, password).build();

        AutoGenerator autoGenerator = new AutoGenerator(dataSourceConfig);

        // 全局配置
        autoGenerator.global(new GlobalConfig.Builder().outputDir(projectPath).author("cn.datafort").disableOpenDir().enableSpringdoc().build());

        // 生成包配置
        autoGenerator.packageInfo(
                new PackageConfig.Builder()
                        .parent(packageName)
                        .xml("dao")
                        .mapper("dao")
                        .entity("model.entity")
                        .pathInfo(Collections.singletonMap(OutputFile.xml, projectPath + "/src/main/resources/mapper/" + packageName.replace(".", "/")))
                        .build());

        // 配置模板
        autoGenerator.template(new TemplateConfig.Builder().disable(TemplateType.CONTROLLER).build());

        // 策略配置
        StrategyConfig strategyConfig = new StrategyConfig.Builder().addInclude(s).build()

                .serviceBuilder().formatServiceFileName("%sService")

                .mapperBuilder().formatMapperFileName("%sDao").formatXmlFileName("%sDao")

                .entityBuilder().naming(NamingStrategy.underline_to_camel).columnNaming(NamingStrategy.underline_to_camel).enableLombok().enableTableFieldAnnotation().enableChainModel()

                .controllerBuilder().enableRestStyle().enableHyphenStyle()

                .build();

        autoGenerator.strategy(strategyConfig);

        System.out.println("===================== MyBatis Plus Generator ==================");
        autoGenerator.execute(new FreemarkerTemplateEngine());
        System.out.println("================= MyBatis Plus Generator Execute Complete ==================");
    }
}
