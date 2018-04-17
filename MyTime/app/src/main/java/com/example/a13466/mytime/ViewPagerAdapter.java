package com.example.a13466.mytime;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 13466 on 2018/3/23.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> mlist;
    public ViewPagerAdapter(FragmentManager fm, List<Fragment> liset) {
        super(fm);
       this.mlist = liset;
    }

    @Override
    public Fragment getItem(int position) {
        return mlist.get(position);
    }

    @Override
    public int getCount() {
        return mlist.size();
    }
}
