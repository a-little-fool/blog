package com.zss.blog.service;

import com.zss.blog.dao.pojo.SysUser;
import com.zss.blog.vo.ResultVo;
import com.zss.blog.vo.params.LoginParam;
import org.springframework.transaction.annotation.Transactional;

// 事务加在接口上，可使其实现类都实现事务功能
@Transactional
public interface LoginService {

    ResultVo login(LoginParam loginParam);

    ResultVo logout(String token);

    ResultVo register(LoginParam loginParam);

    SysUser checkToken(String token);
}
