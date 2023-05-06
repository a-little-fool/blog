package com.zss.blog.controller;

import com.zss.blog.common.aop.LogAnnotation;
import com.zss.blog.common.cache.Cache;
import com.zss.blog.service.ArticleService;
import com.zss.blog.vo.ArticleVo;
import com.zss.blog.vo.ResultVo;
import com.zss.blog.vo.params.ArticleParam;
import com.zss.blog.vo.params.PageParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 周书胜
 * @date 2023年04月20 17:10
 */
@RestController
@RequestMapping("/articles")
public class ArticleController {
    @Autowired
    private ArticleService articleService;


    @PostMapping
    public ResultVo listArticle(@RequestBody PageParam pageParam) {
        // 让serivce层直接返回ResultVo对象即可
        return articleService.listArticle(pageParam);
    }

    @PostMapping("/hot")
    @Cache(expire = 5 * 60 * 1000,name = "hot_article")
    public ResultVo hotArticle() {
        int limit = 5;
        return articleService.hotArticle(limit);
    }

    @PostMapping("/new")
    public ResultVo newArticles() {
        int limit = 5;
        return articleService.newArticles(limit);
    }

    /**
     * 文章归档
     * @return
     */
    @LogAnnotation(module = "文章", operation = "获取文章列表")
    @PostMapping("/listArchives")
    public ResultVo listArchives() {
        return articleService.listArchives();
    }

    @PostMapping("view/{id}")
    public ResultVo findArticleById(@PathVariable("id") Long id) {
        ArticleVo articleVo = articleService.findArticleById(id);

        return ResultVo.success(articleVo);
    }

    @PostMapping("/publish")
    public ResultVo publish(@RequestBody ArticleParam articleParam) {
        return articleService.publish(articleParam);
    }
}
