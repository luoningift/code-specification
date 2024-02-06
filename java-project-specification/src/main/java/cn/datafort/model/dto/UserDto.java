package cn.datafort.model.dto;

import cn.datafort.web.common.validation.ObjectFields;
import cn.datafort.web.common.validation.ObjectFieldsValidatorInterface;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Range;

import javax.validation.GroupSequence;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="用户", description="用户")
@ObjectFields(groups = ObjectFields.class)
@GroupSequence({UserDto.class, ObjectFields.class})
public class UserDto extends BasePageInfoDto implements ObjectFieldsValidatorInterface {
    
    @ApiModelProperty(value = "id", example = "1")
    @Min(value = 10, message = "最小值为10")
    @Max(value = 20, message = "最大值为20")
    @Range(max = 15, min = 12, message = "不符合数据范围")
    private Integer id;
    
    public void multiFieldsValidator () {
        
        System.out.println("xxxxx");
        //throw new BusinessException(RepStatusEnum.PARAM_CHECK_FAIL.getStatus(), "产品名称不能为空且不能超过10个字");
        super.multiFieldsValidator();
    }
}
