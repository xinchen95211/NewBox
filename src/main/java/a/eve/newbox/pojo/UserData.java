package a.eve.newbox.pojo;


import com.baomidou.mybatisplus.annotation.*;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * 密码数据表
 * 	 UserData
 * 		id 唯一id
 * 		name 名称
 * 		account 账户
 * 		password 密码
 * 		notes 备注
 * 		createTime 创建时间
 * 		updateTime 修改时间
 * 	  	isDel 逻辑删除
 */
@Data
//@Entity
@TableName("userdata")
public class UserData {
//    @Id
    @TableId
    private Long id;

    private String name;

    private String account;


    private String password;

    private String notes;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;


    @TableLogic
    private Integer isDel = 0;
}
