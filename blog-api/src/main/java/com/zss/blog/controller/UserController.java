package com.zss.blog.controller;

import com.zss.blog.service.SysUserService;
import com.zss.blog.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 周书胜
 * @date 2023年04月21 21:01
 */

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private SysUserService sysUserService;

    @GetMapping("/currentUser")
    public ResultVo currentUser(@RequestHeader("Authorization") String token){

        return sysUserService.getUserInfoByToken(token);
    }
}
