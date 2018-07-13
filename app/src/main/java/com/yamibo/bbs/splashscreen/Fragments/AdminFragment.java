package com.yamibo.bbs.splashscreen.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.yamibo.bbs.splashscreen.MainNavTabActivity;
import com.yamibo.bbs.splashscreen.R;
import com.yamibo.bbs.splashscreen.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import Adapter.MyRecyclerAdapter;
import Adapter.SectionRecycleViewAdapter;
import Model.Base_Items_Model;
import Model.PostListItems;

public class AdminFragment extends Fragment {
    private RecyclerView recView;
    private MyRecyclerAdapter recAdp;
    private static String[] urls;
    private List<Base_Items_Model> admList;
    private List<SectionRecycleViewAdapter.Sections> sections;
    public AdminFragment() {/*Required empty public constructor*/}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((MainNavTabActivity)getActivity()).fragsCustomToolbar("管理版");

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_posts, container, false);
    }
    @Override
    public void onViewCreated(View v,Bundle savedInstanceState){
        recView=(RecyclerView)v.findViewById(R.id.post_recView);
        recView.setLayoutManager(new LinearLayoutManager(getContext()));
        admList=new ArrayList<>();
        sections=new ArrayList<>();
        urls=getActivity().getResources().getStringArray(R.array.forums_urls);
        getAdminPosts();
    }
    private void getAdminPosts(){
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, urls[1], null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject var=response.getJSONObject("Variables");
                            JSONArray threadArr=var.getJSONArray("forum_threadlist");
                            for(int i=0;i<threadArr.length();i++){
                                JSONObject tObj=threadArr.getJSONObject(i);
                                PostListItems admin=new PostListItems(tObj.getString
                                        ("subject"),tObj.getString("author"),
                                        tObj.getString("lastposter"),tObj.getString("dateline"));
                                String tid=tObj.getString("tid");
                                if(tid.equals("474447")||tid.equals("20425")||tid.equals("232743")
                                        ||tid.equals("240477")){
                                    admList.add(admin);
                                }else {
                                    admList.add(admin);
                                }
                            }
                            sections.add(new SectionRecycleViewAdapter.Sections(0,"全部主題"));
                            sections.add(new SectionRecycleViewAdapter.Sections(5,"版塊主題"));
                            recAdp=new MyRecyclerAdapter(getContext(),admList);

                            SectionRecycleViewAdapter.Sections[] secArr=new SectionRecycleViewAdapter.Sections[sections.size()];
                            SectionRecycleViewAdapter secAdp =new SectionRecycleViewAdapter(getContext(),R.layout.sections_items,
                                    R.id.catListSections,recAdp);
                            secAdp.setSections(sections.toArray(secArr));
                            recView.setAdapter(secAdp);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.getMessage();
            }
        });
        VolleySingleton.getInstance(getContext()).addToRequestQueue(request);
    }

}
