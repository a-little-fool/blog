package com.zss.blog.admin.model.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 周书胜
 * @date 2023年04月25 17:01
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageParam {
    private Integer currentPage;

    private Integer pageSize;

    private String queryString;
}
