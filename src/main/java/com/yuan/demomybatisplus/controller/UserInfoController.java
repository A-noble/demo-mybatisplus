package com.yuan.demomybatisplus.controller;


import com.yuan.demomybatisplus.entityView.UserInfoVo;
import com.yuan.demomybatisplus.entityView.result.ResultVO;
import com.yuan.demomybatisplus.service.UserInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;


/**
 * @author MuXue
 * @create 2022-07-11  16:48 PM
 */
@Controller
@ResponseBody
@RequestMapping("/user")
public class UserInfoController {
    @Resource
    UserInfoService userInfoService;

    @GetMapping("/getUserInfo")
    public ResultVO<UserInfoVo> getUserInfo(String userName){
        return ResultVO.success(userInfoService.getUserInfo(userName));
    }

}
