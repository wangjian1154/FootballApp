package com.wj.base.base;

import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by wj on 2018/1/11.
 * 无MVP基类
 */

public abstract class SimpleFragment extends Fragment {

    private Unbinder bind;
    protected boolean isCreate = false;
    protected boolean isDestroy = false;
    protected boolean isFirstVisible = true;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View itemView = inflater.inflate(getLayoutId(), container, false);
        bind = ButterKnife.bind(this, itemView);
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this);
        isCreate = true;
        boolean isVis = isHidden() || getUserVisibleHint();

        onViewCreated();

        initViewAndEvent(savedInstanceState);

        if (isVis && isFirstVisible) {
            lazyLoad();
            isFirstVisible = false;
        }

        return itemView;
    }

    protected void onViewCreated() {
    }

    /**
     * 初始化界面
     *
     * @param savedInstanceState
     */
    protected abstract void initViewAndEvent(Bundle savedInstanceState);

    /**
     * 数据懒加载
     */
    protected void lazyLoad() {

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            onVisible();
        } else {
            onInVisible();
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            onVisible();
        } else {
            onInVisible();
        }
    }

    /**
     * 当界面不可见时的操作
     */
    protected void onInVisible() {

    }

    /**
     * 当界面可见时的操作
     */
    protected void onVisible() {
        if (isFirstVisible && isResumed()) {
            lazyLoad();
            isFirstVisible = false;
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (bind != null)
            bind.unbind();
        if (EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().unregister(this);
        isDestroy = true;

    }

    protected abstract int getLayoutId();

    @Subscribe
    public void onEventMainThread(Message msg) {
        if (msg != null) {
            switch (msg.what) {

            }
        }
    }
}
