#### Java 模块代码规范帮助文档规范

##### 一、安装
1、pom.xml引入jar包
```
<dependency>
    <groupId>module.datafort</groupId>
    <artifactId>module-user</artifactId>
    <version>1.0.1</version>
</dependency>
```
2、代码中配置configuration
```Java

```Java
//sprint boot 入口类增加选择器
@Import(OemImportSelector.class)

//oem包引入配置类        
package cn.datafort.oem;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class OemImportSelector implements ImportSelector {
    
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        
        return new String[]{"module.datafort.user.Configuration"};
    }
}
```

##### 二、配置文件说明
```json
{
  "module" : {
    "user" : {
      "username" : "用户名"
    }
  }
}
```

##### 三、注意事项

```
请在/etc/datafort/.config_location中配置 配置文件所在目录
```

##### 四、版本变更
| 版本 | 包名 | 说明 |
| ------ | ------ | ------ |
| v1.0.4 | 适配hs登陆 | 无 |
| v1.0.3 | 适配trx登陆 | 无 |
| v1.0.2 | 修复bug | 无 |
| v1.0.1 | 增加oauth2认证 | 无 |