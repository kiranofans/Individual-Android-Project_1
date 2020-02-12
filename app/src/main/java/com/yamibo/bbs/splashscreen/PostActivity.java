package com.yamibo.bbs.splashscreen;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.Toolbar;
import android.widget.SearchView;

import com.yamibo.bbs.data.Model.ForumsContentMod.ForumThreadMod;
import com.yamibo.bbs.splashscreen.databinding.ActivityPostBinding;

import static Utils.AppConstants.KEY_EXTRA_FORUMS_THREAD;

public class PostActivity extends BaseActivity {
    private SearchView searchView;
    private CollapsingToolbarLayout collapseToolbar;
    private Toolbar postToolbar;
    private FragmentTransaction ft;

    private ActivityPostBinding postBinding;

    @SuppressLint({"ResourceAsColor", "ResourceType"})

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        postBinding= DataBindingUtil.setContentView(this,R.layout.activity_post);

        configToolbar();
        getExtraObjectData();
    }

    private void getExtraObjectData(){
        ForumThreadMod forumThreadObj = (ForumThreadMod) getIntent().getSerializableExtra(KEY_EXTRA_FORUMS_THREAD);
        //postBinding.postDateTv.setText(forumThreadObj.getDateline());
        postBinding.toolbar.baseToolbar.setTitle(forumThreadObj.getSubject());
        postBinding.toolbar.baseToolbar.setSubtitle("Author: "+forumThreadObj.getAuthor()+
                "Publish At: "+forumThreadObj.getDateline());
        //setToolbarTitle(forumThreadObj.getSubject(),forumThreadObj.getAuthor(),forumThreadObj.getDateline());

    }

    private void setToolbar(){
        postToolbar = (Toolbar) findViewById(R.id.baseToolbar);
        setSupportActionBar(postToolbar);
        ViewCompat.setTransitionName(findViewById(R.id.app_bar_layout), "");

        collapseToolbar =(CollapsingToolbarLayout)findViewById(R.id.collapsy_toolbar);
        collapseToolbar.setTitleEnabled(true);
        collapseToolbar.setExpandedTitleColor(getResources()
                .getColor(android.R.color.transparent,null));
        collapseToolbar.setCollapsedTitleTextColor(getResources()
                .getColor(R.color.color_dark_red, null));
        postToolbar.hideOverflowMenu();

    }
    public void setFragments(android.support.v4.app.Fragment frg){
        if (frg != null) {
            ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.contentFrm_no_tabs, new Fragment()).commit();
        }
    }
}
