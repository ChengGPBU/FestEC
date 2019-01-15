package com.complier;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * package: com.annotations
 * author: chengguang
 * created on: 2019/1/10 下午12:01
 * description: 微信支付页面  注解
 */

@Target(ElementType.TYPE)//这个注解告诉编译器我们这个注解使用在类上面的
@Retention(RetentionPolicy.SOURCE)
public @interface PayEntryGenerator {
    String packageName();

    Class<?> payEntryTemplete();
}
