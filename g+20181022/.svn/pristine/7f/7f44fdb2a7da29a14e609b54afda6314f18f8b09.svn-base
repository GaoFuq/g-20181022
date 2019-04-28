package com.code.clkj.menggong.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 *  附近Activity的适配器
 * Created by Administrator on 2017/9/6.
 */

public class NearAdapter extends FragmentPagerAdapter{
    private List<Fragment> fragments;
    public NearAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
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
}
