package com.zss.blog.dao.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 周书胜
 * @date 2023年04月20 17:08
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tag {
    private Long id;

    private String avatar;

    private String tagName;
}
