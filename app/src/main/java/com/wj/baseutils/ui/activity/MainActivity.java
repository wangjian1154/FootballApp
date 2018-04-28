package com.wj.baseutils.ui.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.RelativeLayout;

import com.wj.base.base.BaseActivity;
import com.wj.base.base.BasePresenter;
import com.wj.base.base.SimpleActivity;
import com.wj.base.base.SimpleFragment;
import com.wj.base.utils.BaseUtils;
import com.wj.base.utils.HandleBackUtil;
import com.wj.base.utils.SPUtils;
import com.wj.base.utils.StatusBarUtil;
import com.wj.base.utils.ToastUtils;
import com.wj.base.views.FixedViewPager;
import com.wj.base.views.ListIndexView;
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

public class MainActivity extends SimpleActivity {

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

    @Override
    protected void initViewAndEvent(Bundle savedInstanceState) {

        initFragments(savedInstanceState);
        onTabChecked(tabViewId2Index[mTabFragmentAdapter.getCurrentTabIndex()]);

        showNotification();

        SPUtils.getInstance().put(Constants.SHARE_PREFENCE_KEY.SP_IS_FIRST_OPEN, false);
    }

    private void showNotification() {
        boolean isFirst = SPUtils.getInstance().getBoolean(Constants.SHARE_PREFENCE_KEY.SP_IS_FIRST_OPEN, true);
        if (isFirst && !BaseUtils.isNotificationEnable(this)) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("提示");
            builder.setMessage("您没有打开通知权限，可能无法收到通知，立即打开？");
            builder.setNegativeButton("取消", null);
            builder.setPositiveButton("立即打开", (dialog, which) -> {
                try {
                    BaseUtils.openNotificationSetting(this);
                } catch (Exception e) {
                    ToastUtils.showDebugShort(e.getMessage());
                    if (dialog != null)
                        dialog.dismiss();
                }

            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
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
        mTabFragmentAdapter.setonPageChagedListener(showIndex -> {
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
//        StatusBarUtil.setColor(this,getResources().getColor(com.wj.base.R.color.theme_yellow));
        return R.layout.activity_main;
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