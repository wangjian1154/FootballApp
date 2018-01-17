package com.wj.baseutils.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.orhanobut.logger.Logger;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.wj.base.base.BaseFragment;
import com.wj.base.utils.BannerImageLoader;
import com.wj.base.utils.StringUtils;
import com.wj.base.utils.ToastUtils;
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
 *
 */

public class HomeSupportFragment extends BaseFragment<HomeSupportPresenterImpl, HomeSupportModelImpl> implements HomeSupportContract.HomeSupportView {

    @BindView(R.id.lay_banner)
    Banner layBanner;

    private List<String> bannerImg;
    private List<String> bannerTitle;
    private List<HomeDataBean.DataBean.PostsBeanX> posts;

    @Override
    protected void initViewAndEvent(Bundle savedInstanceState) {

        initView();
    }

    @Override
    protected void lazyLoad() {
        super.lazyLoad();
        mPresenter.loadData(true);
    }

    private void initView() {
        bannerImg = new ArrayList<>();
        bannerTitle = new ArrayList<>();
        posts = new ArrayList<>();
        layBanner.setImageLoader(new BannerImageLoader());
        layBanner.setDelayTime(5 * 1000);
        layBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        layBanner.setIndicatorGravity(BannerConfig.LEFT);
        layBanner.setIndicatorGravity(BannerConfig.LEFT);
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
        if (isRefresh) {
            posts.clear();
        }
        try {
            if (homeDataBean != null && homeDataBean.data != null
                    && homeDataBean.data.posts != null && homeDataBean.data.posts.size() > 0) {
                posts.addAll(homeDataBean.data.posts);
                for (int i = 0; i < posts.size(); i++) {
                    if (posts.get(i).getItemType() == HomeDataBean.DataBean.PostsBeanX.ITEM_BANNER) {
                        bannerImg.add(posts.get(i).imageUrls.get(0));
                        bannerTitle.add(posts.get(i).title);
                    }
                }
                layBanner.setImages(bannerImg);
                layBanner.setBannerTitles(bannerTitle);
                layBanner.start();
            }
        } catch (Exception e) {
            ToastUtils.showDebugShort(e.getMessage());
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
