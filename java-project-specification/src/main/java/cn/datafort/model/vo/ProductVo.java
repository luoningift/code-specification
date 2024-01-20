package cn.datafort.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

public interface ProductVo {
    
    @Data
    @ApiModel(value="产品类型", description="产品类型")
    class AmProduct implements Serializable {
        
        private static final long serialVersionUID = 1L;
        
        @ApiModelProperty(value = "主键")
        private Integer id;
        
        @ApiModelProperty(value = "产品名称")
        private String name;
        
        @ApiModelProperty(value = "1 需要选择引擎数量 2 不需要")
        private Integer isEngine;
        
        @ApiModelProperty(value = "描述")
        private String description;
    }
}
