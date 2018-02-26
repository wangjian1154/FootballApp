package com.wj.baseutils.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;

import com.wj.base.base.BaseFragment;
import com.wj.base.base.SimpleFragment;
import com.wj.base.data.Constants;
import com.wj.base.utils.SPUtils;
import com.wj.base.utils.ScreenUtils;
import com.wj.base.utils.ToastUtils;
import com.wj.base.views.tablayout.ColorTrackTabLayout;
import com.wj.baseutils.R;
import com.wj.baseutils.adapter.TagPagerAdapter;
import com.wj.baseutils.bean.HomeTagBean;
import com.wj.baseutils.contract.HomeContract;
import com.wj.baseutils.model.HomeModelImpl;
import com.wj.baseutils.presenter.HomePresenterImpl;

import org.greenrobot.eventbus.EventBus;

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

    private List<HomeTagBean.DataBean> tagList;
    public static List<Fragment> fragments;
    private TagPagerAdapter tabAdapter;
    private HomeTagBean tagBean;
    private CategoryFragment categoryFragment;
    private List<String> tagStrList;
    private List<HomeTagBean.DataBean> spTag;

    @Override
    protected void initViewAndEvent(Bundle savedInstanceState) {

        initView();

        mPresenter.loadData(true);
    }

    private void initView() {
        try {
            spTag = (List<HomeTagBean.DataBean>) SPUtils.
                    getInstance().getObject(Constants.SHARE_PREFENCE_KEY.SP_CATEGORY);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        tagStrList = new ArrayList<>();
        tabAdapter = new TagPagerAdapter(getChildFragmentManager(), fragments, setTagStrList(tagList));
        viewPager.setAdapter(tabAdapter);
        float density = getResources().getDisplayMetrics().density;
        ToastUtils.showShort(density + "");
    }

    private List<String> setTagStrList(List<HomeTagBean.DataBean> tagList) {
        tagStrList.clear();
        for (int i = 0; i < tagList.size(); i++) {
            tagStrList.add(tagList.get(i).name);
        }
        return tagStrList;
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
            if (spTag != null) {
                tagList.addAll(spTag);
            } else {
                tagList.addAll(tagBean.data);
            }
            setTagStrList(tagList);
            for (int i = 0; i < tagBean.data.size(); i++) {
                HomeTagBean.DataBean bean = tagBean.data.get(i);
                if (bean != null) {
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
            categoryFragment = new CategoryFragment(new OnCategoryChangeCallback() {
                @Override
                public void onCategoryChange(List<HomeTagBean.DataBean> tagList, int selectPosition) {
                    tagBean.data = tagList;
                    tagStrList.addAll(setTagStrList(tagList));
                    viewPager.setCurrentItem(selectPosition);
                    tabAdapter.notifyDataSetChanged();
                    viewPager.setOffscreenPageLimit(tagList.size());
                    EventBus.getDefault().post(new Handler(Looper.getMainLooper()).obtainMessage(
                            Constants.Key_EventBus_Msg.CATEGORY_CHANGE));
                }
            });
            Bundle bundle = new Bundle();
            bundle.putSerializable(CategoryFragment.KEY, tagBean);
            bundle.putString(CategoryFragment.KEY_SELECT_CATEGORY, tabAdapter.getPageTitle(
                    viewPager.getCurrentItem()).toString());
            categoryFragment.setArguments(bundle);
            FragmentTransaction transaction = fm.beginTransaction();
            transaction.setCustomAnimations(R.anim.category_enter, R.anim.category_exit);
            transaction.replace(R.id.fl_tag, categoryFragment).commit();
        }
    }

    public interface OnCategoryChangeCallback {
        /**
         * 分类移动后的回调
         *
         * @param tagList        分类集合
         * @param selectPosition 初始选中的标题的pos
         */
        public void onCategoryChange(List<HomeTagBean.DataBean> tagList, int selectPosition);
    }
}
