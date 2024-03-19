package cn.datafort.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

public interface ProductVo {
    
    @Data
    @Schema(title="产品类型", description="产品类型")
    class AmProduct implements Serializable {
        
        private static final long serialVersionUID = 1L;
        
        @Schema(description  = "主键")
        private Integer id;
        
        @Schema(description  = "产品名称")
        private String name;
        
        @Schema(description  = "1 需要选择引擎数量 2 不需要")
        private Integer isEngine;
        
        @Schema(description  = "描述")
        private String description;
    }
}
