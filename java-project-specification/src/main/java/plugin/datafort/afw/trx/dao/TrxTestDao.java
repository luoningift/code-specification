package plugin.datafort.afw.trx.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import plugin.datafort.afw.trx.entity.TrxTestEntity;

@Repository
@Mapper
public interface TrxTestDao extends BaseMapper<TrxTestEntity> {

}
