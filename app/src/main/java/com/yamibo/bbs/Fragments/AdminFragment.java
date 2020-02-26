package com.yamibo.bbs.Fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yamibo.bbs.Adapter.PostsRecyclerViewAdapter;
import com.yamibo.bbs.ViewModels.ForumContentViewModel;
import com.yamibo.bbs.data.Model.ForumsContentMod.ForumThreadMod;
import com.yamibo.bbs.splashscreen.MainNavTabActivity;
import com.yamibo.bbs.splashscreen.R;
import com.yamibo.bbs.splashscreen.databinding.FragmentPostsBinding;

import java.util.ArrayList;
import java.util.List;

public class AdminFragment extends Fragment {
    private ForumContentViewModel adminViewModel;
    private View v;
    private FragmentPostsBinding admBinding;

    private PostsRecyclerViewAdapter recAdp;
    private List<ForumThreadMod> admList=new ArrayList<>();

    public AdminFragment() {/*Required empty public constructor*/}

    private final String ADMIN_FORUM_ID = "16";
    private int currentPageNum = 1;
    private boolean isLoading = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((MainNavTabActivity) getActivity()).fragsCustomToolbar("管理版");
        admBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_posts, container,
                false);
        adminViewModel = ViewModelProviders.of(requireActivity()).get(ForumContentViewModel.class);
        v = admBinding.getRoot();

        // Inflate the layout for this fragment
        return v;
    }

    @Override
    public void onViewCreated(View v, Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);

        recAdp = new PostsRecyclerViewAdapter(v.getContext(), admList);
        admBinding.recyclerViewPost.setLayoutManager(new LinearLayoutManager(v.getContext()));
        admBinding.recyclerViewPost.setItemAnimator(new DefaultItemAnimator());
        admBinding.recyclerViewPost.setAdapter(recAdp);//set recView adapter

        setAdminObserver();
        getForumContentData(currentPageNum,ADMIN_FORUM_ID);
        swipeToRefreshListener();
        onScrollListener();
    }

    private void onScrollListener() {
        admBinding.recyclerViewPost.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                LinearLayoutManager linearLayoutMgr = (LinearLayoutManager) admBinding.recyclerViewPost.getLayoutManager();

                int visibleItemCount = linearLayoutMgr.getChildCount();
                int totalItemCount = linearLayoutMgr.getItemCount();
                int firstVisibleItemPos = linearLayoutMgr.findFirstVisibleItemPosition();
                if (!isLoading) {
                    if ((visibleItemCount + firstVisibleItemPos) >= totalItemCount
                            && firstVisibleItemPos >= 0 && totalItemCount >= 14) {
                        currentPageNum++;
                        getForumContentData(currentPageNum, ADMIN_FORUM_ID);
                        isLoading = true;
                        //Assign True again to make the isLoading variable to False;
                    }
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
        recAdp.notifyDataSetChanged();
    }

    private void swipeToRefreshListener() {
        admBinding.swipeContainer.setOnRefreshListener(() -> {
            currentPageNum = 1;
            recAdp.clearListItems();
            getForumContentData(currentPageNum, ADMIN_FORUM_ID);
        });
    }

    private void setAdminObserver(){
        adminViewModel.getForumLiveData().observe(this, new Observer<List<ForumThreadMod>>() {
            @Override
            public void onChanged(@Nullable List<ForumThreadMod> forumThreadMods) {
                isLoading = false;
                admList.addAll(forumThreadMods);
                admBinding.swipeContainer.setRefreshing(false);
                recAdp.notifyDataSetChanged();
            }
        });
    }
    private void getForumContentData(int pageNumber, String forumId) {
        admBinding.swipeContainer.setRefreshing(true);
        adminViewModel.getForumThreads(forumId,pageNumber);
    }
}
