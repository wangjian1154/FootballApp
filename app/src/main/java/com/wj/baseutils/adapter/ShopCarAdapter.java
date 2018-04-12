package com.wj.baseutils.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wj.base.utils.BaseUtils;
import com.wj.base.utils.ImageLoadUtils;
import com.wj.base.utils.StringUtils;
import com.wj.baseutils.R;
import com.wj.baseutils.bean.ShopCarBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wj on 2018/4/11.
 * <p>
 * 购物车适配
 */

public class ShopCarAdapter extends BaseQuickAdapter<ShopCarBean.ShopCarProductItem, ShopCarAdapter.MyViewHolder> {

    public ShopCarAdapter(@Nullable List<ShopCarBean.ShopCarProductItem> data) {
        super(R.layout.item_list_shop_car, data);
    }

    @Override
    protected void convert(MyViewHolder helper, ShopCarBean.ShopCarProductItem item) {
        helper.tvProductName.setText(item.productname);
        helper.tvCategory.setText(item.category);
        helper.tvPrice.setText(item.productname);

        if (!StringUtils.isEmpty(item.img)) {
            ImageLoadUtils.display(mContext, item.img, helper.ivPic);
        }
    }

    class MyViewHolder extends BaseViewHolder {

        @BindView(R.id.cb_check)
        CheckBox cbCheck;
        @BindView(R.id.iv_pic)
        ImageView ivPic;
        @BindView(R.id.tv_product_name)
        TextView tvProductName;
        @BindView(R.id.tv_category)
        TextView tvCategory;
        @BindView(R.id.tv_price)
        TextView tvPrice;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
