package com.wj.baseutils.contract;

import com.wj.base.base.BaseListener;
import com.wj.base.base.BaseModel;
import com.wj.base.base.BasePresenter;
import com.wj.base.base.BaseView;
import com.wj.baseutils.bean.HomeDataBean;

/**
 * Created by wj on 2018/1/15.
 */

public interface HomeSupportContract {

    interface HomeSupportModel extends BaseModel {
        void loadHomeData(BaseListener<HomeDataBean> listener);
    }

    interface HomeSupportView extends BaseView {
        void setTopNewsData(HomeDataBean homeDataBean);//头条
    }

    abstract class HomeSupportPresenter extends BasePresenter<HomeSupportModel, HomeSupportView> {
        public abstract void loadData(boolean isRefresh);
    }
}
