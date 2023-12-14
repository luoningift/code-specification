package plugin.datafort.afw.hs.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import plugin.datafort.afw.hs.dao.HsTestDao;
import plugin.datafort.afw.hs.entity.HsTestEntity;
import plugin.datafort.afw.hs.service.HsTestService;

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
