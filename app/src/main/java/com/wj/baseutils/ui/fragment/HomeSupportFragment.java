package com.wj.baseutils.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.orhanobut.logger.Logger;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.wj.base.base.BaseFragment;
import com.wj.base.utils.BannerImageLoader;
import com.wj.base.utils.StringUtils;
import com.wj.baseutils.R;
import com.wj.baseutils.bean.HomeDataBean;
import com.wj.baseutils.contract.HomeSupportContract;
import com.wj.baseutils.model.HomeSupportModelImpl;
import com.wj.baseutils.presenter.HomeSupportPresenterImpl;
import com.wj.baseutils.ui.adapter.TopNewsAdapter;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by wj on 2018/1/14.
 */

public class HomeSupportFragment extends BaseFragment<HomeSupportPresenterImpl, HomeSupportModelImpl> implements HomeSupportContract.HomeSupportView {

    @BindView(R.id.lay_banner)
    Banner layBanner;
    @BindView(R.id.smart_refresh)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    private List<String> bannerImg;
    private List<String> bannerTitle;
    private List<HomeDataBean.DataBean.PostsBeanX> posts;
    private TopNewsAdapter adapter;

    @Override
    protected void initViewAndEvent(Bundle savedInstanceState) {

        initView();

        initEvent();

        mPresenter.loadData(true);
    }

    private void initEvent() {
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mPresenter.loadData(true);
            }
        });
    }

    private void initView() {
        bannerImg = new ArrayList<>();
        bannerTitle = new ArrayList<>();
        posts = new ArrayList<>();
        layBanner.setImageLoader(new BannerImageLoader());
        layBanner.setDelayTime(5 * 1000);
        layBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        layBanner.setIndicatorGravity(BannerConfig.LEFT);

        adapter = new TopNewsAdapter(posts, bannerImg, bannerTitle, layBanner);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        layBanner.setImages(bannerImg);
        layBanner.setBannerTitles(bannerTitle);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home_support;
    }

    @Override
    protected HomeSupportPresenterImpl createPresenter() {
        return new HomeSupportPresenterImpl();
    }

    @Override
    protected HomeSupportModelImpl createModel() {
        return new HomeSupportModelImpl();
    }

    @Override
    public void setTopNewsData(boolean isRefresh, HomeDataBean homeDataBean) {
        Logger.i("wangjian","setTopNewsData");
        if (isRefresh) {
            posts.clear();
            layBanner.start();
        }
        if (homeDataBean != null && homeDataBean.data != null
                && homeDataBean.data.posts != null && homeDataBean.data.posts.size() > 0) {
            posts.addAll(homeDataBean.data.posts);
            adapter.notifyDataSetChanged();
            Logger.i("wangjian",""+posts.size());
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        layBanner.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        layBanner.stopAutoPlay();
    }
}
