package com.zss.blog.controller;

import com.zss.blog.service.LoginService;
import com.zss.blog.vo.ResultVo;
import com.zss.blog.vo.params.LoginParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 周书胜
 * @date 2023年04月21 18:09
 */

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping
    public ResultVo login(@RequestBody LoginParam loginParam){
        return loginService.login(loginParam);
    }

}
