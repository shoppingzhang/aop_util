package com.zxp.aspect.aop.support;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;

import com.zxp.aspect.aop.BuildConfig;
import com.zxp.aspect.aop.interfaces.ISetting;

/**
 * @author zhangxiaoping
 * @date 2019/10/24
 * description:
 */
public class Meizu implements ISetting {
    private Context context;

    public Meizu(Context context) {
        this.context = context;
    }

    @Override
    public Intent getSetting() {
        try {
            Intent intent = new Intent("com.meizu.safe.security.SHOW_APPSEC");
            intent.addCategory(Intent.CATEGORY_DEFAULT);
            intent.putExtra("packageName", BuildConfig.APPLICATION_ID);
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
