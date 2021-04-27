package com.example.mymvp;

import android.app.Application;

import com.example.mymvp.base.BaseActivity;
import com.taobao.update.adapter.UpdateAdapter;
import com.taobao.update.apk.ApkUpdater;
import com.taobao.update.common.Config;
import com.taobao.update.common.framework.UpdateRuntime;
import com.taobao.update.datasource.UpdateDataSource;
import com.tencent.imsdk.v2.V2TIMManager;
import com.tencent.imsdk.v2.V2TIMSDKConfig;
import com.tencent.imsdk.v2.V2TIMSDKListener;


public class MyApplication extends Application {


    public static BaseActivity mContext;
    //静态单例
    public static MyApplication instances;

    @Override
    public void onCreate() {
        super.onCreate();
        instances = this;
        //初始化阿里移动devops
        initUpdate();
        //初始化腾讯IM
        initTUIKit();
    }

    private void initTUIKit() {
        // 1. 从 IM 控制台获取应用 SDKAppID，详情请参考 SDKAppID。
        // 2. 初始化 config 对象
        V2TIMSDKConfig config = new V2TIMSDKConfig();
        // 3. 指定 log 输出级别，详情请参考 SDKConfig。
        config.setLogLevel(V2TIMSDKConfig.V2TIM_LOG_INFO);
        // 4. 初始化 SDK 并设置 V2TIMSDKListener 的监听对象。
        // initSDK 后 SDK 会自动连接网络，网络连接状态可以在 V2TIMSDKListener 回调里面监听。
        V2TIMManager.getInstance().initSDK(this, 123, config, new V2TIMSDKListener() {
            // 5. 监听 V2TIMSDKListener 回调
            @Override
            public void onConnecting() {
                // 正在连接到腾讯云服务器
            }

            @Override
            public void onConnectSuccess() {
                // 已经成功连接到腾讯云服务器
            }

            @Override
            public void onConnectFailed(int code, String error) {
                // 连接腾讯云服务器失败
            }
        });

    }

    public static MyApplication getInstances() {
        return instances;
    }

    private void initUpdate() {
        //以下引号部分需要客户根据自己的应用进行配置（）
        Config config = new Config();
        config.group = "333424457" + "@android";   //填写appkey
        config.ttid = "渠道号";    //渠道号
        config.isOutApk = false;
        config.appName = "workermvp"; //app name
        UpdateRuntime.init(this, config.ttid, config.appName, config.group);
        ApkUpdater apkupdate = new ApkUpdater(getApplicationContext(), "333424457", "af55e77782e648e69bda49943dce5ee2", config.group, "渠道号", config.ttid);
        UpdateAdapter updateAdapter = new UpdateAdapter();
        UpdateDataSource.getInstance().init(this, config.group, config.ttid, config.isOutApk, "333424457", "af55e77782e648e69bda49943dce5ee2", "渠道号", updateAdapter);
        UpdateDataSource.getInstance().startUpdate(false);
    }

}
