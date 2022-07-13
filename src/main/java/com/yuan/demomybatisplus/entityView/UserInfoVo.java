package com.yuan.demomybatisplus.entityView;

import lombok.Data;


/**
 * @author MuXue
 * @create 2022-07-11  22:40 PM
 */
@Data
public class UserInfoVo {
    private String cuid;
    private String userName;
    private Integer userAge;
    private String skill;
    private String evaluate;
    private Long fraction;
}
