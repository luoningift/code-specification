package plugin.module.datafort.user.hs.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableName("test")
public class MdUserHsTestEntity {
    
    @TableId(value = "id", type = IdType.AUTO)
    private int id;
    
    @TableField("info")
    private String info;
}
