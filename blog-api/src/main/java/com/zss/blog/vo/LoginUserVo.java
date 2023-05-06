package com.zss.blog.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 周书胜
 * @date 2023年04月21 21:03
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginUserVo {
    private Long id;

    private String account;

    private String nickname;

    private String avatar;
}
