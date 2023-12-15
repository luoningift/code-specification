#### Java 模块代码规范

##### 一、Java 模块 代码规范

1、module包代码结构规范

```
├── pom.xml
├── README.md
└── src
    ├── main
    │   ├── java
    │   │   ├── module
    │   │   │   └── datafort 固定包名前缀
    │   │   │       └── user 模块名称
    │   │   │           ├── configuration
    │   │   │           │   └── MdUserConfiguration.java 所有类文件以Md + 模块名称 开头
    │   │   │           ├── controller
    │   │   │           │   └── MdUserTestController.java
    │   │   │           ├── dao
    │   │   │           │   └── MdUserTestDao.java
    │   │   │           ├── info
    │   │   │           │   └── ModuleInfo.java
    │   │   │           ├── model
    │   │   │           │   └── entity
    │   │   │           │       └── MdUserTestEntity.java
    │   │   │           ├── ModuleApplication.java
    │   │   │           ├── oem
    │   │   │           │   └── MdUserOemImportSelector.java
    │   │   │           └── service
    │   │   │               ├── impl
    │   │   │               │   └── MdUserTestServiceImpl.java
    │   │   │               └── MdUserTestService.java
    │   │   └── plugin
    │   │       └── module
    │   │           └── datafort 固定包名前缀
    │   │               └── user   模块名称 
    │   │                   ├── hs  oem名称
    │   │                   │   ├── controller
    │   │                   │   │   └── MdUserHsTestController.java 所有类文件以Md + 模块名称 + oem名称 开头
    │   │                   │   ├── dao
    │   │                   │   │   └── MdUserHsTestDao.java
    │   │                   │   ├── entity
    │   │                   │   │   └── MdUserHsTestEntity.java
    │   │                   │   ├── MdUserHsOemConfiguration.java
    │   │                   │   ├── MdUserHsOemInitServiceImpl.java
    │   │                   │   ├── README.md
    │   │                   │   └── service
    │   │                   │       ├── impl
    │   │                   │       │   └── MdUserHsTestServiceImpl.java
    │   │                   │       └── MdUserHsTestService.java
    │   │                   ├── OemInitService.java oem接口 
    │   │                   └── trx
    │   │                       ├── controller
    │   │                       │   └── MdUserTrxTestController.java
    │   │                       ├── dao
    │   │                       │   └── MdUserTrxTestDao.java
    │   │                       ├── entity
    │   │                       │   └── MdUserTrxTestEntity.java
    │   │                       ├── MdUserTrxOemConfiguration.java
    │   │                       ├── MdUserTrxOemInitServiceImpl.java
    │   │                       ├── README.md
    │   │                       └── service
    │   │                           ├── impl
    │   │                           │   └── MdUserTrxTestServiceImpl.java
    │   │                           └── MdUserTrxTestService.java
    │   └── resources
    │       ├── application.yml
    │       ├── init.sql
    │       └── log4j2.xml
    └── test
        └── java
```

1、module包代码规范

```Java

//配置扫描路径
package module.datafort.user.configuration;

import module.datafort.user.oem.MdUserOemImportSelector;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@MapperScan("module.datafort.user.dao")
@ComponentScan({"module.datafort.user"})
@Import(MdUserOemImportSelector.class)
public class MdUserConfiguration {
}

```

2、oem包引入配置

```Java
// configuration 引入
@Import(OemImportSelector.class)

//oem包引入配置类        
package module.datafort.user.oem;

import module.datafort.user.info.ModuleInfo;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;
import plugin.module.datafort.user.OemInitService;

public class MdUserOemImportSelector implements ImportSelector {
    
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        
        //根据配置 获取oem信息
        String oem = "trx";
        String moduleName = ModuleInfo.moduleName;
        //MdUserHsOemInitServiceImpl.java
        String oemInitClass = "plugin.module.datafort." + moduleName + "." + oem +".Md" + Character.toUpperCase(moduleName.charAt(0)) + moduleName.substring(1) + Character.toUpperCase(oem.charAt(0)) + oem.substring(1) + "OemInitServiceImpl";
        
        try {
            final Class<? extends OemInitService> initClass = Class.forName(oemInitClass).asSubclass(OemInitService.class);
            final OemInitService oemInitService = initClass.newInstance();
            oemInitService.initConfig();
            oemInitService.initProgram();
            oemInitService.initOther();
        } catch (Exception ignore) {
        }
        
        String oemConfigClass = "plugin.module.datafort." + moduleName + "." + oem + ".Md" + Character.toUpperCase(moduleName.charAt(0)) + moduleName.substring(1) + Character.toUpperCase(oem.charAt(0)) + oem.substring(1) + "OemConfiguration";
        return new String[]{oemConfigClass};
    }
}
```