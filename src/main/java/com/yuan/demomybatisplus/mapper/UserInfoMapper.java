package com.yuan.demomybatisplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yuan.demomybatisplus.entity.UserInfoEntity;
import com.yuan.demomybatisplus.entityView.UserInfoVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;



/**
 * @author MuXue
 * @create 2022-07-11  14:39 PM
 */
@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfoEntity> {

    UserInfoVo getUserInfo(@Param("userName") String userName);
}
