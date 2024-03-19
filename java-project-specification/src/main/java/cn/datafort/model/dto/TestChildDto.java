package cn.datafort.model.dto;

import cn.datafort.web.common.validation.ObjectFields;
import cn.datafort.web.common.validation.ObjectFieldsValidatorInterface;
import io.swagger.v3.oas.annotations.media.Schema;
import javafx.scene.Parent;
import lombok.Data;

import javax.validation.ConstraintValidatorContext;
import javax.validation.constraints.NotNull;

@Data
@ObjectFields
public class TestChildDto implements ObjectFieldsValidatorInterface {
    
    @NotNull(message = "不能为空")
    @Schema(description = "最小值", example = "1")
    private Integer min;
    
    @NotNull(message = "不能为空")
    @Schema(description = "最大值", example = "1")
    private Integer max;
    
    @Override
    public void multiFieldsValidator() {
        
        if (min == null || max == null) {
            throw new RuntimeException("MIN 和 MAX 不能为空");
        }
        if (min > max) {
            throw new RuntimeException("MAX 不能小于 MIN");
        }
    }
}
