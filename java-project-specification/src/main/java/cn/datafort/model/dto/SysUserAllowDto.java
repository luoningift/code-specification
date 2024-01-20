package cn.datafort.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author 胡鑫虎
 * @version 1.0
 * @date 2023/12/20 11:34
 */
@Data
public class SysUserAllowDto {
    @ApiModelProperty(value = "用户id", example = "1")
    private Long userId;

    @ApiModelProperty(value = "range属性", allowableValues = "range[0, 100]", example = "1")
    private String name;

    @ApiModelProperty(value = "状态", allowableValues = "0,1,2", example = "1")
    private Integer status;

    @ApiModelProperty(value = "状态枚举", example = "success")
    private StatusEnumDto statusEnumDto;

    @ApiModelProperty(value = "部门id", example = "123")
    private Long depId;
}
