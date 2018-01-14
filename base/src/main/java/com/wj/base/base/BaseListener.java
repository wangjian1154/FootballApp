package com.wj.base.base;

/**
 * Created by wj on 2018/1/14.
 * 用于数据从 M 到 V 的层间传递。
 */

public interface BaseListener<T> {

    void onSuccess(T result);

    void onError(String msg);
}
