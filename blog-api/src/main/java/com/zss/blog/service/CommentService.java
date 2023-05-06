package com.zss.blog.service;

import com.zss.blog.vo.ResultVo;
import com.zss.blog.vo.params.CommentParam;

public interface CommentService {
    ResultVo commentsByArticleId(Long articleId);

    ResultVo comment(CommentParam commentParam);
}
