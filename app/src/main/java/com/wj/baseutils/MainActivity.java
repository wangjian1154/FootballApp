package com.wj.baseutils;

import android.os.Bundle;

import com.wj.base.base.BaseActivity;

public class MainActivity<MainPresenter> extends BaseActivity {

    @Override
    protected void initViewAndEvent(Bundle savedInstanceState) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

}
