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
 * 数据
 */

public class DataFragment extends SimpleFragment {

    @Override
    protected void initViewAndEvent(Bundle savedInstanceState) {
        Log.i("info", "DataFragment");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_data;
    }

}
