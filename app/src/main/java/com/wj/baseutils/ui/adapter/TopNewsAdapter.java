package com.wj.baseutils.ui.adapter;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wj.baseutils.R;
import com.wj.baseutils.bean.TopNewsMultipleItem;

import java.util.List;

/**
 * Created by wj on 2018/1/15.
 */

public class TopNewsAdapter extends BaseMultiItemQuickAdapter<TopNewsMultipleItem,BaseViewHolder> {

    public TopNewsAdapter(List<TopNewsMultipleItem> data) {
        super(data);
        addItemType(TopNewsMultipleItem.ITEM_NORMAL, R.layout.item_list_top_news_normal);
        addItemType(TopNewsMultipleItem.ITEM_BIG_IMG,R.layout.item_list_top_news_big_pic);
    }

    @Override
    protected void convert(BaseViewHolder helper, TopNewsMultipleItem item) {

    }
}
