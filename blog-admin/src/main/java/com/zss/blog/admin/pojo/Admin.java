package com.zss.blog.admin.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 周书胜
 * @date 2023年04月25 20:50
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Admin {
    @TableId(type = IdType.AUTO)
    private Long id;

    private String username;

    private String password;
}
