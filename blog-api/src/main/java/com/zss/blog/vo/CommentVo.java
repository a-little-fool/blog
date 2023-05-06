package com.zss.blog.vo;

import lombok.Data;

import java.util.List;

/**
 * @author 周书胜
 * @date 2023年04月22 20:38
 */

@Data
public class CommentVo {
    private Long id;

    private UserVo author;

    private String content;

    private List<CommentVo> childrens;

    private String createDate;

    private Integer level;

    private UserVo toUser;
}
