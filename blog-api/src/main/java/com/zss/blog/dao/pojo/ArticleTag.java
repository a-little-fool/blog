package com.zss.blog.dao.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 周书胜
 * @date 2023年04月23 16:15
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleTag {
    private Long id;

    private Long articleId;

    private Long tagId;
}
