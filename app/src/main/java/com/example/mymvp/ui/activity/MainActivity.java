package com.example.mymvp.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.mymvp.R;
import com.example.mymvp.base.BaseActivity;
import com.tencent.qcloud.tim.uikit.modules.conversation.ConversationLayout;

public class MainActivity extends BaseActivity<MainPresenter> implements MainView{


    private ConversationLayout conversationLayout;

    @Override
    protected void initView() {
        conversationLayout = findViewById(R.id.conversation_layout);
        conversationLayout.initDefault();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected MainPresenter getPresenter() {
        return new MainPresenter(this);
    }

    @Override
    protected void initDataAndEvent() {
        mPresenter.setLogin();

    }

    @Override
    public void setLogin() {

    }
}