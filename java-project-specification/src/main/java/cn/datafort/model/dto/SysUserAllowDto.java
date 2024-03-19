package cn.datafort.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author 胡鑫虎
 * @version 1.0
 * @date 2023/12/20 11:34
 */
@Data
public class SysUserAllowDto {
    @Schema(description  = "用户id", example = "1")
    private Long userId;

    @Schema(description  = "range属性", allowableValues = "range[0, 100]", example = "1")
    private String name;

    @Schema(description  = "状态", allowableValues = "0,1,2", example = "1")
    private Integer status;

    @Schema(description  = "状态枚举", example = "success")
    private StatusEnumDto statusEnumDto;

    @Schema(description  = "部门id", example = "123")
    private Long depId;
}
