package com.zss.blog.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zss.blog.dao.mapper.ArticleMapper;
import com.zss.blog.dao.pojo.Article;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author 周书胜
 * @date 2023年04月22 18:55
 */

@Service
public class ThreadService {
    /**
     * 调用taskExecutor线程中的线程完成操作，不会影响到主线程
     * @param articleMapper
     * @param article
     */
    @Async("taskExecutor")
    public void updateViewCount(ArticleMapper articleMapper, Article article){

        Article articleUpdate = new Article();
        articleUpdate.setViewCounts(article.getViewCounts() + 1);
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Article::getId,article.getId());
        queryWrapper.eq(Article::getViewCounts,article.getViewCounts());
        articleMapper.update(articleUpdate,queryWrapper);
        try {
            //睡眠5秒 证明不会影响主线程的使用
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
