package com.wj.baseutils.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.wj.base.base.BaseFragment;
import com.wj.baseutils.R;
import com.wj.baseutils.bean.HomeTagBean;
import com.wj.baseutils.contract.CategoryContract;
import com.wj.baseutils.model.CategoryModelImpl;
import com.wj.baseutils.presenter.CategoryPresenterImpl;
import com.wj.baseutils.ui.adapter.CategoryAdapter;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;
import com.zhy.view.flowlayout.TagView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by wj on 2018/1/18.
 */

public class CategoryFragment extends BaseFragment<CategoryPresenterImpl, CategoryModelImpl>
        implements CategoryContract.CategoryView {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private List<HomeTagBean.DataBean> tagList;
    public static final String KEY = "key";

    @Override
    protected CategoryPresenterImpl createPresenter() {
        return new CategoryPresenterImpl();
    }

    @Override
    protected CategoryModelImpl createModel() {
        return new CategoryModelImpl();
    }

    @Override
    protected void initViewAndEvent(Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        HomeTagBean bean = (HomeTagBean) bundle.getSerializable(KEY);
        tagList = new ArrayList<>();
        tagList.clear();
        tagList.addAll(bean.data);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        CategoryAdapter adapter = new CategoryAdapter(tagList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_category;
    }

    @Override
    public void setTagData(HomeTagBean tagBean) {

    }
}
