package com.zxp.aspect.aop;

import android.support.annotation.NonNull;

import com.zxp.aspect.aop.interfaces.Interceptor;

/**
 * @author zhangxiaoping
 * @date 2019/11/7
 * description:
 */
public class AopSdk {

    public static boolean sIsDebug;
    private static Interceptor sInterceptor;

    /**
     * 是否打开调试模式
     *
     * @param isDebug
     */
    public static void setDebug(boolean isDebug) {
        sIsDebug = isDebug;
    }

    public static void setIntercept(@NonNull Interceptor intercept) {
        sInterceptor = intercept;
    }

    public static Interceptor getInterceptor() {
        return sInterceptor;
    }
}
