#### Java代码规范

##### 一、Java oem 代码规范
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


##### 二、Java controller 参数验证规范

##### 三、Java 常量定义规范

##### 四、Java 对象属性复制规范

##### 五、Java 固定业务模式规范

##### 六、Java 接口编写规范
参考[swagger入门](docs/swagger入门.md)
