package cn.datafort.model.dto;

import cn.datafort.web.common.validation.ObjectFields;
import cn.datafort.web.common.validation.ObjectFieldsValidatorInterface;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@ObjectFields
public class TestDto implements ObjectFieldsValidatorInterface {
    
    @NotNull(message = "不能为空")
    @ApiModelProperty(value = "最小值", example = "1")
    private Integer min;
    
    @NotNull(message = "不能为空")
    @ApiModelProperty(value = "最大值", example = "1")
    private Integer max;
    
    @NotNull(message = "列表不能为空")
    @Valid
    private List<TestChildDto> testChildDtoList;
    
    @Override
    public void multiFieldsValidator() {
        
        if (min == null || max == null) {
            throw new RuntimeException("参数不能为空");
        }
        if (min > max) {
            throw new RuntimeException("最大值不能小于最小值");
        }
    }
}
