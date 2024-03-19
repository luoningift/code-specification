package cn.datafort.controller;

import cn.datafort.model.dto.TestDto;
import cn.datafort.model.entity.Test;
import cn.datafort.service.TestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Tag(name = "参数验证")
@Controller
@ResponseBody
@RequestMapping(value = "/test/")
public class TestController {
    
    @Autowired
    TestService testService;
    
    @RequestMapping(value = "getList", method = RequestMethod.GET)
    public Test getDbList() {
        Test test = testService.getInfo();
        return test;
    }
    
    @Operation(summary = "post参数验证测试")
    @PostMapping("/post")
    public ResponseEntity<String> allowResponseEntity(@RequestBody @Validated TestDto testDto){
        
        return ResponseEntity.ok("success");
    }
}
