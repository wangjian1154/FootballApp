package com.wj.base.base;

/**
 * Created by wj on 2018/1/6.
 * MVP基类
 */

public abstract class BaseFragment<V, T extends BasePresenter<V>> extends SimpleFragment {

    protected T mPresenter;

    @Override
    protected void onViewCreated() {
        super.onViewCreated();
        //初始化Presenter
        mPresenter = createPresenter();
        //presenter与View绑定
        if (null != mPresenter) {
            mPresenter.attachView((V) this);
        }
    }

    /**
     * 创建presenter
     *
     * @return
     */
    protected abstract T createPresenter();

    @Override
    public void onDestroyView() {
        //presenter与activity解绑定
        if (null != mPresenter) {
            mPresenter.detacheView();
            mPresenter = null;
        }
        super.onDestroyView();
    }
}
