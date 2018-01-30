package com.wj.baseutils.contract;

import com.wj.base.base.BaseListener;
import com.wj.base.base.BaseModel;
import com.wj.base.base.BasePresenter;
import com.wj.base.base.BaseView;
import com.wj.baseutils.bean.HotDiscussionBean;

/**
 * Created by wj on 2018/1/30.
 */

public interface TribeRecommendContract {

    interface TribeRecommendModel extends BaseModel {
        //热议
        void loadDiscussuion(BaseListener<HotDiscussionBean> listener);
    }

    interface TribeRecommendView extends BaseView {
        void setDiscussion(HotDiscussionBean discussionBean);
    }

    abstract class TribeRecommendPresenter extends BasePresenter<TribeRecommendModel, TribeRecommendView> {
        public abstract void loadData(boolean isRefresh);
    }
}
