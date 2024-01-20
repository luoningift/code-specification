package cn.datafort.service;

import cn.datafort.model.dto.ProductDto;
import cn.datafort.model.vo.ProductVo;

import java.util.List;

public interface ProductService {
    
    void add(ProductDto.AmProduct product);
    
    List<ProductVo.AmProduct> search(String name);
}
