package com.yamibo.bbs.splashscreen;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.SearchView;

import com.yamibo.bbs.splashscreen.Fragments.AdminFragment;
import com.yamibo.bbs.splashscreen.Fragments.ChatFragment;
import com.yamibo.bbs.splashscreen.Fragments.AnimeDiscussFragment;

public class Activity_Post extends AppCompatActivity {
    private SearchView searchView;
    private CollapsingToolbarLayout collapseToolbar;
    private Toolbar postToolbar;
    private FragmentTransaction ft;
    @SuppressLint({"ResourceAsColor", "ResourceType"})

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        //setToolbarAndFragments();

    }

    private void setToolbarAndFragments(){
        Fragment[] fragments={new ChatFragment()};
        postToolbar = (Toolbar) findViewById(R.id.sharedToolbar);
        setSupportActionBar(postToolbar);
        ViewCompat.setTransitionName(findViewById(R.id.app_bar_layout), "");

        collapseToolbar =(CollapsingToolbarLayout)findViewById(R.id.collapse_toolbar);
        collapseToolbar.setTitleEnabled(true);
        collapseToolbar.setExpandedTitleColor(getResources()
                .getColor(android.R.color.transparent,null));
        collapseToolbar.setCollapsedTitleTextColor(getResources()
                .getColor(R.color.color_dark_red, null));
        postToolbar.hideOverflowMenu();

    }
    public void setChatFrag(android.support.v4.app.Fragment frg){
        if (frg != null) {
            ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.contentFrm_no_tabs, new ChatFragment()).commit();
        }

    }
    public void setMangaFrag(android.support.v4.app.Fragment frg){
        if (frg != null) {
            android.support.v4.app.FragmentTransaction ft =
                    getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.contentFrm_no_tabs, new AnimeDiscussFragment())
                    .commit();
        }
    }
    public void setAdminFrag(Fragment frg){
        if(frg!=null){
            android.support.v4.app.FragmentTransaction ft =
                    getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.contentFrm_no_tabs, new AdminFragment())
                    .commit();
        }
    }
}
