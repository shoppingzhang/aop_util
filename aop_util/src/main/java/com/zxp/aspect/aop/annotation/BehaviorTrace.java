package com.zxp.aspect.aop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author zhangxiaoping
 * @date 2019/10/25
 * description: 是否需要打印方法耗时
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface BehaviorTrace {

    boolean needPrint() default true;
}
