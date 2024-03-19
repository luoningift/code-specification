package cn.datafort.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 产品类型
 * </p>
 *
 * @author cn.datafort
 * @since 2024-01-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("am_product")
@Schema(title="AmProduct对象", description="产品类型")
public class AmProduct implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description  = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description  = "产品名称")
    @TableField("name")
    private String name;

    @Schema(description  = "1 需要选择引擎数量 2 不需要")
    @TableField("is_engine")
    private Integer isEngine;

    @Schema(description  = "1 正常 2 删除")
    @TableField("status")
    private Integer status;

    @Schema(description  = "描述")
    @TableField("description")
    private String description;

    @Schema(description  = "创建时间")
    @TableField("created_at")
    private LocalDateTime createdAt;

    @Schema(description  = "更新时间")
    @TableField("updated_at")
    private LocalDateTime updatedAt;

}
