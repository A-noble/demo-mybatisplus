package com.yuan.demomybatisplus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuan.demomybatisplus.annotate.CacheRedis;
import com.yuan.demomybatisplus.entity.UserInfoEntity;
import com.yuan.demomybatisplus.entityView.UserInfoVo;
import com.yuan.demomybatisplus.mapper.UserInfoMapper;
import com.yuan.demomybatisplus.service.UserInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author MuXue
 * @create 2022-07-12  19:55 PM
 */

@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfoEntity> implements UserInfoService {
    @Resource
    UserInfoMapper userInfoMapper;

    @Override
    @CacheRedis(prefix = "user",element = {"userName"},deadline = 120)
    public UserInfoVo getUserInfo(String userName) {
        return userInfoMapper.getUserInfo(userName);
    }
}
