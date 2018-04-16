package com.wj.baseutils.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wj.base.base.SimpleActivity;
import com.wj.base.utils.ImageLoadUtils;
import com.wj.base.utils.NoFastClickUtils;
import com.wj.base.utils.StringUtils;
import com.wj.baseutils.R;
import com.wj.baseutils.bean.ShopCarBean;
import com.wj.baseutils.ui.activity.ShopCarActivity;

import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wj on 2018/4/11.
 * <p>
 * 购物车适配器
 */

public class ShopCarAdapter extends BaseQuickAdapter<ShopCarBean.ShopCarProductItem, ShopCarAdapter.ViewHolder> {

    private ShopCarActivity activity;

    public ShopCarAdapter(ShopCarActivity activity, @Nullable List<ShopCarBean.ShopCarProductItem> data) {
        super(R.layout.item_list_shop_car, data);
        this.activity = activity;
    }

    @Override
    protected void convert(ViewHolder helper, ShopCarBean.ShopCarProductItem item) {
        helper.btnShopName.setText(StringUtils.setStr(item.shopname));
        helper.tvCategory.setText(StringUtils.setStr(item.category));
        helper.tvPrice.setText(StringUtils.setStr(item.price));
        helper.tvProductName.setText(StringUtils.setStr(item.productname));

        ImageLoadUtils.display(mContext, item.img, helper.ivPic);
        helper.ivCheck.setSelected(item.select);
        helper.btnShopName.setSelected(item.shopSelect);
        helper.tvQuantity.setText(item.quantity + "");

        if (helper.getAdapterPosition() == 0) {
            helper.llShopCarHead.setVisibility(View.VISIBLE);
            helper.line.setVisibility(View.VISIBLE);
            helper.itemSpace.setVisibility(View.GONE);
        } else {
            String lastShopName = mData.get(helper.getAdapterPosition() - 1).shopname;
            if (item.shopname.equals(lastShopName)) {
                helper.llShopCarHead.setVisibility(View.GONE);
                helper.line.setVisibility(View.GONE);
                helper.itemSpace.setVisibility(View.GONE);
            } else {
                helper.llShopCarHead.setVisibility(View.VISIBLE);
                helper.line.setVisibility(View.VISIBLE);
                helper.itemSpace.setVisibility(View.VISIBLE);
            }
        }

        //商品点击事件
        helper.llCheck.setOnClickListener(v -> {
            item.select = !item.select;
            helper.ivCheck.setSelected(item.select);
            //通过循环找出不同商铺的第一个商品的位置
            for (int i = 0; i < mData.size(); i++) {
                if (mData.get(i).isFirst) {
                    //遍历去找出同一家商铺的所有商品的勾选情况
                    for (int j = 0; j < mData.size(); j++) {
                        //如果是同一家商铺的商品，并且其中一个商品是未选中，那么商铺的全选勾选取消
                        if (mData.get(j).shopname == mData.get(i).shopname && !mData.get(j).select) {
                            mData.get(i).shopSelect = false;
                            break;
                        } else {
                            //如果是同一家商铺的商品，并且所有商品是选中，那么商铺的选中全选勾选
                            mData.get(i).shopSelect = true;
                        }
                    }
                }
            }
            activity.showTotalPrice();
            notifyDataSetChanged();
        });

        helper.btnShopName.setOnClickListener(v -> {
            if (item.isFirst) {
                item.shopSelect = !item.shopSelect;
                for (int i = 0; i < mData.size(); i++) {
                    if (mData.get(i).shopname.equals(item.shopname)) {
                        mData.get(i).select = item.shopSelect;
                    }
                }
                activity.showTotalPrice();
                notifyDataSetChanged();
            }
        });

        helper.tvAdd.setOnClickListener(v -> {
            item.quantity = item.quantity + 1;
            helper.tvQuantity.setText(item.quantity + "");
            if (item.select) {
                activity.showTotalPrice();
            }
        });

        helper.tvSub.setOnClickListener(v -> {
            if (item.quantity > 1) {
                item.quantity = item.quantity - 1;
                helper.tvQuantity.setText(item.quantity + "");
            }
            if (item.select) {
                activity.showTotalPrice();
            }
        });
    }


    static class ViewHolder extends BaseViewHolder {
        @BindView(R.id.btn_shop_name)
        Button btnShopName;
        @BindView(R.id.ll_shop_car_head)
        LinearLayout llShopCarHead;
        @BindView(R.id.iv_check)
        ImageView ivCheck;
        @BindView(R.id.ll_check)
        LinearLayout llCheck;
        @BindView(R.id.iv_pic)
        ImageView ivPic;
        @BindView(R.id.tv_product_name)
        TextView tvProductName;
        @BindView(R.id.tv_category)
        TextView tvCategory;
        @BindView(R.id.tv_price)
        TextView tvPrice;
        @BindView(R.id.line)
        View line;
        @BindView(R.id.item_space)
        View itemSpace;
        @BindView(R.id.tv_add)
        TextView tvAdd;
        @BindView(R.id.tv_sub)
        TextView tvSub;
        @BindView(R.id.tv_quantity)
        TextView tvQuantity;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
