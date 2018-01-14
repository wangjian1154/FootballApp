package com.wj.base.base;

import java.lang.ref.WeakReference;

/**
 * Created by wj on 2018/1/6.
 */

public abstract class BasePresenter<M, V> {

    protected M mModel;

    protected WeakReference<V> mViewRef;

    protected void attachView(M model, V view) {
        mModel = model;
        mViewRef = new WeakReference<>(view);
    }

    protected V getView() {
        return isViewAttached() ? mViewRef.get() : null;
    }

    protected boolean isViewAttached() {
        return null != mViewRef && null != mViewRef.get();
    }

    protected void detachView() {
        if (null != mViewRef) {
            mViewRef.clear();
            mViewRef = null;
        }
    }

}
