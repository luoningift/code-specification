package cn.datafort.service.impl;

import cn.datafort.dao.TestDao;
import cn.datafort.model.entity.Test;
import cn.datafort.service.TestService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {
    
    @Autowired
    private TestDao testDao;
    
    @Override
    public Test getInfo() {
        
        final LambdaQueryWrapper<Test> query = new LambdaQueryWrapper<>();
        query.eq(Test::getId, 1);
        return testDao.selectList(query).get(0);
    }
}
