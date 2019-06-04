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
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.github.ybq.android.spinkit.style.FadingCircle;
import com.yamibo.bbs.splashscreen.MainNavTabActivity;
import com.yamibo.bbs.splashscreen.R;

import Utils.Utility;
import Utils.VolleyHelper;
import Utils.VolleyResultCallback;
import Utils.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import Adapter.MyRecyclerAdapter;
import Adapter.SectionRecycleViewAdapter;
import Model.Base_Items_Model;
import Model.PostListItemsMod;

import static Utils.ApiConstants.FORUM_ANIME_MANGA_URL;

public class AnimeDiscussFragment extends Fragment implements MyRecyclerAdapter.OnItemClickListener {
    private static View v;
    private MyRecyclerAdapter recAdp;
    private RecyclerView mangaRecView;
    private List<Base_Items_Model> mangaDiscussList;
    private PostListItemsMod animes;
    private String tid;
    private List<SectionRecycleViewAdapter.Sections> animeSecs;

    private ProgressBar progressBar;

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
        mangaDiscussList = new ArrayList<>();
        mangaRecView = (RecyclerView) v.findViewById(R.id.post_recView);
        mangaRecView.setLayoutManager(new LinearLayoutManager(getContext()));
        animeSecs = new ArrayList<>();

        progressBar = (ProgressBar) v.findViewById(R.id.posts_loader);
        jsonParser();
    }

    private void jsonParser() {
        progressBar.setVisibility(View.VISIBLE);
        progressBar.setIndeterminateDrawable(new FadingCircle());
        VolleyHelper.volleyGETRequest(getContext(), FORUM_ANIME_MANGA_URL, new VolleyResultCallback() {
            @Override
            public void jsonResponse(JSONObject response) {
                try {
                    JSONObject var = response.getJSONObject("Variables");
                    JSONArray tArr = var.getJSONArray("forum_threadlist");
                    for (int i = 0; i < tArr.length(); i++) {
                        JSONObject tObj = tArr.getJSONObject(i);
                        animes = new PostListItemsMod(tObj.getString("subject"),
                                tObj.getString("author"),
                                tObj.getString("lastposter"),
                                "發於：" + tObj.getString("dateline"));
                        tid = tObj.getString("tid");
                        Utility.getSpecialThreadIds(mangaDiscussList, tid, animes);
                    }
                    animeSecs.add(new SectionRecycleViewAdapter.Sections(0, "全部主題"));
                    animeSecs.add(new SectionRecycleViewAdapter.Sections(4, "版塊主題"));

                    recAdp = new MyRecyclerAdapter(getContext(), mangaDiscussList);
                    recAdp.setOnItemClickListener(AnimeDiscussFragment.this);
                    SectionRecycleViewAdapter.Sections[] secArr = new SectionRecycleViewAdapter.Sections[animeSecs.size()];
                    SectionRecycleViewAdapter secAdp = new SectionRecycleViewAdapter(getContext(), R.layout.items_section,
                            R.id.catListSections, recAdp);
                    secAdp.setSections(animeSecs.toArray(secArr));
                    mangaRecView.setAdapter(secAdp);
                } catch (JSONException je) {
                    Toast.makeText(getContext(), je.getMessage(), Toast.LENGTH_LONG).show();
                }
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void responseError(VolleyError error) {
                error.getMessage();
            }
        });
    }

    @Override
    public void onItemClick(int position) {

    }
}
