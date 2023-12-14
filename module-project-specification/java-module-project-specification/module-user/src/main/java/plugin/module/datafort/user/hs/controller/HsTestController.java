package plugin.module.datafort.user.hs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import plugin.module.datafort.user.hs.entity.HsTestEntity;
import plugin.module.datafort.user.hs.service.HsTestService;

@Controller
@ResponseBody
@RequestMapping(value = "/hs/test/")
public class HsTestController {
    
    @Autowired
    HsTestService testService;
    
    @RequestMapping(value = "getList", method = RequestMethod.GET)
    public HsTestEntity getDbList() {
        HsTestEntity testEntity = testService.getInfo();
        return testEntity;
    }
}
