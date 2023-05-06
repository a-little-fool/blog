package com.zss.blog.controller;

import com.zss.blog.dao.pojo.Tag;
import com.zss.blog.service.TagService;
import com.zss.blog.vo.ResultVo;
import com.zss.blog.vo.TagVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.transform.Result;
import java.util.List;

/**
 * @author 周书胜
 * @date 2023年04月20 21:24
 */

@RestController
@RequestMapping("/tags")
public class TagsController {

    @Autowired
    private TagService tagService;

    @GetMapping("/hot")
    public ResultVo listHotTags() {
        int limit = 6; // 假设只显示前六条最热标签
        // 分组查询并选出前六条最热标签
        List<TagVo> tagList = tagService.hot(limit);
        return ResultVo.success(tagList);
    }

    @GetMapping
    public ResultVo findAll() {
        return tagService.findAll();
    }

    @GetMapping("/detail")
    public ResultVo findAllDetail(){
        return tagService.findAllDetail();
    }

    @GetMapping("/detail/{id}")
    public ResultVo findDetailById(@PathVariable("id") Long id){
        return tagService.findAllDetailById(id);
    }
}
