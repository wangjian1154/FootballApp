package com.wj.baseutils.ui.fragment;

import android.os.Bundle;

import com.wj.base.base.BaseFragment;
import com.wj.baseutils.R;
import com.wj.baseutils.bean.HomeDataBean;
import com.wj.baseutils.contract.HomeSupportContract;
import com.wj.baseutils.model.HomeSupportModelImpl;
import com.wj.baseutils.presenter.HomeSupportPresenterImpl;
import com.youth.banner.Banner;

import java.util.List;

import butterknife.BindView;

/**
 * Created by wj on 2018/1/14.
 */

public class HomeSupportFragment extends BaseFragment<HomeSupportPresenterImpl, HomeSupportModelImpl> implements HomeSupportContract.HomeSupportView {

    @BindView(R.id.lay_banner)
    Banner layBanner;

    @Override
    protected void initViewAndEvent(Bundle savedInstanceState) {

        mPresenter.loadData(true);
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
        if(homeDataBean!=null){
            List<HomeDataBean.DataBean.PostsBeanX> posts = homeDataBean.data.posts;
            loadBanner(posts);
        }
    }

    private void loadBanner(List<HomeDataBean.DataBean.PostsBeanX> posts) {
        
    }

}
