package plugin.module.datafort.user.trx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import plugin.module.datafort.user.trx.dao.TrxTestDao;
import plugin.module.datafort.user.trx.entity.TrxTestEntity;
import plugin.module.datafort.user.trx.service.TrxTestService;

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
