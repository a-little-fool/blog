package com.zss.blog.service;

import com.zss.blog.dao.pojo.SysUser;
import com.zss.blog.vo.ResultVo;
import com.zss.blog.vo.UserVo;

public interface SysUserService {
    // 根据articleId获取author
    SysUser getAuthorByAuthorId(Long authorId);

    SysUser findUser(String account, String pwd);

    ResultVo getUserInfoByToken(String token);

    SysUser findUserByAccount(String account);

    void save(SysUser sysUser);

    UserVo findUserVoById(Long authorId);
}
