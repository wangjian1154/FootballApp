package com.wj.baseutils.adapter;

import android.support.annotation.Nullable;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wj.base.utils.StringUtils;
import com.wj.baseutils.R;
import com.wj.baseutils.bean.TribeCategoryBean;

import java.util.List;

/**
 * Created by wj on 2018/2/6.
 * 部落左边分类适配器
 */

public class TribeCategoryTypeAdapter extends BaseQuickAdapter<TribeCategoryBean.
        DataBean.CategoriesBean, BaseViewHolder> {

    public TribeCategoryTypeAdapter(@Nullable List<TribeCategoryBean.DataBean.CategoriesBean> data) {
        super(R.layout.item_list_tribe_category_left_type, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TribeCategoryBean.DataBean.CategoriesBean item) {
        LinearLayout ll_type = helper.getView(R.id.ll_type);
        TextView tvType = helper.getView(R.id.tv_type);
        tvType.setText(StringUtils.setStr(item.name));
    }
}
