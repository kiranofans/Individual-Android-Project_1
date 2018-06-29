package com.yamibo.bbs.splashscreen;

import android.annotation.SuppressLint;
import android.app.SearchManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;

import com.yamibo.bbs.splashscreen.Fragments.ChatFragment;
import com.yamibo.bbs.splashscreen.Fragments.MangaDiscussionFragment;

public class PostActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private SearchView searchView;
    private CollapsingToolbarLayout collapseToolbar;
    private Toolbar postToolbar;
    private FragmentTransaction ft;
    private DrawerLayout drawer;
    private NavigationView post_nav; private View headerView;
    private Button toLoginBtn,regBtn;
    @SuppressLint({"ResourceAsColor", "ResourceType"})
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts);

        setToolbar();
        setNavDrawerView();
        post_nav = (NavigationView) findViewById(R.id.nav_view);
        headerView = post_nav.getHeaderView(0);
        toLoginBtn = (Button) headerView.findViewById(R.id.loginReqstBtn);
        regBtn = (Button) headerView.findViewById(R.id.regBtn);

        post_nav.setNavigationItemSelectedListener(this);
        setChatFrag(new ChatFragment());

    }
    private void setToolbar(){
        postToolbar = (Toolbar) findViewById(R.id.sharedToolbar);
        setSupportActionBar(postToolbar);
        ViewCompat.setTransitionName(findViewById(R.id.app_bar_layout), "");

        collapseToolbar =(CollapsingToolbarLayout)findViewById(R.id.collapse_toolbar);
        collapseToolbar.setExpandedTitleColor(getResources()
                .getColor(android.R.color.transparent,null));
        collapseToolbar.setCollapsedTitleTextColor(getResources()
                .getColor(R.color.color_dark_red, null));
        postToolbar.hideOverflowMenu();

    }
    private void setNavDrawerView() {
        drawer = (DrawerLayout) findViewById(R.id.chat_drawer);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, postToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }
    public void setChatFrag(android.support.v4.app.Fragment frg){
        if (frg != null) {
            ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.contentFrm_no_tabs, new ChatFragment()).commit();

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.chat_drawer);
        drawer.closeDrawer(GravityCompat.START);
    }
    public void setMangaFrag(android.support.v4.app.Fragment frg){
        if (frg != null) {
            android.support.v4.app.FragmentTransaction ft =
                    getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.contentFrm_no_tabs, new MangaDiscussionFragment())
                    .commit();
        }
    }
  /*  @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main2,menu);
        MenuItem searchItem=menu.findItem(R.id.act_search);
        SearchManager searchMng=(SearchManager)getSystemService(SEARCH_SERVICE);
        searchView=(SearchView)searchItem.getActionView();
        searchView.setSearchableInfo(searchMng.getSearchableInfo(getComponentName()));

        searchView.setIconifiedByDefault(false);
        // Configure the search info and add any event listeners here...

        return true;
    }*/

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}
