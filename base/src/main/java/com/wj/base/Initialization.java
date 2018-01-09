package com.wj.base;

import android.content.Context;

/**
 * Created by wj on 2018/1/6.
 * 工具类初始化
 */

public class Initialization {

    public static final boolean DEBUG = true;

    public Initialization(Context context) {
        this.context = context;
    }

    private static Context context;

    public static Context getContext() {
        return context;
    }





}
