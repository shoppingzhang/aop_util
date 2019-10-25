package com.zxp.aspect.aop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author zhangxiaoping
 * @date 2019/10/24
 * description: 防止按钮快速点击的注解 默认间隔为1000ms,可以自定义时间间隔 例如@SingleClick(2000)
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface SingleClick {
    long intervalTime() default 1000L;
}
