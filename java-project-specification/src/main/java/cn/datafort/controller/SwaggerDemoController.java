package cn.datafort.controller;

import cn.datafort.model.dto.SysUserAllowDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.enums.ParameterStyle;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author 胡鑫虎
 * @version 1.0
 * @date 2023/12/20 11:34
 */
@Slf4j
@Tag(name = "示例controller")
@RestController
@RequestMapping(value = "/demo",produces = MediaType.APPLICATION_JSON_VALUE)
public class SwaggerDemoController {
    @Operation(summary = "文件上传-带参数Header")
    @Parameters({
            @Parameter(name = "token", description = "请求token", required = true, in = ParameterIn.HEADER),
            @Parameter(name = "file", description = "文件", required = true, style = ParameterStyle.FORM),
            @Parameter(name = "name", description = "文件名称", required = true),
    })
    @PostMapping("/uploadParamHeader")
    public ResponseEntity<String> uploadParamHeader(@RequestHeader("token") String token, @RequestParam("file") MultipartFile[] files, @RequestParam("name") String name){
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("token:").append(token);
        for (MultipartFile file:files){
            stringBuilder.append("file:"+file.getOriginalFilename());
        }
        stringBuilder.append("name:").append(name);
        return ResponseEntity.ok(stringBuilder.toString());
    }
    @Operation(summary = "allow-示例")
    @PostMapping("/allow")
    public ResponseEntity<SysUserAllowDto> allowResponseEntity(SysUserAllowDto sysUserAllowDto){
        return ResponseEntity.ok(sysUserAllowDto);
    }

    @Operation(summary = "allow-示例2")
    @PostMapping("/allow2")
    public ResponseEntity<SysUserAllowDto> allowResponseEntity2(@RequestBody SysUserAllowDto sysUserAllowDto){
        return ResponseEntity.ok(sysUserAllowDto);
    }

    @Operation(summary = "动态参数")
    @Parameters({
            @Parameter(name = "name",description = "名称",example = "张飞",schema =@Schema(type = "String")),
            @Parameter(name = "money",description = "金钱",example = "123",schema =@Schema(type = "integer(int64)")),
            @Parameter(name = "age",description = "年龄",example = "22",schema =@Schema(type = "integer(int32)"))
    })
    @PostMapping("/params")
    public ResponseEntity<Map<String,String>> params(@Parameter(hidden = true) @RequestParam Map<String,String> value){
        return ResponseEntity.ok(value);
    }

    @Operation(summary = "path中的参数说明demo")
    @Parameter(name = "email",description = "邮箱地址",example = "123@foxmail.com",in = ParameterIn.PATH)
    @GetMapping("/test/{email}")
    public ResponseEntity<String> email(@PathVariable("email") String email){
        return ResponseEntity.ok("email:"+email);
    }
}
