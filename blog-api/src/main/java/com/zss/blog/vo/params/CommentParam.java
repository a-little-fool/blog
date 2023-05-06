package com.zss.blog.vo.params;

import lombok.Data;

/**
 * @author 周书胜
 * @date 2023年04月22 20:57
 */

@Data
public class CommentParam {

    private Long articleId;

    private String content;

    private Long parent;

    private Long toUserId;
}
