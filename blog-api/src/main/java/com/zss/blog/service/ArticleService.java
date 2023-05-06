package com.zss.blog.service;

import com.zss.blog.vo.ArticleVo;
import com.zss.blog.vo.ResultVo;
import com.zss.blog.vo.params.ArticleParam;
import com.zss.blog.vo.params.PageParam;

public interface ArticleService {

    /**
     * 实现分页查询文章
     * @param pageParam
     * @return
     */
    ResultVo listArticle(PageParam pageParam);

    ResultVo hotArticle(int limit);

    ResultVo newArticles(int limit);

    ResultVo listArchives();


    ArticleVo findArticleById(Long id);

    ResultVo publish(ArticleParam articleParam);
}
