package module.datafort.user.module.dao;

import module.datafort.user.module.model.entity.TestEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface TestDao extends BaseMapper<TestEntity> {

}
