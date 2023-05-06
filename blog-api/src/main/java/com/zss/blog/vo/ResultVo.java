package com.zss.blog.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 周书胜
 * @date 2023年04月20 17:14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultVo {
    private boolean success;
    private int code;
    private String msg;
    private Object data;

    public static ResultVo success(Object data) {
        return new ResultVo(true, 200, "success", data);
    }

    public static ResultVo fail(int code, String msg) {
        return new ResultVo(false, code, msg, null);
    }
}
