package cn.datafort.model.entity;

import cn.datafort.web.common.validation.ObjectFields;
import cn.datafort.web.common.validation.ObjectFieldsCheckInterface;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.Data;

import javax.validation.ConstraintValidatorContext;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@ObjectFields
@Data
public class TestDto implements ObjectFieldsCheckInterface {
    
    @NotNull(message = "不能为空")
    @ApiModelProperty(value = "最小值", example = "1")
    private Integer min;
    
    @NotNull(message = "不能为空")
    @ApiModelProperty(value = "最大值", example = "1")
    private Integer max;
    
    @NotNull(message = "列表不能为空")
    @Valid
    private List<TestChildDto> testChildDtoList;
    
    public boolean multiFieldsValidator(ConstraintValidatorContext context) {
        
        if (min == null || max == null) {
            throw new RuntimeException("参数不能为空");
        }
        if (min > max) {
            throw new RuntimeException("最大值不能小于最小值");
        }
        return true;
    }
}
