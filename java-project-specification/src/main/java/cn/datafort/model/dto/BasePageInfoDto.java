package cn.datafort.model.dto;

import cn.datafort.constant.RepStatusEnum;
import cn.datafort.exception.BusinessException;
import cn.datafort.web.common.validation.ObjectFields;
import cn.datafort.web.common.validation.ObjectFieldsValidatorInterface;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.GroupSequence;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
@Schema(title="分页", description="分页")
@ObjectFields(groups = ObjectFields.class)
@GroupSequence({BasePageInfoDto.class, ObjectFields.class})
public class BasePageInfoDto implements ObjectFieldsValidatorInterface {
    
    @Schema(description  = "页数", example = "1")
    @Min(value = 10, message = "最小值为10")
    @Max(value = 20, message = "最大值为20")
    @Range(max = 15, min = 12, message = "不符合数据范围")
    private Integer page;
    
    @Schema(description  = "每页条目数", example = "1")
    @Min(value = 10, message = "最小值为10")
    @Max(value = 20, message = "最大值为20")
    @Range(max = 15, min = 12, message = "不符合数据范围")
    private Integer limit;
    
    public void multiFieldsValidator () {
        System.out.println("mmmmm");
        throw new BusinessException(RepStatusEnum.PARAM_CHECK_FAIL.getStatus(), "xxxxx");
    }
}
