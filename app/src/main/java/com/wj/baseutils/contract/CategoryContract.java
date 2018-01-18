package com.wj.baseutils.contract;

import com.wj.base.base.BaseListener;
import com.wj.base.base.BaseModel;
import com.wj.base.base.BasePresenter;
import com.wj.base.base.BaseView;
import com.wj.baseutils.bean.HomeTagBean;

/**
 * Created by wj on 2018/1/18.
 */

public interface CategoryContract {

    interface CategoryModel extends BaseModel {
        void loadData(BaseListener<HomeTagBean> listener);
    }

    interface CategoryView extends BaseView {
        void setTagData(HomeTagBean tagBean);
    }

    abstract class CategoryPresenter extends BasePresenter<CategoryModel, CategoryView> {
        public abstract void loadData();
    }

}
