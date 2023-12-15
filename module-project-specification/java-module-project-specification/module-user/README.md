#### Java 模块代码规范

##### 一、Java 模块 代码规范

1、module包代码结构规范

```
├── pom.xml
├── src
│   ├── main
│   │   ├── java
│   │   │   ├── module
│   │   │   │   └── datafort 固定前缀
│   │   │   │       └── user  模块名称
│   │   │   │           └── module
│   │   │   │               ├── configuration
│   │   │   │               │   └── UserConfiguration.java 模块自动配置文件，需项目使用import引入
│   │   │   │               ├── controller
│   │   │   │               │   └── TestController.java
│   │   │   │               ├── dao
│   │   │   │               │   └── TestDao.java
│   │   │   │               ├── model
│   │   │   │               │   └── entity
│   │   │   │               │       └── TestEntity.java
│   │   │   │               ├── oem
│   │   │   │               │   └── OemImportSelector.java
│   │   │   │               ├── service
│   │   │   │               │   ├── impl
│   │   │   │               │   │   └── TestServiceImpl.java
│   │   │   │               │   └── TestService.java
│   │   │   │               └── WebApplication.java
│   │   │   └── plugin
│   │   │       └── module
│   │   │           └── datafort
│   │   │               └── user  模块名称
│   │   │                   ├── hs   山石定制化内容
│   │   │                   │   ├── controller
│   │   │                   │   │   └── HsTestController.java
│   │   │                   │   ├── dao
│   │   │                   │   │   └── HsTestDao.java
│   │   │                   │   ├── entity
│   │   │                   │   │   └── HsTestEntity.java
│   │   │                   │   ├── HsOemConfiguration.java
│   │   │                   │   ├── HsOemInitServiceImpl.java
│   │   │                   │   ├── README.md
│   │   │                   │   └── service
│   │   │                   │       ├── HsTestService.java
│   │   │                   │       └── impl
│   │   │                   │           └── HsTestServiceImpl.java
│   │   │                   ├── OemInitService.java
│   │   │                   └── trx 天融信定制化内容
│   │   │                       ├── controller
│   │   │                       │   └── TrxTestController.java
│   │   │                       ├── dao
│   │   │                       │   └── TrxTestDao.java
│   │   │                       ├── entity
│   │   │                       │   └── TrxTestEntity.java
│   │   │                       ├── README.md
│   │   │                       ├── service
│   │   │                       │   ├── impl
│   │   │                       │   │   └── TrxTestServiceImpl.java
│   │   │                       │   └── TrxTestService.java
│   │   │                       ├── TrxOemConfiguration.java
│   │   │                       └── TrxOemInitServiceImpl.java
│   │   └── resources 打包时可排除 测试使用
│   │       ├── application.yml
│   │       ├── init.sql
│   │       └── log4j2.xml
│   └── test
│       └── java
```

1、module包代码规范

```Java

// 配置扫描路径
package module.datafort.user.module.configuration;

import OemImportSelector;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@MapperScan("module.datafort.user.module.dao")
@ComponentScan({"module.datafort.user.module"})
//oem 相关引入
@Import(OemImportSelector.class)
public class UserConfiguration {
}

```

2、oem包引入配置

```Java
// configuration 引入
@Import(OemImportSelector.class)

//oem包引入配置类        
package module.datafort.user.module.oem;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class OemImportSelector implements ImportSelector {
    
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        
        String oem = "trx";
        //todo OemInitService
        System.out.println(oem);
        return new String[]{"plugin.module.datafort.user." + oem + "." + Character.toUpperCase(oem.charAt(0)) + oem.substring(1) + "OemConfiguration"};
    }
}
```