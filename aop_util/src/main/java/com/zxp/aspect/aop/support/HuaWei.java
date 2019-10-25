package com.zxp.aspect.aop.support;

import android.content.ComponentName;
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

public class HuaWei implements ISetting {

    private Context context;

    public HuaWei(Context context) {
        this.context = context;
    }

    @Override
    public Intent getSetting() {
        try {
            Intent intent = new Intent();
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            ComponentName comp = new ComponentName("com.huawei.systemmanager", "com.huawei.permissionmanager.ui.MainActivity");//华为权限管理
            intent.setComponent(comp);
            return intent;
        } catch (Exception e) {
            e.printStackTrace();
            Intent localIntent = new Intent();
            localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            localIntent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            localIntent.setData(Uri.fromParts("package", context.getPackageName(), null));
            return localIntent;
        }
    }
}
