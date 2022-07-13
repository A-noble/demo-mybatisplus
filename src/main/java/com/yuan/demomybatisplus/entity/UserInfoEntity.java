package com.yuan.demomybatisplus.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author MuXue
 * @create 2022-07-11  14:33 PM
 */
@Data
@TableName("user_info")
public class UserInfoEntity {
    private String cuid;
    private String userName;
    private Integer userAge;
    private String skill;
    private String evaluate;
    private Long fraction;
}
