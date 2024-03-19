# Swagger3 使用介绍

# 一、Swagger3 简介

`Swagger` 是一个规范和完整的框架，用于生成可视化 RESTful 风格的 Web 服务。是一个简单且功能强大的API工具。几乎所有的现代编程语言，都在支持和使用。

# 二、与Swagger2注解对比

之前在SpringBoot项目中一直使用的是`SpringFox`提供的Swagger库,已经很久没有更新了。 `SpringDoc`
是一款可以结合SpringBoot使用的API文档生成工具，基于OpenAPI 3, 是一款更好用的Swagger库！值得一提的是SpringDoc不仅支持Spring
WebMvc项目，还可以支持Spring WebFlux项目，甚至Spring Rest和Spring Native项目，总之非常强大

经常使用的Swagger注解，看看`SpringFox的和SpringDoc`的有啥区别，毕竟对比已学过的技术能更快掌握新技术;
![](swagger3入门.assets/758b4c34479a41778ff3e9210caa55f1~tplv-k3u1fbpfcp-zoom-in-crop-mark_1512_0_0_0.awebp)

# 三、 使用步骤

## 1.导入依赖

```xml

<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-ui</artifactId>
    <version>1.5.10</version>
</dependency>
```

## 2.添加配置类

进行SpringDoc的配置，使用`OpenAPI`来配置基础的文档信息，通过`GroupedOpenApi`配置分组的API文档，SpringDoc支持直接使用接口路径进行配置。

```java

@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(new Info()
                .title("代码平台 API")
                .description("SpringDoc API 演示")
                .version("v1.0.0")
        );
    }

    @Bean
    public GroupedOpenApi adminApi() {
        return GroupedOpenApi.builder()
                .group("普通接口文档")
                .pathsToMatch("/web/test/**").build();
    }

    @Bean
    public GroupedOpenApi homeApi() {
        return GroupedOpenApi.builder()
                .group("首页相关接口")
                .pathsToMatch("/query/excel/**")
                .build();
    }
}
```

## 3.常用注解

### 1.@Tag注解

用来描述一组操作的信息（通常用在controller控制层类上）

![](swagger3入门.assets/67d37e6e3f2442ab928c847b1109fdb0~tplv-k3u1fbpfcp-zoom-in-crop-mark_1512_0_0_0.awebp)

例：

```java

@Tag(name = "控制器")
@RestController
@RequestMapping("web/test")
public class TestController {
}
```

### 2.@Operation注解

用来描述接口信息（通常用在控制层的具体方法上 ）

![](swagger3入门.assets/a28c34d7e2f44be597f10bb4d5e5bc5e~tplv-k3u1fbpfcp-zoom-in-crop-mark_1512_0_0_0.awebp)

例：

```java

@Tag(name = "控制器")
@RestController
@RequestMapping("web/test")
public class TestController {

    @Operation(summary = "测试")
    @RostMapping("test")
    public ActionResult getUserInfo(String name) {
        return ActionResult.success(name + "成功");
    }
}
```

### 3.@Schema注解

该注解用来定义模型及模型的属性（可以用在dto/vo以及其属性上）  
![](swagger3入门.assets/d99f50661cc74d2ab2c6193770655834~tplv-k3u1fbpfcp-zoom-in-crop-mark_1512_0_0_0.awebp)

例：

```java

@Data
@Schema(name = "测试")
public class ZnjExpertConsultFastLanguageInsertDTO {

    @Schema(description = "主键id")
    private Integer id;
}
```

# 四、页面访问

> ```
> http://ip:port/swagger-ui/index.html
> ```

![](swagger3入门.assets/4ffedaf301894c2d89bb25a258f710fb~tplv-k3u1fbpfcp-zoom-in-crop-mark_1512_0_0_0.awebp)

# 五、常用配置

~~SpringDoc还有一些常用的配置可以了解下。~~
与示例项目版本不一致，仅做参考，默认开箱即用，无需另配置
```yaml
springdoc:
  swagger-ui:
    # 修改Swagger UI路径
    path: /swagger-ui.html
    # 开启Swagger UI界面
    enabled: true
  api-docs:
    # 修改api-docs路径
    path: /v3/api-docs
    # 开启api-docs
    enabled: true
  # 配置需要生成接口文档的扫描包
  packages-to-scan: cn.com.datafort
  # 配置不同组
  group-configs:
    - group: 'default'
      paths-to-match: '/**'
      packages-to-scan: cn.com.datafort
```