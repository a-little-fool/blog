package com.zss.blog.admin.service;

import com.zss.blog.admin.model.param.PageParam;
import com.zss.blog.admin.pojo.Permission;
import com.zss.blog.admin.vo.ResultVo;

public interface PermissionService {
    ResultVo listPermission(PageParam pageParam);

    ResultVo add(Permission permission);

    ResultVo update(Permission permission);

    ResultVo delete(Long id);
}
