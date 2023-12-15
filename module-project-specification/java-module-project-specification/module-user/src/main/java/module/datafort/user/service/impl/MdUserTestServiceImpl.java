package module.datafort.user.service.impl;

import module.datafort.user.dao.MdUserTestDao;
import module.datafort.user.model.entity.MdUserTestEntity;
import module.datafort.user.service.MdUserTestService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MdUserTestServiceImpl implements MdUserTestService {
    
    @Autowired
    private MdUserTestDao mdUserTestDao;
    
    @Override
    public MdUserTestEntity getInfo() {
        
        final LambdaQueryWrapper<MdUserTestEntity> query = new LambdaQueryWrapper<>();
        query.eq(MdUserTestEntity::getId, 1);
        return mdUserTestDao.selectList(query).get(0);
    }
}
