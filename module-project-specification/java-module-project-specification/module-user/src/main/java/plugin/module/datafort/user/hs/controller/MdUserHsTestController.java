package plugin.module.datafort.user.hs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import plugin.module.datafort.user.hs.entity.MdUserHsTestEntity;
import plugin.module.datafort.user.hs.service.MdUserHsTestService;

@Controller
@ResponseBody
@RequestMapping(value = "/hs/user/test/")
public class MdUserHsTestController {
    
    @Autowired
    MdUserHsTestService testService;
    
    @RequestMapping(value = "getList", method = RequestMethod.GET)
    public MdUserHsTestEntity getDbList() {
        MdUserHsTestEntity testEntity = testService.getInfo();
        return testEntity;
    }
}
