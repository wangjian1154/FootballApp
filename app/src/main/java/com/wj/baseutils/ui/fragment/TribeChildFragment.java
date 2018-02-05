package com.wj.baseutils.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.wj.base.base.BaseFragment;
import com.wj.baseutils.R;
import com.wj.baseutils.contract.TribeChildContract;
import com.wj.baseutils.model.TribeChildModelImpl;
import com.wj.baseutils.presenter.TribeChildPresenterImpl;

import butterknife.BindView;

/**
 * Created by wj on 2018/1/23.
 * 部落中的部落
 */

public class TribeChildFragment extends BaseFragment<TribeChildPresenterImpl, TribeChildModelImpl>
        implements TribeChildContract.TribeChildView {

    @BindView(R.id.recyclerView_category)
    RecyclerView recyclerViewCategory;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

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

    @Override
    public void setCategory() {

    }
}
