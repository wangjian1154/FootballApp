package com.wj.baseutils.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;

import com.wj.base.base.BaseFragment;
import com.wj.base.base.SimpleFragment;
import com.wj.base.utils.ScreenUtils;
import com.wj.base.utils.ToastUtils;
import com.wj.base.views.tablayout.ColorTrackTabLayout;
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
import butterknife.OnClick;

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
    private HomeTagBean tagBean;
    private CategoryFragment categoryFragment;

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
        float density=getResources().getDisplayMetrics().density;
        ToastUtils.showShort(density+"");
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
        this.tagBean = tagBean;
        tagList.clear();
        fragments.clear();
        if (tagBean != null && tagBean.data != null) {
            for (int i = 0; i < tagBean.data.size(); i++) {
                HomeTagBean.DataBean bean = tagBean.data.get(i);
                if (bean != null) {
                    tagList.add(tagBean.data.get(i).name);
                    SimpleFragment fragment;
                    if (Constants.TYPE.TOP_VIDEO.equals(bean.key)) {
                        fragment = new VideoFragment();
                    } else {
                        fragment = new HomeSupportFragment();
                    }
                    Bundle bundle = new Bundle();
                    bundle.putString(Constants.Key.KEY, bean.key);
                    fragment.setArguments(bundle);
                    fragments.add(fragment);
                }
            }
        }
        tabAdapter.notifyDataSetChanged();
        viewPager.setOffscreenPageLimit(tagList.size());
    }

    @OnClick(R.id.ll_change_tag)
    public void changeTag() {
        if (tagBean != null) {
            FragmentManager fm = getChildFragmentManager();
            categoryFragment = new CategoryFragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable(CategoryFragment.KEY, tagBean);
            categoryFragment.setArguments(bundle);
            FragmentTransaction transaction = fm.beginTransaction();
            transaction.setCustomAnimations(R.anim.top_slide_in, R.anim.top_slide_out);
            transaction.replace(R.id.fl_tag, categoryFragment).commit();
        }
    }

    /**
     * 隐藏TagFragment
     */
    public boolean hideTagFragment() {
        if (categoryFragment != null && !categoryFragment.isHidden() && categoryFragment.isVisible()) {
            getChildFragmentManager().beginTransaction()
                    .setCustomAnimations(R.anim.top_slide_in, R.anim.top_slide_out)
                    .remove(categoryFragment).commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean onBackPressed() {
        return hideTagFragment();
    }
}
