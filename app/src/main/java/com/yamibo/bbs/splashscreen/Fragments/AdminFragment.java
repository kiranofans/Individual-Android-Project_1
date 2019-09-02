package com.yamibo.bbs.splashscreen.Fragments;

import android.os.Bundle;
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

import java.util.ArrayList;
import java.util.List;

import Adapter.MyRecyclerAdapter;
import Adapter.SectionRecycleViewAdapter;
import Model.Base_Items_Model;

import static Utils.ApiConstants.FORUM_ADMIN_URL;

public class AdminFragment extends Fragment {
    private RecyclerView recView;
    private MyRecyclerAdapter recAdp;
    private List<Base_Items_Model> admList;
    private List<SectionRecycleViewAdapter.Sections> sections;

    public AdminFragment() {/*Required empty public constructor*/}

    private ProgressBar progressBar;

    private PostContentsManager postMgr;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((MainNavTabActivity) getActivity()).fragsCustomToolbar("管理版");

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_posts, container, false);
    }

    @Override
    public void onViewCreated(View v, Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);
        postMgr = PostContentsManager.getInstance();

        recView = (RecyclerView) v.findViewById(R.id.post_recView);
        recView.setLayoutManager(new LinearLayoutManager(getContext()));
        admList = new ArrayList<>();
        sections = new ArrayList<>();

        progressBar = (ProgressBar) v.findViewById(R.id.posts_loader);
        if (postMgr != null) {
            postMgr.volleyGetPostContent(getContext(), FORUM_ADMIN_URL, progressBar, recView);
        }
    }
}
