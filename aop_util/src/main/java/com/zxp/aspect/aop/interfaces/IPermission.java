package com.zxp.aspect.aop.interfaces;

import java.util.List;

/**
 * @author zhangxiaoping
 * @date 2019/10/24
 * description:
 */
public interface IPermission {
    //同意权限
    void PermissionGranted();

    //拒绝权限并且选中不再提示
    void PermissionDenied(int requestCode, List<String> denyList);

    //取消权限
    void PermissionCanceled(int requestCode);
}
