package com.yamibo.bbs.splashscreen.Fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.*;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yamibo.bbs.splashscreen.MainNavTabActivity;
import com.yamibo.bbs.splashscreen.R;

import Adapter.SlidingTabsAdapter;

/**
 * Created by Kira on 2017/11/20.
 */
public class TabsFragment extends Fragment {
    View v;
    ViewPager viewPager;
    TabLayout tabLayout;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.tab_sliding_tabs,container,false);
        ((MainNavTabActivity)getActivity()).fragsCustomToolbar("百合會主頁");
        return v;
    }
    @Override
    public void onViewCreated(View v,Bundle savedInstantState){
        viewPager=(ViewPager)v.findViewById(R.id.viewPagerTabs);

        viewPager.setAdapter(new SlidingTabsAdapter(getChildFragmentManager()));
        //tabLayout
        tabLayout=(TabLayout)v.findViewById(R.id.sliding_tabs);
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
            }
        });
    }
    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        Activity main=new MainNavTabActivity();
        if(context instanceof Activity){
            main=(Activity)context;
        }
    }

}
