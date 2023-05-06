package com.zss.blog.dao.pojo;

import lombok.Data;

/**
 * @author 周书胜
 * @date 2023年04月22 20:30
 */

@Data
public class Comment {
    private Long id;

    private String content;

    private Long createDate;

    private Long articleId;

    private Long authorId;

    private Long parentId;

    private Long toUid;

    private Integer level;
}
