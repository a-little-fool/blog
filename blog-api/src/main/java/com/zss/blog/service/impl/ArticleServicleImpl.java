package com.zss.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zss.blog.dao.dos.Archives;
import com.zss.blog.dao.mapper.ArticleBodyMapper;
import com.zss.blog.dao.mapper.ArticleMapper;
import com.zss.blog.dao.mapper.ArticleTagMapper;
import com.zss.blog.dao.pojo.Article;
import com.zss.blog.dao.pojo.ArticleBody;
import com.zss.blog.dao.pojo.ArticleTag;
import com.zss.blog.dao.pojo.SysUser;
import com.zss.blog.service.*;
import com.zss.blog.utils.UserThreadLocal;
import com.zss.blog.vo.*;
import com.zss.blog.vo.params.ArticleParam;
import com.zss.blog.vo.params.PageParam;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 周书胜
 * @date 2023年04月20 17:36
 */

@Service
public class ArticleServicleImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private TagService tagService;

    @Autowired
    private SysUserService sysUserService;

    public List<ArticleVo> copyList(List<Article> articleList, boolean isTag, boolean isAuthor) {
        ArrayList<ArticleVo> articleVos = new ArrayList<>();
        for (Article article : articleList) {
            articleVos.add(copy(article, isTag, isAuthor, false, false));
        }
        return articleVos;
    }

    public ArticleVo copy(Article article, boolean isTag, boolean isAuthor, boolean isBody, boolean isCategory) {
        ArticleVo articleVo = new ArticleVo();
        BeanUtils.copyProperties(article, articleVo);
        articleVo.setCreateDate(new DateTime(article.getCreateDate()).toString("yyyy-MM-dd HH:mm"));

        // 并不是所有的接口, 都需要Tag和Author
        if (isTag) {
            articleVo.setTags(tagService.findTagsByArticleId(article.getId()));
        }
        if (isAuthor) {
            SysUser sysUser = sysUserService.getAuthorByAuthorId(article.getAuthorId());
            articleVo.setAuthor(sysUser.getNickname());
        }
        if (isBody) {
            ArticleBodyVo articleBody = findArticleBody(article.getId());
            articleVo.setBody(articleBody);
        }
        if (isCategory) {
            CategoryVo categoryVo = findCategory(article.getCategoryId());
            articleVo.setCategory(categoryVo);
        }

        return articleVo;
    }

//    @Override
//    public ResultVo listArticle(PageParam pageParam) {
//        // mybatispuls分页查询
//        Page<Article> articlePage = new Page<>(pageParam.getPageNum(), pageParam.getPageSize());
//        // LambdaQueryWrapper构建条件表达式
//        LambdaQueryWrapper<Article>  lambdaQueryWrapper = new LambdaQueryWrapper<>();
//
//        // 查询文章的参数 加上分类id，判断不为空 加上分类条件
//        if (pageParam.getCategoryId() != null) {
//            lambdaQueryWrapper.eq(Article::getCategoryId,pageParam.getCategoryId());
//        }
//        // 查询文章的参数 加上标签id，判断不为空 加上分类条件
//        List<Long> articleIdList = new ArrayList<>();
//        if (pageParam.getTagId() != null) {
//            LambdaQueryWrapper<ArticleTag> articleTagLambdaQueryWrapper = new LambdaQueryWrapper<>();
//            articleTagLambdaQueryWrapper.eq(ArticleTag::getTagId, pageParam.getTagId());
//            List<ArticleTag> articleTags = articleTagMapper.selectList(articleTagLambdaQueryWrapper);
//
//            for (ArticleTag articleTag : articleTags) {
//                articleIdList.add(articleTag.getArticleId());
//            }
//
//            if (articleIdList.size() > 0) {
//                lambdaQueryWrapper.in(Article::getId, articleIdList);
//            }
//        }
//
//        // 先根据文章权重，再根据文章创建时间倒序排序
//        lambdaQueryWrapper.orderByDesc(Article::getWeight, Article::getCreateDate);
//
//        Page<Article> selectPage = articleMapper.selectPage(articlePage, lambdaQueryWrapper);
//        // 我们不能直接返回, 一般我们都用XXXVo作为真正的返回数据, 而不是和数据库中的数据耦合
//        List<Article> articleList = selectPage.getRecords();
//
//        // 封装为ArticleVo再返回
//        List<ArticleVo> articleVoList = copyList(articleList, true, true);
//        return ResultVo.success(articleVoList);
//    }

    @Override
    public ResultVo listArticle(PageParam pageParam) {
        Page<Article> page = new Page<>(pageParam.getPageNum(),pageParam.getPageSize());
        IPage<Article> articleIPage = articleMapper.listArticle(page,pageParam.getCategoryId(),pageParam.getTagId(),pageParam.getYear(),pageParam.getMonth());
        return ResultVo.success(copyList(articleIPage.getRecords(),true,true));
    }

    @Override
    public ResultVo hotArticle(int limit) {
        LambdaQueryWrapper<Article> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.orderByDesc(Article::getViewCounts);
        lambdaQueryWrapper.select(Article::getId, Article::getTitle);
        lambdaQueryWrapper.last("limit " + limit);
        List<Article> articles = articleMapper.selectList(lambdaQueryWrapper);
        return ResultVo.success(copyList(articles, false, false));
    }

    @Override
    public ResultVo newArticles(int limit) {
        LambdaQueryWrapper<Article> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.orderByDesc(Article::getCreateDate);
        lambdaQueryWrapper.select(Article::getId, Article::getTitle);
        lambdaQueryWrapper.last("limit " + limit);
        List<Article> articles = articleMapper.selectList(lambdaQueryWrapper);
        return ResultVo.success(copyList(articles, false, false));
    }

    @Override
    public ResultVo listArchives() {
        List<Archives> archives = articleMapper.listArchives();
        return ResultVo.success(archives);
    }

    @Autowired
    private ThreadService threadService;

    @Override
    public ArticleVo findArticleById(Long id) {
        Article article = articleMapper.selectById(id);
        threadService.updateViewCount(articleMapper, article);

        //  1405916999732707300 1405916999732707330

        System.out.println("id=" + id);
        System.out.println(article);
        return copy(article, true, true, true, true);
    }

    @Autowired
    private ArticleTagMapper articleTagMapper;

    @Transactional
    @Override
    public ResultVo publish(ArticleParam articleParam) {
        SysUser sysUser = UserThreadLocal.get();

        Article article = new Article();
        article.setAuthorId(sysUser.getId());
        article.setCategoryId(articleParam.getCategory().getId());
        article.setCreateDate(System.currentTimeMillis());
        article.setCommentCounts(0);
        article.setSummary(articleParam.getSummary());
        article.setTitle(articleParam.getTitle());
        article.setViewCounts(0);
        article.setWeight(Article.Article_Common);
        article.setBodyId(-1L);
        this.articleMapper.insert(article);

        //tags
        List<TagVo> tags = articleParam.getTags();
        if (tags != null) {
            for (TagVo tag : tags) {
                ArticleTag articleTag = new ArticleTag();
                articleTag.setArticleId(article.getId());
                articleTag.setTagId(tag.getId());
                articleTagMapper.insert(articleTag);
            }
        }
        ArticleBody articleBody = new ArticleBody();
        articleBody.setContent(articleParam.getBody().getContent());
        articleBody.setContentHtml(articleParam.getBody().getContentHtml());
        articleBody.setArticleId(article.getId());
        articleBodyMapper.insert(articleBody);

        article.setBodyId(articleBody.getId());
        articleMapper.updateById(article);
        ArticleVo articleVo = new ArticleVo();
        articleVo.setId(article.getId());

        return ResultVo.success(articleVo);
    }

    @Autowired
    private CategoryService categoryService;

    private CategoryVo findCategory(Long categoryId) {
        return categoryService.findCategoryById(categoryId);
    }

    @Autowired
    private ArticleBodyMapper articleBodyMapper;

    private ArticleBodyVo findArticleBody(Long articleId) {
        LambdaQueryWrapper<ArticleBody> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ArticleBody::getArticleId,articleId);
        ArticleBody articleBody = articleBodyMapper.selectOne(queryWrapper);
        ArticleBodyVo articleBodyVo = new ArticleBodyVo();
        articleBodyVo.setContent(articleBody.getContent());
        return articleBodyVo;
    }
}
