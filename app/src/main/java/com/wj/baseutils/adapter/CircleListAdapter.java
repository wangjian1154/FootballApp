package com.wj.baseutils.adapter;

import android.support.annotation.Nullable;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wj.base.utils.ImageBrowserActivity;
import com.wj.base.utils.ImageLoadUtils;
import com.wj.base.utils.StringUtils;
import com.wj.base.utils.TimeUtils;
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
        if (StringUtils.isEmpty(item.title)) {
            tvTitle.setVisibility(View.GONE);
        } else {
            tvTitle.setVisibility(View.VISIBLE);
            tvTitle.setText(item.title);
        }

        if (StringUtils.isEmpty(item.content)) {
            tvContent.setVisibility(View.GONE);
        } else {
            tvContent.setVisibility(View.VISIBLE);
            tvContent.setText(Html.fromHtml(item.content));
        }
        tvName.setText(StringUtils.setStr(item.author));
        tvUpdateTime.setText(TimeUtils.updateStr(item.recommendWeight));
        tvType.setText(StringUtils.setStr(item.groupName));
        tvCommentNum.setText(StringUtils.setStr(item.commentCount + ""));
    }

    /**
     * 设置内容图片
     *
     * @param imageUrls
     * @param imageViews
     */
    private void setContentImageView(final List<String> imageUrls, final ImageView[] imageViews) {
        for (int i = 0; i < imageViews.length; i++) {
            imageViews[i].setVisibility(View.GONE);
            final int selectPos = i;
            imageViews[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ImageBrowserActivity.show(mContext, imageUrls, selectPos);
                }
            });
        }
        if (imageUrls != null && imageUrls.size() > 0) {
            int imgSize = imageUrls.size() >= 3 ? 3 : imageUrls.size();
            for (int i = 0; i < imageViews.length; i++) {
                imageViews[i].setVisibility(View.INVISIBLE);
            }
            for (int i = 0; i < imgSize; i++) {
                imageViews[i].setVisibility(View.VISIBLE);
                ImageLoadUtils.display(mContext, imageUrls.get(i), imageViews[i]);
            }
        }
    }
}
