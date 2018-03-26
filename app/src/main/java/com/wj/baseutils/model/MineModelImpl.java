package com.wj.baseutils.model;

import com.wj.base.base.BaseListener;
import com.wj.baseutils.contract.MineContract;

/**
 * Created by wj on 2018/3/26.
 */

public class MineModelImpl implements MineContract.MineModel {

    @Override
    public void setData(BaseListener<Object> listener) {
        listener.onSuccess(null);
    }
}
