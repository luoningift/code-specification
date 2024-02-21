### Java代码规范
#### 一、名词解释
- 大驼峰法：变量名或者函数名称由一个或者多个单词连结在一起，形成唯一识别字时，单词首字母采用大写模式，常用于类名等，如DatabaseUser
- 小驼峰法：变量名或者函数名称由一个或者多个单词连结在一起，形成唯一识别字时, 第一个单词以小写字母开始，从第二个单词开始以后的每个单词都采用大写字母，常用于变量等，如studentCount

#### 二、WEB开发开发基本依赖规范
必须依赖的组件为swagger2，mapstruct，log4j2，mybatis-plus，lombok, spring validation等

#### 三、web项目工程结构
###### 1、工程基本结构
```
├── controller  请求处理层
│   ├── ProductController.java
│   ├── SwaggerDemoController.java
│   ├── SwaggerI18nDemoController.java
│   └── TestController.java
├── dao         数据访问层 文件名与数据库表名称保持一致 比如：表名为user_role 则数据库访问层的表名为UserRoleDao.java
│   ├── AmProductDao.java
│   ├── AmSupplierDao.java
│   └── TestDao.java
├── model       实体类 
│   ├── dto         数据传输对象，请求处理层接收请求参数
│   │   ├── ProductDto.java
│   │   ├── StatusEnumDto.java
│   │   ├── SysUserAllowDto.java
│   │   ├── TestChildDto.java
│   │   └── TestDto.java
│   ├── entity      数据对象，与数据库表保持一致
│   │   ├── AmProduct.java
│   │   ├── AmSupplier.java
│   │   └── Test.java
│   ├── ObjectMapper.java
│   └── vo          展示层视图结构
│       └── ProductVo.java
├── service     业务处理层
│   ├── impl
│   │   ├── ProductServiceImpl.java 业务接口具体逻辑实现
│   │   └── TestServiceImpl.java
│   ├── ProductService.java    业务接口定义
│   └── TestService.java
```
请求处理层规范
```
符合
```

#### 二、日志规范
```
统一使用log4j2日志组件，使用lombok Slf4j注解声明日志实例
禁止在Dto、Vo、Entity等临时对象声明@Slf4j
```

#### 三、java开发规范
```
1、任何数据结构的构造或初始化时需指定容量大小；
2、不推荐使用JsonObject,Map,Propertity等数据结构；
```

#### 四、web项目开发规范
```
1、 使用swagger2编写接口文档, 使用是swagger2编写接口涉及到controller、dto、vo可不写java注释；
2、 禁止在WEB开发中使用JsonObject、Map、Property等数据结构；
3、 在web接口参数不超过三个(基础类型 boolean Integer String等)时，可不用新建实体dto类，超过三个参数时需要新建dto类接收参数；
4、 所有web接口参数必须校验合法性；使用spring validation校验参数合法性；参数合法性校验在请求处理层或者dto中；参数校验可以写注解或者继承ObjectFieldsValidatorInterface接口java编码校验;
    三个参数以下校验：
      @Slf4j
@Api(tags = "代码规范例子代码-产品管理")
@RestController
@RequestMapping(value = "/product",produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class ProductController {
    
    @Autowired
    private ProductService productService;
    
    @ApiOperation(value = "获取产品列表")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "name",value = "产品名称",required = true)
    )
    @GetMapping("/search")
    public RepVo<List<ProductVo.AmProduct>> get(@RequestParam("name") @NotNull @Length(min = 2, max = 10, message = "产品名称不能为空且字数范围为2到10") String name){
        
        return new RepVo<>(productService.search(name));
    }
}
    超过三个参数使用dto进行校验:
        @ApiOperation(value = "添加产品")
    @PostMapping("/add")
    public RepVo<Object> allowResponseEntity(@RequestBody @Validated ProductDto.AmProduct product){
        
        productService.add(product);
        return RepVo.SUCCESS();
    }
    
     @Data
    @ApiModel(value="产品类型请求体", description="产品类型")
    @ObjectFields
    class AmProduct implements Serializable, ObjectFieldsValidatorInterface {
        
        private static final long serialVersionUID = 1L;
        
        @ApiModelProperty(value = "主键")
        private Integer id;
        
        @ApiModelProperty(value = "产品名称")
        private String name;
        
        @ApiModelProperty(value = "1 需要选择引擎数量 2 不需要")
        private Integer isEngine;
        
        @ApiModelProperty(value = "描述")
        private String description;
    
        @Override
        public void multiFieldsValidator() {
            
            if (name == null || name.length() <= 0 || name.length() > 10) {
                throw new BusinessException(RepStatusEnum.PARAM_CHECK_FAIL.getStatus(), "产品名称不能为空且不能超过10个字");
            }
            boolean engineFlag = false;
            for (ProductEngineConst value : ProductEngineConst.values()) {
                if (value.getCode() == isEngine) {
                    engineFlag = true;
                    break;
                }
            }
            if (!engineFlag) {
                throw new BusinessException(RepStatusEnum.PARAM_CHECK_FAIL.getStatus(), "未选择是否需要选择引擎");
            }
    
            if (description == null || description.length() <= 0 || description.length() > 10) {
                throw new BusinessException(RepStatusEnum.PARAM_CHECK_FAIL.getStatus(), "描述信息不能为空且不能超过10个字");
            }
        }
    }
5、在请求处理层不包含业务逻辑代码；
6、返回值格式定义：
{
  "status": 1000,     状态码：1000 返回成功，1999 当前用户状态未启用，2000 模块授权失败， 2001 用户权限不足，2002 身份识别错误 需要登陆, 3000 参数校验不通过错误，4000 数据错误， 7000其他错误 可在此基础上扩展
  "message": "操作成功!",  状态码非1000时，详细错误的描述信息
  "data": [           具体的业务数据
    {
      "id": 7,
      "name": "10",
      "isEngine": 1,
      "description": "10"
    },
    {
      "id": 8,
      "name": "10",
      "isEngine": 1,
      "description": "10"
    },
    {
      "id": 9,
      "name": "10",
      "isEngine": 1,
      "description": "10"
    }
  ]
}   
7、在接收参数和返回值中尽量不要有null，0 代表全部 状态类的参数定义不要使用0这个值，从1开始定义；
8、entity数据对象规范 需包含swagger类注解和属性注解，mybatis-plus的类注解和属性注解 参考项目：http://git.csdev.com/toolkit/code-specification/-/blob/main/mybatis-plus-entity-generator/src/main/java/cn/datafort/generator/GeneratorMain.java 自动生成数据对象
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("am_product")
@ApiModel(value="AmProduct对象", description="产品类型")
public class AmProduct implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "产品名称")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "1 需要选择引擎数量 2 不需要")
    @TableField("is_engine")
    private Integer isEngine;

    @ApiModelProperty(value = "1 正常 2 删除")
    @TableField("status")
    private Integer status;

    @ApiModelProperty(value = "描述")
    @TableField("description")
    private String description;

    @ApiModelProperty(value = "创建时间")
    @TableField("created_at")
    private LocalDateTime createdAt;

    @ApiModelProperty(value = "更新时间")
    @TableField("updated_at")
    private LocalDateTime updatedAt;

}
9、推荐使用mybatis-plus LambdaWrapper完成简单的查询，复杂查询使用xml；
10、对象属性复制使用mapstruct 参考：http://git.csdev.com/toolkit/code-specification/-/blob/main/java-project-specification/src/main/java/cn/datafort/model/ObjectMapper.java
11、常量定义使用enum、方便扩展；
 enum DBType {
    
    ORACLE(0, "Oracle", 1521, 1, 1, 1, new String[]{"9.1.0.1", "9.2.0.1", "9.2.0.2", "9.2.0.4", "10.1.0.1", "10.1.0.2", "10.1.0.3", "10.1.0.4", "10.2.0.1", "10.2.0.2", "10.2.0.3", "10.2.0.4", "11.1.0.1", "11.1.0.2", "11.1.0.3", "11.1.0.4", "11.1.0.5", "11.1.0.6", "11.1.0.7", "11.2.0.1", "11.2.0.2", "11.2.0.3", "11.2.0.4", "12.1.0.1", "12.1.0.2", "12.2.0.1", "12.2.0.2", "unknown"}),
    SQLSERVER(1, "SQL Server", 1433, 0, 1, 1, new String[]{"SQL Server2000（8.0）", "SQL Server2005（9.0）", "SQL Server2008(10.0)", "SQL Server2008R2(10.50)", "SQL Server2012(11.0)", "SQL Server2014(12.0)", "SQL Server2016(13.0)", "SQL Server2017(14.0)", "unknown"}),
    
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
}
12、禁止使用除get post之外的接口定义方式。
```
[mapstruct帮助手册](docs/mapstruct.pdf)

##### 五、安全规范
```
1、用户敏感数据禁止直接展示，必须对展示数据进行脱敏；
2、用户输入的SQL参数严格使用参数绑定或者METADATA字段值限定，防止SQL注入，禁止字符串拼接SQL访问数据库；
3、配置文件中的密码需要加密；
```

##### 六、mysql数据库
```
1、表名、字段名必须使用小写字母或数字，禁止出现数字开头禁止两个下划线中间只出现数字。数据库字段名的修改代价很大，因为无法进行预发布，所以字段名称需要慎重考虑；
   MySQL在Windows下不区分大小写，但在Linux下默认是区分大小写。因此，数据库名、表名、字段名，都不允许出现任何大写字母，避免节外生枝。
   正例：aliyun_admin，rdc_config，level3_name
   反例：AliyunAdmin，rdcConfig，level_3_nam
2、禁用保留字，如desc、range、match、delayed等，请参考MySQL官方保留字;   
3、表必备三个字段：自增id，创建时间、更新时间；配置表可不使用自增主键；
4、不得使用外键与级联，一切外键概念必须在应用层解决；
5、禁止使用存储过程，存储过程难以调试和扩展，更没有移植性；
6、超过三个表禁止join。需要join的字段，数据类型保持绝对一致；多表关联查询时，保证被关联的字段需要有索引；
7、在varchar字段上建立索引时，必须指定索引长度，没必要对全字段建立索引，根据实际文本区分度决定索引长度；
```


##### 七、Java oem 相关建议
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




