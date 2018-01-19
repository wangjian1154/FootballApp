package com.wj.baseutils.ui.adapter;

import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wj.base.utils.StringUtils;
import com.wj.baseutils.R;
import com.wj.baseutils.bean.HomeTagBean;

import java.util.List;

/**
 * Created by wj on 2018/1/19.
 */

public class CategoryAdapter extends BaseQuickAdapter<HomeTagBean.DataBean, BaseViewHolder> {

    public CategoryAdapter(@Nullable List<HomeTagBean.DataBean> data) {
        super(R.layout.item_tag, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeTagBean.DataBean item) {
        TextView tvTag = helper.getView(R.id.tv_tag);
        tvTag.setText(StringUtils.setStr(item.name));
    }
}
