package com.yuan.demomybatisplus.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author MuXue
 * @create 2022-07-11  14:33 PM
 */
@Data
@TableName("user_info")
public class UserInfoEntity implements Serializable {
    @TableId("CUID")
    private String cuid;
    private String userName;
    private Integer userAge;
    private String skill;
    private String evaluate;
    private Long fraction;
}
