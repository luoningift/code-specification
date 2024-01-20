package cn.datafort.model.dto;

import cn.datafort.web.common.validation.ObjectFields;
import cn.datafort.web.common.validation.ObjectFieldsValidatorInterface;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.ConstraintValidatorContext;
import javax.validation.constraints.NotNull;

@ObjectFields
@Data
public class TestChildDto implements ObjectFieldsValidatorInterface {
    
    @NotNull(message = "不能为空")
    @ApiModelProperty(value = "最小值", example = "1")
    private Integer min;
    
    @NotNull(message = "不能为空")
    @ApiModelProperty(value = "最大值", example = "1")
    private Integer max;
    
    public void multiFieldsValidator() {
        
        if (min == null || max == null) {
            throw new RuntimeException("MIN 和 MAX 不能为空");
        }
        if (min > max) {
            throw new RuntimeException("MAX 不能小于 MIN");
        }
    }
}
