package com.example.mymvp.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

/**
 * 基础fragment
 *
 * @param <P>
 */
public abstract class BaseFragment<P extends IPresenter> extends LazyFragment {
    protected View mRootView;

    private boolean isPrepared;
    private boolean isFirst = true;

    protected P mPresenter;
    // 两次点击按钮之间的点击间隔不能少于500毫秒
    protected static final int MIN_CLICK_DELAY_TIME = 500;
    protected static long lastClickTime;

    public boolean isDoubleClick() {
        long curClickTime = System.currentTimeMillis();
        if ((curClickTime - lastClickTime) < MIN_CLICK_DELAY_TIME) {
            return true;
        }
        lastClickTime = curClickTime;
        return false;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EventBus.getDefault().register(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mPresenter == null) {
            mPresenter = getPresenter();
        }
        if (mRootView == null) {
            mRootView = inflater.inflate(getLayoutId(), null);
            initEventAndData();
            isPrepared = true;
            lazyLoad();
        }
        ViewGroup parent = (ViewGroup) mRootView.getParent();
        if (parent != null) {
            parent.removeView(mRootView);
        }

        return mRootView;
    }


    protected abstract void initEventAndData();

    protected abstract int getLayoutId();

    protected abstract P getPresenter();

    @Override
    public void onDestroy() {
        if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter = null;
        }
        super.onDestroy();
    }


    @Override
    protected void lazyLoad() {
        if (!isVisible || isPrepared) {
            return;
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


}
