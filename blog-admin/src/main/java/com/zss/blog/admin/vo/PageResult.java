package com.zss.blog.admin.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author 周书胜
 * @date 2023年04月25 17:14
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResult<T> {

    private List<T> list;

    private Long total;
}
