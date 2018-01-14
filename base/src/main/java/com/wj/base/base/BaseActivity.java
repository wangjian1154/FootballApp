package com.wj.base.base;

/**
 * MVP基类
 *
 * @param <V> 子activity的view接口
 * @param <T> 子activity关联的presenter： T extends BasePresenter<V>
 */
public abstract class BaseActivity<V, T extends BasePresenter<V>> extends SimpleActivity {

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
    protected void onDestroy() {
        //presenter与activity解绑定
        if (null != mPresenter) {
            mPresenter.detacheView();
            mPresenter = null;
        }
        super.onDestroy();
    }
}
