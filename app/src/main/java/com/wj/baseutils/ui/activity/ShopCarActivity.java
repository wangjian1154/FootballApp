package com.wj.baseutils.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.wj.base.base.BaseActivity;
import com.wj.base.views.TitleBar;
import com.wj.baseutils.R;
import com.wj.baseutils.adapter.ShopCarAdapter;
import com.wj.baseutils.bean.ShopCarBean;
import com.wj.baseutils.contract.ShopCarContract;
import com.wj.baseutils.model.ShopCarModelImpl;
import com.wj.baseutils.presenter.ShopCarPresenterImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShopCarActivity extends BaseActivity<ShopCarPresenterImpl, ShopCarModelImpl> implements
        ShopCarContract.ShopCarView {

    @BindView(R.id.lay_title)
    TitleBar layTitle;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.smart_layout)
    SmartRefreshLayout smartLayout;
    @BindView(R.id.ll_head_view)
    View headView;
    @BindView(R.id.tv_delete)
    TextView tvDelete;
    @BindView(R.id.tv_total_price)
    TextView tvTotalPrice;
    private ShopCarAdapter adapter;
    private List<ShopCarBean.ShopCarProductItem> data;

    public static void show(Context context) {
        Intent intent = new Intent(context, ShopCarActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initViewAndEvent(Bundle savedInstanceState) {
        layTitle.setTitle(getString(R.string.title_my_shop_car));

        data = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        data.clear();
        for (int i = 0; i < ShopCarBean.createLocalDate().size(); i++) {
            ShopCarBean shopCarBean = ShopCarBean.createLocalDate().get(i);
            List<ShopCarBean.ShopCarProductItem> item = shopCarBean.item;
            item.get(0).isFirst = true;
            for (int j = 1; j < item.size(); j++) {
                if (item.get(j).shopname.equals(item.get(j - 1).shopname)) {
                    item.get(j).isFirst = false;
                } else {
                    item.get(j).isFirst = true;
                }
            }
            data.addAll(shopCarBean.item);
        }
        adapter = new ShopCarAdapter(ShopCarActivity.this, data);
        recyclerView.setAdapter(adapter);

        initEvent();
    }

    private void initEvent() {
        layTitle.setBackButton(v -> finish());
        layTitle.setRightText("管理", v -> {
            if (data.size() != 0)
                if (headView.getVisibility() == View.GONE) {
                    headView.setVisibility(View.VISIBLE);
                    layTitle.setRightBtnText("完成");
                } else {
                    headView.setVisibility(View.GONE);
                    layTitle.setRightBtnText("管理");
                }
        });
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_shop_car;
    }

    @Override
    protected ShopCarPresenterImpl createPresenter() {
        return new ShopCarPresenterImpl();
    }


    @OnClick({R.id.btn_selector_all, R.id.tv_delete})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_selector_all:
                selectAll(view);
                break;

            case R.id.tv_delete:
                setProgressIndicator(true);
                for (int i = 0; i < data.size(); i++) {
                    ShopCarBean.ShopCarProductItem item = data.get(i);
                    if (item.select) {
                        data.remove(item);
                        i--;
                        showTotalPrice();
                        adapter.notifyDataSetChanged();
                    }
                }
                if (data.size() == 0) {
                    headView.setVisibility(View.GONE);
                }
                setProgressIndicator(false);
                break;
        }
    }


    //全选
    private void selectAll(View view) {
        if (data == null && data.size() > 0)
            return;
        setProgressIndicator(true);
        boolean selected = !view.isSelected();
        view.setSelected(selected);
        for (int i = 0; i < data.size(); i++) {
            ShopCarBean.ShopCarProductItem bean = data.get(i);
            bean.select = selected;
            bean.shopSelect = selected;
        }
        showTotalPrice();
        adapter.notifyDataSetChanged();
        setProgressIndicator(false);
    }

    /**
     * 计算总价
     */
    public void showTotalPrice() {
        BigDecimal totalPrice = new BigDecimal("0.00");
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).select) {
                int quantity = data.get(i).quantity;
                String price = data.get(i).price;
                BigDecimal unitPriceB = new BigDecimal(price);
                BigDecimal quantityB = new BigDecimal(quantity);
                BigDecimal multiply = unitPriceB.multiply(quantityB);
                totalPrice = multiply.add(totalPrice);
            }
        }
        tvTotalPrice.setText(totalPrice.toString().trim());
    }
}
