package plugin.module.datafort.user.trx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import plugin.module.datafort.user.trx.entity.TrxTestEntity;
import plugin.module.datafort.user.trx.service.TrxTestService;

@Controller
@ResponseBody
@RequestMapping(value = "/trx/test/")
public class TrxTestController {
    
    @Autowired
    TrxTestService trxTestService;
    
    @RequestMapping(value = "getList", method = RequestMethod.GET)
    public TrxTestEntity getDbList() {
        TrxTestEntity trxTestEntity = trxTestService.getInfo();
        return trxTestEntity;
    }
}
