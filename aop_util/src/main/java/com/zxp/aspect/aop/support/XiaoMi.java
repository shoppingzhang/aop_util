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

public class XiaoMi implements ISetting {

    private Context context;

    public XiaoMi(Context context) {
        this.context = context;
    }

    @Override
    public Intent getSetting() {
        try { // MIUI 8
            Intent localIntent = new Intent("miui.intent.action.APP_PERM_EDITOR");
            localIntent.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.PermissionsEditorActivity");
            localIntent.putExtra("extra_pkgname", context.getPackageName());
            return localIntent;
        } catch (Exception e) {
            try { // MIUI 5/6/7
                Intent localIntent = new Intent("miui.intent.action.APP_PERM_EDITOR");
                localIntent.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.AppPermissionsEditorActivity");
                localIntent.putExtra("extra_pkgname", context.getPackageName());
                return localIntent;
            } catch (Exception e1) { // 否则跳转到应用详情
                Intent localIntent = new Intent();
                localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                localIntent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                localIntent.setData(Uri.fromParts("package", context.getPackageName(), null));
                return localIntent;
            }
        }
    }
}
