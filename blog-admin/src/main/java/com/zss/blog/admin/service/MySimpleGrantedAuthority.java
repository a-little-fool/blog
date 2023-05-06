package com.zss.blog.admin.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

/**
 * @author 周书胜
 * @date 2023年04月25 21:17
 */

@Component
public class MySimpleGrantedAuthority implements GrantedAuthority {
    private String authority;
    private String path;

    public MySimpleGrantedAuthority(){}

    public MySimpleGrantedAuthority(String authority){
        this.authority = authority;
    }

    public MySimpleGrantedAuthority(String authority,String path){
        this.authority = authority;
        this.path = path;
    }

    @Override
    public String getAuthority() {
        return authority;
    }

    public String getPath() {
        return path;
    }
}
