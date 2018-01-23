package com.wj.baseutils.ui.fragment;

import android.os.Bundle;
import android.util.Log;

import com.orhanobut.logger.Logger;
import com.wj.base.base.BaseFragment;
import com.wj.base.base.BasePresenter;
import com.wj.base.base.SimpleFragment;
import com.wj.base.views.tablayout.ColorTrackTabLayout;
import com.wj.baseutils.R;

import butterknife.BindView;

/**
 * Created by wj on 2018/1/11.
 * 部落
 */

public class TribeFragment extends SimpleFragment {

    @BindView(R.id.tab_layout_tribe)
    ColorTrackTabLayout trackTabLayout;

    @Override
    protected void initViewAndEvent(Bundle savedInstanceState) {


    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_tribe;
    }

}
