package com.zss.blog.vo.params;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 周书胜
 * @date 2023年04月23 16:03
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleBodyParam {
    private String content;

    private String contentHtml;
}
