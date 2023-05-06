package com.zss.blog.common.cache;

import java.lang.annotation.*;

@Target(
        {
                ElementType.METHOD,
                ElementType.TYPE
        }
)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Cache {
    long expire() default 1 * 60 * 1000; // 默认一分钟

    String name() default "";
}
