package com.yamibo.bbs.splashscreen;

import android.content.Intent;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import android.app.SearchManager;
import android.widget.SearchView;

import Adapter.ImgViewPagerAdapter;

public class BaseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private ViewPager imgVp; ImgViewPagerAdapter vpAdp;
    protected static CollapsingToolbarLayout collapsyToolbar;
    private static ImageButton leftNav,rightNav;
    private float preX,preY; private Button plsLogBtn,regBtn;
    private MainNavTabActivity main=new MainNavTabActivity();
    private static android.support.v4.app.Fragment chatFrg;
    private static View v; private LayoutInflater inflater;
    private SearchView searchView;
    private NavigationView chat_nav;
    private DrawerLayout baseDrawer;
    private Intent searchIntent;
    @Override
    public void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_bar_layout);

        imgVp=(ViewPager)findViewById(R.id.imgViewPager);
/*
        searchBtn=(ImageButton)findViewById(R.id.)
*/
        String largeImg="https://cdn-images-1.medium.com/max/1440/1*7Ur8GqhvuHU7uKFlDJsx1g.png";
        //ViewPager with images
        vpAdp=new ImgViewPagerAdapter(this);
        baseDrawer=(DrawerLayout)findViewById(R.id.chat_drawer);
        imgVp.setAdapter(vpAdp);

        main.imgNav();

        //Toolbar (For collapsing Toolbar layout)
        Toolbar baseToolbar = (Toolbar) findViewById(R.id.baseToolbar);
        setSupportActionBar(baseToolbar);

        ActionBar ab=getSupportActionBar();
        ab.setHomeButtonEnabled(true); ab.setDisplayHomeAsUpEnabled(true);
        ab.setHomeAsUpIndicator(android.R.drawable.ic_menu_view);

        collapsyToolbar =(CollapsingToolbarLayout)findViewById(R.id.collapsy_toolbar);
        collapsyToolbar.setExpandedTitleColor(getResources()
                .getColor(android.R.color.transparent,null));
        collapsyToolbar.setCollapsedTitleTextColor(getResources()
                .getColor(R.color.color_dark_red, null));

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle
                (this, baseDrawer,baseToolbar, R.string.navigation_drawer_open,
                        R.string.navigation_drawer_close);
        baseDrawer.addDrawerListener(toggle);
        toggle.syncState();

        chat_nav=(NavigationView)findViewById(R.id.chat_nav);
        chat_nav.setNavigationItemSelectedListener(this);
        View nav_view=chat_nav.getHeaderView(0);


        plsLogBtn=(Button)nav_view.findViewById(R.id.loginRqstBtn);
        regBtn=(Button)nav_view.findViewById(R.id.regBtn);

        setLogRqstAndRegBtn();
        searchIntent=getIntent();
        handleIntent(searchIntent);
    }
    @Override
    protected void onNewIntent(Intent intent){
        handleIntent(intent);
    }
    private void handleIntent(Intent intent){
        /**hanlde intent for searchView*/
        if(Intent.ACTION_SEARCH.equals(intent.getAction())){
            String query=intent.getStringExtra(SearchManager.QUERY);

            /**Codes here to use query to search data*/
        }
        setDefaultKeyMode(DEFAULT_KEYS_SEARCH_LOCAL);
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
    @Override
    public boolean onOptionsItemSelected(MenuItem  items){

        switch (items.getItemId()){
            //Response to the action bar's Up/home button
            case android.R.id.home:
                supportFinishAfterTransition();
                return true;
            case R.id.action_settings:

                return true;
            case R.id.act_search:

                return true;
        }
        return super.onOptionsItemSelected(items);
    }

    public void setChatDrawerListener(){
        /**listen to the drawer transitions*/
        baseDrawer.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
                // Respond when the drawer's position changes
            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {
                // Respond when the drawer is opened
            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {
                // Respond when the drawer is closed
            }

            @Override
            public void onDrawerStateChanged(int newState) {
                // Respond when the drawer motion state changes
            }
        });
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.chat_drawer);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.my_account) {
            // Handle the camera action
        } else if (id == R.id.my_space) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.chat_drawer);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    public boolean onSearchRequested(){
        Bundle appDataBundle=new Bundle();
        if(appDataBundle!=null){
           // boolean jargon=appDataBundle.getBoolean(Base_appBar_activ);
        }
        //appDataBundle.putBoolean(ChatSec_Activity.JARGON);

        startSearch(null,false ,
                null ,false );
        return true;
    }
    public void setLogRqstAndRegBtn(){
        plsLogBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BaseActivity.this,LoginActivity.class));
            }
        });
        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent
                        (BaseActivity.this,AccountRegistPage.class));
            }
        });
        plsLogBtn.setPaintFlags(plsLogBtn.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
        regBtn.setPaintFlags(regBtn.getPaintFlags()|Paint.UNDERLINE_TEXT_FLAG);
    }
}
