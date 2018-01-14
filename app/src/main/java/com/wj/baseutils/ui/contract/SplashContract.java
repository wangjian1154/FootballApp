package com.wj.baseutils.ui.contract;

import android.content.Context;
import android.widget.RelativeLayout;

import com.wj.base.base.IBaseView;

/**
 * Created by wj on 2018/1/12.
 */

public interface SplashContract {

    //IBaseView定义了一些View的公共方法，根据实际需要可自行定义。
    interface View extends IBaseView {

    }

    interface Presenter{

        void startAnim(RelativeLayout view);

        void toMain(Context context);
    }
}
