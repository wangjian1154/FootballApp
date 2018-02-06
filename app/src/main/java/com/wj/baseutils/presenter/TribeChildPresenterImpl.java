package com.wj.baseutils.presenter;

import com.wj.base.base.BaseListener;
import com.wj.baseutils.bean.TribeCategoryBean;
import com.wj.baseutils.contract.TribeChildContract;

/**
 * Created by wj on 2018/1/26.
 */

public class TribeChildPresenterImpl extends TribeChildContract.TribePresenter {

    @Override
    public void loadData() {
        final TribeChildContract.TribeChildView mView = getView();
        if (mView == null) return;

        mModel.loadCategory(new BaseListener<TribeCategoryBean>() {
            @Override
            public void onSuccess(TribeCategoryBean result) {
                mView.setCategory(result);
            }

            @Override
            public void onError(String msg) {

            }
        });
    }
}
