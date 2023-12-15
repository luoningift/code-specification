package module.datafort.user.dao;

import module.datafort.user.model.entity.MdUserTestEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface MdUserTestDao extends BaseMapper<MdUserTestEntity> {

}
