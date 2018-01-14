package com.wj.base.base;

/**
 * MVP基类
 */
public abstract class BaseActivity<T extends BasePresenter, M extends BaseModel> extends SimpleActivity {

    protected T mPresenter;
    protected M mModel;

    @Override
    protected void onViewCreated() {
        super.onViewCreated();
        //初始化Presenter
        mPresenter = createPresenter();
        //presenter与View绑定
        if (null != mPresenter) {
            mPresenter.attachView(mModel, this);
        }
    }

    /**
     * 创建presenter
     *
     * @return
     */
    protected abstract T createPresenter();

    @Override
    protected void onDestroy() {
        //presenter与activity解绑定
        if (null != mPresenter) {
            mPresenter.detachView();
            mPresenter = null;
        }
        super.onDestroy();
    }
}
