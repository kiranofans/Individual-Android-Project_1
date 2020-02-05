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
    }

    private void setToolbar(){
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
    public void setFragments(android.support.v4.app.Fragment frg){
        if (frg != null) {
            ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.contentFrm_no_tabs, new Fragment()).commit();
        }
    }
}
