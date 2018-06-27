package com.yamibo.bbs.splashscreen.Fragments;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.yamibo.bbs.splashscreen.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import Adapter.MyRecyclerAdapter;
import Adapter.SectionRecycleViewAdapter;
import Model.Base_Items_Model;
import Model.PostsListItems;

public class ChatFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener,
MyRecyclerAdapter.OnItemClickListener{
    private static View v;
    private static RecyclerView recyclerView,recyclerView1;
    private static MyRecyclerAdapter recycleAdp,recycleAdp_1;
    private static List<Base_Items_Model> baseModels,baseModels_1;
    private static String[] urls;
    private static RequestQueue rqstQueue; private int pos;
    private static SwipeRefreshLayout refreshSwiper;
    private static Handler handler=new Handler();
    private List<SectionRecycleViewAdapter.Sections> secsList;
    public ChatFragment(){}
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.fragment_post,container,false);
        return v;
    }
    @Override
    public void onViewCreated(View v,Bundle savedInstanceState){
        recyclerView = (RecyclerView)v.findViewById(R.id.post_recyc_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        rqstQueue = Volley.newRequestQueue(getContext());
        baseModels=new ArrayList<>();
        loadContent();
        if (isRefreshing()) {
            SwipeRefreshLayout.OnRefreshListener refreshListener =
                    new SwipeRefreshLayout.OnRefreshListener() {
                        @Override
                        public void onRefresh() {
                            refreshSwiper.setRefreshing(true);
                        }
                    };
        } else {
            retrieveDocuments();
        }
    }
    protected void retrieveDocuments(){
        if(refreshSwiper.isRefreshing()){
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    refreshSwiper.setRefreshing(false);
                }
            }, 1000);
        }
    }

    private void loadContent(){
        refreshSwiper=(SwipeRefreshLayout)v.findViewById(R.id.swipe_container);
        refreshSwiper.setOnRefreshListener(ChatFragment.this);
        refreshSwiper.post(new Runnable() {
            @Override
            public void run() {
                post_subs_JsonParser();
            }
        });
    }
    @Override
    public void onRefresh() {
        post_subs_JsonParser();
    }
    private boolean isRefreshing(){
        boolean flag=false;
        if(flag){
            return false;
        }
        return true;
    }
    private void post_subs_JsonParser(){
        refreshSwiper.setRefreshing(true);
        secsList=new ArrayList<>();
        urls=getResources().getStringArray(R.array.yamibo_api_urls);
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, urls[2], null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject var = response.getJSONObject("Variables");
                            JSONArray threadArr = var.getJSONArray("forum_threadlist");
                            for (int i = 0; i < threadArr.length(); i++) {
                                JSONObject threadObj = threadArr.getJSONObject(i);
                                String tid=threadObj.getString("tid");
                                PostsListItems postItems=new PostsListItems
                                        (threadObj.getString("subject"),
                                                threadObj.getString("author"),
                                                threadObj.getString("lastposter"),
                                                "發於："+threadObj.getString("dateline"));

                                if(tid.equals("474447")||tid.equals("20425")||tid.equals("232743")
                                        ||tid.equals("240477")){
                                    baseModels.add(postItems);

                                }else {
                                    baseModels.add(postItems);
                                }
                            }
                            secsList.add(new SectionRecycleViewAdapter.Sections(0,"全部主題"));
                            secsList.add(new SectionRecycleViewAdapter.Sections(4,"版塊主題"));

                            recycleAdp=new MyRecyclerAdapter(getContext(),baseModels);
                            recycleAdp.setOnItemClickListener(ChatFragment.this);
                            SectionRecycleViewAdapter.Sections[] secArr=new SectionRecycleViewAdapter.Sections[secsList.size()];
                            SectionRecycleViewAdapter secAdp =new SectionRecycleViewAdapter(getContext(),R.layout.catlist_sections,
                                    R.id.catListNames,recycleAdp);
                            secAdp.setSections(secsList.toArray(secArr));
                            recyclerView.setAdapter(secAdp);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        refreshSwiper.setRefreshing(false);
        rqstQueue.add(request);
    }
    @Override
    public void onItemClick(int position) {

    }
}