package com.yamibo.bbs.splashscreen;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.*;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.yamibo.bbs.splashscreen.Fragments.AccountFragment;
import com.yamibo.bbs.splashscreen.Fragments.GalleryFragment;
import com.yamibo.bbs.splashscreen.Fragments.ProfileFragment;
import com.yamibo.bbs.splashscreen.Fragments.SpaceFragment;
import com.yamibo.bbs.splashscreen.Fragments.TabsFragment;

import java.util.ArrayList;
import java.util.List;

import Adapter.ImgViewPagerAdapter;

public class MainNavTabActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener,AccountFragment.OnFragmentInteractionListener,
GalleryFragment.OnFragmentInteractionListener{
    public static ViewPager imgVp;
    public static ImgViewPagerAdapter vpAdp;
    protected static CollapsingToolbarLayout collapseToolbar;
    private static ImageView leftNav, rightNav,avatarBtn;
    private Button plsLogBtn, regBtn,logoutBtn;
    private static FragmentManager fragMg;
    private static FragmentTransaction ft;
    private Toolbar toolbar;
    private View headerView,loginView;
    private NavigationView nav_view;
    private DrawerLayout drawer;
    private TextView usernameTv;
    private String username;
    private AutoCompleteTextView userInput;
    private List<String> imgUrlList; private Handler handler;
    private static final boolean AUTO_HIDE = true;
    private static final int AUTO_HIDE_DELAY_MILLIS = 1000;
    private static View view;
    private static final int UI_ANIMATION_DELAY = 300;
    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_nav);
        imgVp = (ViewPager) findViewById(R.id.imgViewPager);
        nav_view = (NavigationView) findViewById(R.id.nav_view);
        headerView = nav_view.getHeaderView(0);
        plsLogBtn = (Button) headerView.findViewById(R.id.loginReqstBtn);
        regBtn = (Button) headerView.findViewById(R.id.regBtn);
        avatarBtn = (ImageView) headerView.findViewById(R.id.avatarImgBtn);
        loginView=View.inflate(this,R.layout.activity_login,null);
        usernameTv = (TextView) headerView.findViewById(R.id.usrNameTxt);

        drawer=(DrawerLayout)findViewById(R.id.drawer_layout);
        imgUrlList=new ArrayList<>();

        String[] imgUrls=getResources().getStringArray(R.array.img_urls);
        //ViewPager with images
        imgUrlList.add(imgUrls[1]);
        vpAdp = new ImgViewPagerAdapter(this,imgUrlList);
        imgVp.setAdapter(vpAdp);

        imgNav();  setCollapsedBarMain();
        setNavDrawerView();

        nav_view.setNavigationItemSelectedListener(this);
        setLogRqstAndRegBtn();setBtnOnClicks();

        //ViewPager Tabs and tab fragments
        setTabsFragments(new TabsFragment());//init
        initChildFragments();
    }
    public void fragsCustomToolbar(String title){
        collapseToolbar.setTitle(title);
    }
    private void initChildFragments(){
        Fragment forumsFragment, activeUserFrag, novelFrag, mangaFrag;
        forumsFragment = getFragmentManager()
                .findFragmentById(R.id.forumsFrm);
        activeUserFrag = getFragmentManager().findFragmentById(R.layout.tab_active_user);
        novelFrag=getFragmentManager().findFragmentById(R.layout.tab_novels);
        mangaFrag=getFragmentManager().findFragmentById(R.layout.tab_manga);

    }
    private void setCollapsedBarMain() {
        toolbar = (Toolbar) findViewById(R.id.baseToolbar);
        setSupportActionBar(toolbar);
        ViewCompat.setTransitionName(findViewById(R.id.app_bar_main), "");
        collapseToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsy_toolbar);
        collapseToolbar.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));
        collapseToolbar.setCollapsedTitleTextColor(getResources()
                .getColor(R.color.color_dark_red, null));
    }

    private void setNavDrawerView() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }
    private void setBtnOnClicks() {
        avatarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Will start a new activity
                ft=fragMg.beginTransaction();
                ft.replace(R.id.rootViewPage,new ProfileFragment()).commit();
            }
        });
    }
    private void setTabsFragments(android.support.v4.app.Fragment fg){
        fragMg=getSupportFragmentManager();
        if (fg != null) {/**Set ViewPager tabs fragment*/
            //Have to use v4.app.FragmentTransaction
            ft =fragMg.beginTransaction();
            ft.replace(R.id.rootViewPage, new TabsFragment()).commit();
        }
    }
    @Override
    public void onBackPressed () {
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.nav_and_tab, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected (MenuItem item){
        /** Handle action bar item clicks here. The action bar will
         automatically handle clicks on the Home/Up button, so long
         as you specify a parent activity in AndroidManifest.xml.*/
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onNavigationItemSelected (MenuItem item){
        /** Handle navigation view item clicks here.*/
        int id = item.getItemId();
        if(id==R.id.item_home){
            ft=fragMg.beginTransaction();
            ft.replace(R.id.rootViewPage,new TabsFragment()).commit();

        }else if (id == R.id.item_account) {
            ft=fragMg.beginTransaction();
            ft.replace(R.id.rootViewPage,new AccountFragment()).commit();
        } else if (id == R.id.item_space) {
            ft=fragMg.beginTransaction();
            ft.replace(R.id.rootViewPage,new SpaceFragment()).commit();

        } else if (id == R.id.item_gallery) {
            ft=fragMg.beginTransaction();
            ft.replace(R.id.rootViewPage,new GalleryFragment()).commit();
        } else if (id == R.id.item_manage) {
            ft=fragMg.beginTransaction();
            ft.replace(R.id.rootViewPage,new TabsFragment()).commit();
        } else if (id == R.id.nav_share) {
           //display the social medias in a dialog box or something
        } else if (id == R.id.nav_send) {
            ft=fragMg.beginTransaction();
            ft.replace(R.id.rootViewPage,new TabsFragment()).commit();
        }
        toolbar=(Toolbar)findViewById(R.id.baseToolbar);
        return false;
    }
    public void imgNav () {
        leftNav = (ImageButton) findViewById(R.id.left_nav);
        rightNav = (ImageButton) findViewById(R.id.right_nav);
        leftNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(getApplicationContext(),Main2Activity.class));
                int tab = imgVp.getCurrentItem();
                if (tab > 0) {
                    tab--;
                    imgVp.setCurrentItem(tab);

                } else if (tab == 0) {
                    imgVp.setCurrentItem(tab);

                }
            }
        });
        //Images right navigation
        rightNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tab = imgVp.getCurrentItem();
                tab++;
                imgVp.setCurrentItem(tab);
            }
        });

    }
    public void setLogRqstAndRegBtn () {
        plsLogBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainNavTabActivity.this, Activity_Login.class));
            }
        });
        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent
                        (MainNavTabActivity.this, AccountRegistPage.class));
            }
        });
        plsLogBtn.setPaintFlags(plsLogBtn.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        regBtn.setPaintFlags(regBtn.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
