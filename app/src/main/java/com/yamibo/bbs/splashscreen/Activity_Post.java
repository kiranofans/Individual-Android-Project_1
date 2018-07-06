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

import com.yamibo.bbs.splashscreen.Fragments.FragmentChat;
import com.yamibo.bbs.splashscreen.Fragments.MangaDiscussionFragment;

public class Activity_Post extends AppCompatActivity {
    private SearchView searchView;
    private CollapsingToolbarLayout collapseToolbar;
    private Toolbar postToolbar;
    private FragmentTransaction ft;
    @SuppressLint({"ResourceAsColor", "ResourceType"})
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts);

        setToolbarAndFragments();
       // setChatFrag(new FragmentChat());

    }
    private void setToolbarAndFragments(){
        Fragment[] fragments={new FragmentChat()};
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

        int pos=0;
        switch(pos){
            case 0:
                setChatFrag(new FragmentChat());
                collapseToolbar.setTitle("海域區");
            break;
            case 1:
                collapseToolbar.setTitle("動漫區");
            break;
        }

    }

    public void setChatFrag(android.support.v4.app.Fragment frg){
        if (frg != null) {
            ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.contentFrm_no_tabs, new FragmentChat()).commit();
        }

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

}
