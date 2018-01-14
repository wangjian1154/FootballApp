package com.wj.baseutils.ui.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.wj.baseutils.R;

/**
 * Created by wj on 2018/1/13.
 */

public class BaseTabFragmentAdapter {

    private Fragment[] mFragments;
    private FragmentManager mFmg;
    private int mFragmentContentId;
    private int mCurrentTabIndex;
    private OnPageChangedListener OnPageChangedListener;

    public BaseTabFragmentAdapter(FragmentManager mFmg, int mFragmentContentId,
                                  Fragment... mFragments) {
        this.mFmg = mFmg;
        this.mFragmentContentId = mFragmentContentId;
        this.mFragments = mFragments;
    }

    /**
     * 获取顶部显示的Fragment
     *
     * @return
     */
    public Fragment getTopFragment() {
        if (mFmg != null && mFragments != null && mCurrentTabIndex < mFragments.length) {
            return mFragments[mCurrentTabIndex];
        }
        return null;
    }

    /**
     * 显示Tab页
     *
     * @param idx
     * @param animalType
     */
    public void showTabFragment(int idx, int animalType, Bundle bundle) {
        for (int i = 0; i < mFragments.length; i++) {
            String tagName = mFragments[i].getClass().getSimpleName();
            Fragment fragment = mFmg.findFragmentByTag(tagName);
            if (fragment == null) {
                fragment = mFragments[i];
            } else {
                mFragments[i] = fragment;
            }

            //隐藏其他Fragment
            if (idx == i) {
                FragmentTransaction ft = obtainFragmentTransaction(idx, mFmg.beginTransaction(), animalType);
                if (null != bundle) fragment.setArguments(bundle);
                if (!fragment.isAdded()) {
                    String tag = fragment.getClass().getSimpleName();
                    ft.add(mFragmentContentId, fragment, tag).show(fragment).commitAllowingStateLoss();
                    mFmg.executePendingTransactions();
                } else {
                    ft.show(fragment).commitAllowingStateLoss();
                    mFmg.executePendingTransactions();
                }
            } else {
                if (!fragment.isHidden()) {
                    FragmentTransaction ft = obtainFragmentTransaction(idx, mFmg.beginTransaction(), animalType);
                    ft.hide(fragment).commitAllowingStateLoss();
                    mFmg.executePendingTransactions();
                }
            }
        }

        if (null != OnPageChangedListener)
            OnPageChangedListener.onPageChanged(idx);
        mCurrentTabIndex = idx;
    }

    private FragmentTransaction obtainFragmentTransaction(int index, FragmentTransaction ft, int animalType) {
        switch (animalType) {
            case 0:
                if (index > mCurrentTabIndex) {
                    ft.setCustomAnimations(R.anim.anim_right_in_3s,
                            R.anim.anim_left_out_3s);
                } else {
                    ft.setCustomAnimations(R.anim.anim_left_in_3s,
                            R.anim.anim_right_out_3s);
                }
                break;

            case 1:
                if (index > mCurrentTabIndex) {
                    ft.setCustomAnimations(R.anim.anim_bottom_in_3s,
                            R.anim.anim_zoomout_95p_3s);
                } else {
                    ft.setCustomAnimations(R.anim.anim_top_in_3s,
                            R.anim.anim_zoomout_95p_3s);
                }
                break;
        }
        return ft;
    }

    /**
     * 获取当前展示页索引
     *
     * @return
     */
    public int getCurrentTabIndex() {
        return mCurrentTabIndex;
    }

    /**
     * 获取当前展示页
     *
     * @return
     */
    public Fragment getCurrentFragment() {
        return mFragments[mCurrentTabIndex];
    }

    public Fragment[] getFragments() {
        return mFragments;
    }


    public interface OnPageChangedListener {
        void onPageChanged(int showIndex);
    }

    public void setonPageChagedListener(OnPageChangedListener OnPageChangedListener) {
        this.OnPageChangedListener = OnPageChangedListener;
    }

}
