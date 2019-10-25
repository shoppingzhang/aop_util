package com.zxp.aspect.aop.utils;

import android.view.View;

/**
 * @author zhangxiaoping
 * @date 2019/10/24
 * description:
 */
public final class ClickUtil {

    /**
     * 最近一次点击的时间
     */
    private static long mLastClickTime;
    /**
     * 最近一次点击的控件ID
     */
    private static int mLastClickViewId;

    /**
     * 是否是快速点击
     *
     * @param v              点击的控件
     * @param intervalMillis 时间间期（毫秒）
     * @return true:是，false:不是
     */
    public static boolean isFastDoubleClick(View v, long intervalMillis) {
        int viewId = v.getId();
        long time = System.currentTimeMillis();
        long timeInterval = Math.abs(time - mLastClickTime);
        if (timeInterval < intervalMillis && viewId == mLastClickViewId) {
            return true;
        } else {
            mLastClickTime = time;
            mLastClickViewId = viewId;
            return false;
        }
    }
}