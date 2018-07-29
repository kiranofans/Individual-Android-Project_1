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
import android.widget.Toast;

import com.android.volley.Request;
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

public class AnimeDiscussFragment extends Fragment implements MyRecyclerAdapter.OnItemClickListener{
    private static View v;
    private MyRecyclerAdapter recAdp;
    private RecyclerView mangaRecView;
    private List<Base_Items_Model> mangaDiscussList;
    private PostListItems animes;
    private String[] urls;
    private String tid;
    private List<SectionRecycleViewAdapter.Sections> animeSecs;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.fragment_posts,container,false);
        ((MainNavTabActivity)getActivity()).fragsCustomToolbar("動漫區");
        return v;
    }
    @Override
    public void onViewCreated(View v,Bundle savedInstanceState){
        super.onViewCreated(v, savedInstanceState);
        mangaDiscussList=new ArrayList<>();
        mangaRecView=(RecyclerView)v.findViewById(R.id.post_recView);
        mangaRecView.setLayoutManager(new LinearLayoutManager(getContext()));
        urls=getActivity().getResources().getStringArray(R.array.forums_urls);
        animeSecs=new ArrayList<>();

        jsonParser();
    }
    private void jsonParser(){

        JsonObjectRequest rqst=new JsonObjectRequest(Request.Method.GET, urls[2], null,
                new com.android.volley.Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try{
                            JSONObject var=response.getJSONObject("Variables");
                            JSONArray tArr=var.getJSONArray("forum_threadlist");
                            for (int i=0;i<tArr.length();i++){
                                JSONObject tObj=tArr.getJSONObject(i);
                                animes=new PostListItems(tObj.getString("subject"),
                                                tObj.getString("author"),
                                                tObj.getString("lastposter"),
                                                "發於："+tObj.getString("dateline"));
                                tid=tObj.getString("tid");
                                if(tid.equals("474447")||tid.equals("20425")||tid.equals("232743")
                                        ||tid.equals("240477")){
                                    mangaDiscussList.add(animes);

                                }else {
                                    mangaDiscussList.add(animes);
                                }
                            }
                            animeSecs.add(new SectionRecycleViewAdapter.Sections(0,"全部主題"));
                            animeSecs.add(new SectionRecycleViewAdapter.Sections(4,"版塊主題"));

                            recAdp=new MyRecyclerAdapter(getContext(), mangaDiscussList);
                            recAdp.setOnItemClickListener(AnimeDiscussFragment.this);
                            SectionRecycleViewAdapter.Sections[] secArr=new SectionRecycleViewAdapter.Sections[animeSecs.size()];
                            SectionRecycleViewAdapter secAdp =new SectionRecycleViewAdapter(getContext(),R.layout.items_section,
                                    R.id.catListSections,recAdp);
                            secAdp.setSections(animeSecs.toArray(secArr));
                            mangaRecView.setAdapter(secAdp);
                        }catch (JSONException je){
                            Toast.makeText(getContext(),je.getMessage(),Toast.LENGTH_LONG).show();
                        }
                    }
                }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(),error.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
        VolleySingleton.getInstance(getContext()).addToRequestQueue(rqst);
    }

    @Override
    public void onItemClick(int position) {

    }
}
