package com.zxp.aspect.aop.aspect;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.zxp.aspect.aop.PermissionRequestActivity;
import com.zxp.aspect.aop.annotation.NeedPermission;
import com.zxp.aspect.aop.annotation.PermissionCanceled;
import com.zxp.aspect.aop.annotation.PermissionDenied;
import com.zxp.aspect.aop.bean.CancelBean;
import com.zxp.aspect.aop.bean.DenyBean;
import com.zxp.aspect.aop.interfaces.IPermission;
import com.zxp.aspect.aop.utils.ApplicationUtils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @author zhangxiaoping
 * @date 2019/10/24
 * description:
 */

@Aspect
public class PermissionAspect {

    Context context;

    @Pointcut("execution(@com.zxp.aspect.aop.annotation.NeedPermission * *(..)) && @annotation(needPermission)")
    public void requestPermissionMethod(NeedPermission needPermission) {

    }

    @Around("requestPermissionMethod(needPermission)")
    public void aroundJointPoint(final ProceedingJoinPoint joinPoint, final NeedPermission needPermission) {
        final Object object = joinPoint.getThis();
        if (object == null) {
            return;
        }

        if (object instanceof Context) {
            context = (Context) object;
        } else if (object instanceof Fragment) {
            context = ((Fragment) object).getActivity();
        } else if (object instanceof android.app.Fragment) {
            context = ((android.app.Fragment) object).getActivity();
        } else {
            Object[] args = joinPoint.getArgs();
            if (args.length > 0) {
                if (args[0] instanceof Context) {
                    //方法第一参数是context
                    context = (Context) args[0];
                } else {
                    //没有传入context, 默认使用application
                    context = ApplicationUtils.getApp();
                }
            } else {
                context = ApplicationUtils.getApp();
            }
        }

        if (context == null && needPermission == null) {
            return;
        }

        PermissionRequestActivity.PermissionRequest(context, needPermission.permiss(), needPermission.requestCode(), new IPermission() {
            @Override
            public void PermissionGranted() {
                try {
                    joinPoint.proceed();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }

            @Override
            public void PermissionDenied(int requestCode, List<String> denyList) {
                Class<?> aClass = object.getClass();
                //拿到所在类的所有方法
                Method[] methods = aClass.getDeclaredMethods();
                if (methods.length == 0) {
                    return;
                }
                for (Method method : methods) {
                    //过滤掉不含PermissionDenied注解的方法
                    boolean isHasAnnotation = method.isAnnotationPresent(PermissionDenied.class);
                    if (isHasAnnotation) {
                        method.setAccessible(true);
                        //获取含有注解方法的参数类型
                        Class<?>[] parameterTypes = method.getParameterTypes();
                        if (parameterTypes.length != 1) {
                            return;
                        }
                        PermissionDenied annotation = method.getAnnotation(PermissionDenied.class);
                        if (annotation == null) {
                            return;
                        }
                        DenyBean denyBean = new DenyBean();
                        denyBean.setContext(context);
                        denyBean.setDenyList(denyList);
                        denyBean.setRequestCode(requestCode);
                        try {
                            //通过反射给该含有注解的方法设置参数
                            method.invoke(object, denyBean);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

            @Override
            public void PermissionCanceled(int requestCode) {
                Class<?> cls = object.getClass();
                Method[] methods = cls.getDeclaredMethods();
                if (methods.length == 0) return;
                for (Method method : methods) {
                    //过滤不含自定义注解PermissionCanceled的方法
                    boolean isHasAnnotation = method.isAnnotationPresent(PermissionCanceled.class);
                    if (isHasAnnotation) {
                        method.setAccessible(true);
                        //获取含有注解方法的参数类型
                        Class<?>[] types = method.getParameterTypes();
                        if (types.length != 1) return;
                        //获取方法上的注解
                        PermissionCanceled aInfo = method.getAnnotation(PermissionCanceled.class);
                        if (aInfo == null) return;
                        //解析注解上对应的信息
                        CancelBean bean = new CancelBean();
                        bean.setContext(context);
                        bean.setRequestCode(requestCode);
                        try {
                            //通过反射给该含有注解的方法设置参数
                            method.invoke(object, bean);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
    }
}
