package com.zss.blog.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zss.blog.admin.mapper.PermissionMapper;
import com.zss.blog.admin.model.param.PageParam;
import com.zss.blog.admin.pojo.Permission;
import com.zss.blog.admin.service.PermissionService;
import com.zss.blog.admin.vo.PageResult;
import com.zss.blog.admin.vo.ResultVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 周书胜
 * @date 2023年04月25 17:03
 */

@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public ResultVo listPermission(PageParam pageParam) {
        Page<Permission> page = new Page<>(pageParam.getCurrentPage(), pageParam.getPageSize());
        LambdaQueryWrapper<Permission> permissionLambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(pageParam.getQueryString())) {
            permissionLambdaQueryWrapper.eq(Permission::getName, pageParam.getQueryString());
        }

        Page<Permission> permissionPage = permissionMapper.selectPage(page, permissionLambdaQueryWrapper);

        PageResult<Permission> pageResult = new PageResult<>();
        pageResult.setList(permissionPage.getRecords());
        pageResult.setTotal(permissionPage.getTotal());

        return ResultVo.success(pageResult);
    }

    @Override
    public ResultVo add(Permission permission) {
        permissionMapper.insert(permission);
        return ResultVo.success(null);
    }

    @Override
    public ResultVo update(Permission permission) {
        permissionMapper.updateById(permission);
        return ResultVo.success(null);
    }

    @Override
    public ResultVo delete(Long id) {
        permissionMapper.deleteById(id);
        return ResultVo.success(null);
    }
}
