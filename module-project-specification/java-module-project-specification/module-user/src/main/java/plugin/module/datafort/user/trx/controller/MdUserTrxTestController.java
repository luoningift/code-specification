package plugin.module.datafort.user.trx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import plugin.module.datafort.user.trx.entity.MdUserTrxTestEntity;
import plugin.module.datafort.user.trx.service.MdUserTrxTestService;

@Controller
@ResponseBody
@RequestMapping(value = "/trx/user/test/")
public class MdUserTrxTestController {
    
    @Autowired
    MdUserTrxTestService mdUserTrxTestService;
    
    @RequestMapping(value = "getList", method = RequestMethod.GET)
    public MdUserTrxTestEntity getDbList() {
        MdUserTrxTestEntity mdUserTrxTestEntity = mdUserTrxTestService.getInfo();
        return mdUserTrxTestEntity;
    }
}
