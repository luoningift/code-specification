package plugin.datafort.afw.trx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import plugin.datafort.afw.trx.entity.TrxTestEntity;
import plugin.datafort.afw.trx.service.TrxTestService;

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
