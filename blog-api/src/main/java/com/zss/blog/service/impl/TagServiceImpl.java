package com.zss.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zss.blog.dao.mapper.TagMapper;
import com.zss.blog.dao.pojo.Tag;
import com.zss.blog.service.TagService;
import com.zss.blog.vo.ResultVo;
import com.zss.blog.vo.TagVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author 周书胜
 * @date 2023年04月20 20:19
 */
@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagMapper tagMapper;


    public TagVo copy(Tag tag){
        TagVo tagVo = new TagVo();
        BeanUtils.copyProperties(tag,tagVo);
        return tagVo;
    }
    public List<TagVo> copyList(List<Tag> tagList){
        List<TagVo> tagVoList = new ArrayList<>();
        for (Tag tag : tagList) {
            tagVoList.add(copy(tag));
        }
        return tagVoList;
    }

    @Override
    public List<TagVo> findTagsByArticleId(Long articleId) {
        List<Tag> tagList = tagMapper.findTagsByArticleId(articleId);
        return copyList(tagList);
    }

    @Override
    public List<TagVo> hot(int limit) {
        List<Long> hotTagsIds = tagMapper.findHotsTagIds(limit);
        if (CollectionUtils.isEmpty(hotTagsIds)) {
            return Collections.emptyList();
        }
        List<Tag> hotTags = tagMapper.findTagsByTagsIds(hotTagsIds);
        return copyList(hotTags);
    }

    @Override
    public ResultVo findAll() {
        List<Tag> tags = tagMapper.selectList(null);
        return ResultVo.success(copyList(tags));
    }

    @Override
    public ResultVo findAllDetail() {
        LambdaQueryWrapper<Tag> queryWrapper = new LambdaQueryWrapper<>();
        List<Tag> tags = this.tagMapper.selectList(queryWrapper);
        return ResultVo.success(copyList(tags));
    }

    @Override
    public ResultVo findAllDetailById(Long id) {
        Tag tag = tagMapper.selectById(id);
        TagVo tagVo = copy(tag);
        return ResultVo.success(tagVo);
    }


}
