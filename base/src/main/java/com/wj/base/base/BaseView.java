package com.wj.base.base;

/**
 * Created by wj on 2018/1/6.
 */

public interface BaseView {

    void showErrorMsg(String msg);

    void stateError();

    void stateEmpty();

    void stateLoading();
}
