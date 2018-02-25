package com.wj.base.base;

import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wj.base.utils.HandleBackUtil;
import com.wj.base.views.LoadingProgress;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by wj on 2018/1/11.
 * 无MVP基类
 */

public abstract class SimpleFragment extends Fragment implements HandleBackUtil.HandleBackInterface  {

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
        return itemView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        onViewCreated();
        initViewAndEvent(savedInstanceState);
        boolean isVis = isHidden() || getUserVisibleHint();
        if (isVis && isFirstVisible) {
            lazyLoad();
            isFirstVisible = false;
        }
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
     * 是否显示加载对话框
     *
     * @param active
     */
    public void setProgressIndicator(boolean active) {
        if (active) {
            LoadingProgress.getInstance(getActivity()).show();
        } else {
            LoadingProgress.getInstance(getActivity()).dismiss();
        }
    }

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

    @Override
    public boolean onBackPressed() {
        return HandleBackUtil.handleBackPress(this);
    }

    @Subscribe
    public void onEventMainThread(Message msg) {
        if (msg != null) {
            switch (msg.what) {

            }
        }
    }

}
