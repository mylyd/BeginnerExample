package com.example.a13466.jl.ViewPagerAdapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import java.util.List;

/**
 * Created by 13466 on 2018/3/21.
 */

public class FragmentViewPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> mListFragment;

    public FragmentViewPagerAdapter(FragmentManager fm,List<Fragment> fragments) {
        super(fm);
        this.mListFragment=fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mListFragment.get(position);
    }

    @Override
    public int getCount() {
        return mListFragment.size();
    }
}
