package com.yamibo.bbs.splashscreen;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import java.util.*;

import Fragments.*;
import Adapter.ImgViewPagerAdapter;
import Model.DeferredFragmentTransaction;

public class MainNavTabActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private ViewPager imgVp; ImgViewPagerAdapter vpAdp;
    protected static CollapsingToolbarLayout collapsyToolbar;
    private static  ImageButton leftNav,rightNav;
    private float preX,preY; private Button plsLogBtn,regBtn;
    private Fragment forumsFragment,activeUserFrag,novelFrag,mangaFrag;
    private boolean isRunning;
    protected Queue<DeferredFragmentTransaction> fragTransQueue =new ArrayDeque<DeferredFragmentTransaction>();
    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_nav);
        imgVp=(ViewPager)findViewById(R.id.imgViewPager);

        String largeImg="https://cdn-images-1.medium.com/max/1440/1*7Ur8GqhvuHU7uKFlDJsx1g.png";
        //ViewPager with images
        vpAdp=new ImgViewPagerAdapter(this);
        imgVp.setAdapter(vpAdp);
        leftNav=(ImageButton)findViewById(R.id.left_nav);
        rightNav=(ImageButton)findViewById(R.id.right_nav);
        imgNav();

        //Toolbar (For collapsing Toolbar layout)
        Toolbar toolbar = (Toolbar) findViewById(R.id.baseToolbar);
        setSupportActionBar(toolbar);
        ViewCompat.setTransitionName(findViewById(R.id.app_bar_main),largeImg);

        collapsyToolbar =(CollapsingToolbarLayout)findViewById(R.id.collapsy_toolbar);
        collapsyToolbar.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));
        collapsyToolbar.setCollapsedTitleTextColor(R.color.color_dark_red);

        //Floating Action Button
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

       //Drawer
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        //NavigationView
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View nav_view=navigationView.getHeaderView(0);
        plsLogBtn=(Button)nav_view.findViewById(R.id.loginReqstBtn);
        regBtn=(Button)nav_view.findViewById(R.id.regBtn);

        setLogRqstAndRegBtn();

        setFragment(new TabsFragment());//init
        forumsFragment=getFragmentManager()
                .findFragmentById(R.id.forumsFrm);
        activeUserFrag=getFragmentManager().findFragmentById(R.layout.tab_active_user);




    }

    //SetFragment function
    private void setFragment(android.support.v4.app.Fragment fg) {
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
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
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

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
   public void imgNav(){
       leftNav.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               //startActivity(new Intent(getApplicationContext(),Main2Activity.class));
                int tab=imgVp.getCurrentItem();
                if(tab>0){
                    tab--;
                    imgVp.setCurrentItem(tab);

                }else if(tab==0){
                    imgVp.setCurrentItem(tab);

                }
           }
       });
       //Images right navigation
        rightNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tab=imgVp.getCurrentItem();
                tab++;
                imgVp.setCurrentItem(tab);
            }
        });
    }
    @Override
    public boolean onTouchEvent(MotionEvent event){
      //  final float TOUCH_SCALE_FACTOR=180.0f/320;

        float x=event.getX();
        float y=event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                float dx=x-preX;
                float dy=y-preY;
                //Reverse direction of rotation above the mid-line
                leftNav.setVisibility(View.VISIBLE);
                rightNav.setVisibility(View.VISIBLE);
            break;
            case MotionEvent.ACTION_UP:
                leftNav.setVisibility(View.INVISIBLE);
                rightNav.setVisibility(View.INVISIBLE);
                break;
        }
        preX=x;
        preY=y;
        return true;
    }
    public void setLogRqstAndRegBtn(){
        plsLogBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainNavTabActivity.this,LoginActivity.class));
            }
        });
        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent
                        (MainNavTabActivity.this,AccountRegistPage.class));
            }
        });
        plsLogBtn.setPaintFlags(plsLogBtn.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
        regBtn.setPaintFlags(regBtn.getPaintFlags()|Paint.UNDERLINE_TEXT_FLAG);
    }

    /** @param fragFrameId the view the fragment is going to be inflated in
     * @param replaceFrag    the fragment that is going to be inflated.*/
    public void replaceFragAddToBackStack(int fragFrameId, final android.support.v4.app.Fragment replaceFrag){
        if(!isRunning){
            //This handles switching of fragments when the activity is paused. To prevent IllegalSttateException
            //This transaction will be used in the resume part too.
            DeferredFragmentTransaction fragTrans=new DeferredFragmentTransaction(){
                @Override
                public void commit(){
                    replaceFragAddToBackStack(getContentFrameId(), getReplacingFrag());
                }
            };
            fragTrans.setContentFrameId(fragFrameId);
            fragTrans.setReplaceFrag(replaceFrag);

            fragTransQueue.add(fragTrans);
        }else{
            replaceFragAddToBackStack(fragFrameId,replaceFrag);
        }
    }
    private void replaceFragAddToBackStackInternal(int fragFrameId, android.support.v4.app.Fragment replaceFrag){
        FragmentManager fragMg=this.getSupportFragmentManager();
        fragMg.beginTransaction().replace(fragFrameId,replaceFrag)
                .addToBackStack(replaceFrag.getClass().getSimpleName()).commit();
    }

    /**Replaces the fragment currently occupying the view with id contentFrameId.
     *
     * @param fragFrameId    the view the fragment is going to be inflated in
     * @param replaceFrag the fragment that is going to be inflated.*/
    public void replaceFrag(int fragFrameId, android.support.v4.app.Fragment replaceFrag){
        if(!isRunning){
            /**This handles switching of fragments when the activity
             * is paused in order to prevent IllegalStateException
             * This transaction will be used in the resume part too*/
            DeferredFragmentTransaction fragTrans=new DeferredFragmentTransaction() {
                @Override
                public void commit() {
                    replaceFragInternal(getContentFrameId(),getReplacingFrag() );
                }
            };
            fragTrans.setContentFrameId(fragFrameId);
            fragTrans.setReplaceFrag(replaceFrag);

            fragTransQueue.add(fragTrans);
        }else {
            replaceFragInternal(fragFrameId,replaceFrag);
        }
    }
    private void replaceFragInternal(int fragFrameId, android.support.v4.app.Fragment replaceFrag){
        FragmentManager fragMg=getSupportFragmentManager();
        fragMg.beginTransaction().replace(fragFrameId,replaceFrag).commit();
    }

    @Override
    protected void onResume(){
        super.onResume();
        isRunning=true;
    }
    @Override
    protected void onPostResume(){
        super.onPostResume();
        while(!fragTransQueue.isEmpty()){
            fragTransQueue.remove().commit();
        }
    }
    @Override
    protected void onPause(){
        super.onPause();
        isRunning=false;
    }
}
