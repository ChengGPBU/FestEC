package com.complier;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * package: com.annotations
 * author: chengguang
 * created on: 2019/1/10 上午11:58
 * description: 微信入口页面注解
 */
@Target(ElementType.TYPE)  //类注解
@Retention(RetentionPolicy.SOURCE) // 编译器在处理注解的时候 在源码时候处理的
public @interface EntryGenerator {
    String packageName();
    Class<?> entryTemplete();
}
