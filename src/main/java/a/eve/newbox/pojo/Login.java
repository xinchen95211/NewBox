package a.eve.newbox.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 登陆密码表
 * 	password 登陆密码
 * 	createTime 创建时间
 * 	updateTime 修改时间
 */
@Data
//@Entity
@TableName("login")
public class Login {
//    @Id
    @TableId
    private Long id;


    private String loginpassword;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}
