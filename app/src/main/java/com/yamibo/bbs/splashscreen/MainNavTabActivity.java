package com.yamibo.bbs.splashscreen;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.*;
import android.view.MotionEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.*;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.volley.*;
import com.android.volley.toolbox.JsonObjectRequest;
import com.yamibo.bbs.splashscreen.Fragments.TabsFragment;
import org.json.*;
import Adapter.ImgViewPagerAdapter;

public class MainNavTabActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener {
    private ViewPager imgVp;
    private ImgViewPagerAdapter vpAdp;
    protected static CollapsingToolbarLayout collapsyToolbar;
    private static ImageButton leftNav, rightNav;
    private float preX, preY;
    private Button plsLogBtn, regBtn;
    private Fragment forumsFragment, activeUserFrag, novelFrag, mangaFrag;
    private boolean isRunning;
    private Button logoutBtn;
    private Toolbar toolbar;
    private View headerView;
    private NavigationView nav_view;
    private DrawerLayout drawer;
    private TextView usernameTv;
    private static ImageButton avatarBtn;
    public static String[] urls;
    private RequestQueue rqstQueue;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_nav);
        imgVp = (ViewPager) findViewById(R.id.imgViewPager);
        usernameTv = (TextView) headerView.findViewById(R.id.usrNameTxt);

        //ViewPager with images
        vpAdp = new ImgViewPagerAdapter(this);
        imgVp.setAdapter(vpAdp);
        leftNav = (ImageButton) findViewById(R.id.left_nav);
        rightNav = (ImageButton) findViewById(R.id.right_nav);
        imgNav();

        setCollapsyToolbar();
        setNavDrawerView();

        headerView = nav_view.getHeaderView(0);

        nav_view.setNavigationItemSelectedListener(this);
        setLogRqstAndRegBtn();
        setBtnOnClicks();

        setFragment(new TabsFragment());//init
        forumsFragment = getFragmentManager()
                .findFragmentById(R.id.forumsFrm);
        activeUserFrag = getFragmentManager().findFragmentById(R.layout.tab_active_user);
    }

    private void setCollapsyToolbar() {
        toolbar = (Toolbar) findViewById(R.id.baseToolbar);
        setSupportActionBar(toolbar);
        ViewCompat.setTransitionName(findViewById(R.id.app_bar_main), "");
        collapsyToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsy_toolbar);
        collapsyToolbar.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));
        collapsyToolbar.setCollapsedTitleTextColor(getResources()
                .getColor(R.color.color_dark_red, null));

    }

    private void setNavDrawerView() {
        nav_view = (NavigationView) findViewById(R.id.nav_view);

        //Drawer
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }
    private void setBtnOnClicks() {
        avatarBtn = (ImageButton) headerView.findViewById(R.id.avatarImgBtn);
        plsLogBtn = (Button) headerView.findViewById(R.id.loginReqstBtn);
        regBtn = (Button) headerView.findViewById(R.id.regBtn);
        avatarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Will start a new activity
                startActivity(new Intent(MainNavTabActivity.this, SpaceActivity.class));
            }
        });
    }
    //SetFragment function
    private void setFragment (android.support.v4.app.Fragment fg){
        if (fg != null) {
            //Have to use v4.app.FragmentTransaction
            android.support.v4.app.FragmentTransaction ft =
                    getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.rootViewPage, new TabsFragment()).commit();

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }
    @Override
    public void onBackPressed () {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
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
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onNavigationItemSelected (MenuItem item){
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

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void imgNav () {
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
                startActivity(new Intent(MainNavTabActivity.this, LoginActivity.class));
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
    private void usersJSONParser () {
        urls = getResources().getStringArray(R.array.yamibo_api_urls);
        JsonObjectRequest profileRqst = new JsonObjectRequest(Request.Method.GET, urls[1], null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        /*try {*/
                        if (response != null) {
                            String usrNames = response.optString("member_username");
                            if (usrNames != null) {
                                JSONArray jArr = response.optJSONArray("Variables");
                                if (jArr != null) {
                                    for (int i = 0; i < jArr.length(); i++) {
                                        Log.i("T6", "Username: " + usrNames);

                                    }
                                }
                            }
                        }
                            /*JSONObject usrVar=response.getJSONObject("Variables");
                            Picasso.with(NavHeaderActivity.this).load(""+usrVar.get("member_avatar"))
                                    .fit().centerInside().into(avatarBtn);
                            String names=""+usrVar.get("member_username");
                            usernameTv.setText(names);
                            Log.d("TEST6","Username: "+names);*/
                        /*catch (JSONException je) {
                            je.printStackTrace();
                        }*/

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        rqstQueue.add(profileRqst);
    }
    private void floatingFab () {
        //Floating Action Button
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}