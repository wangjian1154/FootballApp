package com.wj.baseutils.presenter;

import com.wj.base.base.BaseListener;
import com.wj.baseutils.contract.MineContract;

/**
 * Created by wj on 2018/3/26.
 */

public class MinePresenterImpl extends MineContract.MinePresenter {

    @Override
    public void setData() {
        MineContract.MineView mView = getView();
        if (mView == null) return;
        mModel.setData(new BaseListener<Object>() {
            @Override
            public void onSuccess(Object result) {
                mView.setBackgroundBlur();
            }

            @Override
            public void onError(String msg) {

            }
        });
    }
}
