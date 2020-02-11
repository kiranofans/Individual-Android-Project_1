package com.yamibo.bbs.splashscreen.Fragments;

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
import android.widget.ProgressBar;

import com.yamibo.bbs.Adapter.RecyclerViewForumAdmin;
import com.yamibo.bbs.ViewModels.ViewModelForums;
import com.yamibo.bbs.data.Model.ForumsContentMod.ForumThreadMod;
import com.yamibo.bbs.splashscreen.MainNavTabActivity;
import com.yamibo.bbs.splashscreen.R;

import java.util.ArrayList;
import java.util.List;

import com.yamibo.bbs.splashscreen.databinding.FragmentPostsBinding;

public class AdminFragment extends Fragment {
    private ViewModelForums adminViewModel;
    private View v;
    private FragmentPostsBinding admBinding;

    private RecyclerViewForumAdmin recAdp;
    private List<ForumThreadMod> admList;
    //private List<SectionRecycleViewAdapter.Sections> sections;

    public AdminFragment() {/*Required empty public constructor*/}

    private ProgressBar progressBar;

    private final String ADMIN_FORUM_ID="16";
    private int currentPageNum = 1;
    private boolean isLoading = false;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((MainNavTabActivity) getActivity()).fragsCustomToolbar("管理版");
        admBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_posts, container,
                false);
        adminViewModel = ViewModelProviders.of(requireActivity()).get(ViewModelForums.class);
        v = admBinding.getRoot();

        // Inflate the layout for this fragment
        return v;
    }

    @Override
    public void onViewCreated(View v, Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);

        admList = new ArrayList<>();
        getAdminForumData(currentPageNum,ADMIN_FORUM_ID);
        swipeToRefreshListener();
    }

    private void setDataToRecView() {
        recAdp = new RecyclerViewForumAdmin(v.getContext(), admList);

        admBinding.recViewPost.setLayoutManager(new LinearLayoutManager(v.getContext()));
        admBinding.recViewPost.setItemAnimator(new DefaultItemAnimator());
        admBinding.recViewPost.setAdapter(recAdp);//set recView adapter

        admBinding.recViewPost.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                LinearLayoutManager linearLayoutMgr = (LinearLayoutManager) admBinding.recViewPost.getLayoutManager();

                int visibleItemCount = linearLayoutMgr.getChildCount();
                int totalItemCount = linearLayoutMgr.getItemCount();
                int firstVisibleItemPos = linearLayoutMgr.findFirstVisibleItemPosition();
                if (!isLoading) {
                    if ((visibleItemCount + firstVisibleItemPos) >= totalItemCount
                            && firstVisibleItemPos >= 0 && totalItemCount >= 15) {
                        currentPageNum++;
                        getAdminForumData(currentPageNum,ADMIN_FORUM_ID);

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
            getAdminForumData(currentPageNum,ADMIN_FORUM_ID);
        });
    }

    private void getAdminForumData(int pageNumber,String forumId) {
        admBinding.swipeContainer.setRefreshing(true);
        adminViewModel.getForumThreads(forumId,pageNumber).observe(this, new Observer<List<ForumThreadMod>>() {
            @Override
            public void onChanged(@Nullable List<ForumThreadMod> forumThreadMods) {
                isLoading = false;
                admList.addAll(forumThreadMods);
                admBinding.swipeContainer.setRefreshing(false);
                setDataToRecView();
            }
        });
    }
}
