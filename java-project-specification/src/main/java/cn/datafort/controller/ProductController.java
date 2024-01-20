package cn.datafort.controller;

import cn.datafort.constant.RepVo;
import cn.datafort.model.dto.ProductDto;
import cn.datafort.model.vo.ProductVo;
import cn.datafort.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@Slf4j
@Api(tags = "代码规范例子代码-产品管理")
@RestController
@RequestMapping(value = "/product",produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class ProductController {
    
    @Autowired
    private ProductService productService;
    
    @ApiOperation(value = "添加产品")
    @PostMapping("/add")
    public RepVo<Object> allowResponseEntity(@RequestBody @Validated ProductDto.AmProduct product){
        
        productService.add(product);
        return RepVo.SUCCESS();
    }
    
    @ApiOperation(value = "获取产品列表")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "name",value = "产品名称",required = true)
    )
    @GetMapping("/search")
    public RepVo<List<ProductVo.AmProduct>> get(@RequestParam("name") @NotNull @Length(min = 2, max = 10, message = "产品名称不能为空且字数范围为2到10") String name){
        
        return new RepVo<>(productService.search(name));
    }
}
