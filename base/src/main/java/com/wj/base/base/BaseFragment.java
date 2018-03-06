package com.wj.base.base;

import android.view.View;

/**
 * Created by wj on 2018/1/6.
 * MVP基类
 */

public abstract class BaseFragment<T extends BasePresenter, M extends BaseModel> extends SimpleFragment {

    protected T mPresenter;
    protected M mModel;

    @Override
    protected void onViewCreated(View view) {
        super.onViewCreated(view);
        //初始化Presenter
        mPresenter = createPresenter();
        mModel = createModel();
        //presenter与View绑定
        if (null != mPresenter && mModel != null) {
            mPresenter.attachView(mModel, this);
        }
    }

    /**
     * 创建presenter
     *
     * @return
     */
    protected abstract T createPresenter();

    protected abstract M createModel();

    @Override
    public void onDestroyView() {
        //presenter与activity解绑定
        if (null != mPresenter) {
            mPresenter.detachView();
            mPresenter = null;
        }
        super.onDestroyView();
    }
}
