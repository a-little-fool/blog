package com.zss.blog.dao.dos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 周书胜
 * @date 2023年04月21 12:10
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Archives {
    private Integer year;

    private Integer month;

    private Integer count;
}
