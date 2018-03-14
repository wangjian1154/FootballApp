package com.wj.baseutils.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.wj.base.base.SimpleFragment;
import com.wj.base.utils.ScreenUtils;
import com.wj.base.utils.ToastUtils;
import com.wj.base.views.tablayout.ColorTrackTabLayout;
import com.wj.baseutils.R;
import com.wj.base.data.Constants;
import com.wj.baseutils.adapter.TagPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by wj on 2018/1/11.
 * 部落
 */

public class TribeFragment extends SimpleFragment {

    @BindView(R.id.tab_layout_tribe)
    ColorTrackTabLayout tabLayout;
    @BindView(R.id.vp_tribe)
    ViewPager viewPager;
    private String titles[] = {Constants.TribeCategory.TITLE_RECOMMEND,
            Constants.TribeCategory.TITLE_NEW, Constants.TribeCategory.TITLE_TRIBE};
    private List<SimpleFragment> fragments;
    private TagPagerAdapter tagPagerAdapter;

    @Override
    protected void initViewAndEvent(Bundle savedInstanceState) {
        fragments = new ArrayList<>();
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.post(() -> tabLayout.setTabPaddingLeftAndRight(
                ScreenUtils.dp2px(getResources().getDimension(R.dimen.widget_size_4)),
                ScreenUtils.dp2px(getResources().getDimension(R.dimen.widget_size_4))));

        TribeRecommendFragment recommendFragment = new TribeRecommendFragment();
        TribeNewestFragment newestFragment = new TribeNewestFragment();
        TribeChildFragment childFragment = new TribeChildFragment();

        fragments.add(recommendFragment);
        fragments.add(newestFragment);
        fragments.add(childFragment);

        tagPagerAdapter = new TagPagerAdapter(getChildFragmentManager(), fragments, titles);
        viewPager.setAdapter(tagPagerAdapter);
        viewPager.setOffscreenPageLimit(3);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_tribe;
    }

}
