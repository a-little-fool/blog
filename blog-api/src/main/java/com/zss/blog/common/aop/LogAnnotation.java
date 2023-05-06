package com.zss.blog.common.aop;

import java.lang.annotation.*;

/**
 * 日志注解开发
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogAnnotation {
    String module() default "";
    String operation() default "";
}
