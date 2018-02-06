package com.wj.baseutils.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wj.base.utils.ImageLoadUtils;
import com.wj.base.utils.StringUtils;
import com.wj.baseutils.R;
import com.wj.baseutils.bean.TribeCategoryBean;

import java.util.List;

/**
 * Created by wj on 2018/2/6.
 * 部落分类右边列表
 */

public class TribeCategoryListAdapter extends BaseQuickAdapter<TribeCategoryBean.DataBean.
        CategoryGroupsMapBean.ObjBean, BaseViewHolder> {

    public TribeCategoryListAdapter(@Nullable List<TribeCategoryBean.DataBean.CategoryGroupsMapBean.ObjBean> data) {
        super(R.layout.item_list_category_list, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TribeCategoryBean.DataBean.CategoryGroupsMapBean.ObjBean item) {
        ImageView ivPic = helper.getView(R.id.iv_pic);
        TextView tvName = helper.getView(R.id.tv_name);
        ImageLoadUtils.display(mContext, item.icon, ivPic);
        tvName.setText(StringUtils.setStr(item.name));
    }
}
