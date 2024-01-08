package cn.datafort.controller;

import cn.datafort.model.entity.TestDto;
import cn.datafort.model.entity.TestEntity;
import cn.datafort.service.TestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Api(tags = "参数验证")
@Controller
@ResponseBody
@RequestMapping(value = "/test/")
public class TestController {
    
    @Autowired
    TestService testService;
    
    @RequestMapping(value = "getList", method = RequestMethod.GET)
    public TestEntity getDbList() {
        TestEntity testEntity = testService.getInfo();
        return testEntity;
    }
    
    @ApiOperation(value = "post参数验证测试")
    @PostMapping("/post")
    public ResponseEntity<String> allowResponseEntity(@RequestBody @Validated TestDto testDto){
        
        return ResponseEntity.ok("success");
    }
}
