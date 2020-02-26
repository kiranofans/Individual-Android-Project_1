package com.yamibo.bbs.splashscreen;

import android.annotation.SuppressLint;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.widget.SearchView;

import com.yamibo.bbs.data.Model.ForumsContentMod.ForumThreadMod;
import com.yamibo.bbs.splashscreen.databinding.ActivityPostBinding;
import com.yamibo.bbs.splashscreen.databinding.AppBarLayoutBinding;

import static com.yamibo.bbs.data.AppConstants.KEY_EXTRA_FORUMS_THREAD;

public class ThreadContentActivity extends BaseActivity {
    private SearchView searchView;
    private CollapsingToolbarLayout collapseToolbar;
    private Toolbar postToolbar;
    private FragmentTransaction ft;

    private ActivityPostBinding postBinding;
    private AppBarLayoutBinding appBarLayoutBinding;

    @SuppressLint({"ResourceAsColor", "ResourceType"})

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        postBinding = DataBindingUtil.setContentView(this, R.layout.activity_post);

        showHomeAsUp();
        getExtraObjectData();
    }

    private void getExtraObjectData() {
        ForumThreadMod forumThreadObj = (ForumThreadMod) getIntent().getSerializableExtra(KEY_EXTRA_FORUMS_THREAD);
        postBinding.postDateTv.setText(forumThreadObj.getDateline());
        setToolbarTitle(forumThreadObj.getSubject(), forumThreadObj.getAuthor(), forumThreadObj.getDateline());

    }

    public void setFragments(android.support.v4.app.Fragment frg) {
        if (frg != null) {
            ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.contentFrm_no_tabs, new Fragment()).commit();
        }
    }
}
