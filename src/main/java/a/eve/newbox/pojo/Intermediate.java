package a.eve.newbox.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 中间表
 * 	Intermediate
 * 		userDataID 密码数据表的id
 * 		classificationID 对应的分类表id
 */
@Data
//@Entity
@TableName("intermediate")
public class Intermediate {
//    @Id
    private Long id;

    @TableField("userdataid")
    private Long userDataID;
    @TableField("classificationid")
    private Long classificationID;
}
