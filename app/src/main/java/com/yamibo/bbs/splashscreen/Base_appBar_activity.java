package com.yamibo.bbs.splashscreen;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SearchView;

import Adapter.ImgViewPagerAdapter;
import Fragments.ChatFragment;

public class Base_appBar_activity extends AppCompatActivity {
    private ViewPager imgVp; ImgViewPagerAdapter vpAdp;
    protected static CollapsingToolbarLayout collapsyToolbar;
    private static ImageButton leftNav,rightNav;
    private float preX,preY; private Button plsLogBtn,regBtn;
    private MainNavTabActivity main=new MainNavTabActivity();
    private static android.support.v4.app.Fragment chatFrg;
    private static View v; private LayoutInflater inflater;
    @SuppressLint("ResourceAsColor")
    public Base_appBar_activity(){}

    @Override
    public void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_bar_layout);

        imgVp=(ViewPager)findViewById(R.id.imgViewPager);

        String largeImg="https://cdn-images-1.medium.com/max/1440/1*7Ur8GqhvuHU7uKFlDJsx1g.png";
        //ViewPager with images
        vpAdp=new ImgViewPagerAdapter(this);
        imgVp.setAdapter(vpAdp);

        main.imgNav();

        //Toolbar (For collapsing Toolbar layout)
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar ab=getSupportActionBar();
        ab.setHomeButtonEnabled(true); ab.setDisplayHomeAsUpEnabled(true);
        ab.setHomeAsUpIndicator(android.R.drawable.ic_menu_view);

        ViewCompat.setTransitionName(findViewById(R.id.app_bar_layout),largeImg);

        collapsyToolbar =(CollapsingToolbarLayout)findViewById(R.id.collapsy_toolbar);
        collapsyToolbar.setExpandedTitleColor(getResources()
                .getColor(android.R.color.transparent,null));
        collapsyToolbar.setCollapsedTitleTextColor(getResources()
                .getColor(R.color.color_dark_red, null));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main2,menu);
        MenuItem searchItem=menu.findItem(R.id.act_search);
        SearchView searchView=(SearchView)searchItem.getActionView();

        // Configure the search info and add any event listeners here...

        return super.onCreateOptionsMenu(menu);
    }
}
