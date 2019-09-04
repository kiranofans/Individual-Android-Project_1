package com.yamibo.bbs.splashscreen.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.yamibo.bbs.splashscreen.MainNavTabActivity;
import com.yamibo.bbs.splashscreen.R;
import Managers.PostContentsManager;
import Adapter.MyRecyclerAdapter;
import static Utils.ApiConstants.FORUM_ANIME_MANGA_URL;

public class AnimeDiscussFragment extends Fragment implements MyRecyclerAdapter.OnItemClickListener {
    private static View v;
    private RecyclerView mangaRecView;

    private ProgressBar progressBar;
    private PostContentsManager postMgr;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_posts, container, false);
        ((MainNavTabActivity) getActivity()).fragsCustomToolbar("動漫區");
        return v;
    }

    @Override
    public void onViewCreated(View v, Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);
        postMgr = PostContentsManager.getInstance();
        mangaRecView = (RecyclerView) v.findViewById(R.id.recView_post);
        mangaRecView.setLayoutManager(new LinearLayoutManager(getContext()));

        progressBar = (ProgressBar) v.findViewById(R.id.posts_loader);
        postMgr.volleyGetPostContent(getContext(), FORUM_ANIME_MANGA_URL, progressBar, mangaRecView);
    }

    @Override
    public void onItemClick(int position) {

    }
}
