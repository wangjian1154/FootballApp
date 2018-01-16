package com.wj.baseutils.presenter;

import com.wj.base.base.BaseListener;
import com.wj.baseutils.bean.HomeDataBean;
import com.wj.baseutils.contract.HomeSupportContract;

/**
 * Created by wj on 2018/1/15.
 */

public class HomeSupportPresenterImpl extends HomeSupportContract.HomeSupportPresenter {
    @Override
    public void loadData(final boolean isRefresh) {
        final HomeSupportContract.HomeSupportView mView = getView();
        if (mView == null) return;

        mModel.loadHomeData(new BaseListener<HomeDataBean>() {
            @Override
            public void onSuccess(HomeDataBean result) {
                mView.setTopNewsData(isRefresh,result);
            }

            @Override
            public void onError(String msg) {

            }
        });
    }
}
