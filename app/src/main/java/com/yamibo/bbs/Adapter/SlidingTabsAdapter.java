package com.yamibo.bbs.Adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.yamibo.bbs.splashscreen.Fragments.ActiveUsersFragment;
import com.yamibo.bbs.splashscreen.Fragments.ForumsFragment;
import com.yamibo.bbs.splashscreen.Fragments.MangaFragment;
import com.yamibo.bbs.splashscreen.Fragments.NovelsFragment;

/**可滑動Tab的類*/
public class SlidingTabsAdapter extends FragmentStatePagerAdapter {
    private String[] tabTitles={"論壇","誰在線","漫畫","小說"};
    public SlidingTabsAdapter(FragmentManager fragMg){
        super(fragMg);//Default constructor
    }
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new ForumsFragment();
            case 1:
                return new ActiveUsersFragment();
            case 2:
                return new MangaFragment();
            case 3:
                return new NovelsFragment();
        }
        return null;
    }
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
    @Override
    public int getCount() {
        return tabTitles.length;
    }

}
