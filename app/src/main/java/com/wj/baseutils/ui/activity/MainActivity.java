package com.wj.baseutils.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.wj.base.base.BaseActivity;
import com.wj.base.base.BasePresenter;
import com.wj.base.base.SimpleFragment;
import com.wj.base.utils.HandleBackUtil;
import com.wj.base.utils.StatusBarUtil;
import com.wj.base.utils.ToastUtils;
import com.wj.baseutils.R;
import com.wj.base.data.Constants;
import com.wj.baseutils.adapter.BaseTabFragmentAdapter;
import com.wj.baseutils.ui.fragment.DataFragment;
import com.wj.baseutils.ui.fragment.HomeFragment;
import com.wj.baseutils.ui.fragment.MatchFragment;
import com.wj.baseutils.ui.fragment.MineFragment;
import com.wj.baseutils.ui.fragment.TribeFragment;

import butterknife.BindView;
import butterknife.OnClick;
import cn.jzvd.JZVideoPlayer;

public class MainActivity extends BaseActivity {

    @BindView(R.id.rl_tab_home)
    RelativeLayout rlTabHome;
    @BindView(R.id.rl_tab_tribe)
    RelativeLayout rlTabTribe;
    @BindView(R.id.rl_tab_match)
    RelativeLayout rlTabMatch;
    @BindView(R.id.rl_tab_data)
    RelativeLayout rlTabData;
    @BindView(R.id.rl_tab_mine)
    RelativeLayout rlTabMine;

    private long exitTime = 0;
    private HomeFragment homeFragment;
    private TribeFragment tribeFragment;
    private MatchFragment matchFragment;
    private DataFragment dataFragment;
    private MineFragment mineFragment;
    private BaseTabFragmentAdapter mTabFragmentAdapter;
    private int[] tabViewId2Index = {R.id.rl_tab_home, R.id.rl_tab_tribe,
            R.id.rl_tab_match, R.id.rl_tab_data, R.id.rl_tab_mine};
    private SimpleFragment mBackHandedFragment;
    private boolean hadIntercept;

    @Override
    protected void initViewAndEvent(Bundle savedInstanceState) {

        initFragments(savedInstanceState);
        onTabChecked(tabViewId2Index[mTabFragmentAdapter.getCurrentTabIndex()]);
    }

    private void initFragments(Bundle savedInstanceState) {
        homeFragment = (HomeFragment) getSupportFragmentManager().findFragmentByTag(HomeFragment.class.getSimpleName());
        if (homeFragment == null) {
            homeFragment = new HomeFragment();
        }

        tribeFragment = (TribeFragment) getSupportFragmentManager().findFragmentByTag(TribeFragment.class.getSimpleName());
        if (tribeFragment == null) {
            tribeFragment = new TribeFragment();
        }

        matchFragment = (MatchFragment) getSupportFragmentManager().findFragmentByTag(MatchFragment.class.getSimpleName());
        if (matchFragment == null) {
            matchFragment = new MatchFragment();
        }

        dataFragment = (DataFragment) getSupportFragmentManager().findFragmentByTag(DataFragment.class.getSimpleName());
        if (dataFragment == null) {
            dataFragment = new DataFragment();
        }

        mineFragment = (MineFragment) getSupportFragmentManager().findFragmentByTag(MineFragment.class.getSimpleName());
        if (mineFragment == null) {
            mineFragment = new MineFragment();
        }

        mTabFragmentAdapter = new BaseTabFragmentAdapter(getSupportFragmentManager(), R.id.fl_content,
                homeFragment, tribeFragment, matchFragment, dataFragment, mineFragment);
        mTabFragmentAdapter.setonPageChagedListener(new BaseTabFragmentAdapter.OnPageChangedListener() {
            @Override
            public void onPageChanged(int showIndex) {
                switch (showIndex) {
                    case 0:
                        onTabChecked(R.id.rl_tab_home);
                        break;

                    case 1:
                        onTabChecked(R.id.rl_tab_tribe);
                        break;

                    case 2:
                        onTabChecked(R.id.rl_tab_match);
                        break;

                    case 3:
                        onTabChecked(R.id.rl_tab_data);
                        break;

                    case 4:
                        onTabChecked(R.id.rl_tab_mine);
                        break;
                }
            }
        });

        int pageIndex = 0;
        if (null != savedInstanceState)
            pageIndex = savedInstanceState.getInt(Constants.Key.BUNDLE, 1);
        mTabFragmentAdapter.showTabFragment(pageIndex, -1, null);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (null != mTabFragmentAdapter)
            outState.putInt(Constants.Key.BUNDLE, mTabFragmentAdapter.getCurrentTabIndex());
        super.onSaveInstanceState(outState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    private void onTabChecked(int id) {
        rlTabHome.setSelected(false);
        rlTabTribe.setSelected(false);
        rlTabMatch.setSelected(false);
        rlTabData.setSelected(false);
        rlTabMine.setSelected(false);
        switch (id) {
            case R.id.rl_tab_home:
                rlTabHome.setSelected(true);
                break;

            case R.id.rl_tab_tribe:
                rlTabTribe.setSelected(true);
                break;

            case R.id.rl_tab_match:
                rlTabMatch.setSelected(true);
                break;

            case R.id.rl_tab_data:
                rlTabData.setSelected(true);
                break;

            case R.id.rl_tab_mine:
                rlTabMine.setSelected(true);
                break;
        }

    }

    @OnClick({R.id.rl_tab_home, R.id.rl_tab_tribe, R.id.rl_tab_match,
            R.id.rl_tab_data, R.id.rl_tab_mine})
    public void onClickTab(View view) {
        onTabChecked(view.getId());
        switch (view.getId()) {
            case R.id.rl_tab_home:
                if (mTabFragmentAdapter.getCurrentTabIndex() == 0) {

                } else {
                    mTabFragmentAdapter.showTabFragment(0, -1, null);
                }
                break;
            case R.id.rl_tab_tribe:
                if (mTabFragmentAdapter.getCurrentTabIndex() == 1) {

                } else {
                    mTabFragmentAdapter.showTabFragment(1, -1, null);
                }
                break;
            case R.id.rl_tab_match:
                if (mTabFragmentAdapter.getCurrentTabIndex() == 2) {

                } else {
                    mTabFragmentAdapter.showTabFragment(2, -1, null);
                }
                break;
            case R.id.rl_tab_data:
                if (mTabFragmentAdapter.getCurrentTabIndex() == 3) {

                } else {
                    mTabFragmentAdapter.showTabFragment(3, -1, null);
                }
                break;

            case R.id.rl_tab_mine:
                if (mTabFragmentAdapter.getCurrentTabIndex() == 4) {

                } else {
                    mTabFragmentAdapter.showTabFragment(4, -1, null);
                }
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if (JZVideoPlayer.backPress()) {
            return;
        }
        if (!HandleBackUtil.handleBackPress(this)) {
            dealQuit();
        }
    }

    private void dealQuit() {
        if (System.currentTimeMillis() - exitTime < 2000) {
            super.onBackPressed();
        } else {
            ToastUtils.showShort(getString(R.string.string_exit));
            exitTime = System.currentTimeMillis();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        JZVideoPlayer.releaseAllVideos();
    }

}