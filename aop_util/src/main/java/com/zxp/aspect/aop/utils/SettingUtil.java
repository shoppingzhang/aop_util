package com.zxp.aspect.aop.utils;

import android.content.Context;
import android.os.Build;

import com.zxp.aspect.aop.interfaces.ISetting;
import com.zxp.aspect.aop.support.Default;
import com.zxp.aspect.aop.support.HuaWei;
import com.zxp.aspect.aop.support.Meizu;
import com.zxp.aspect.aop.support.OPPO;
import com.zxp.aspect.aop.support.ViVo;
import com.zxp.aspect.aop.support.XiaoMi;

/**
 * @author zhangxiaoping
 * @date 2019/10/24
 * description:
 */

public class SettingUtil {

    private static final String MANUFACTURER_HUAWEI = "huawei";//华为
    private static final String MANUFACTURER_HONOR = "honor";//华为
    private static final String MANUFACTURER_MEIZU = "meizu";//魅族
    private static final String MANUFACTURER_XIAOMI = "xiaomi";//小米
    private static final String MANUFACTURER_HONGMI = "redmi";//红米
    private static final String MANUFACTURER_OPPO = "oppo";
    private static final String MANUFACTURER_VIVO = "vivo";
    private static final String MANUFACTURER_SONY = "sony";//索尼
    private static final String MANUFACTURER_LG = "lg";
    private static final String MANUFACTURER_SAMSUNG = "samsung";//三星
    private static final String MANUFACTURER_LETV = "Letv";//乐视
    private static final String MANUFACTURER_ZTE = "ZTE";//中兴
    private static final String MANUFACTURER_YULONG = "YuLong";//酷派
    private static final String MANUFACTURER_LENOVO = "LENOVO";//联想

    /**
     * 跳设置界面
     *
     * @param context context
     */
    public static void go2Setting(Context context) {
        ISetting iSetting;

        switch (Build.BRAND.toLowerCase()) {
            case MANUFACTURER_VIVO:
                iSetting = new ViVo(context);
                break;
            case MANUFACTURER_OPPO:
                iSetting = new OPPO(context);
                break;
            case MANUFACTURER_HONGMI:
            case MANUFACTURER_XIAOMI:
                iSetting = new XiaoMi(context);
                break;
            case MANUFACTURER_HUAWEI:
            case MANUFACTURER_HONOR:
                iSetting = new HuaWei(context);
                break;
            case MANUFACTURER_MEIZU:
                iSetting = new Meizu(context);
                break;
            default:
                iSetting = new Default(context);
                break;
        }
        if (iSetting.getSetting() == null) return;
        context.startActivity(iSetting.getSetting());
    }

}
