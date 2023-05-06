package com.zss.blog.utils;

import com.zss.blog.dao.pojo.SysUser;

/**
 * @author 周书胜
 * @date 2023年04月22 17:21
 */
public class UserThreadLocal {

    private UserThreadLocal(){}

    private static final ThreadLocal<SysUser> LOCAL = new ThreadLocal<>();

    public static void put(SysUser sysUser){
        LOCAL.set(sysUser);
    }
    public static SysUser get(){
        return LOCAL.get();
    }
    public static void remove(){
        LOCAL.remove();
    }
}
