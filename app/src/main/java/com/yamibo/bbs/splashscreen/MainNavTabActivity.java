package com.yamibo.bbs.splashscreen;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.yamibo.bbs.splashscreen.Fragments.AccountFragment;
import com.yamibo.bbs.splashscreen.Fragments.GalleryFragment;
import com.yamibo.bbs.splashscreen.Fragments.ProfileFragment;
import com.yamibo.bbs.splashscreen.Fragments.SettingsFragment;
import com.yamibo.bbs.splashscreen.Fragments.SpaceFragment;
import com.yamibo.bbs.splashscreen.Fragments.TabsFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Adapter.ImgViewPagerAdapter;
import Adapter.MyRecyclerAdapter;
import Model.AuthMod;
import Model.Base_Items_Model;
import Model.HitsMod;
import Utils.Utility;
import VolleyService.VolleyHelper;
import VolleyService.VolleyResultCallback;

import static Utils.ApiConstants.FORUM_DAILY_HITS_URL;
import static Utils.ApiConstants.IMG_BASE_URL;
import static Utils.AppConstants.PREF_KEY_AVATAR;
import static Utils.AppConstants.PREF_KEY_USERNAME;

public class MainNavTabActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener, AccountFragment.OnFragmentInteractionListener,
        GalleryFragment.OnFragmentInteractionListener {
    private static String LOG_TAG = MainNavTabActivity.class.getSimpleName();

    public static ViewPager imgVp;
    public static ImgViewPagerAdapter vpAdp;
    protected static CollapsingToolbarLayout collapseToolbar;

    private static ImageView avatarBtn;
    private Button plsLogBtn, regBtn, logoutBtn;
    public static View headerView, loginView;
    public static NavigationView nav_view;
    private TextView usernameTv, tvOr;

    private static FragmentManager fragMg;
    private static FragmentTransaction ft;

    private Toolbar toolbar;
    private DrawerLayout drawer;
    private String username;
    private AutoCompleteTextView userInput;
    private List<String> imgUrlList;
    private Handler handler;

    private RecyclerView vpRecView;
    private MyRecyclerAdapter vpRecAdp;

    private List<Base_Items_Model> hitsList;
    private SessionManager session;

    private AuthMod.Auth authMod;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_nav);

        initMainContent();

        //RecView for hits
        vpRecView = (RecyclerView) findViewById(R.id.hitsRecView);
        vpRecView.setLayoutManager(new LinearLayoutManager(this));

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        imgUrlList = new ArrayList<>();
        hitsList = new ArrayList<>();

        setHitsToImgVP();
        setCollapsedBarMain();
        setNavDrawerView();

        nav_view.setNavigationItemSelectedListener(this);
        setLogRqstAndRegBtn();

        setBtnOnClicks();

        //ViewPager Tabs and tab fragments
        setTabsFragment(new TabsFragment());//init
        initChildFragments();
        getBasicUserInfo();
    }


    @Override
    protected void onResume() {
        super.onResume();
        getBasicUserInfo();
    }

    private void initMainContent() {

        imgVp = (ViewPager) findViewById(R.id.imgViewPager);

        loginView = View.inflate(this, R.layout.activity_login, null);
        navHeaderViewInit();
    }

    private void navHeaderViewInit() {
        nav_view = (NavigationView) findViewById(R.id.nav_view);
        headerView = nav_view.getHeaderView(0);
        plsLogBtn = (Button) headerView.findViewById(R.id.btn_request_login);
        regBtn = (Button) headerView.findViewById(R.id.btn_request_register);
        avatarBtn = (ImageView) headerView.findViewById(R.id.btn_avatar);
        usernameTv = (TextView) headerView.findViewById(R.id.tv_usernname);

        tvOr = headerView.findViewById(R.id.tv_or);
    }

    public void fragsCustomToolbar(String title) {
        collapseToolbar.setTitle(title);
    }

    private void initChildFragments() {
        // Fragment forumsFragment, activeUserFrag, novelFrag, mangaFrag;
        getSupportFragmentManager().findFragmentById(R.id.forumsFrm);
        getSupportFragmentManager().findFragmentById(R.id.frame_active_user);
        getSupportFragmentManager().findFragmentById(R.id.frame_novel);
        getSupportFragmentManager().findFragmentById(R.id.frame_manga);
        getSupportFragmentManager().findFragmentById(R.id.frame_setting);
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
                ft = fragMg.beginTransaction();
                ft.replace(R.id.rootViewPage, new ProfileFragment()).addToBackStack(LOG_TAG).commit();
            }
        });

       /* logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //log out
                session.logoutUser();
                finish();
            }
        });*/
    }

    private void setTabsFragment(android.support.v4.app.Fragment fg) {
        fragMg = getSupportFragmentManager();
        if (fg != null) {/**Set fragments method*/
            //Have to use v4.app.FragmentTransaction
            ft = fragMg.beginTransaction();
            ft.replace(R.id.rootViewPage, new TabsFragment()).addToBackStack(LOG_TAG).commit();
        }
    }

    private void getBasicUserInfo() {
        session = new SessionManager(getApplicationContext());
        if (session.isLoggedIn()) {
            plsLogBtn.setVisibility(View.GONE);
            regBtn.setVisibility(View.GONE);
            tvOr.setVisibility(View.GONE);

            HashMap<String, String> userInfo = session.getUserDetails();
            usernameTv.setText(userInfo.get(PREF_KEY_USERNAME));
            Glide.with(MainNavTabActivity.this).load(userInfo.get(PREF_KEY_AVATAR))
                    .override(270,270).into(avatarBtn);
        }else{
            plsLogBtn.setVisibility(View.VISIBLE);
            regBtn.setVisibility(View.VISIBLE);
            tvOr.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.nav_and_tab, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        /** Handle action bar item clicks here. The action bar will
         automatically handle clicks on the Home/Up button, so long
         as you specify a parent activity in AndroidManifest.xml.*/
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setHitsToImgVP() {
        //ViewPager with images
        VolleyHelper.volleyGETRequest(this, FORUM_DAILY_HITS_URL, new VolleyResultCallback() {
            @Override
            public void jsonResponse(JSONObject response) {
                try {
                    JSONObject var = response.getJSONObject("Variables");
                    JSONArray hitsImgArr = var.getJSONArray("data_img");
                    JSONArray hitsTxtArr = var.getJSONArray("data_txt");

                    for (int i = 0; i < hitsImgArr.length(); i++) {
                        JSONObject imgObj = hitsImgArr.getJSONObject(i);
                        String pic = imgObj.getString("pic");
                        imgUrlList.add(IMG_BASE_URL + pic);
                    }
                    for (int i = 0; i < hitsTxtArr.length(); i++) {
                        JSONObject txtObj = hitsTxtArr.getJSONObject(i);
                        String title = txtObj.getString("fulltitle");
                        String date = txtObj.getString("lastpost");
                        String author = txtObj.getString("author");
                        HitsMod posts = new HitsMod(title, date, author);
                        hitsList.add(posts);
                    }
                    vpRecAdp = new MyRecyclerAdapter(getApplicationContext(), hitsList);
                    vpRecView.setAdapter(vpRecAdp);
                    vpAdp = new ImgViewPagerAdapter(getApplicationContext(), imgUrlList);
                    imgVp.setAdapter(vpAdp);
                } catch (JSONException je) {
                    Toast.makeText(MainNavTabActivity.this, je.getMessage()
                            , Toast.LENGTH_LONG).show();
                    //index 3 out of range 0 to 3
                }
            }

            @Override
            public void stringResponse(String strResponse) {

            }

            @Override
            public void responseError(VolleyError error) {
                Utility.showErrorMessageToast(getApplicationContext(), error.getMessage());
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        /** Handle navigation view item clicks here.*/
        int id = item.getItemId();
        if (id == R.id.item_home) {
            setTabsFragment(new TabsFragment());
        } else if (id == R.id.item_account) {
            ft = fragMg.beginTransaction();
            ft.replace(R.id.rootViewPage, new AccountFragment()).addToBackStack(LOG_TAG).commit();
        } else if (id == R.id.item_space) {
            ft = fragMg.beginTransaction();
            ft.replace(R.id.rootViewPage, new SpaceFragment()).addToBackStack(LOG_TAG).commit();
        } else if (id == R.id.item_gallery) {
            ft = fragMg.beginTransaction();
            ft.replace(R.id.rootViewPage, new GalleryFragment()).addToBackStack(LOG_TAG).commit();
        } else if (id == R.id.item_setting) {
            ft = fragMg.beginTransaction();
            ft.replace(R.id.rootViewPage, new SettingsFragment()).addToBackStack(LOG_TAG).commit();
        } else if (id == R.id.nav_share) {
            //display the social medias in a dialog box or something
        } else if (id == R.id.nav_send) {
            setTabsFragment(new TabsFragment());//Default display
            /*ft=fragMg.beginTransaction();
            ft.replace(R.id.rootViewPage,new TabsFragment()).commit();*/
        }
        toolbar = findViewById(R.id.baseToolbar);
        drawer.closeDrawers();
        return false;
    }

    public void setLogRqstAndRegBtn() {
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
