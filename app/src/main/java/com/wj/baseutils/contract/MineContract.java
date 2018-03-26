package com.wj.baseutils.contract;

import com.wj.base.base.BaseListener;
import com.wj.base.base.BaseModel;
import com.wj.base.base.BasePresenter;
import com.wj.base.base.BaseView;

/**
 * Created by wj on 2018/3/26.
 */

public interface MineContract {

    interface MineModel extends BaseModel {
        void setData(BaseListener<Object> listener);
    }

    interface MineView extends BaseView {
        void setBackgroundBlur();
    }

    abstract class MinePresenter extends BasePresenter<MineModel, MineView> {
        public abstract void setData();
    }
}
