package com.wj.baseutils.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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

public class ShopCarActivity extends BaseActivity<ShopCarPresenterImpl, ShopCarModelImpl> implements
        ShopCarContract.ShopCarView {

    @BindView(R.id.lay_title)
    TitleBar layTitle;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private List<ShopCarBean> data;

    public static void show(Context context){
        Intent intent=new Intent(context,ShopCarActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initViewAndEvent(Bundle savedInstanceState) {
        layTitle.setTitle(getString(R.string.title_my_shop_car));

        data = ShopCarBean.createLocalDate();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ShopCarAdapter adapter=new ShopCarAdapter(data.get(0).item);
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

}
