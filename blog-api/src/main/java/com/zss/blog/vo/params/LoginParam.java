package com.zss.blog.vo.params;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 周书胜
 * @date 2023年04月21 18:10
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginParam {
    private String account;

    private String password;

    private String nickname;
}
