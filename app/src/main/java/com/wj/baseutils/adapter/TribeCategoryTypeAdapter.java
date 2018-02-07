package com.wj.baseutils.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wj.base.utils.StringUtils;
import com.wj.baseutils.R;
import com.wj.baseutils.app.App;
import com.wj.baseutils.bean.TribeCategoryBean;

import java.util.List;

/**
 * Created by wj on 2018/2/6.
 * 部落左边分类适配器
 */

public class TribeCategoryTypeAdapter extends BaseQuickAdapter<TribeCategoryBean.
        DataBean.CategoriesBean, BaseViewHolder> {

    private int mSelectedPos = -1;//当前选中的Position

    private int grayColor = App.getContext().getResources().getColor(R.color.page_bg);


    public TribeCategoryTypeAdapter(@Nullable List<TribeCategoryBean.DataBean.CategoriesBean> data) {
        super(R.layout.item_list_tribe_category_left_type, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TribeCategoryBean.DataBean.CategoriesBean item) {
        LinearLayout ll_type = helper.getView(R.id.ll_type);
        TextView tvType = helper.getView(R.id.tv_type);
        tvType.setText(StringUtils.setStr(item.name));
        if (mSelectedPos == helper.getAdapterPosition()) {
            ll_type.setBackgroundColor(Color.WHITE);
            tvType.setTextColor(Color.BLACK);
        } else {
            ll_type.setBackgroundColor(grayColor);
            tvType.setTextColor(mContext.getResources().getColor(R.color.font_gay));
        }
    }

    public void setSelection(int position) {
        this.mSelectedPos = position;
        notifyDataSetChanged();
    }

}
