package com.zss.blog.service;

import com.zss.blog.vo.ResultVo;
import com.zss.blog.vo.TagVo;

import javax.xml.transform.Result;
import java.util.List;

public interface TagService {
    // 根据articleId返回Tags
    List<TagVo> findTagsByArticleId(Long articleId);

    List<TagVo> hot(int limit);

    ResultVo findAll();

    ResultVo findAllDetail();

    ResultVo findAllDetailById(Long id);
}
