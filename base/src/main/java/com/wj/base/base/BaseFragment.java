package com.wj.base.base;

/**
 * Created by wj on 2018/1/6.
 * MVP基类
 */

public abstract class BaseFragment<T extends BasePresenter> extends SimpleFragment implements BaseView {

    protected T mPresenter;

    @Override
    protected void onViewCreated() {
        super.onViewCreated();
        if (mPresenter != null)
            mPresenter.attachView(this);
    }

    @Override
    public void onDestroyView() {
        if (mPresenter != null)
            mPresenter.detachView();
        super.onDestroyView();
    }
}
