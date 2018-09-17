package com.example.administrator.myapplication.utils;

import android.app.Application;
import android.util.Log;

import org.xutils.BuildConfig;
import org.xutils.x;

/**
 * Created by Administrator on 2018/9/17.
 */
// 必须继承Android自带Application类型，app初始化都在此处完成
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("test","onCrate........");
        // 初始化xutils3框架
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG); // 是否输出debug日志, 开启debug会影响性能.
    }
}
