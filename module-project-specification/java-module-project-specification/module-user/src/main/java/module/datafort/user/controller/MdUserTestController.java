package module.datafort.user.controller;

import module.datafort.user.model.entity.MdUserTestEntity;
import module.datafort.user.service.MdUserTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@RequestMapping(value = "/user/test/")
public class MdUserTestController {
    
    @Autowired
    MdUserTestService testService;
    
    @RequestMapping(value = "getList", method = RequestMethod.GET)
    public MdUserTestEntity getDbList() {
        MdUserTestEntity testEntity = testService.getInfo();
        return testEntity;
    }
}
