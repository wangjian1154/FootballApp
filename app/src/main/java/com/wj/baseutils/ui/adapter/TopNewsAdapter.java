package com.wj.baseutils.ui.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wj.base.utils.ImageLoadUtils;
import com.wj.base.utils.StringUtils;
import com.wj.baseutils.R;
import com.wj.baseutils.bean.HomeDataBean;

import java.util.List;

/**
 * Created by wj on 2018/1/15.
 */

public class TopNewsAdapter extends BaseMultiItemQuickAdapter<HomeDataBean.DataBean.PostsBeanX, BaseViewHolder> {

    public TopNewsAdapter(List<HomeDataBean.DataBean.PostsBeanX> data) {
        super(data);
        addItemType(HomeDataBean.DataBean.PostsBeanX.ITEM_NORMAL, R.layout.item_list_top_news_normal);
        addItemType(HomeDataBean.DataBean.PostsBeanX.ITEM_BIG_IMG, R.layout.item_list_top_news_big_pic);
        addItemType(HomeDataBean.DataBean.PostsBeanX.ITEM_BANNER, R.layout.layout_banner);
        addItemType(HomeDataBean.DataBean.PostsBeanX.ITEM_THREE_IMG, R.layout.layout_banner);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeDataBean.DataBean.PostsBeanX item) {
        switch (helper.getItemViewType()) {
            case HomeDataBean.DataBean.PostsBeanX.ITEM_NORMAL:
                ImageView iv_normal_pic = helper.getView(R.id.iv_normal_pic);
                TextView tv_normal_title = helper.getView(R.id.tv_normal_title);
                TextView tv_normal_hotnum = helper.getView(R.id.tv_normal_hotnum);
                loadImageData(item, iv_normal_pic);
                tv_normal_title.setText(StringUtils.setStr(item.title));
                tv_normal_hotnum.setText(StringUtils.setStr(item.heatValue + ""));
                break;

            case HomeDataBean.DataBean.PostsBeanX.ITEM_BIG_IMG:
                TextView tv_big_pic_title = helper.getView(R.id.tv_big_pic_title);
                ImageView iv_big_pic = helper.getView(R.id.iv_big_pic);
                loadImageData(item, iv_big_pic);
                tv_big_pic_title.setText(StringUtils.setStr(item.title));
                break;

            case HomeDataBean.DataBean.PostsBeanX.ITEM_BANNER:


                break;
            case HomeDataBean.DataBean.PostsBeanX.ITEM_THREE_IMG:


                break;
        }


    }

    private void loadImageData(HomeDataBean.DataBean.PostsBeanX item, ImageView imageView) {
        if (item.imageUrls != null && item.imageUrls.size() > 0) {
            imageView.setVisibility(View.VISIBLE);
            ImageLoadUtils.display(mContext, item.imageUrls.get(0), imageView);
        } else {
            imageView.setVisibility(View.INVISIBLE);
        }
    }
}
