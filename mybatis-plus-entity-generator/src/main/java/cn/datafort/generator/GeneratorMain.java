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
    
    private static final String url = "jdbc:mysql://127.0.0.1:3306/datafort_dag?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=utf8&useSSL=false&allowMultiQueries=true&tinyInt1isBit=false&zeroDateTimeBehavior=CONVERT_TO_NULL&autoReconnect=true&failOverReadOnly=false";
    private static final String driverName = "com.mysql.cj.jdbc.Driver";
    private static final String username = "root";
    private static final String password = "DF*c3000sdf";
    //需要生成的表名
    private static final String[] table = new String[]{"user"};
    //生成的包名前缀
    private static final String packageName = "cn.datafort";
    
    private static final String tables = "agent_policy_send_record am_product_module bd_log_sql_type bd_threat_src_stat cms_access_config cms_agent_policy cms_agent_policy_rule cms_application cms_apps_url cms_backup_log cms_cluster_node cms_db cms_dbes_policy cms_dbes_policy_not_exist_record cms_dbes_policy_record cms_dbes_rule cms_device cms_device_agent cms_device_backup_log cms_device_department cms_device_group cms_device_info cms_device_log_clean_record cms_device_report cms_device_restore_log cms_device_run_log cms_device_status_log cms_device_upgrade_log cms_dns_config cms_enum_param cms_file cms_group cms_interface cms_interface_group cms_mask cms_mask_inc cms_mask_info cms_ntp_config cms_operate_action cms_operate_log cms_policy cms_policy_log cms_policy_param cms_policy_param_item cms_policy_rule cms_restore_log cms_route_config cms_run_log cms_scan cms_scan_info cms_scan_listone cms_scan_range cms_security_config cms_smtp_config cms_src_db cms_src_dmp cms_src_file cms_styles_config cms_support cms_sys_config cms_upgrade_log cms_user cms_user_role cms_warn_case cms_warn_config cms_warn_handle cms_warn_log cms_warn_log_detail compliance_check compliance_check_sub_term compliance_evaluate_quota dag_access_relation dag_alarm_rule dag_alarm_src dag_api_category dag_api_level dag_asset_category dag_asset_diff_log dag_asset_level dag_asset_risk_access dag_asset_search dag_baseline_src_rule dag_business_applications dag_cache dag_category_label dag_category_level dag_db_field dag_db_flow dag_db_flow_subset dag_db_privileges dag_db_role dag_db_role_priv dag_db_schema dag_db_table dag_db_user dag_db_user_obj_priv dag_db_user_priv dag_db_user_role dag_db_visit dag_department dag_device_api dag_device_project dag_dic_data dag_dic_data_item dag_disk_mount dag_dispatch_task dag_file_column dag_file_schema dag_file_sync dag_file_sync_asset dag_file_sync_job dag_file_sync_job_asset dag_file_sync_job_asset_snapshot dag_file_sync_job_diff dag_file_sync_job_exception dag_file_table dag_file_template dag_industry_template dag_industry_template_file dag_journal_asset dag_journal_src dag_leak_scan dag_leak_scan_job dag_leak_scan_result dag_level_label dag_level_label_group dag_mark_field dag_mark_job dag_mark_job_exception dag_mark_job_result dag_mark_schema dag_mark_table dag_mark_task dag_masker_method dag_masker_rule dag_masker_rule_group dag_notification_send dag_plugin_class dag_priv_change_job dag_priv_change_job_result dag_priv_change_job_snapshot dag_report dag_report_schedule dag_report_schedule_src dag_row_label dag_row_mark dag_row_mark_exception dag_row_mark_label dag_row_mark_result dag_row_mark_statistic_result dag_row_mark_table dag_rule dag_rule_category dag_rule_filter dag_rule_group dag_rule_level dag_rule_method dag_rule_sub dag_safe_knowledge_file dag_safe_knowledge_store dag_scan dag_scan_job dag_scan_job_exception dag_scan_job_result dag_scan_leaks dag_scan_schema dag_scan_table dag_schedule_task dag_schedule_task_job dag_security_event dag_security_event_rule dag_sens_column dag_sens_field dag_src dag_src_device dag_src_device_policy dag_src_group dag_src_group_config dag_src_node dag_src_scan_job dag_src_scan_job_result dag_src_scan_record dag_src_scan_task dag_sync dag_sync_job dag_sync_job_exception dag_sync_job_schema dag_sync_job_schema_detail dag_sync_job_schema_snapshot dag_sync_job_table_detail dag_sync_permission dag_sync_permission_job dag_sync_permission_job_exception dag_sync_permission_job_result dag_sync_schema dag_user_department ddm_rule ddm_rule_group ddm_rule_method mark_rule rule_sorts sdm_system_status sys_action sys_log sys_module_url sys_modules sys_netcard_alias sys_netcard_index sys_url upload_file user user_asset user_asset_res user_device user_device_res user_role user_role_auth";
    
    public static void main(String[] args) {
    
        final String[] s = tables.split(" ", -1);
        System.out.println(s.length);
    
        for (String tmpTable : s) {
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
                    return projectPath + "/src/main/resources/mapper/" + tableInfo.getEntityName() + "Dao" + StringPool.DOT_XML;
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
            strategyConfig.setInclude(tmpTable);
            strategyConfig.setControllerMappingHyphenStyle(true);
            strategyConfig.setEntityTableFieldAnnotationEnable(true);
            strategyConfig.setChainModel(true);
            // 忽略表名前缀
            strategyConfig.setTablePrefix("");
    
            autoGenerator.setStrategy(strategyConfig);
            autoGenerator.setTemplateEngine(new FreemarkerTemplateEngine());
            System.out.println("===================== MyBatis Plus Generator ==================");
            autoGenerator.execute();
            System.out.println("================= MyBatis Plus Generator Execute Complete ==================");
        }
        
        if (true) {
            return;
        }
    
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
                return projectPath + "/src/main/resources/mapper/" + tableInfo.getEntityName() + "Dao" + StringPool.DOT_XML;
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
