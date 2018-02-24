package com.wj.baseutils.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.chad.library.adapter.base.BaseViewHolder;
import com.wj.base.base.BaseFragment;
import com.wj.baseutils.R;
import com.wj.baseutils.bean.HomeTagBean;
import com.wj.baseutils.contract.CategoryContract;
import com.wj.baseutils.model.CategoryModelImpl;
import com.wj.baseutils.presenter.CategoryPresenterImpl;
import com.wj.baseutils.adapter.CategoryAdapter;
import com.wj.baseutils.widget.ItemDragHelperCallBack;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by wj on 2018/1/18.
 */

public class CategoryFragment extends BaseFragment<CategoryPresenterImpl, CategoryModelImpl>
        implements CategoryContract.CategoryView {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private List<HomeTagBean.DataBean> tagList;
    public static final String KEY = "key";
    private CategoryAdapter adapter;

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

        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 4);
        adapter = new CategoryAdapter(tagList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(getContext())
                .color(getResources().getColor(R.color.white))
                .size((int) getResources().getDimension(R.dimen.widget_size_15))
                .build());
        recyclerView.setAdapter(adapter);
        ItemTouchHelper helper = new ItemTouchHelper(new ItemDragHelperCallBack(
                new ItemDragHelperCallBack.OnChannelDragListener() {
                    @Override
                    public void onItemMove(int starPos, int endPos) {
                        Collections.swap(tagList, starPos, endPos);
                        adapter.notifyItemMoved(starPos,endPos);
                    }
                }));

        helper.attachToRecyclerView(recyclerView);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_category;
    }

    @Override
    public void setTagData(HomeTagBean tagBean) {

    }

    @OnClick(R.id.ll_hide)
    public void hideFragment() {
        getFragmentManager().beginTransaction().remove(this).commit();
    }

}
