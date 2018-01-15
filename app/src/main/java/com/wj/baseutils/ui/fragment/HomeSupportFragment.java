package com.wj.baseutils.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.wj.base.base.BaseFragment;
import com.wj.base.utils.BannerImageLoader;
import com.wj.base.utils.StringUtils;
import com.wj.baseutils.R;
import com.wj.baseutils.bean.HomeDataBean;
import com.wj.baseutils.contract.HomeSupportContract;
import com.wj.baseutils.model.HomeSupportModelImpl;
import com.wj.baseutils.presenter.HomeSupportPresenterImpl;
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

    @Override
    protected void initViewAndEvent(Bundle savedInstanceState) {

        initView();

        mPresenter.loadData(true);
    }

    private void initView() {
        bannerImg = new ArrayList<>();
        bannerTitle = new ArrayList<>();
        layBanner.setImageLoader(new BannerImageLoader());
        layBanner.setDelayTime(5 * 1000);
        layBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
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
    public void setTopNewsData(HomeDataBean homeDataBean) {
        if (homeDataBean != null) {
            List<HomeDataBean.DataBean.PostsBeanX> posts = homeDataBean.data.posts;
            loadBanner(posts);
        }
    }

    private void loadBanner(List<HomeDataBean.DataBean.PostsBeanX> posts) {
        bannerImg.clear();
        if (posts != null && posts.size() > 0) {
            int loadSize = posts.size() >= 5 ? 5 : posts.size();
            for (int i = 0; i < loadSize; i++) {
                List<String> imageUrls = posts.get(i).imageUrls;
                if (imageUrls != null && imageUrls.size() > 0) {
                    bannerImg.add(imageUrls.get(0));
                }
                bannerTitle.add(StringUtils.isEmpty(posts.get(i).title) ? "" : posts.get(i).title);
            }
            layBanner.setImages(bannerImg);
            layBanner.setBannerTitles(bannerTitle);
        }
        layBanner.start();
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
