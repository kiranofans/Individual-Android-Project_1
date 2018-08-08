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
import android.view.WindowManager;
import android.widget.SearchView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.yamibo.bbs.splashscreen.MainNavTabActivity;
import com.yamibo.bbs.splashscreen.R;
import Utility.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import Adapter.ImgViewPagerAdapter;
import Adapter.MyRecyclerAdapter;
import Adapter.SectionRecycleViewAdapter;
import Model.Base_Items_Model;
import Model.PostListItems;

public class ChatFragment extends Fragment implements
MyRecyclerAdapter.OnItemClickListener{
    private static View v;
    private static RecyclerView recyclerView;
    private static MyRecyclerAdapter recycleAdp;
    private static List<Base_Items_Model> chatList;
    private static String[] urls;
    private static SwipeRefreshLayout refreshSwiper;
    private static Handler handler=new Handler();
    private SearchView searchView;
    private List<SectionRecycleViewAdapter.Sections> secsList;
    private ImgViewPagerAdapter vpAdp;
    private int refresh_count;
    public ChatFragment(){/*empty constructor is required*/}
    private static List<String> imgUrlList;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.fragment_posts,container,false);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        ((MainNavTabActivity)getActivity()).fragsCustomToolbar("海域區");

        return v;
    }
    @Override
    public void onViewCreated(View v,Bundle savedInstanceState){
        recyclerView = (RecyclerView)v.findViewById(R.id.post_recView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

      /*  //ViewPager items
        vpAdp=new ImgViewPagerAdapter(getContext(),imgUrlList);
        urls=v.getContext().getResources().getStringArray(R.array.img_urls);
        setViewPagerImg(urls[0]);
        MainNavTabActivity.imgVp.setAdapter(vpAdp);//直接用MainActivity的*/

        //RecyclerView items
        chatList =new ArrayList<>();
        chatJSONParser();
        refresher();

    }
    private void refresher(){
        refreshSwiper=(SwipeRefreshLayout)v.findViewById(R.id.swipe_container);
        refreshSwiper.setColorSchemeColors(getResources().getColor(R.color.colorAccent,null));
        refreshSwiper.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshSwiper.setRefreshing(false);
                    }
                },1000);
            }
        });
    }

    private void chatJSONParser(){
        secsList=new ArrayList<>();
        urls=getResources().getStringArray(R.array.forums_urls);
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, urls[0], null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject var = response.getJSONObject("Variables");
                            JSONArray threadArr = var.getJSONArray("forum_threadlist");
                            for (int i = 0; i < threadArr.length(); i++) {
                                JSONObject threadObj = threadArr.getJSONObject(i);
                                String tid=threadObj.getString("tid");
                                PostListItems postItems=new PostListItems
                                        (threadObj.getString("subject"),
                                                threadObj.getString("author"),
                                                threadObj.getString("lastposter"),
                                                "發於："+threadObj.getString("dateline"));

                                if(tid.equals("474447")||tid.equals("20425")||tid.equals("232743")
                                        ||tid.equals("240477")){
                                    chatList.add(postItems);

                                }else {
                                    chatList.add(postItems);
                                }
                            }
                            secsList.add(new SectionRecycleViewAdapter.Sections(0,"全部主題"));
                                    secsList.add(new SectionRecycleViewAdapter.Sections(4,"版塊主題"));
                                    recycleAdp=new MyRecyclerAdapter(getContext(),chatList);
                                    recycleAdp.setOnItemClickListener(ChatFragment.this);
                                    SectionRecycleViewAdapter.Sections[] secArr=new SectionRecycleViewAdapter.Sections[secsList.size()];
                                    SectionRecycleViewAdapter secAdp =new SectionRecycleViewAdapter(getContext(),R.layout.items_section,
                                            R.id.catListSections,recycleAdp);
                                    secAdp.setSections(secsList.toArray(secArr));
                                    recyclerView.setAdapter(secAdp);
                                    refreshSwiper.setRefreshing(false);
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
        VolleySingleton.getInstance(getContext()).addToRequestQueue(request);

    }
    @Override
    public void onItemClick(int position) {

    }
    private void setViewPagerImg(String imgUrls){
        imgUrlList =new ArrayList<>();
        imgUrlList.add(imgUrls);
    }
}