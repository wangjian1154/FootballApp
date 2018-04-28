package com.wj.base.base;

import android.os.Bundle;
import android.os.Message;

import com.wj.base.views.LoadingProgress;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by wj on 2018/1/11.
 * 无MVP基类
 */

public abstract class SimpleActivity extends PermissionActivity {

    private Unbinder bind;
    protected boolean isCreate = false;
    protected boolean isDestroy = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        StatusBarUtil.setTranslucent(this);
        setContentView(getLayoutId());
        bind = ButterKnife.bind(this);
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this);
        isCreate = true;

        onViewCreated();

        initViewAndEvent(savedInstanceState);
    }

    protected void onViewCreated() {}

    protected abstract int getLayoutId();

    protected abstract void initViewAndEvent(Bundle savedInstanceState);

    /**
     * 是否显示加载对话框
     *
     * @param active
     */
    public void setProgressIndicator(boolean active) {
        if (active) {
            LoadingProgress.getInstance(this).show();
        } else {
            LoadingProgress.getInstance(this).dismiss();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (bind != null)
            bind.unbind();
        if (EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().unregister(this);
        isDestroy = true;
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(Message msg) {
        if (msg != null) {
            switch (msg.what) {

            }
        }
    }
}
