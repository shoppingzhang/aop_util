package com.zxp.aspect.aop.aspect;

import android.view.View;

import com.zxp.aspect.aop.annotation.SingleClick;
import com.zxp.aspect.aop.utils.ClickUtil;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

/**
 * @author zhangxiaoping
 * @date 2019/10/24
 * description:
 */
@Aspect
public class SingleClickAspect {

    @Pointcut("execution(@com.zxp.aspect.aop.annotation.SingleClick * *(..))")
    public void onMethodAnnotated() {

    }

    @Around("onMethodAnnotated()")
    public void aroundPointJoint(ProceedingJoinPoint joinPoint) throws Throwable {
        View view = null;
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            if (arg instanceof View) {
                view = (View) arg;
                break;
            }
        }
        if (view == null) {
            return;
        }
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        if (!method.isAnnotationPresent(SingleClick.class)) {
            return;
        }
        SingleClick singleClick = method.getAnnotation(SingleClick.class);
        if (!ClickUtil.isFastDoubleClick(view, singleClick.intervalTime())) {
            //不是快速点击 直接执行
            joinPoint.proceed();
        }
    }
}
