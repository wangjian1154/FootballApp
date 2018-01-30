package com.wj.baseutils.presenter;

import com.wj.base.base.BaseListener;
import com.wj.baseutils.bean.HotDiscussionBean;
import com.wj.baseutils.contract.TribeRecommendContract;

/**
 * Created by wj on 2018/1/30.
 * 部落推荐热议
 */

public class TribeRecommendPresenterImpl extends TribeRecommendContract.TribeRecommendPresenter {

    @Override
    public void loadData(boolean isRefresh) {
        final TribeRecommendContract.TribeRecommendView mView = getView();
        if (mView == null) return;

        mModel.loadDiscussuion(new BaseListener<HotDiscussionBean>() {
            @Override
            public void onSuccess(HotDiscussionBean result) {
                mView.setDiscussion(result);
            }

            @Override
            public void onError(String msg) {

            }
        });


    }
}
