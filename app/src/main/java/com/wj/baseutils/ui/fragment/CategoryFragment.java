package com.wj.baseutils.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.chad.library.adapter.base.BaseViewHolder;
import com.wj.base.base.BaseFragment;
import com.wj.baseutils.R;
import com.wj.baseutils.bean.HomeTagBean;
import com.wj.baseutils.contract.CategoryContract;
import com.wj.baseutils.model.CategoryModelImpl;
import com.wj.baseutils.presenter.CategoryPresenterImpl;
import com.wj.baseutils.ui.adapter.CategoryAdapter;
import com.wj.baseutils.widget.ItemDragHelperCallBack;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;
import com.zhy.view.flowlayout.TagView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by wj on 2018/1/18.
 */

public class CategoryFragment extends BaseFragment<CategoryPresenterImpl, CategoryModelImpl>
        implements CategoryContract.CategoryView, ItemDragHelperCallBack.OnChannelDragListener {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private List<HomeTagBean.DataBean> tagList;
    public static final String KEY = "key";
    private CategoryAdapter adapter;
    private ItemTouchHelper mHelper;

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
        ItemDragHelperCallBack callBack = new ItemDragHelperCallBack(this);
        mHelper = new ItemTouchHelper(callBack);
        mHelper.attachToRecyclerView(recyclerView);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(getContext())
                .color(getResources().getColor(R.color.white))
                .size((int) getResources().getDimension(R.dimen.widget_size_15))
                .build());
        recyclerView.setAdapter(adapter);
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

    @Override
    public void onStarDrag(BaseViewHolder baseViewHolder) {
        //开始拖动
        mHelper.startDrag(baseViewHolder);
    }

    @Override
    public void onItemMove(int starPos, int endPos) {

    }

    @Override
    public void onMoveToMyChannel(int starPos, int endPos) {

    }

    @Override
    public void onMoveToOtherChannel(int starPos, int endPos) {

    }
}
