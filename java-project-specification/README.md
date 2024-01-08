#### Java代码规范

##### 一、Java 接口文档编写规范
参考[swagger入门](docs/swagger入门.md)

[代码示例](src/main/java/cn/datafort/controller/SwaggerDemoController.java)

[配置示例](src/main/java/cn/datafort/config/Knife4jConfiguration.java)


##### 二、Java controller 参数验证规范

参数验证：使用spring validate 所有参数校验在dto类中完成
[代码实例](src/main/java/cn/datafort/controller/TestController.java)

##### 三、Java 常量定义规范

尽量使用enum定义常量 方便扩展
```java
 enum DBType {
    
    ORACLE(0, "Oracle", 1521, 1, 1, 1, new String[]{"9.1.0.1", "9.2.0.1", "9.2.0.2", "9.2.0.4", "10.1.0.1", "10.1.0.2", "10.1.0.3", "10.1.0.4", "10.2.0.1", "10.2.0.2", "10.2.0.3", "10.2.0.4", "11.1.0.1", "11.1.0.2", "11.1.0.3", "11.1.0.4", "11.1.0.5", "11.1.0.6", "11.1.0.7", "11.2.0.1", "11.2.0.2", "11.2.0.3", "11.2.0.4", "12.1.0.1", "12.1.0.2", "12.2.0.1", "12.2.0.2", "unknown"}),
    SQLSERVER(1, "SQL Server", 1433, 0, 1, 1, new String[]{"SQL Server2000（8.0）", "SQL Server2005（9.0）", "SQL Server2008(10.0)", "SQL Server2008R2(10.50)", "SQL Server2012(11.0)", "SQL Server2014(12.0)", "SQL Server2016(13.0)", "SQL Server2017(14.0)", "unknown"}),
    DB2(2, "DB2", 50000, 0, 1, 0, new String[]{"V3.4", "V8.1", "V8.2", "v9.1", "V9.5", "V9.7", "V10.1", "V10.5", "V11.1", "unknown"}),
    MYSQL(3, "MySQL", 3306, 0, 1, 1, new String[]{"5.0", "5.1", "5.5", "5.6", "5.7", "5.8", "unknown"}),
    CACHE(4, "Cache", 1972, 0, 1, 0, new String[]{"2014", "2015", "2016", "2017", "unknown"}),
    SYBASE(5, "Sybase", 5000, 0, 1, 0, new String[]{"10.0", "11.0", "12.0", "13.0", "15.0", "16.0", "unknown"}),
    DAMENG7(6, "DM7", 5236, 0, 0, 0, new String[]{"7.1.3", "7.1.5", "7.1.6", "unknown"}),
    INFORMIX(7, "Informix", 9088, 0, 1, 0, new String[]{"7", "8", "9", "10", "11", "12", "unknown"}),
    KSTORE(8, "神通", 9088, 0, 1, 0, new String[]{"6.0", "7.0", "8.0", "unknown"}),
    KINGBASE(9, "人大金仓V8", 54321, 0, 1, 1, new String[]{"V8"}),
    TERADATA(10, "Teradata", 8002, 0, 1, 0, new String[]{"unknown"}),
    POSTGRESQL(11, "PostgreSQL", 5432, 0, 1, 1, new String[]{"8.0", "8.1", "8.2", "8.3", "8.4", "9.0", "9.1", "9.2", "9.3", "9.4", "9.5", "9.6", "10.0", "10.1", "unknown"}),
    GBASE(12, "GBase 8a", 5258, 0, 1, 1, new String[]{"V8.5", "unknown"}),
    DAMENG6(13, "DM6", 12345, 0, 0, 0, new String[]{"unknown"}),
    SR(14, "上容", 1975, 0, 1, 0, new String[]{"unknown"}),
    OTHER(-128, "", 0, 0, 1, 0, new String[]{"unknown"}),
    KDB(15, "KDB", 8629, 0, 1, 0, new String[]{"11", "unknown"}),
    HIVE(16, "Hive", 10000, 0, 1, 1, new String[]{"1.1.0", "3.1.0", "unknown"}),
    MONGODB(17, "MongoDB", 27017, 0, 1, 0, new String[]{"unknown"}),
    HANA(18, "HANA", 30015, 0, 1, 0, new String[]{"unknown"}),
    HighGo(19, "HighGo", 5866, 0, 1, 0, new String[]{"unknown"}),
    HWMPP(20, "HWMPP", 25308, 0, 1, 0, new String[]{"unknown"}),
    HBASE(21, "HBase", 21300, 0, 1, 0, new String[]{"1.3.1", "unknown"}),
    ES(22, "ES", 9200, 0, 1, 0, new String[]{"unknown"}),
    CacheWeb(23, "CacheWeb", 57772, 0, 1, 0, new String[]{"unknown"}),
    Redis(24, "Redis", 6379, 0, 1, 0, new String[]{"unknown"}),
    Solr(25, "Solr", 8983, 0, 1, 0, new String[]{"unknown"}),
    HDFS(26, "HDFS", 8020, 0, 1, 0, new String[]{"2.7.3"}),
    EsgynDB(27, "EsgynDB(易鲸捷)", 23400, 0, 1, 0, new String[]{"unknown"}),
    SSDB(28, "SSDB", 8888, 0, 1, 0, new String[]{"unknown"}),
    Tibero(29, "Tibero", 8629, 0, 1, 0, new String[]{"unknown"}),
    GAUSSDB(30, "GaussDB", 25308, 0, 1, 1, new String[]{"unknown"}),
    DM8(31, "DM8", 5236, 0, 1, 1, new String[]{"unknown"}),
    ClickHouse(32, "ClickHouse", 8123, 1, 0, 1, new String[]{"unknown"});
    
    private final byte code;
    private final String name;
    private final int defaultPort;
    private final String[] versions;
    private final int isSupportCluster;
    private final int needDbName;
    private final int isSupportDDM;
    
    DBType(Integer code, String name, int port, int isSupportCluster, int needDbName, int isSupportDDM, String[] versions) {
        this.code = Byte.parseByte(String.valueOf(code));
        this.name = name;
        this.defaultPort = port;
        this.versions = versions;
        this.isSupportCluster = isSupportCluster;
        this.needDbName = needDbName;
        this.isSupportDDM = isSupportDDM;
    }
    
    public int isSupportCluster() {
        return isSupportCluster;
    }
    
    public boolean isNeedDbName() {
        return needDbName == 1;
    }
    
    public boolean isSupportDDM() {
        return isSupportDDM == 1;
    }
    
    public byte getCode() {
        return code;
    }
    
    public String getName() {
        return name;
    }
    
    public int getDefaultPort() {
        return defaultPort;
    }
    
    public String[] getVersions() {
        return versions;
    }
    
    public static DBType getDbTypeByCode(byte code) {
        return Arrays.stream(DBType.values()).filter(e -> e.getCode() == code).findFirst().orElse(DBType.OTHER);
    }
    
    public static DBType getDbTypeByPort(int port) {
        return Arrays.stream(DBType.values()).filter(e -> e.getDefaultPort() == port).findFirst().orElse(DBType.OTHER);
    }
    
    public static String getNameByCode(int code) {
        
        final DBType dbType = Arrays.stream(DBType.values()).filter(e -> (int) (e.getCode()) == code).findFirst().orElse(DBType.OTHER);
        return dbType.getName();
    }
    
    public static byte getCodeByName(String name) {
        final DBType dbType = Arrays.stream(DBType.values()).filter(e -> e.getName().equals(name)).findFirst().orElse(DBType.OTHER);
        return dbType.getCode();
    }
    
    /**
     * 获取支持脱敏的数据库类型
     *
     * @return 支持脱敏的数据库列表
     */
    public static List<Integer> getDDMSupportDbType() {
        
        final List<Integer> supportDbTypeList = new ArrayList<>();
        for (DBType dbType : DBType.values()) {
            if (dbType.isSupportDDM()) {
                supportDbTypeList.add((int) dbType.getCode());
            }
        }
        return supportDbTypeList;
    }
    
    /**
     * 获取支持脱敏的数据库类型
     *
     * @return 支持脱敏的数据库列表
     */
    public static boolean getIsDDMSupportDbType(Integer code) {
        
        for (DBType dbType : DBType.values()) {
            if (((int) dbType.getCode()) == code && dbType.isSupportDDM()) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * 获取支持脱敏的数据库类型
     *
     * @return 支持脱敏的数据库列表
     */
    public static List<Map<String, Object>> getDDMSupportDbList() {
        
        List<Map<String, Object>> supportDbList = new ArrayList<>();
        for (DBType value : DBType.values()) {
            if (value.isSupportDDM()) {
                extractInfo(supportDbList, value);
            }
        }
        return supportDbList;
    }
}
```

##### 四、Java 对象属性复制规范

使用mapstruct 简化属性复制
pom配置参考
```
   <dependency>
            <groupId>org.mapstruct</groupId>
            <artifactId>mapstruct</artifactId>
            <version>1.5.5.Final</version>
        </dependency>
```
和lombok冲突配置参考
```
 <build>
        <pluginManagement>
            <plugins>
                <plugin>
                    <groupId>org.apache.maven.plugins</groupId>
                    <artifactId>maven-compiler-plugin</artifactId>
                    <configuration>
                        <source>1.8</source>
                        <target>1.8</target>
                        <annotationProcessorPaths>
                            <path>
                                <groupId>org.mapstruct</groupId>
                                <artifactId>mapstruct-processor</artifactId>
                                <version>1.5.5.Final</version>
                            </path>
                            <path>
                                <groupId>org.projectlombok</groupId>
                                <artifactId>lombok</artifactId>
                                <version>1.18.20</version>
                            </path>
                            <path>
                                <groupId>org.projectlombok</groupId>
                                <artifactId>lombok-mapstruct-binding</artifactId>
                                <version>0.2.0</version>
                            </path>
                        </annotationProcessorPaths>
                    </configuration>
                </plugin>
            </plugins>
        </pluginManagement>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
```
[帮助手册](docs/mapstruct.pdf)

##### 五、Java 固定业务模式规范

##### 六、Java oem 代码规范
1、oem包代码结构规范
```Java

// oem 包名规则 
// plugin.datafort + 产品编码 + oem厂商名称

// oem包下必须有两个类 
// oem包扫描配置范围配置类
package plugin.datafort.afw.hs;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("plugin.datafort.afw.hs.dao")
@ComponentScan({"plugin.datafort.afw.hs"})
public class HsOemConfiguration {
}

// oem初始化实现plugin.datafort.afw.OemInitService方法
package plugin.datafort.afw.hs;
import plugin.datafort.afw.OemInitService;
public class HsOemInitServiceImpl implements OemInitService {
    
    @Override
    public void initConfig() {
    
    }
    
    @Override
    public void initProgram() {
    
    }
    
    @Override
    public void initOther() {
    
    }
}
```

2、oem包引入配置

```Java
//sprint boot 入口类增加选择器
@Import(OemImportSelector.class)

//oem包引入配置类        
package cn.datafort.oem;

import module.datafort.user.configuration.MdUserConfiguration;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;
import plugin.datafort.afw.OemInitService;

public class OemImportSelector implements ImportSelector {
    
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        
        String oem = "trx";
        String object = "afw";
        
        //todo OemInitService
        System.out.println(oem);
        
        String oemInitClass = "plugin.datafort."+object+"." + oem + "." + Character.toUpperCase(oem.charAt(0)) + oem.substring(1) + "OemInitServiceImpl";
        try {
            final Class<? extends OemInitService> initClass = Class.forName(oemInitClass).asSubclass(OemInitService.class);
            final OemInitService oemInitService = initClass.newInstance();
            oemInitService.initConfig();
            oemInitService.initProgram();
            oemInitService.initOther();
        } catch (Exception ignore) {
        }
        
        
        String oemConfigClass = "plugin.datafort."+object+"." + oem + "." + Character.toUpperCase(oem.charAt(0)) + oem.substring(1) + "OemConfiguration";
        return new String[]{oemConfigClass, MdUserConfiguration.class.getName()};
    }
}
```

3、oem包使用举例

| 需求 | 建议方式 | 注意事项 |
| ------ | ------ | ------ |
| 推送消息 | 使用事件订阅模式，在主代码中定义事件，oem代码中订阅事件信息 | 无 |




