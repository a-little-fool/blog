package com.zss.blog.controller;

import com.zss.blog.dao.pojo.SysUser;
import com.zss.blog.utils.UserThreadLocal;
import com.zss.blog.vo.ResultVo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 周书胜
 * @date 2023年04月22 17:24
 */

@RestController
@RequestMapping("/test")
public class TestController {
    @RequestMapping
    public ResultVo test(){
//        SysUser
        SysUser sysUser = UserThreadLocal.get();
        System.out.println(sysUser);
        return ResultVo.success(null);
    }
}
