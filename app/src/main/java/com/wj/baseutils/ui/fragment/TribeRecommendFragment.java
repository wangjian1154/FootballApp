package com.wj.baseutils.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wj.base.base.BaseFragment;
import com.wj.baseutils.R;
import com.wj.baseutils.adapter.CircleListAdapter;
import com.wj.baseutils.adapter.DiscussionBeanAdapter;
import com.wj.baseutils.bean.CircleBean;
import com.wj.baseutils.bean.HotDiscussionBean;
import com.wj.baseutils.contract.TribeRecommendContract;
import com.wj.baseutils.model.TribeRecommendModelImpl;
import com.wj.baseutils.presenter.TribeRecommendPresenterImpl;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by wj on 2018/1/26.
 * 部落推荐
 */

public class TribeRecommendFragment extends BaseFragment<TribeRecommendPresenterImpl,
        TribeRecommendModelImpl> implements TribeRecommendContract.TribeRecommendView {

    private RecyclerView recyclerView;
    @BindView(R.id.recyclerViewCircle)
    RecyclerView recyclerViewCircle;

    private List<HotDiscussionBean.DataBean.DiscussionBean> discussionList;
    private List<CircleBean.DataBean> circleList;
    private DiscussionBeanAdapter adapterDuscussion;
    private int pageSize = 10;
    private CircleListAdapter circleAdapter;
    private View headView;

    @Override
    protected void initViewAndEvent(Bundle savedInstanceState) {
        initView();
    }

    @Override
    protected void lazyLoad() {
        super.lazyLoad();
        mPresenter.loadData("", pageSize + "", true);
    }

    private void initView() {

        initHeadView();
        discussionList = new ArrayList<>();
        circleList = new ArrayList<>();
        LinearLayoutManager lm = new LinearLayoutManager(getContext());
        adapterDuscussion = new DiscussionBeanAdapter(discussionList);
        recyclerView.setLayoutManager(lm);
        recyclerView.setAdapter(adapterDuscussion);


        LinearLayoutManager lmCircle = new LinearLayoutManager(getContext());
        circleAdapter = new CircleListAdapter(circleList);
        recyclerViewCircle.setAdapter(circleAdapter);
        recyclerViewCircle.setLayoutManager(lmCircle);

        circleAdapter.addHeaderView(headView);
    }

    private void initHeadView() {
        headView = View.inflate(getActivity(), R.layout.layout_tribe_recommend_head, null);
        recyclerView=headView.findViewById(R.id.recyclerView);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_tribe_recommend;
    }


    @Override
    public void setDiscussion(HotDiscussionBean discussionBean) {
        if (discussionBean != null && discussionBean.data != null) {
            List<HotDiscussionBean.DataBean.DiscussionBean> beans = discussionBean.data.正在热议;
            if (beans != null && beans.size() > 0) {
                discussionList.clear();
                discussionList.addAll(beans);
                adapterDuscussion.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void setCircle(CircleBean circleBean) {
        if (circleBean != null && circleBean.data != null
                && circleBean.data.size() > 0) {
            circleList.clear();
            circleList.addAll(circleBean.data);
            circleAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected TribeRecommendPresenterImpl createPresenter() {
        return new TribeRecommendPresenterImpl();
    }

    @Override
    protected TribeRecommendModelImpl createModel() {
        return new TribeRecommendModelImpl();
    }

}
