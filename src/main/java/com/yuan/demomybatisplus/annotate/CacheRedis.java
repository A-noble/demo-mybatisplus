package com.yuan.demomybatisplus.annotate;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author MuXue
 * @create 2022-07-13  17:49 PM
 */
@Target(ElementType.METHOD)         // 注解作用到方法上
@Retention(RetentionPolicy.RUNTIME) // 注解保留大运行时
public @interface CacheRedis {
    String prefix();               // Redis 健值的前缀
    String[] element() default {}; // 参与生成健值的元素，与方法上的参数签名保持一致
    long deadline() default 30L;   // 过期时间（秒），设置为 -1 永不过期
}
