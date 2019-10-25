package com.zxp.aspect.aop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author zhangxiaoping
 * @date 2019/10/24
 * description: 申请权限注解
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface NeedPermission {
    String[] permiss();

    int requestCode() default 0;
}
