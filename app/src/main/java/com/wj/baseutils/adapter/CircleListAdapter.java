package com.wj.baseutils.adapter;

import android.support.annotation.Nullable;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wj.base.utils.ImageLoadUtils;
import com.wj.base.utils.StringUtils;
import com.wj.baseutils.R;
import com.wj.baseutils.bean.CircleBean;

import java.util.List;

/**
 * Created by wj on 2018/2/1.
 */

public class CircleListAdapter extends BaseQuickAdapter<CircleBean.DataBean, BaseViewHolder> {


    public CircleListAdapter(@Nullable List<CircleBean.DataBean> data) {
        super(R.layout.item_list_circlelist, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CircleBean.DataBean item) {
        ImageView ivAvatar = helper.getView(R.id.iv_avatar);
        ImageView imageView1 = helper.getView(R.id.iv_1);
        ImageView imageView2 = helper.getView(R.id.iv_2);
        ImageView imageView3 = helper.getView(R.id.iv_3);
        TextView tvTitle = helper.getView(R.id.tv_title);
        TextView tvName = helper.getView(R.id.tv_name);
        TextView tvUpdateTime = helper.getView(R.id.tv_update_time);
        TextView tvContent = helper.getView(R.id.tv_content);
        TextView tvType = helper.getView(R.id.tv_type);
        TextView tvCommentNum = helper.getView(R.id.tv_comment_num);

        List<String> imageUrls = item.imageUrls;

        ImageView[] imageViews = {imageView1, imageView2, imageView3};

        ImageLoadUtils.display(mContext, StringUtils.setStr(item.avatar), ivAvatar);
        setContentImageView(imageUrls, imageViews);
        tvTitle.setText(StringUtils.setStr(item.title));
        tvName.setText(StringUtils.setStr(item.author));
        tvUpdateTime.setText(StringUtils.setStr(item.time + ""));
        tvContent.setText(Html.fromHtml(StringUtils.setStr(item.content)));
        tvType.setText(StringUtils.setStr(item.groupName));
        tvCommentNum.setText(StringUtils.setStr(item.commentCount + ""));
    }

    /**
     * 设置内容图片
     *
     * @param imageUrls
     * @param imageViews
     */
    private void setContentImageView(List<String> imageUrls, ImageView[] imageViews) {
        for (int i = 0; i < imageViews.length; i++) {
            imageViews[i].setVisibility(View.GONE);
        }
        if (imageUrls != null && imageUrls.size() > 0) {
            int imgSize = imageUrls.size() >= 3 ? 3 : imageUrls.size();
            for (int i = 0; i < imgSize; i++) {
                imageViews[i].setVisibility(View.VISIBLE);
                ImageLoadUtils.display(mContext, imageUrls.get(i), imageViews[i]);
            }
        }
    }
}
