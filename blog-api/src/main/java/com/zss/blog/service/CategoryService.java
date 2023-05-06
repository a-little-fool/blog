package com.zss.blog.service;

import com.zss.blog.vo.CategoryVo;
import com.zss.blog.vo.ResultVo;

public interface CategoryService {

    CategoryVo findCategoryById(Long categoryId);

    ResultVo findAll();

    ResultVo findAllDetail();

    ResultVo categoriesDetailById(Long id);
}
