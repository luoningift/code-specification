package plugin.module.datafort.user.trx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import plugin.module.datafort.user.trx.dao.MdUserTrxTestDao;
import plugin.module.datafort.user.trx.entity.MdUserTrxTestEntity;
import plugin.module.datafort.user.trx.service.MdUserTrxTestService;

@Service
public class MdUserTrxTestServiceImpl implements MdUserTrxTestService {
    
    @Autowired
    private MdUserTrxTestDao mdUserTrxTestDao;
    
    @Override
    public MdUserTrxTestEntity getInfo() {
        
        final LambdaQueryWrapper<MdUserTrxTestEntity> query = new LambdaQueryWrapper<>();
        query.eq(MdUserTrxTestEntity::getId, 3);
        return mdUserTrxTestDao.selectList(query).get(0);
    }
}
