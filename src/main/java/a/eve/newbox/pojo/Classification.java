package a.eve.newbox.pojo;

import com.baomidou.mybatisplus.annotation.*;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 分类表
 * 	Classification
 * 		id 唯一id
 * 		name 分类名称
 * 		isDel 逻辑删除
 * 		createTime 创建时间
 * 		updateTime 修改时间
 */
@Data
//@Entity
@TableName("classification")
public class Classification {

//    @Id
    @TableId
    private Long id;

    private String name;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableLogic
    private Integer isDel = 0;
}
