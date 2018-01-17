package com.wj.baseutils.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.wj.base.base.BaseFragment;
import com.wj.base.utils.ScreenUtils;
import com.wj.base.views.ColorTrackTabLayout;
import com.wj.baseutils.R;
import com.wj.baseutils.app.Constants;
import com.wj.baseutils.bean.HomeTagBean;
import com.wj.baseutils.contract.HomeContract;
import com.wj.baseutils.model.HomeModelImpl;
import com.wj.baseutils.presenter.HomePresenterImpl;
import com.wj.baseutils.ui.adapter.HomeTagPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by wj on 2018/1/11.
 * 首页
 */

public class HomeFragment extends BaseFragment<HomePresenterImpl, HomeModelImpl> implements HomeContract.HomeView {

    @BindView(R.id.tab_layout_home)
    ColorTrackTabLayout tabLayout;
    @BindView(R.id.vp_home)
    ViewPager viewPager;

    private List<String> tagList;
    private List<Fragment> fragments;
    private HomeTagPagerAdapter tabAdapter;

    @Override
    protected void initViewAndEvent(Bundle savedInstanceState) {

        initView();

        mPresenter.loadData(true);
    }

    private void initView() {
        tagList = new ArrayList<>();
        fragments = new ArrayList<>();
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setTabPaddingLeftAndRight(
                        ScreenUtils.dp2px(getResources().getDimension(R.dimen.widget_size_4)),
                        ScreenUtils.dp2px(getResources().getDimension(R.dimen.widget_size_4)));
            }
        });
        tabAdapter = new HomeTagPagerAdapter(getChildFragmentManager(), fragments, tagList);
        viewPager.setAdapter(tabAdapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected HomePresenterImpl createPresenter() {
        return new HomePresenterImpl();
    }

    @Override
    protected HomeModelImpl createModel() {
        return new HomeModelImpl();
    }

    @Override
    public void setTagData(HomeTagBean tagBean) {
        tagList.clear();
        fragments.clear();
        if (tagBean != null && tagBean.data != null) {
            for (int i = 0; i < tagBean.data.size(); i++) {
                if (tagBean.data.get(i) != null) {
                    tagList.add(tagBean.data.get(i).name);
                    HomeSupportFragment fragment = new HomeSupportFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString(Constants.Key.KEY, tagBean.data.get(i).key);
                    fragment.setArguments(bundle);
                    fragments.add(fragment);
                }
            }
        }
        tabAdapter.notifyDataSetChanged();
        viewPager.setOffscreenPageLimit(tagList.size());
    }

}
