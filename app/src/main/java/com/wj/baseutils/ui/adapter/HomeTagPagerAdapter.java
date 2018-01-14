package com.wj.baseutils.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by wj on 2018/1/14.
 */

public class HomeTagPagerAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> fragments;
    private List<String> tagList;

    public HomeTagPagerAdapter(FragmentManager fm, List<Fragment> fragments, List<String> tagList) {
        super(fm);
        this.tagList = tagList;
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tagList.get(position);
    }
}
