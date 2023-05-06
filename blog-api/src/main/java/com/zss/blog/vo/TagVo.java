package com.zss.blog.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 周书胜
 * @date 2023年04月20 17:47
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TagVo {
    private Long id;

    private String tagName;

    private String avatar;
}
