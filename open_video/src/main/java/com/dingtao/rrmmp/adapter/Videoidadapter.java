package com.dingtao.rrmmp.adapter;


import java.util.ArrayList;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

/**
 * data:${DATA}
 * author:刘(Administrator)
 * function
 */
public class Videoidadapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> mList;

    public Videoidadapter(FragmentManager fm, ArrayList<Fragment> list) {
        super(fm);
        mList = list;
    }

    @Override
    public Fragment getItem(int position) {
        return mList.get(position);
    }

    @Override
    public int getCount() {
        return mList.size();
    }
}
