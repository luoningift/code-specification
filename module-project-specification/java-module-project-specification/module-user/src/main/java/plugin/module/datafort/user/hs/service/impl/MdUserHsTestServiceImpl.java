package plugin.module.datafort.user.hs.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import plugin.module.datafort.user.hs.dao.MdUserHsTestDao;
import plugin.module.datafort.user.hs.entity.MdUserHsTestEntity;
import plugin.module.datafort.user.hs.service.MdUserHsTestService;

@Service
public class MdUserHsTestServiceImpl implements MdUserHsTestService {
    
    @Autowired
    private MdUserHsTestDao testDao;
    
    @Override
    public MdUserHsTestEntity getInfo() {
        
        final LambdaQueryWrapper<MdUserHsTestEntity> query = new LambdaQueryWrapper<>();
        query.eq(MdUserHsTestEntity::getId, 2);
        return testDao.selectList(query).get(0);
    }
}
