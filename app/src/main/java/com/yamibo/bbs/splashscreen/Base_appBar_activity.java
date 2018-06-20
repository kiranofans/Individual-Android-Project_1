package com.yamibo.bbs.splashscreen;

import android.annotation.SuppressLint;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
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
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_app_bar);

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
        ab.setDisplayHomeAsUpEnabled(true);

        ViewCompat.setTransitionName(findViewById(R.id.app_bar_layout),largeImg);

        collapsyToolbar =(CollapsingToolbarLayout)findViewById(R.id.collapsy_toolbar);
        collapsyToolbar.setExpandedTitleColor(getResources().getColor(android.R.color.transparent,null));
        collapsyToolbar.setCollapsedTitleTextColor(R.color.color_dark_red);
        setChatFrag(chatFrg);

    }
    public void setChatFrag(android.support.v4.app.Fragment frg){
        if (frg != null) {
            android.support.v4.app.FragmentTransaction ft =
                    getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.post_fragment, new ChatFragment()).commit();
        }
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
