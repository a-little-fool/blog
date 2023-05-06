package com.zss.blog.vo.params;

import com.zss.blog.vo.CategoryVo;
import com.zss.blog.vo.TagVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author 周书胜
 * @date 2023年04月23 16:02
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleParam {
    private Long id;

    private ArticleBodyParam body;

    private CategoryVo category;

    private String summary;

    private List<TagVo> tags;

    private String title;
}
