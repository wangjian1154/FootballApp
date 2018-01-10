package com.wj.base.base;

import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by wj on 2018/1/6.
 */

public abstract class BaseActivity extends AppCompatActivity {

    private Unbinder bind;
    protected boolean isCreate = false;
    protected boolean isDestroy = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        bind = ButterKnife.bind(this);
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this);
        isCreate = true;

        initViewAndEvent(savedInstanceState);
    }

    protected abstract void initViewAndEvent(Bundle savedInstanceState);

    @Override
    protected void onDestroy() {
        super.onDestroy();
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
