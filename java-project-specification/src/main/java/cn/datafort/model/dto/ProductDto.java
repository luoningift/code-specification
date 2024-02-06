package cn.datafort.model.dto;

import cn.datafort.constant.ProductEngineConst;
import cn.datafort.constant.RepStatusEnum;
import cn.datafort.exception.BusinessException;
import cn.datafort.web.common.validation.ObjectFields;
import cn.datafort.web.common.validation.ObjectFieldsValidatorInterface;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.group.GroupSequenceProvider;

import javax.validation.GroupSequence;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.groups.Default;
import java.io.Serializable;

public interface ProductDto {
    
    @Data
    @ObjectFields(groups = ObjectFields.class)
    @GroupSequence({AmProduct.class, ObjectFields.class})
    @ApiModel(value="产品类型请求体", description="产品类型")
    class AmProduct implements Serializable, ObjectFieldsValidatorInterface {
        
        private static final long serialVersionUID = 1L;
        
        @ApiModelProperty(value = "主键")
        @Min(value = 10, message = "最小值为10")
        @Max(value = 20, message = "最大值为20")
        @Range(max = 15, min = 12, message = "不符合数据范围")
        private Integer id;
        
        @ApiModelProperty(value = "产品名称")
        private String name;
        
        @ApiModelProperty(value = "1 需要选择引擎数量 2 不需要")
        private Integer isEngine;
        
        @ApiModelProperty(value = "描述")
        private String description;
        
        @Override
        public void multiFieldsValidator() {
            
            if (name == null || name.length() <= 0 || name.length() > 10) {
                throw new BusinessException(RepStatusEnum.PARAM_CHECK_FAIL.getStatus(), "产品名称不能为空且不能超过10个字");
            }
            boolean engineFlag = false;
            for (ProductEngineConst value : ProductEngineConst.values()) {
                if (value.getCode() == isEngine) {
                    engineFlag = true;
                    break;
                }
            }
            if (!engineFlag) {
                throw new BusinessException(RepStatusEnum.PARAM_CHECK_FAIL.getStatus(), "未选择是否需要选择引擎");
            }
    
            if (description == null || description.length() <= 0 || description.length() > 10) {
                throw new BusinessException(RepStatusEnum.PARAM_CHECK_FAIL.getStatus(), "描述信息不能为空且不能超过10个字");
            }
        }
    }
}
