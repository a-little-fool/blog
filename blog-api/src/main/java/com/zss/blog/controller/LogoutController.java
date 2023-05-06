package com.zss.blog.controller;

import com.zss.blog.service.LoginService;
import com.zss.blog.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 周书胜
 * @date 2023年04月21 21:10
 */

@RestController
@RequestMapping("/logout")
public class LogoutController {

    @Autowired
    private LoginService loginService;


    @GetMapping
    public ResultVo logout(@RequestHeader("Authorization") String token) {
        return loginService.logout(token);
    }
}
