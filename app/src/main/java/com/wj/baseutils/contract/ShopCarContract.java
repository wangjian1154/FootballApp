package com.wj.baseutils.contract;

import com.wj.base.base.BaseModel;
import com.wj.base.base.BasePresenter;
import com.wj.base.base.BaseView;

/**
 * Created by wj on 2018/4/11.
 */

public interface ShopCarContract {

    interface ShopCarModel extends BaseModel{

    }

    interface ShopCarView extends BaseView{

    }

    abstract class ShopCarPresenter extends BasePresenter<ShopCarModel,ShopCarView>{

    }
}
