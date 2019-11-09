package com.zxp.aspect.aop.aspect;

import com.zxp.aspect.aop.AopSdk;
import com.zxp.aspect.aop.annotation.Intercept;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @author zhangxiaoping
 * @date 2019/11/7
 * description:
 */
@Aspect
public class InterceptAspect {

    @Pointcut("within(@com.zxp.aspect.aop.annotation.Intercept *)")
    public void withinAnnotatedClass() { //被intercept注解修饰的类

    }

    @Pointcut("execution(!synthetic * *(..)) && withinAnnotatedClass()")
    public void methodInsideAnnotedType() { //非java关键字synthetic修饰，并且带有注解Intercept的类中所有方法

    }

    @Pointcut("execution(!synthetic *.new(..)) && withinAnnotatedClass()")
    public void constructorInsideAnnotedType() { //非java关键字synthetic修饰，并且带有注解Intercept的构造方法

    }

    @Pointcut("execution(@com.zxp.aspect.aop.annotation.Intercept * *(..)) || methodInsideAnnotedType()")
    public void method() { //带有注解Intercept的方法 或者 被注解Intercept修饰的类中的方法

    }

    @Pointcut("execution(@com.zxp.aspect.aop.annotation.Intercept *.new(..)) || constructorInsideAnnotedType()")
    public void constructor() {

    }

    @Around("method() && @annotation(intercept)")
    public Object aroundJointPoint(ProceedingJoinPoint joinPoint, Intercept intercept) throws Throwable {
        if (AopSdk.getInterceptor() == null) {
            return joinPoint.proceed();
        }
        boolean result = proceedInterceptor(intercept.value(), joinPoint);
        return result ? null : joinPoint.proceed();
    }

    private boolean proceedInterceptor(int[] types, ProceedingJoinPoint joinPoint) throws Throwable {
        for (int type : types) {
            if (AopSdk.getInterceptor().intercept(type, joinPoint)) {
                return true;
            }
        }
        return false;
    }
}
