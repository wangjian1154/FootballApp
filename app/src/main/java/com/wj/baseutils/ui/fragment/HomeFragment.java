package com.wj.baseutils.ui.fragment;

import android.os.Bundle;
import android.util.Log;

import com.orhanobut.logger.Logger;
import com.wj.base.base.BaseFragment;
import com.wj.base.base.BasePresenter;
import com.wj.baseutils.R;

/**
 * Created by wj on 2018/1/11.
 * 首页
 */

public class HomeFragment extends BaseFragment {

    @Override
    protected void initViewAndEvent(Bundle savedInstanceState) {
        Log.i("info", "HomeFragment");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }
}
