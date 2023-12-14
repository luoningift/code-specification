package module.datafort.user.module.controller;

import module.datafort.user.module.model.entity.TestEntity;
import module.datafort.user.module.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
}
