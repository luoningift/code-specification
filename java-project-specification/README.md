### Java代码规范
#### 一、名词解释
- 大驼峰法：变量名或者函数名称由一个或者多个单词连结在一起，形成唯一识别字时，单词首字母采用大写模式，常用于类名等，如DatabaseUser
- 小驼峰法：变量名或者函数名称由一个或者多个单词连结在一起，形成唯一识别字时, 第一个单词以小写字母开始，从第二个单词开始以后的每个单词都采用大写字母，常用于变量等，如studentCount

#### 二、WEB开发基本依赖规范
- 必须依赖的组件为swagger2，mapstruct，log4j2，mybatis-plus，lombok, spring validation等

#### 三、web项目工程结构
##### 1、工程基本结构
```
├── controller  请求处理层
│   ├── ProductController.java
│   ├── SwaggerDemoController.java
│   ├── SwaggerI18nDemoController.java
│   └── TestController.java
├── dao         数据访问层 
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
##### 2、请求处理层规范
- 文件命名符合大驼峰法且以Controller结尾
- 禁止Controller之间互相调用
##### 3、数据访问层规范
- 文件命名符合大驼峰法且以Dao结尾，如CmsApplicationDao.java，禁止以非Dao结尾的其他命名方式，比如Mapper等
- 对应的mybatis xml文件命名符合大驼峰法且以Dao结尾，如CmsApplicationDao.xml
- 文件名与数据库表名称保持一致 比如：表名为user_role 则数据库访问层的表名为UserRoleDao.java 在数据库访问层命名无需体现业务逻辑
- 禁止省略数据库表名前缀命名，比如数据库中的表都以cm_开头，cm_user,cm_user_role等等，对应的Dao命名为CmUserDao.java,CmUserRoleDao.java
##### 4、数据对象规范
- 文件命名符合大驼峰法 且 与 数据库表名称保持一致
- 禁止省略数据库表名前缀命名，比如数据库中的表都以cm_开头，cm_user,cm_user_role等等，对应的Dao命名为CmUserDao.java,CmUserRoleDao.java
- 数据对象中禁止添加除数据表字段对应的属性字段外的其他属性
```
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("am_product")
@ApiModel(value="AmProduct对象", description="产品类型")
public class AmProduct implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "用户名称列表")
    @TableField("name_list")
    private String nameList;
    
    //不允许 数据对象中禁止添加除数据表字段对应的属性字段外的其他属性
    private List<String> nameArrayList;
}
```
##### 5、业务处理层规范
- 业务接口定义文件命名符合大驼峰法且以Service结尾
- 业务接口具体逻辑实现文件命名符合大驼峰法且以ServiceImpl结尾
- 业务接口具体逻辑实现文件除实现业务接口及mysqlbatis-plus相关接口文件外，禁止继承其他类文件或者接口, 比如继承Controller文件

##### 6、数据传输对象规范
- 数据传输对象文件命名符合大驼峰法且以Dto结尾
- 在数据传输对象中完成数据格式校验，禁止将数据格式校验逻辑扩散到Controller和Service

##### 7、model层临时对象规范
- 禁止在属性中出现java基础类型，统一使用包装类 正例 Integer Boolean 反例 int boolean, 需要高性能的代码可酌情使用基础类型


#### 四、日志规范
- 统一使用log4j2日志组件，使用lombok Slf4j注解声明日志实例
- 禁止在Dto、Vo、Entity等临时对象声明@Slf4j
- 记录的日志中不建议使用中文

#### 五、WEB项目开发规范
##### 1、http状态码及返回值格式定义
- 返回值包含code，msg，data三个字段。

- code为状态码，类型为整形。

  以下为通用状态码：

  200代表成功。

  203代表密码过期，前端跳转到密码修改界面

  401代表用户未授权，前端跳转到登陆页面

  403代表没有权限

  404代表参数校验失败，请求方法不正确

  405代表设备未授权，授权过期或授权异常

  407代表还未实现的操作

  500代表其它异常。服务层校验失败，发生运行时异常，都返回500。

  不要随意定义新状态码，除非与第三方对接或前端有特殊需求。一个状态码可以对应不同的msg。

- msg为返回结果描述，类型为字符串。msg在配置文件中配置，需要支持多语言。

- data为返回数据。返回类型必需是一个对象。不能为整形，字符串等基本类型，也不能是数组。当操作失败，或请求不需要有返回值时，data可以为null。
 ```json
{
  "code": 200,
  "msg": "操作成功",
  "data": {
    "list": [
      {
        "id": 7,
        "name": "10"
      },
      {
        "id": 8,
        "name": "10"
      }
    ]
  }
}  
 ```

##### 2、接口文档规范
- 使用swagger2编写接口文档, 使用是swagger2编写接口涉及到controller、dto、vo可不写java注释
```java
 @ApiOperation(value = "获取产品列表")
 @ApiImplicitParams(
            @ApiImplicitParam(name = "name",value = "产品名称",required = true)
 )
 @GetMapping("/search")
 public RepVo<List<ProductVo.AmProduct>> get(@RequestParam("name") @NotNull @Length(min = 2, max = 10, message = "产品名称不能为空且字数范围为2到10") String name){
        
    return new RepVo<>(productService.search(name));
 }
```
- model对象swagger定义举例
```java
  @Data
  @ApiModel(value="产品类型请求体", description="产品类型")
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
= }
```
##### 3、数据传输对象验证规范
- 所有web接口参数必须校验合法性；使用spring validation校验参数合法性；参数合法性校验在请求处理层或者dto中；参数校验可以写注解或者继承ObjectFieldsValidatorInterface接口java编码校验
- spring validation配置为尽快结束模式，只要检测有一个格式错误就返回
```java

@Configuration
public class ValidationConfiguration {
    
    @Bean
    public Validator validator() {
        return Validation.byProvider(HibernateValidator.class).configure().failFast(true).buildValidatorFactory().getValidator();
    }
}
```
- 在web接口参数不超过三个(基础类型 boolean Integer String等)时，可不用新建实体dto类，超过三个参数时需要新建dto类接收参数
```java
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
```
- 超过三个参数使用dto进行校验

```java
    import cn.datafort.model.dto.ProductDto;
import cn.datafort.web.common.validation.ObjectFields;

import javax.validation.GroupSequence;

@ApiOperation(value = "添加产品")
@PostMapping("/add")
public RepVo<Object> allowResponseEntity(@RequestBody @Validated ProductDto.AmProduct product){
        
        productService.add(product);
        return RepVo.SUCCESS();
        }

@Data
@ApiModel(value = "产品类型请求体", description = "产品类型")
@ObjectFields(groups = ObjectFields.class)
@GroupSequence({ProductDto.AmProduct.class, ObjectFields.class})
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
    
    //数据关联验证
    @Override
    public void multiFieldsValidator() {
        
        if (true) {
            throw new BusinessException(RepStatusEnum.PARAM_CHECK_FAIL.getStatus(), "未选择是否需要选择引擎");
        }
    }
}
```
- dto参数校验固定格式
```java
    @Data //lombok注解
    @ObjectFields(groups = ObjectFields.class) //将参数关联验证定义组为ObjectFields.class 固定格式
    @GroupSequence({AmProduct.class, ObjectFields.class}) //定义验证顺序 先进行单属性验证 单属性验证通过后进行数据关联验证
    @ApiModel(value="产品类型请求体", description="产品类型")
    class AmProduct implements Serializable, ObjectFieldsValidatorInterface {
        
        private static final long serialVersionUID = 1L;
        
        @ApiModelProperty(value = "主键")
        @Min(value = 10, message = "最小值为10")
        @Max(value = 20, message = "最大值为20")
        @Range(max = 15, min = 12, message = "不符合数据范围")
        private Integer id;
        
        @ApiModelProperty(value = "产品名称")
        private String name;
        
        @ApiModelProperty(value = "1 需要选择引擎数量 2 不需要")
        private Integer isEngine;
        
        @ApiModelProperty(value = "描述")
        private String description;
        
        //数据关联验证
        @Override
        public void multiFieldsValidator() {
            
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
```
- 公共属性定义通过范型方式定义 todo 
- dto参数校验继承实现公共属性定义固定格式
```java
@Data
@ApiModel(value="分页", description="分页")
@ObjectFields(groups = ObjectFields.class)
@GroupSequence({BasePageInfoDto.class, ObjectFields.class})
public class BasePageInfoDto implements ObjectFieldsValidatorInterface {
    
    @ApiModelProperty(value = "页数", example = "1")
    @Min(value = 10, message = "最小值为10")
    @Max(value = 20, message = "最大值为20")
    @Range(max = 15, min = 12, message = "不符合数据范围")
    private Integer page;
    
    @ApiModelProperty(value = "每页条目数", example = "1")
    @Min(value = 10, message = "最小值为10")
    @Max(value = 20, message = "最大值为20")
    @Range(max = 15, min = 12, message = "不符合数据范围")
    private Integer limit;
    
    public void multiFieldsValidator () {
        System.out.println("mmmmm");
        throw new BusinessException(RepStatusEnum.PARAM_CHECK_FAIL.getStatus(), "xxxxx");
    }
}
```
```java
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="用户", description="用户")
@ObjectFields(groups = ObjectFields.class)
@GroupSequence({UserDto.class, ObjectFields.class})
public class UserDto extends BasePageInfoDto implements ObjectFieldsValidatorInterface {
    
    @ApiModelProperty(value = "id", example = "1")
    @Min(value = 10, message = "最小值为10")
    @Max(value = 20, message = "最大值为20")
    @Range(max = 15, min = 12, message = "不符合数据范围")
    private Integer id;
    
    public void multiFieldsValidator () {
        
        System.out.println("xxxxx");
        //throw new BusinessException(RepStatusEnum.PARAM_CHECK_FAIL.getStatus(), "产品名称不能为空且不能超过10个字");
        super.multiFieldsValidator();
    }
}
```

```java
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

@Data
@ApiModel(value = "分页查询", description = "分页查询")
public class QueryEntity<F> {
    
    @NotNull(message = "PAGE_IS_NULL")
    @Min(value = 1, message = "PAGE_MIN_VALUE")
    @ApiModelProperty(value = "页数")
    private int page;
    
    @NotNull(message = "PAGE_SIZE_IS_NULL")
    @Min(value = -1, message = "PAGE_SIZE_MIN_VALUE")
    @Max(value = 500, message = "PAGE_SIZE_MAX_VALUE")
    @ApiModelProperty(value = "每页条目数")
    private int limit;
    
    @Valid
    @ApiModelProperty(value = "具体的限制条件")
    private F filters;
}
```
##### 4、entity数据对象规范
- entity数据对象规范 需包含swagger类注解和属性注解，mybatis-plus的类注解和属性注解 参考项目: [entity数据对象自动生成](http://git.csdev.com/toolkit/code-specification/-/blob/main/mybatis-plus-entity-generator/src/main/java/cn/datafort/generator/GeneratorMain.java)
```java

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
```
##### 5、数据库操作规范
- 推荐使用mybatis-plus LambdaWrapper完成简单的增删改查，复杂查询使用xml

##### 6、禁止使用除get post之外的接口定义方式，不推荐restful格式的接口请求地址定义
- 市面上部分安全软件会认为除get和post外的方法为不安全，使用get和post之外的请求方式可能导致产品的安全合规检查不通过
- restful方式的接口定义导致后续的统计分析困难
```
不使用restful的接口定义有一组日志数据
getUser
getUserRole
getOrderList
可以使用一个linux命令统计出每个地址的访问次数 cat xxx.log | sort | uniq -c | sort -nr | head -n 10

使用restful的接口定义的一组日志数据
user/1/2
user/1/role/2
user/1/name/3
role/1/name/334
统计较麻烦，增加维护成本
```

##### 7、方法返回值规范及错误处理
- 错误处理统一使用抛出异常的方式
原因：使用其他方式导致代码中的方法返回值不明确，只能范性化处理，比如使用Object、RepVo、RepVo< Object >当返回值，导致代码阅读困难
```
//反例1：在service层直接使用Object作为返回值, 同时使用统一的范型包装类在业务逻辑中处理的错误和正确信息
 /**
     * @Auth: lys
     * Description: 普通登录检验登陆用户信息
     * @Date:16:19 2017/8/21 0021
     */
    @Transactional
    @Override
    public Object checkExist(String name, String password, RepVo msg, HttpSession session) {
        User user = this.userDao.selectUserByName(name);
        if (user == null) {
            msg.setMessage("用户名或密码错误！");
            msg.setStatus(ToolsConstant.Status.DATA_ERROR);
            return msg;
        } else {
            //默认用户不允许登录
            if (user.getRoleId() == 7) {
                msg.setMessage("用户未授权！");
                msg.setStatus(ToolsConstant.Status.DATA_ERROR);
                return msg;
            }
            //查询账户安全配置
            UserConfig userConfig = this.userConfigDao.selectUserSafe(1);
            if (userConfig == null) {
                userConfig = new UserConfig();
            }
            long time = System.currentTimeMillis();

            //安全登录时间差
            long timeDifference = time - userConfig.getLogTm() * 1000L;
            //登录失败次数
            int errorCount = userDao.getCountByUserId(user.getId(), timeDifference);
            user.setLastLogin(time);
            long updateTime = 0L;
            //开启了密码过期配置
            if (userConfig.getPwdExpire()) {
                //获取安全配置的修改时间 此时间只有在开启过期按钮操作的时候存在
                long configUpdateTime = userConfig.getUpdateTime().getTime();
                //获取用户最后修改时间 此修改时间只是在授权新建用户或者修改密码的时候会存在值
                long userUpdateTime = user.getLastModify();
                //判断安全配置的修改时间和用户最后的修改时间那一个距离当前时间最近
                updateTime = configUpdateTime > userUpdateTime ? configUpdateTime : userUpdateTime;
            }
            if (user.getExpireTime() != 0L && (userConfig.getLogLock() * 60000L + user.getExpireTime()) > time) {//用户锁定
                msg.setMessage("该用户已被锁定，请" + ((user.getExpireTime() + userConfig.getLogLock() * 60000L) - time) / 1000L + "秒后再登录！");
                msg.setStatus(ToolsConstant.Status.OTHER);
                return msg;
            } else if (userConfig.getPwdExpire() && (time - updateTime > userConfig.getExpire() * 24 * 60 * 60000L)) {//过期判断
                msg.setMessage("该用户的密码已过期不能登录！请联系管理员！");
                msg.setStatus(ToolsConstant.Status.POWER_USER_PSWORDTIMEOUT);
                return msg;
            } else {
                // 提出密文
                String key = user.getKeyValue().substring(0, 128);
                if (PBKDF2Util.authenticate(password, key, user.getKeyValue().replaceAll(key, ""))) {
                    session.setAttribute(ToolsConstant.CURRENT_USER_KEY, user);
                    Role role = roleDao.getRoleById(user.getRoleId());
                    if (role != null) {
                        Map<String, Object> map = getRightsToMap(role);
                        session.setAttribute(ToolsConstant.CURRENT_USER_RIGHTS_KEY, map);
                    }
                    user.setLastLogin(time);
                    //设置session失效时间
                    session.setMaxInactiveInterval(userConfig.getSessionTimeout() * 60);
                    //更新用户信息
                    this.userDao.updateUser(user);

                    if (user.isUkStatus()) {
                        this.userDao.updateUserUK(user);
                    }

                    //删除登录用户对应的错误登录日志
                    this.userDao.deleteByUserErrorloginId(user.getId());
                    msg.setMessage("登录成功");
                    msg.setStatus(ToolsConstant.Status.SUCCESS);
                    return user;
                } else {
                    if ((time - user.getLastLogin()) > userConfig.getLogTm() * 1000L) {
                        this.userDao.deleteByUserErrorloginId(user.getId());
                    }
                    //添加错误记录
                    this.userDao.insertUserLogin(new UserErrorLogin(user.getId(), time));
                    session.setMaxInactiveInterval(userConfig.getSessionTimeout() * 60);
                    int co = userConfig.getLogCount() - errorCount;
                    if (co > 1) {//未锁定
                        long loginremtime = userConfig.getLogTm() - (time - user.getLastLogin()) / 1000L;
                        msg.setMessage("用户名或密码错误，" + loginremtime + "秒内，您再连续输入" + (co - 1) + "次错误密码，您的帐号将被锁定！");
                        msg.setStatus(ToolsConstant.Status.OTHER);
                    } else {//已锁定
                        user.setExpireTime(time);
                        msg.setMessage("用户名或密码错误，帐号已被锁定，" + userConfig.getLogLock() * 60 + "秒后再登录！");
                        msg.setStatus(ToolsConstant.Status.OTHER);
                    }
                    this.userDao.updateUser(user);
                    return msg;
                }
            }
        }
    }
```
```
    //反例2：在service层直接使用Object作为返回值
    @Override
    public List<Map<String, Object>> queryAccountTableList(Map<String, Object> params) {
        List<Map<String, Object>> result = accountDao.queryTableList(params);
        for (Map<String, Object> map : result) {
            Integer tableId = Integer.parseInt(map.get("id").toString());
            map.putAll(accountDao.queryMarkInfoByTableId(tableId));
            Map<String, Object> categoryLabelIdAndNameByIds = null;
            if (map.get("categoryIds") != null) {
                categoryLabelIdAndNameByIds = CategoryUtils.getCategoryLabelIdAndNameByIds(Arrays.stream(map.get("categoryIds").toString().split(",")).map(Integer::parseInt).collect(toList()));
                map.put("categoryLabels", categoryLabelIdAndNameByIds.get("names"));
            } else {
                map.put("categoryLabels", null);
            }
            map.put("levelLabels", accountDao.queryTableLevelInfo(tableId));
            map.putAll(accountDao.querySenInfoByTableId(tableId));
            if (!CollectionUtils.isEmpty(categoryLabelIdAndNameByIds)) {
                List<CategoryLabelVo> category = (List<CategoryLabelVo>) categoryLabelIdAndNameByIds.get("category");
                List<Integer> signList = category.stream().map(CategoryLabelVo::getSign).collect(toList());
                if (signList.contains(1) && !signList.contains(2)) {
                    map.put("sign", 1);
                }
                if (signList.contains(2) && !signList.contains(1)) {
                    map.put("sign", 2);
                }
                if (!signList.contains(1) && !signList.contains(2)) {
                    map.put("sign", 0);
                }
            }
        }
        return result;
    }
```
```
    //反例3：在service层直接使用统一的范型包装类, 错误信息使用返回值的方式处理
    @Override
    public RepVo addFtpAuditPoint(FtpAuditPoint point) {
        //判断是否添加相同ip和端口的引擎
        List<FtpAuditPoint> point2 = this.ftpAduitPointDao.queryPointByIPandPort(point.getIp(), point.getPort());
        if (point2 != null && point2.size() > 0) {
            return new RepVo(ToolsConstant.Status.PARAM_CHECKFAIL, "相同IP和端口的引擎已存在!");
        }
        point.setAuditStatus((short) 1);
        List<FtpRules> rules = this.ftpRulesDao.queryByIdAndDefault(0L, true);
        StringBuilder ruleIds = new StringBuilder();
        for (FtpRules rule : rules) {
            ruleIds.append(rule.getId()).append(",");
        }
        if (ruleIds.length() > 0)
            ruleIds.deleteCharAt(ruleIds.length() - 1);
        point.setRuleIds(ruleIds.toString());
        this.ftpAduitPointDao.savePoint(point);
        //根据Ip和端口获取新增引擎的Id
        List<FtpAuditPoint> pointlist = this.ftpAduitPointDao.queryPointByIPandPort(point.getIp(), point.getPort());
        if (pointlist != null && pointlist.size() > 0) {
            point.setId(pointlist.get(0).getId());
            FtpAuditUtil.addEngine(point.getId(), rules);
        }
        return new RepVo();
    }
```
- 异常类型分类 异常类统一继承RuntimeException
```
比如有以下几种错误信息

  203代表密码过期，前端跳转到密码修改界面
  401代表用户未授权，前端跳转到登陆页面
  
则异常类定义如下：
// 203 代表密码过期，前端跳转到密码修改界面
PassExpireException
// 401 用户未授权
UserPowerErrException

在程序运行中抛出以上异常信息，在统一的异常处理中将错误信息转换为统一的范型包装返回值
```
- Controller及Service的返回值需明确表达含义且格式唯一 禁止使用Object等不能唯一表达返回值的对象
- Service返回值不能使用统一的范型包装返回值 禁止出现RepVo类似的返回值
- Controller返回值需明确指定返回的对象类型 反例 RepVo RepVo< Object > RepVo< List< Object >> RepVo< Map< String, Object > > 正例RepVo< User > RepVo< ListRepVo < List< User > > > 
- Controller返回值 禁止返回基础类型和map 反例 RepVo< Integer >  RepVo< Boolean > RepVo< Map < String, User > >
- 禁止在Controller，Service层返回值中出现Map和Object等关键字
- RepVo序列化之后的json数据的data数据必须为{} 举例：{data: {status: 1}} , {data: {list: []}}, {data: {list: {username:1111, age:12}}}
```
    正例：
    
    //Service层返回值明确
    @Override
    public List<DeviceApiVo> getApiList(JSONObject parameterJson) {
        
        //伪代码
        if (数据错误) {
            throw new DataErrException("数据错误");
        }
        
        if (其他错误) {
            throw new OtherErrException("地球炸了");
        }
        List<DeviceApiVo> apiList = apiAssetsDao.getApiList(parameterJson);
        //将API集合中标签JSON转为集合
        this.convertTagJson(apiList);
        //设置API分类分级标签
        this.buildApiCategoryAndLevel(apiList);
        return apiList;
    }
    
    //Controller层返回值使用统一的范型包装类 明确制定返回值
     public RepVo<List<DeviceApi>> apiList(@RequestBody JSONObject parameterJson) {

       List<DeviceApiVo> list = apiAssetsService.getApiList(parameterJson);
       return new RepVo<>(list); 
    }
```

##### 8、其他规范
- 禁止一个Controller的方法返回多种不同格式的数据
反例：
```
    @RequestMapping(value = "/account-list", method = RequestMethod.POST)
    public RepVo accountList(@RequestBody JSONObject parameterJson, HttpServletRequest request) {
        Integer srcId = parameterJson.getInteger("srcId");
        if (Objects.isNull(srcId)) {
            throw new DagRuntimeException("ID_IS_NULL");
        }
        userInfoCacheService.checkUserAuth(Collections.singletonList(srcId), UserUtil.getToken(request), TokenRedisType.GROUP_SRC_RES);

        if (!StringUtils.isEmpty(parameterJson.getString("keyWord")) && parameterJson.getString("keyWord").length() > 255) {
            throw new DagRuntimeStatusException(CommonConstant.Status.PARAM_CHECKFAIL, "查询项有误");
        }
        if (PageUtil.parsePage(parameterJson)) {
            List<Map<String, Object>> result = accountCombService.queryAccountList(parameterJson);
            Integer count = accountCombService.queryAccountListCount(parameterJson);
            //一种返回格式
            return new RepVo(new Page<>(result, count));
        }
        List<Map<String, Object>> result = accountCombService.queryAccountAllList(parameterJson);
        //另外一种返回格式
        return new RepVo(result);
    }
```
- 临时对象中除dto和entity之外的所有对象命名方式使用Vo结尾
- 任何数据结构的构造或初始化时需指定容量大小
- 禁止在web开发中使用JsonObject,Propertity等范性化的数据结构
- 在请求处理层不包含业务逻辑代码
- 在java类属性及数据库表字段定义中不推荐null，0等值， 在接收参数中使用0代表全部；
- 对象属性复制使用mapstruct [参考代码](http://git.csdev.com/toolkit/code-specification/-/blob/main/java-project-specification/src/main/java/cn/datafort/model/ObjectMapper.java) [帮助手册](docs/mapstruct.pdf)
- 常量定义推荐使用enum，方便扩展
```java
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
```

##### 六、命令行程序开发规范
- 建议遵循WEB开发相关的目录规范和代码规范
- 建议基于spring boot构建公共的代码库
```
//spring boot命令行程序启动配置
public class App {
    public static void main(String[] args) {
        // 设置时区
        TimeZone timeZone = TimeZone.getTimeZone("GMT+8");
        TimeZone.setDefault(timeZone);
        // web启动
        //SpringApplication.run(App.class, args);
        // 命令行程序启动 关闭tomcat等WEB服务启动
        new SpringApplicationBuilder(App.class).web(WebApplicationType.NONE).run(args);
    }
}
//其他问题
使用spring security 需配置 @EnableGlobalMethodSecurity(prePostEnabled = true)
使用websocket相关服务 需将相关的bean排除 因为WEB服务没了
```

##### 七、安全规范
- 用户敏感数据禁止直接展示，必须对展示数据进行脱敏
- 用户输入的SQL参数严格使用参数绑定或者METADATA字段值限定，防止SQL注入，禁止字符串拼接SQL访问数据库
- 配置文件中的密码需要加密

##### 八、mysql数据库
- 表名、字段名必须使用小写字母或数字，禁止出现数字开头禁止两个下划线中间只出现数字。数据库字段名的修改代价很大，因为无法进行预发布，所以字段名称需要慎重考虑；
```
        MySQL在Windows下不区分大小写，但在Linux下默认是区分大小写。因此，数据库名、表名、字段名，都不允许出现任何大写字母，避免节外生枝。
        正例：aliyun_admin，rdc_config，level3_name
        反例：AliyunAdmin，rdcConfig，level_3_nam
```
- 禁用保留字，如desc、range、match、delayed等，请参考MySQL官方保留字;   
- 表必备四个字段：自增id，创建用户, 创建时间、更新时间； 配置表可不使用自增主键；历史表必备三个字段: 自增id，创建用户, 创建时间
```
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间（自动填充）',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间（自动填充）',
```
- 表必备删除标识字段 invalid
```
  `invalid` tinyint UNSIGNED NOT NULL DEFAULT '0' COMMENT 'Whether deleted: 0 No, 1 Yes',
```
- 不得使用外键与级联，一切外键概念必须在应用层解决；
- 禁止使用存储过程，存储过程难以调试和扩展，更没有移植性；
- 超过三个表禁止join。需要join的字段，数据类型保持绝对一致；多表关联查询时，保证被关联的字段需要有索引；
- 在varchar字段上建立索引时，必须指定索引长度，没必要对全字段建立索引，根据实际文本区分度决定索引长度；


##### 九、Java oem 相关建议
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




