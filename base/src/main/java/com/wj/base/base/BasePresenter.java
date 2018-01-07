package com.wj.base.base;

/**
 * Created by wj on 2018/1/6.
 */

public interface BasePresenter<T extends BaseView> {

    void attachView(T view);

    void detachView();
}
