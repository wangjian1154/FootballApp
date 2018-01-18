package com.wj.baseutils.presenter;

import com.wj.base.base.BaseListener;
import com.wj.baseutils.bean.HomeTagBean;
import com.wj.baseutils.contract.CategoryContract;

/**
 * Created by wj on 2018/1/18.
 */

public class CategoryPresenterImpl extends CategoryContract.CategoryPresenter {

    @Override
    public void loadData() {
        final CategoryContract.CategoryView mView = getView();
        if (mView == null) return;

        mModel.loadData(new BaseListener<HomeTagBean>() {
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
