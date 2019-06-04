package com.yamibo.bbs.splashscreen.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.android.volley.VolleyError;
import com.github.ybq.android.spinkit.style.FadingCircle;
import com.yamibo.bbs.splashscreen.MainNavTabActivity;
import com.yamibo.bbs.splashscreen.R;

import Utils.Utility;
import Utils.VolleyHelper;
import Utils.VolleyResultCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import Adapter.MyRecyclerAdapter;
import Adapter.SectionRecycleViewAdapter;
import Model.Base_Items_Model;
import Model.PostListItemsMod;

import static Utils.ApiConstants.FORUM_ADMIN_URL;

public class AdminFragment extends Fragment {
    private RecyclerView recView;
    private MyRecyclerAdapter recAdp;
    private List<Base_Items_Model> admList;
    private List<SectionRecycleViewAdapter.Sections> sections;
    public AdminFragment() {/*Required empty public constructor*/}

    private ProgressBar progressBar;
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

        progressBar = (ProgressBar)v.findViewById(R.id.posts_loader);
        getAdminPost();
    }
    private void getAdminPost(){
        progressBar.setVisibility(View.VISIBLE);
        progressBar.setIndeterminateDrawable(new FadingCircle());
        VolleyHelper.volleyGETRequest(getContext(), FORUM_ADMIN_URL, new VolleyResultCallback() {
            @Override
            public void jsonResponse(JSONObject response) {
                try {
                    JSONObject var=response.getJSONObject("Variables");
                    JSONArray threadArr=var.getJSONArray("forum_threadlist");
                    for(int i=0;i<threadArr.length();i++){
                        JSONObject tObj=threadArr.getJSONObject(i);
                        PostListItemsMod admin=new PostListItemsMod(tObj.getString
                                ("subject"),tObj.getString("author"),
                                tObj.getString("lastposter"),
                                tObj.getString("dateline"));
                        Utility.getSpecialThreadIds(admList,tObj.getString("tid"),admin);
                    }
                    sections.add(new SectionRecycleViewAdapter.Sections(0,"全部主題"));
                    sections.add(new SectionRecycleViewAdapter.Sections(5,"版塊主題"));
                    recAdp=new MyRecyclerAdapter(getContext(),admList);

                    SectionRecycleViewAdapter.Sections[] secArr=new SectionRecycleViewAdapter.Sections[sections.size()];
                    SectionRecycleViewAdapter secAdp =new SectionRecycleViewAdapter(getContext(),R.layout.items_section,
                            R.id.catListSections,recAdp);
                    secAdp.setSections(sections.toArray(secArr));
                    recView.setAdapter(secAdp);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void responseError(VolleyError error) {
                Utility.showErrorMessageToast(getContext(),error.getMessage());
            }
        });
    }
}
