package com.wj.baseutils.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wj on 2018/1/14.
 */

public class TagPagerAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> fragments;
    private List<String> tagList;

    public TagPagerAdapter(FragmentManager fm, List<Fragment> fragments, List<String> tagList) {
        super(fm);
        this.tagList = tagList;
        this.fragments = fragments;
    }


    public TagPagerAdapter(FragmentManager fm, List<Fragment> fragments, String[] tags) {
        super(fm);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < tags.length; i++) {
            list.add(tags[i]);
        }
        tagList = list;
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
