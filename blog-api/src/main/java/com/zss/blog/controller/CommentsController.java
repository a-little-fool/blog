package com.zss.blog.controller;

import com.zss.blog.service.CommentService;
import com.zss.blog.vo.ResultVo;
import com.zss.blog.vo.params.CommentParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 周书胜
 * @date 2023年04月22 20:31
 */

@RestController
@RequestMapping("/comments")
public class CommentsController {

    @Autowired
    private CommentService commentService;

    @GetMapping("article/{id}")
    public ResultVo comments(@PathVariable("id") Long articleId){

        return commentService.commentsByArticleId(articleId);
    }



    @PostMapping("create/change")
    public ResultVo comment(@RequestBody CommentParam commentParam){
        return commentService.comment(commentParam);
    }
}
