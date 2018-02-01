package com.wj.baseutils.contract;

import com.wj.base.base.BaseListener;
import com.wj.base.base.BaseModel;
import com.wj.base.base.BasePresenter;
import com.wj.base.base.BaseView;
import com.wj.baseutils.bean.CircleBean;
import com.wj.baseutils.bean.HotDiscussionBean;

/**
 * Created by wj on 2018/1/30.
 */

public interface TribeRecommendContract {

    interface TribeRecommendModel extends BaseModel {
        //热议
        void loadDiscussuion(BaseListener<HotDiscussionBean> listener);

        //朋友圈
        void loadCircle(String lastId,String pageSize,BaseListener<CircleBean> listener);
    }

    interface TribeRecommendView extends BaseView {

        void setDiscussion(HotDiscussionBean discussionBean);

        void setCircle(CircleBean circleBean);
    }

    abstract class TribeRecommendPresenter extends BasePresenter<TribeRecommendModel, TribeRecommendView> {
        public abstract void loadData(String lastId,String pageSize,boolean isRefresh);
    }
}
