package com.wj.baseutils.presenter;

import com.wj.base.base.BaseListener;
import com.wj.baseutils.bean.HomeTagBean;
import com.wj.baseutils.contract.HomeContract;

/**
 * Created by wj on 2018/1/14.
 */

public class HomePresenterImpl extends HomeContract.HomePresenter{

    @Override
    public void loadData(boolean isRefresh) {
        final HomeContract.HomeView mView = getView();
        if (mView == null) return;

        mModel.loadTag(new BaseListener<HomeTagBean>() {
            @Override
            public void onSuccess(HomeTagBean result) {
                mView.setTagData(result);
            }

            @Override
            public void onError(String msg) {

            }
        });
    }

}
