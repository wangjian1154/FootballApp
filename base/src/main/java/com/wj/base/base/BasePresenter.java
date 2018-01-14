package com.wj.base.base;

import java.lang.ref.WeakReference;

/**
 * Created by wj on 2018/1/6.
 */

public abstract class BasePresenter<T> {

    /**
     * View的引用，使用弱引用，当弱引用所引用的对象被销毁，软引用也会被释放
     */
    protected WeakReference<T> mViewRef;


    /**
     * Presenter与View关联
     *
     * @param view
     */
    public void attachView(T view) {
        mViewRef = new WeakReference<T>(view);
    }

    /**
     * Presenter与View解除关联
     */
    public void detacheView() {
        if (mViewRef != null) {
            mViewRef.clear();
            mViewRef = null;
        }
    }

    protected T getView() {
        if (mViewRef != null) {
            return mViewRef.get();
        }
        return null;
    }

    /**
     * Presenter与View是否已关联
     *
     * @return
     */
    public boolean isViewAttached() {
        return mViewRef != null && mViewRef.get() != null;
    }

}
