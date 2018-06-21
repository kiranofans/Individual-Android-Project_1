package com.yamibo.bbs.splashscreen;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;

import Adapter.ImgViewPagerAdapter;
import Fragments.ChatFragment;

public class ChatSec_Activity extends BaseActivity {
    private ImgViewPagerAdapter pagerAdp;
    private NavigationView chat_nav;
    private DrawerLayout chatDrawer;
    @SuppressLint({"ResourceAsColor", "ResourceType"})
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts);
        ActionBar ab=getSupportActionBar();
        Toolbar chatToolbar = (Toolbar) findViewById(R.id.baseToolbar);
        chatToolbar.setTitle("海域區");
        setSupportActionBar(chatToolbar);

        setChatFrag(new ChatFragment());
    }
    public void setChatFrag(android.support.v4.app.Fragment frg){
        if (frg != null) {
            android.support.v4.app.FragmentTransaction ft =
                    getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.contentFrm_no_tabs, new ChatFragment()).commit();
        }
    }

}
