package plugin.datafort.afw.trx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import plugin.datafort.afw.trx.dao.TrxTestDao;
import plugin.datafort.afw.trx.entity.TrxTestEntity;
import plugin.datafort.afw.trx.service.TrxTestService;

@Service
public class TrxTestServiceImpl implements TrxTestService {
    
    @Autowired
    private TrxTestDao trxTestDao;
    
    @Override
    public TrxTestEntity getInfo() {
        
        final LambdaQueryWrapper<TrxTestEntity> query = new LambdaQueryWrapper<>();
        query.eq(TrxTestEntity::getId, 3);
        return trxTestDao.selectList(query).get(0);
    }
}
