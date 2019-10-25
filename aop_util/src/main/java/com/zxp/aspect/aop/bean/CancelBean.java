package com.zxp.aspect.aop.bean;

import android.content.Context;

/**
 * @author zhangxiaoping
 * @date 2019/10/24
 * description:
 */

public class CancelBean {
    private int requestCode;
    private Context context;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public int getRequestCode() {
        return requestCode;
    }

    public void setRequestCode(int requestCode) {
        this.requestCode = requestCode;
    }
}
