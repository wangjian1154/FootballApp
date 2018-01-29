package com.wj.baseutils.ui.fragment;

import android.os.Bundle;

import com.wj.base.base.BaseFragment;
import com.wj.baseutils.R;
import com.wj.baseutils.contract.TribeChildContract;
import com.wj.baseutils.model.TribeChildModelImpl;
import com.wj.baseutils.presenter.TribeChildPresenterImpl;

/**
 * Created by wj on 2018/1/23.
 * 部落中的部落
 */

public class TribeChildFragment extends BaseFragment<TribeChildPresenterImpl, TribeChildModelImpl>
        implements TribeChildContract.TribeChildView {

    @Override
    protected TribeChildPresenterImpl createPresenter() {
        return new TribeChildPresenterImpl();
    }

    @Override
    protected TribeChildModelImpl createModel() {
        return new TribeChildModelImpl();
    }

    @Override
    protected void initViewAndEvent(Bundle savedInstanceState) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_tribe_child;
    }
}
