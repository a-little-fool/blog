package com.zss.blog.controller;

import com.zss.blog.service.LoginService;
import com.zss.blog.vo.ResultVo;
import com.zss.blog.vo.params.LoginParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 周书胜
 * @date 2023年04月22 11:04
 */

@RestController
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    private LoginService loginService;

    @PostMapping
    public ResultVo register(@RequestBody LoginParam loginParam){
        System.out.println(loginParam);
        return loginService.register(loginParam);
    }
}
