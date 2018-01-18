package com.wj.baseutils.ui.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wj.base.utils.ImageLoadUtils;
import com.wj.base.utils.StringUtils;
import com.wj.baseutils.R;
import com.wj.baseutils.bean.HomeDataBean;

import java.util.List;

import cn.jzvd.JZVideoPlayerStandard;

/**
 * Created by wj on 2018/1/18.
 */

public class VideoListAdapter extends BaseQuickAdapter<HomeDataBean.DataBean.PostsBeanX, BaseViewHolder> {

    public VideoListAdapter(@Nullable List<HomeDataBean.DataBean.PostsBeanX> data) {
        super(R.layout.item_list_video, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeDataBean.DataBean.PostsBeanX item) {
        JZVideoPlayerStandard videoPlayer = helper.getView(R.id.videoplayer);
        if (item.video != null && !StringUtils.isEmpty(item.video.url)) {
            String url = item.video.url;
            String image = item.video.image;
            String title = item.video.title;
            videoPlayer.setUp(url, JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL, StringUtils.setStr(title));
            videoPlayer.thumbImageView.setScaleType(ImageView.ScaleType.CENTER);
            ImageLoadUtils.display(mContext, image, videoPlayer.thumbImageView);
        }

    }
}
