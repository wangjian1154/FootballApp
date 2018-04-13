package com.wj.baseutils.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.wj.base.base.BaseActivity;
import com.wj.base.views.TitleBar;
import com.wj.baseutils.R;
import com.wj.baseutils.adapter.ShopCarAdapter;
import com.wj.baseutils.bean.ShopCarBean;
import com.wj.baseutils.contract.ShopCarContract;
import com.wj.baseutils.model.ShopCarModelImpl;
import com.wj.baseutils.presenter.ShopCarPresenterImpl;

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
    private ShopCarAdapter adapter;
    private List<ShopCarBean.ShopCarProductItem> data;

    public static void show(Context context) {
        Intent intent = new Intent(context, ShopCarActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initViewAndEvent(Bundle savedInstanceState) {
        layTitle.setTitle(getString(R.string.title_my_shop_car));
        layTitle.setBackButton(v -> finish());

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
        adapter = new ShopCarAdapter(data);
        recyclerView.setAdapter(adapter);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_shop_car;
    }

    @Override
    protected ShopCarPresenterImpl createPresenter() {
        return new ShopCarPresenterImpl();
    }


    @OnClick({R.id.btn_selector_all})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_selector_all:
                selectAll(view);
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
            bean.shopSelect=selected;
        }
        adapter.notifyDataSetChanged();
        setProgressIndicator(false);
    }
}
