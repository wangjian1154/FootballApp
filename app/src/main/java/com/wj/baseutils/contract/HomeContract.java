package com.wj.baseutils.contract;

import com.wj.base.base.BaseListener;
import com.wj.base.base.BaseModel;
import com.wj.base.base.BasePresenter;
import com.wj.base.base.BaseView;
import com.wj.baseutils.bean.HomeTagBean;

/**
 * Created by wj on 2018/1/14.
 */

public interface HomeContract {

    interface HomeModel extends BaseModel {
        void loadTag(BaseListener<HomeTagBean> listener);
    }

    interface HomeView extends BaseView {

        void setTagData(HomeTagBean tagBean);
    }

    abstract class HomePresenter extends BasePresenter<HomeModel, HomeView> {

        public abstract void loadData(boolean isRefresh);
    }

}
