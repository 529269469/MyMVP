package com.example.mymvp;

import android.app.Application;

import com.example.mymvp.base.BaseActivity;
import com.taobao.update.adapter.UpdateAdapter;
import com.taobao.update.apk.ApkUpdater;
import com.taobao.update.common.Config;
import com.taobao.update.common.framework.UpdateRuntime;
import com.taobao.update.datasource.UpdateDataSource;


public class MyApplication extends Application {


    public static BaseActivity mContext;
    //静态单例
    public static MyApplication instances;

    @Override
    public void onCreate() {
        super.onCreate();
        instances = this;
        initUpdate();

    }

    public static MyApplication getInstances() {
        return instances;
    }

    private void initUpdate() {
        //以下引号部分需要客户根据自己的应用进行配置（）
        Config config = new Config();
        config.group = "appkey" + "@android";   //填写appkey
        config.ttid = "渠道号";    //渠道号
        config.isOutApk = false;
        config.appName = "appname"; //app name
        UpdateRuntime.init(this, config.ttid, config.appName, config.group);
        ApkUpdater apkupdate = new ApkUpdater(getApplicationContext(), "appkey", "appsecret", config.group, "渠道号", config.ttid);
        UpdateAdapter updateAdapter = new UpdateAdapter();
        UpdateDataSource.getInstance().init(this, config.group, config.ttid, config.isOutApk, "appkey", "appsecret", "渠道号", updateAdapter);
        UpdateDataSource.getInstance().startUpdate(false);
    }

}