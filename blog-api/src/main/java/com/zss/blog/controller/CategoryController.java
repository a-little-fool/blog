package com.zss.blog.controller;

import com.zss.blog.service.CategoryService;
import com.zss.blog.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 周书胜
 * @date 2023年04月23 15:55
 */

@RestController
@RequestMapping("/categorys")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResultVo listCategory() {
        return categoryService.findAll();
    }

    @GetMapping("detail")
    public ResultVo categoriesDetail(){
        return categoryService.findAllDetail();
    }

    @GetMapping("detail/{id}")
    public ResultVo categoriesDetailById(@PathVariable("id") Long id){
        return categoryService.categoriesDetailById(id);
    }
}
