package plugin.module.datafort.user.hs.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import plugin.module.datafort.user.hs.entity.MdUserHsTestEntity;

@Repository
@Mapper
public interface MdUserHsTestDao extends BaseMapper<MdUserHsTestEntity> {

}
