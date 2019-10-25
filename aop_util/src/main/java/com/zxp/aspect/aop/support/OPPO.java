package com.zxp.aspect.aop.support;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;

import com.zxp.aspect.aop.interfaces.ISetting;


/**
 * @author zhangxiaoping
 * @date 2019/10/24
 * description:
 */

public class OPPO implements ISetting {

    private Context context;

    public OPPO(Context context) {
        this.context = context;
    }

    @Override
    public Intent getSetting() {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.fromParts("package", context.getPackageName(), null));
        return intent;
    }
}
