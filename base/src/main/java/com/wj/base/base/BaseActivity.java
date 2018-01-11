package com.wj.base.base;

/**
 * Created by wj on 2018/1/6.
 * MVP基类
 */

public abstract class BaseActivity<T extends BasePresenter> extends SimpleActivity implements BaseView {

    protected T mPresenter;

    @Override
    protected void onViewCreated() {
        if (mPresenter != null)
            mPresenter.attachView(this);
    }

    @Override
    public void showErrorMsg(String msg) {

    }

    @Override
    public void stateError() {

    }

    @Override
    public void stateEmpty() {

    }

    @Override
    public void stateLoading() {

    }

    @Override
    protected void onDestroy() {
        if (mPresenter != null)
            mPresenter.detachView();
        super.onDestroy();
    }
}
