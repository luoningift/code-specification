package plugin.module.datafort.user.hs.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import plugin.module.datafort.user.hs.dao.HsTestDao;
import plugin.module.datafort.user.hs.entity.HsTestEntity;
import plugin.module.datafort.user.hs.service.HsTestService;

@Service
public class HsTestServiceImpl implements HsTestService {
    
    @Autowired
    private HsTestDao testDao;
    
    @Override
    public HsTestEntity getInfo() {
        
        final LambdaQueryWrapper<HsTestEntity> query = new LambdaQueryWrapper<>();
        query.eq(HsTestEntity::getId, 2);
        return testDao.selectList(query).get(0);
    }
}
