package com.example.mymvp.ui.activity;

import com.example.mymvp.base.BasePresenter;
import com.example.mymvp.base.IView;

/**
 * @author wyw
 * 创建日期：2021/4/22 9:51
 * 描述：My Application
 */

public class MainPresenter extends BasePresenter<MainView> {

    public MainPresenter(MainView view) {
        super(view);
    }

    public void setLogin(){
        mView.setLogin();
    }
}
