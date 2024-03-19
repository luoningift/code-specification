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
 * 供应商信息表
 * </p>
 *
 * @author cn.datafort
 * @since 2024-01-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("am_supplier")
@Schema(title="AmSupplier对象", description="供应商信息表")
public class AmSupplier implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description  = "设备ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description  = "供应商名称")
    @TableField("name")
    private String name;

    @Schema(description  = "描述")
    @TableField("description")
    private String description;

    @Schema(description  = "1 正常 2 删除")
    @TableField("status")
    private Integer status;

    @Schema(description  = "创建时间")
    @TableField("created_at")
    private LocalDateTime createdAt;

    @Schema(description  = "更新时间")
    @TableField("updated_at")
    private LocalDateTime updatedAt;


}
