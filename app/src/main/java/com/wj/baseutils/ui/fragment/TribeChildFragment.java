package com.wj.baseutils.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.wj.base.base.BaseFragment;
import com.wj.base.utils.ToastUtils;
import com.wj.baseutils.R;
import com.wj.baseutils.adapter.TribeCategoryListAdapter;
import com.wj.baseutils.adapter.TribeCategoryTypeAdapter;
import com.wj.baseutils.bean.TribeCategoryBean;
import com.wj.baseutils.contract.TribeChildContract;
import com.wj.baseutils.model.TribeChildModelImpl;
import com.wj.baseutils.presenter.TribeChildPresenterImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * Created by wj on 2018/1/23.
 * 部落中的部落
 */

public class TribeChildFragment extends BaseFragment<TribeChildPresenterImpl, TribeChildModelImpl>
        implements TribeChildContract.TribeChildView {

    @BindView(R.id.recyclerView_category)
    RecyclerView recyclerViewCategory;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private List<TribeCategoryBean.DataBean.CategoriesBean> typeList;
    private List<TribeCategoryBean.DataBean.CategoryGroupsMapBean.ObjBean> list;
    private List<String> keyList;
    private TribeCategoryTypeAdapter typeAdapter;
    private TribeCategoryListAdapter mAdapter;
    private Map<String, List<TribeCategoryBean.DataBean.CategoryGroupsMapBean.ObjBean>> categoryGroupsMap;

    @Override
    protected TribeChildPresenterImpl createPresenter() {
        return new TribeChildPresenterImpl();
    }

    @Override
    protected TribeChildModelImpl createModel() {
        return new TribeChildModelImpl();
    }

    @Override
    protected void initViewAndEvent(Bundle savedInstanceState) {
        initView();
        initEvent();
    }

    @Override
    protected void lazyLoad() {
        super.lazyLoad();
        mPresenter.loadData();
    }

    private void initEvent() {
        typeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                list.clear();
                typeAdapter.setSelection(position);
                if (categoryGroupsMap != null) {
                    list.addAll(categoryGroupsMap.get(keyList.get(position)));
                }
                mAdapter.notifyDataSetChanged();
            }
        });
    }

    private void initView() {
        typeList = new ArrayList<>();
        list = new ArrayList<>();
        keyList = new ArrayList<>();
        LinearLayoutManager lmCategory = new LinearLayoutManager(getContext());
        recyclerViewCategory.setLayoutManager(lmCategory);
        typeAdapter = new TribeCategoryTypeAdapter(typeList);
        recyclerViewCategory.setAdapter(typeAdapter);

        GridLayoutManager lmList = new GridLayoutManager(getContext(), 3);
        recyclerView.setLayoutManager(lmList);
        mAdapter = new TribeCategoryListAdapter(list);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_tribe_child;
    }

    @Override
    public void setCategory(TribeCategoryBean result) {
        typeList.clear();
        list.clear();
        keyList.clear();
        if (result != null && result.data != null
                && result.data.categories != null &&
                result.data.categories.size() > 0) {

            typeList.addAll(result.data.categories);
            typeAdapter.notifyDataSetChanged();
            categoryGroupsMap = result.data.categoryGroupsMap;
            for (String key : categoryGroupsMap.keySet()) {
                keyList.add(key);
            }
            if (categoryGroupsMap != null) {
                list.addAll(categoryGroupsMap.get(keyList.get(0)));
                typeAdapter.setSelection(0);
                mAdapter.notifyDataSetChanged();
                typeAdapter.notifyDataSetChanged();
            }
        }
    }
}
