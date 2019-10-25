package com.zxp.aspect.aop.aspect;

import android.util.Log;

import com.zxp.aspect.aop.annotation.BehaviorTrace;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * @author zhangxiaoping
 * @date 2019/10/25
 * description: 监控方法耗时
 */
@Aspect
public class BehaviorTraceAspect {

    @Pointcut("execution(@com.zxp.aspect.aop.annotation.BehaviorTrace * *(..))")
    public void annoBehavior() {

    }

    @Around("annoBehavior() && @annotation(behaviorTrace)")
    public void aroundAnnoBehavior(ProceedingJoinPoint joinPoint, BehaviorTrace behaviorTrace) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Object ob = joinPoint.getThis();
        Class<?> aClass = ob.getClass();
        String simpleName = aClass.getSimpleName();
        BehaviorTrace annotation = signature.getMethod().getAnnotation(BehaviorTrace.class);
        if (annotation == null) {
            joinPoint.proceed();
            return;
        }
        long start = System.currentTimeMillis();
        joinPoint.proceed();
        long end = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        sb.append(signature.getMethod().getName());
        sb.append("()");
        sb.append(" 耗时为：");
        sb.append((end - start));
        sb.append("ms");
        if (annotation.needPrint()) {
            Log.d(simpleName, sb.toString());
        }
    }
}
