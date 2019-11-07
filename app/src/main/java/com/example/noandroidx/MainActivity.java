package com.example.noandroidx;

import android.Manifest;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.zxp.aspect.aop.annotation.NeedPermission;
import com.zxp.aspect.aop.annotation.PermissionCanceled;
import com.zxp.aspect.aop.annotation.PermissionDenied;
import com.zxp.aspect.aop.annotation.SingleClick;
import com.zxp.aspect.aop.bean.CancelBean;
import com.zxp.aspect.aop.bean.DenyBean;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_test).setOnClickListener(new View.OnClickListener() {

            @SingleClick
            @Override
            public void onClick(View v) {
                Log.i("zxp", "click normal");
                callPhone();
            }
        });
        findViewById(R.id.btn_test1).setOnClickListener(new View.OnClickListener() {

            @SingleClick
            @Override
            public void onClick(View v) {
                Log.i("zxp", "click normal");
                callStorage();
            }
        });
        findViewById(R.id.btn_test2).setOnClickListener(new View.OnClickListener() {

            @SingleClick
            @Override
            public void onClick(View v) {
                Log.i("zxp", "click normal");
                callLocation();
            }
        });
        findViewById(R.id.btn_test3).setOnClickListener(new View.OnClickListener() {

            @SingleClick
            @Override
            public void onClick(View v) {
                Log.i("zxp", "click normal");
            }
        });
    }

    private void callLocation() {

    }

    @NeedPermission(permission = Manifest.permission.READ_EXTERNAL_STORAGE, requestCode = 200)
    private void callStorage() {
        Toast.makeText(this, "huoqu quanxian le ", Toast.LENGTH_SHORT).show();
    }

    @NeedPermission(permission = Manifest.permission.CALL_PHONE, requestCode = 100)
    private void callPhone() {
        Toast.makeText(this, "huoqu quanxian le ", Toast.LENGTH_SHORT).show();
    }

    @PermissionDenied
    private void callPhoneDenied(DenyBean denyBean) {
        Log.i("Zxp", "permission denied" + denyBean.getRequestCode());
    }

    @PermissionCanceled
    private void callCancel(CancelBean cancelBean) {
        Log.i("zxp", "per,ission cancel" + cancelBean.getRequestCode());
    }

    @SingleClick()
    public void onClick1(View view) {
        Log.i("zxp", "click1 normal");
    }
}
