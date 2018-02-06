package com.wj.baseutils.contract;

import com.wj.base.base.BaseListener;
import com.wj.base.base.BaseModel;
import com.wj.base.base.BasePresenter;
import com.wj.base.base.BaseView;
import com.wj.baseutils.bean.TribeCategoryBean;

/**
 * Created by wj on 2018/1/26.
 */

public interface TribeChildContract {

    interface TribeChildModel extends BaseModel {
        void loadCategory(BaseListener<TribeCategoryBean> listener);
    }

    interface TribeChildView extends BaseView {
        void setCategory(TribeCategoryBean tribeCategoryBean);
    }

    abstract class TribePresenter extends BasePresenter<TribeChildModel, TribeChildView> {
        public abstract void loadData();
    }

}
