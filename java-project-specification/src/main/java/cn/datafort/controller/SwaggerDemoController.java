package cn.datafort.controller;

import cn.datafort.model.entity.SysUserAllow;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author 胡鑫虎
 * @version 1.0
 * @date 2023/12/20 11:34
 */
@Slf4j
@Api(tags = "示例controller")
@RestController
@RequestMapping(value = "/demo",produces = MediaType.APPLICATION_JSON_VALUE)
public class SwaggerDemoController {

    @ApiOperation(value = "批量上传文件")
    @PostMapping("/batchUpload")
    @ApiImplicitParam(name = "files",value = "文件",required = true,paramType = "form",allowMultiple = true,dataType = "MultipartFile")
    public ResponseEntity<String> uploadBatch(@RequestPart("files")List<MultipartFile> files){

        return ResponseEntity.ok(files.stream().map(MultipartFile::getOriginalFilename).collect(Collectors.joining()));
    }

    @ApiOperation(value = "文件上传-带参数Header")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token",value = "请求token",required = true,paramType = "header"),
            @ApiImplicitParam(name = "file",value = "文件",required = true,paramType = "form",allowMultiple = true,dataType = "__File"),
            @ApiImplicitParam(name = "name",value = "文件名称",required = true),
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

    @ApiOperation(value = "文件上传-带参数Header")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file",value = "文件",required = true,paramType = "form",dataType = "__File"),
            @ApiImplicitParam(name = "name",value = "文件名称",required = true),
    })
    @PostMapping("/uploadParamHeader1")
    public ResponseEntity<String> uploadParamHeader1(@RequestParam("file") MultipartFile file, @RequestParam("name") String name){
        String stringBuilder = "token:" +
                "file:" + file.getOriginalFilename() +
                "name:" + name;
        return ResponseEntity.ok(stringBuilder);
    }

    @ApiOperation(value = "allow-示例")
    @PostMapping("/allow")
    public ResponseEntity<SysUserAllow> allowResponseEntity(SysUserAllow sysUserAllow){
        return ResponseEntity.ok(sysUserAllow);
    }

    @ApiOperation(value = "allow-示例2")
    @PostMapping("/allow2")
    public ResponseEntity<SysUserAllow> allowResponseEntity2(@RequestBody SysUserAllow sysUserAllow){
        return ResponseEntity.ok(sysUserAllow);
    }

    @ApiOperation(value = "动态参数")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name",value = "名称",example = "张飞",dataTypeClass = String.class),
            @ApiImplicitParam(name = "money",value = "金钱",example = "123",dataTypeClass = Long.class),
            @ApiImplicitParam(name = "age",value = "年龄",example = "22",dataTypeClass = Integer.class)
    })
    @PostMapping("/params")
    public ResponseEntity<Map<String,String>> params(@ApiIgnore @RequestParam Map<String,String> value){
        return ResponseEntity.ok(value);
    }

    @ApiOperation(value = "path中的参数说明demo")
    @ApiImplicitParam(name = "email",value = "邮箱地址",defaultValue = "123@foxmail.com",paramType = "path")
    @GetMapping("/test/{email}")
    public ResponseEntity<String> email(@PathVariable("email") String email){
        return ResponseEntity.ok("email:"+email);
    }
}
