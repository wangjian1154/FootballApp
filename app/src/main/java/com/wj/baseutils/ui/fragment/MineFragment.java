package com.wj.baseutils.ui.fragment;

import android.os.Bundle;
import android.util.Log;

import com.orhanobut.logger.Logger;
import com.wj.base.base.BaseFragment;
import com.wj.base.base.BasePresenter;
import com.wj.base.base.SimpleFragment;
import com.wj.baseutils.R;

/**
 * Created by wj on 2018/1/11.
 * 我的
 */

public class MineFragment extends SimpleFragment {
    @Override
    protected void initViewAndEvent(Bundle savedInstanceState) {
        Log.i("info", "MineFragment");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

}
