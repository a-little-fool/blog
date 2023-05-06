package com.zss.blog.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 周书胜
 * @date 2023年04月22 18:20
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryVo {
    private Long id;

    private String avatar;

    private String categoryName;

    private String description;
}
