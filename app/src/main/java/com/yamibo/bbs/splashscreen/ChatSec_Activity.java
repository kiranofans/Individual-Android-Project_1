package com.yamibo.bbs.splashscreen;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;

import com.android.volley.RequestQueue;

import java.util.List;

import Adapter.ImgViewPagerAdapter;
import Adapter.MyRecyclerAdapter;
import Fragments.ChatFragment;
import Model.Base_Items_Model;

public class ChatSec_Activity extends Base_appBar_activity {
    private ImgViewPagerAdapter pagerAdp;
    private NavigationView chat_nav;
    private DrawerLayout chatDrawer;
    @SuppressLint({"ResourceAsColor", "ResourceType"})
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts);

        chatDrawer=(DrawerLayout)findViewById(R.id.chat_drawer);
        chat_nav=(NavigationView)findViewById(R.id.chat_nav);
        chat_nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                item.setChecked(true);
                chatDrawer.closeDrawers();
                return true;
            }
        });
        setChatDrawerListener();
        setChatFrag(new ChatFragment());
    }
    public void setChatFrag(android.support.v4.app.Fragment frg){
        if (frg != null) {
            android.support.v4.app.FragmentTransaction ft =
                    getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.contentFrm_no_tabs, new ChatFragment()).commit();
        }
        DrawerLayout chatDrawer= (DrawerLayout) findViewById(R.id.chat_drawer);
        chatDrawer.closeDrawer(GravityCompat.START);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem  items){
        switch (items.getItemId()){
            //Response to the action bar's Up/home button
            case android.R.id.home:
                supportFinishAfterTransition();
                return true;
        }
        return super.onOptionsItemSelected(items);
    }
    public void setChatDrawerListener(){
        chatDrawer.addDrawerListener(new DrawerLayout.DrawerListener() {
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

}
