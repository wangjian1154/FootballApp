package com.wj.baseutils.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;

import com.wj.base.base.SimpleFragment;
import com.wj.baseutils.bean.HomeTagBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wj on 2018/1/14.
 */

public class TagPagerAdapter extends FragmentStatePagerAdapter {

    private List<SimpleFragment> fragments;
    private List<String> list;

    public TagPagerAdapter(FragmentManager fm, List<SimpleFragment> fragments, List<String> list) {
        super(fm);
        this.fragments = fragments;
        this.list = list;
    }

    public TagPagerAdapter(FragmentManager fm, List<SimpleFragment> fragments, String[] tags) {
        super(fm);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < tags.length; i++) {
            list.add(tags[i]);
        }
        this.list = list;
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
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return list != null && list.size() > 0 ? list.get(position) : "";
    }
}
