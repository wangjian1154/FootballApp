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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View itemView = inflater.inflate(getLayoutId(), container, false);
        bind = ButterKnife.bind(this, itemView);
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this);
        isCreate = true;

        onViewCreated();
        
        initViewAndEvent(savedInstanceState);
        
        return itemView;
    }

    protected void onViewCreated() {
    }

    protected abstract void initViewAndEvent(Bundle savedInstanceState);

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
