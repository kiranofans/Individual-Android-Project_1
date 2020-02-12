package com.yamibo.bbs.splashscreen;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;

import com.yamibo.bbs.data.Model.ForumsContentMod.ForumThreadMod;
import com.yamibo.bbs.splashscreen.databinding.AppBarLayoutBinding;

public abstract class BaseActivity extends AppCompatActivity {
    private String TAG = BaseActivity.class.getSimpleName();

    private AppBarLayoutBinding baseActivityBinding;
    private boolean isNavigationAsHome = false;

    private ViewGroup mRootContent;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        baseActivityBinding= DataBindingUtil.setContentView(this,R.layout.app_bar_layout);

        configToolbar();

    }

    void showHomeAsUp(){
        isNavigationAsHome = true;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    void hideHomeAsUp(){
        isNavigationAsHome= false;
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }

    void setToolbarTitle(String title, String author, String date){
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setSubtitle("Author: "+author+
                " Publish At: "+date);
    }
    void configToolbar(){
        if(baseActivityBinding.baseToolbar!=null){
            setSupportActionBar(baseActivityBinding.baseToolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        }
    }
}
