package cn.datafort.service.impl;

import cn.datafort.dao.AmProductDao;
import cn.datafort.model.ObjectMapper;
import cn.datafort.model.dto.ProductDto;
import cn.datafort.model.entity.AmProduct;
import cn.datafort.model.entity.Test;
import cn.datafort.model.vo.ProductVo;
import cn.datafort.service.ProductService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    
    @Autowired
    private AmProductDao amProductDao;
    
    @Override
    public void add(ProductDto.AmProduct product) {
    
        final AmProduct amProduct = ObjectMapper.mapper.toAmProduct(product);
        amProductDao.insert(amProduct);
    }
    
    @Override
    public List<ProductVo.AmProduct> search(String name) {
    
        final LambdaQueryWrapper<AmProduct> query = new LambdaQueryWrapper<>();
        query.like(AmProduct::getName, name);
        final List<AmProduct> amProducts = amProductDao.selectList(query);
        if (amProducts == null || amProducts.size() == 0) {
            return new ArrayList<>(0);
        }
        final List<ProductVo.AmProduct> amProductVoList = ObjectMapper.mapper.toAmProductVo(amProducts);
        return amProductVoList;
    }
}
