package com.zss.blog.admin.controller;

import com.zss.blog.admin.model.param.PageParam;
import com.zss.blog.admin.pojo.Permission;
import com.zss.blog.admin.service.PermissionService;
import com.zss.blog.admin.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 周书胜
 * @date 2023年04月25 16:58
 */

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private PermissionService permissionService;

    @PostMapping("/permission/permissionList")
    public ResultVo permissionList(@RequestBody PageParam pageParam){
        return permissionService.listPermission(pageParam);
    }

    @PostMapping("/permission/add")
    public ResultVo add(@RequestBody Permission permission){
        return permissionService.add(permission);
    }

    @PostMapping("/permission/update")
    public ResultVo update(@RequestBody Permission permission){
        return permissionService.update(permission);
    }

    @GetMapping("/permission/delete/{id}")
    public ResultVo delete(@PathVariable("id") Long id){
        return permissionService.delete(id);
    }
}
