package plugin.module.datafort.user.hs.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import plugin.module.datafort.user.hs.entity.HsTestEntity;

@Repository
@Mapper
public interface HsTestDao extends BaseMapper<HsTestEntity> {

}
