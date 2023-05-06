package com.zss.blog.dao.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 周书胜
 * @date 2023年04月22 18:11
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleBody {
    private Long id;
    private String content;
    private String contentHtml;
    private Long articleId;
}
