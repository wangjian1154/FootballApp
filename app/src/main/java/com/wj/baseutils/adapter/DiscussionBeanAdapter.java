package com.wj.baseutils.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wj.base.utils.ImageLoadUtils;
import com.wj.base.utils.StringUtils;
import com.wj.baseutils.R;
import com.wj.baseutils.bean.HotDiscussionBean;

import java.util.List;

/**
 * Created by wj on 2018/1/30.
 */

public class DiscussionBeanAdapter extends BaseQuickAdapter<HotDiscussionBean.DataBean.DiscussionBean
        , BaseViewHolder> {

    public DiscussionBeanAdapter(@Nullable List<HotDiscussionBean.DataBean.DiscussionBean> data) {
        super(R.layout.item_list_disussion, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HotDiscussionBean.DataBean.DiscussionBean item) {
        ImageView imageView = helper.getView(R.id.iv_pic);
        TextView tvTitle = helper.getView(R.id.tv_title);
        TextView tvHotValue = helper.getView(R.id.tv_hot_value);
        String imgUrl = item.toHomeCover;
        if (!StringUtils.isEmpty(imgUrl)) {
            ImageLoadUtils.display(mContext, imgUrl, imageView);
        } else {
            imageView.setImageResource(R.drawable.ic_default);
        }
        tvTitle.setText(StringUtils.setStr(item.title));
        String heatValue = String.format(mContext.getResources().getString(R.string.tribe_hot_value),
                StringUtils.setStr(item.heatValue + ""));
        tvHotValue.setText(heatValue);
    }
}
