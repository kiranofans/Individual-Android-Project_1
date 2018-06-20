package com.yamibo.bbs.splashscreen;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import Adapter.ImgViewPagerAdapter;
import Adapter.MyRecyclerAdapter;
import Fragments.ChatFragment;
import Model.Base_Items_Model;
import Model.DeferredFragmentTransaction;
import Model.PostsListItems;

public class ChatSec_Activity extends Base_appBar_activity implements NavigationView.OnNavigationItemSelectedListener
{
    private static RecyclerView recyclerView,recyclerView1;
    private static MyRecyclerAdapter recycleAdp,recycleAdp_1;
    private static List<Base_Items_Model> baseModels,baseModels_1;
    private static String[] urls;
    private static RequestQueue rqstQueue; private int pos;
    private static SwipeRefreshLayout refreshSwiper;
    private static Handler handler=new Handler();
    private android.support.v4.app.Fragment chatFragment;
    private boolean isRunning;
    private ImgViewPagerAdapter pagerAdp;
    @SuppressLint({"ResourceAsColor", "ResourceType"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts);


    }
       /* recyclerView = (RecyclerView)findViewById(R.id.post_recyc_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView1 = (RecyclerView)findViewById(R.id.postRecView_2);
        recyclerView1.setLayoutManager(new LinearLayoutManager((this)));

        rqstQueue = Volley.newRequestQueue(this);
        baseModels_1=new ArrayList<>(); baseModels=new ArrayList<>();

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
        loadContent();
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
        refreshSwiper=(SwipeRefreshLayout)findViewById(R.id.swipe_container);
        refreshSwiper.setOnRefreshListener(ChatSec_Activity.this);

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
        baseModels_1=new ArrayList<>(); baseModels=new ArrayList<>();
        urls=getResources().getStringArray(R.array.yamibo_api_urls);
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, urls[2], null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject var = response.getJSONObject("Variables");
                            JSONArray threadArr = var.getJSONArray("forum_threadlist");
                            String tid="";
                            for (int i = 0; i < threadArr.length(); i++) {
                                JSONObject threadObj = threadArr.getJSONObject(i);
                                tid=threadObj.getString("tid");
                                PostsListItems postItems=new PostsListItems
                                        (threadObj.getString("subject"),
                                                threadObj.getString("author"),
                                                threadObj.getString("lastposter"),
                                                threadObj.getString("views")+"查看",
                                                threadObj.getString("replies")+"回復");

                                if(tid.equals("474447")||tid.equals("20425")||tid.equals("232743")
                                        ||tid.equals("240477")){
                                    baseModels_1.add(postItems);
                                }else {
                                    baseModels.add(postItems);
                                }
                            }

                            recycleAdp=new MyRecyclerAdapter(getApplicationContext(),baseModels);
                            recyclerView.setAdapter(recycleAdp);

                            recycleAdp_1=new MyRecyclerAdapter(getApplicationContext(),baseModels_1);
                            recyclerView1.setAdapter(recycleAdp_1);

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

        }*/
    @Override
    public boolean onOptionsItemSelected(MenuItem  items){
        switch (items.getItemId()){
            //Response to the action bar's Up/home button
            case android.R.id.home:
                supportFinishAfterTransition();
                return true;
        }
        return super.onOptionsItemSelected(items);
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return true;
    }

}
