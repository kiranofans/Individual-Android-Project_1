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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yamibo.bbs.Adapter.PostsRecyclerViewAdapter;
import com.yamibo.bbs.ViewModels.ForumContentViewModel;
import com.yamibo.bbs.data.Model.ForumsContentMod.ForumThreadMod;
import com.yamibo.bbs.splashscreen.MainNavTabActivity;
import com.yamibo.bbs.splashscreen.R;
import com.yamibo.bbs.Adapter.MyRecyclerAdapter;
import com.yamibo.bbs.splashscreen.databinding.FragmentPostsBinding;

import java.util.ArrayList;
import java.util.List;

public class AnimeDiscussFragment extends Fragment implements MyRecyclerAdapter.OnItemClickListener {
    private final String TAG = AnimeDiscussFragment.class.getSimpleName();

    private static View v;
    private ForumContentViewModel animeViewModel;
    private FragmentPostsBinding animeBinding;

    private PostsRecyclerViewAdapter recyclerViewAdapter;

    private boolean isLoading = false;
    private int currentPageNum = 1;
    private final String ANIME_FORUM_ID = "5";

    private List<ForumThreadMod> animeList= new ArrayList<>();
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        ((MainNavTabActivity) getActivity()).fragsCustomToolbar("動漫區");

        animeBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_posts,container,false);

        //Set ViewModel
        animeViewModel= ViewModelProviders.of(requireActivity()).get(ForumContentViewModel.class);

        v=animeBinding.getRoot();
        return v;
    }

    @Override
    public void onViewCreated(View v, Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);

        initRecyclerView();
        setDataObserver();
        getForumContentData(currentPageNum,ANIME_FORUM_ID);
        swipeToRefreshListener();
        onScrollListener();
    }

    private void initRecyclerView(){
        recyclerViewAdapter = new PostsRecyclerViewAdapter(v.getContext(), animeList);

        animeBinding.recyclerViewPost.setLayoutManager(new LinearLayoutManager(v.getContext()));
        animeBinding.recyclerViewPost.setItemAnimator(new DefaultItemAnimator());
        animeBinding.recyclerViewPost.setAdapter(recyclerViewAdapter);//set recView adapter
    }
    private void setDataObserver(){
        animeViewModel.getForumLiveData().observe(this, new Observer<List<ForumThreadMod>>() {
            @Override
            public void onChanged(@Nullable List<ForumThreadMod> forumThreadMods) {
                isLoading = false;
                animeList.addAll(forumThreadMods);
                animeBinding.swipeContainer.setRefreshing(false);
                recyclerViewAdapter.notifyDataSetChanged();
            }
        });
    }
    private void onScrollListener() {
        animeBinding.recyclerViewPost.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                LinearLayoutManager linearLayoutMgr = (LinearLayoutManager) animeBinding.recyclerViewPost.getLayoutManager();

                int visibleItemCount = linearLayoutMgr.getChildCount();
                int totalItemCount = linearLayoutMgr.getItemCount();
                int firstVisibleItemPos = linearLayoutMgr.findFirstVisibleItemPosition();
                if (!isLoading) {
                    if ((visibleItemCount + firstVisibleItemPos) >= totalItemCount
                            && firstVisibleItemPos >= 0 && totalItemCount >= 14) {
                        currentPageNum++;
                        getForumContentData(currentPageNum, ANIME_FORUM_ID);
                        isLoading = true;
                        //Assign True again to make the isLoading variable to False;
                        Log.d(TAG,"Data is loading");
                    }
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
        recyclerViewAdapter.notifyDataSetChanged();
    }

    private void getForumContentData(int pageNumber, String forumId) {
        Log.d(TAG,"API called "+pageNumber);
        animeBinding.swipeContainer.setRefreshing(true);
        animeViewModel.getForumThreads(forumId,pageNumber);
    }

    private void swipeToRefreshListener() {
        animeBinding.swipeContainer.setOnRefreshListener(() -> {
            currentPageNum = 1;
            recyclerViewAdapter.clearListItems();
            getForumContentData(currentPageNum, ANIME_FORUM_ID);
        });
    }
    @Override
    public void onItemClick(int position) {

    }
}
