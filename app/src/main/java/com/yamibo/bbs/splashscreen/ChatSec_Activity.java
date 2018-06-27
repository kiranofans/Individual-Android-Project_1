package com.yamibo.bbs.splashscreen;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.SearchManager;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import com.yamibo.bbs.splashscreen.Fragments.ChatFragment;

public class ChatSec_Activity extends AppCompatActivity {
    private SearchView searchView;
    private CollapsingToolbarLayout collapseToolbar;
    @SuppressLint({"ResourceAsColor", "ResourceType"})
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts);

        Toolbar chatToolbar = (Toolbar) findViewById(R.id.sharedToolbar);
        chatToolbar.setTitle("海域區");
        setSupportActionBar(chatToolbar);

        ViewCompat.setTransitionName(findViewById(R.id.app_bar_layout), "");

        collapseToolbar =(CollapsingToolbarLayout)findViewById(R.id.collapse_toolbar);
        collapseToolbar.setExpandedTitleColor(getResources()
                .getColor(android.R.color.transparent,null));
        collapseToolbar.setCollapsedTitleTextColor(getResources()
                .getColor(R.color.color_dark_red, null));

        setChatFrag(new ChatFragment());
    }
    public void setChatFrag(android.support.v4.app.Fragment frg){
        if (frg != null) {
            android.support.v4.app.FragmentTransaction ft =
                    getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.contentFrm_no_tabs, new ChatFragment()).commit();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main2,menu);
        MenuItem searchItem=menu.findItem(R.id.act_search);
        SearchManager searchMng=(SearchManager)getSystemService(SEARCH_SERVICE);
        searchView=(SearchView)searchItem.getActionView();
        searchView.setSearchableInfo(searchMng.getSearchableInfo(getComponentName()));

        searchView.setIconifiedByDefault(false);
        // Configure the search info and add any event listeners here...

        return true;
    }
}
