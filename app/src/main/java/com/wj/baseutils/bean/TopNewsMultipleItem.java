package com.wj.baseutils.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Created by wj on 2018/1/15.
 */

public class TopNewsMultipleItem implements MultiItemEntity {
    /**
     * itemType
     * 1:Item横向普通图文
     * 2:Item纵向大图文
     * 3:Banner
     */
    private int itemType;
    public static final int ITEM_NORMAL = 1;
    public static final int ITEM_BIG_IMG = 2;
    public static final int ITEM_BANNER = 3;


    public TopNewsMultipleItem(int itemType) {
        this.itemType = itemType;
    }

    @Override
    public int getItemType() {
        return itemType;
    }
}
