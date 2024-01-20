package cn.datafort.model;

import cn.datafort.model.dto.ProductDto;
import cn.datafort.model.entity.AmProduct;
import cn.datafort.model.vo.ProductVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ObjectMapper {
    
    ObjectMapper mapper = Mappers.getMapper(ObjectMapper.class);
    
    AmProduct toAmProduct(ProductDto.AmProduct amProduct);
    ProductVo.AmProduct toAmProductVo(AmProduct amProduct);
    List<ProductVo.AmProduct> toAmProductVo(List<AmProduct> amProduct);
}
