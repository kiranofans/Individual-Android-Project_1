package com.yamibo.bbs.Fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.SearchView;

import com.yamibo.bbs.Adapter.ImgViewPagerAdapter;
import com.yamibo.bbs.Adapter.ForumsRecyclerViewAdapter;
import com.yamibo.bbs.Adapter.SectionRecycleViewAdapter;
import com.yamibo.bbs.data.Model.Base_Items_Model;
import com.yamibo.bbs.splashscreen.MainNavTabActivity;
import com.yamibo.bbs.splashscreen.R;

import java.util.ArrayList;
import java.util.List;

import static com.yamibo.bbs.Network.ApiConstants.MY_POSTS_API_URL;

public class ChatFragment extends Fragment implements
        ForumsRecyclerViewAdapter.OnItemClickListener {
    private static String LOG_TAG = ChatFragment.class.getSimpleName();

    private static View v;
    private static RecyclerView recyclerView;
    private static ForumsRecyclerViewAdapter recycleAdp;
    private static List<Base_Items_Model> chatList;
    private static SwipeRefreshLayout refreshSwiper;
    private static Handler handler = new Handler();
    private SearchView searchView;
    private List<SectionRecycleViewAdapter.Sections> secsList;
    private ImgViewPagerAdapter vpAdp;
    private int refresh_count;

    public ChatFragment() {/*empty constructor is required*/}

    private static List<String> imgUrlList;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_posts, container, false);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        ((MainNavTabActivity) getActivity()).fragsCustomToolbar("海域區");

        return v;
    }

    @Override
    public void onViewCreated(View v, Bundle savedInstanceState) {
        recyclerView = (RecyclerView) v.findViewById(R.id.recycler_view_post);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        secsList = new ArrayList<>();

        //RecyclerView items
        chatList = new ArrayList<>();

        //ChattingRestListener chatListener = new ChattingRestListener(getActivity(),);
        //restClientUtils.post(MY_POSTS_API_URL,chatListener,chatListener);
        // initVolleyCallback();
        //getData();
        Log.d(LOG_TAG, "Enqueuing the following REST request " + MY_POSTS_API_URL);

        //refresher();

    }

    private void refresher() {
        refreshSwiper = (SwipeRefreshLayout) v.findViewById(R.id.swipe_container);
        refreshSwiper.setColorSchemeColors(getResources().getColor(R.color.colorAccent, null));
        refreshSwiper.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshSwiper.setRefreshing(false);
                    }
                }, 1000);/**/
            }
        });
    }

   /* private void getData() {
        VolleyHelper.volleyGETRequest(getContext(),MY_POSTS_API_URL, new VolleyResultCallback(){
            @Override
            public void jsonResponse(JSONObject response) {
                try {

                    PostListItemsMod postListItemMod = new PostListItemsMod(chatList,postItemInstance,response);
                    //chatList.add(postListItemMod);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                secsList.add(new SectionRecycleViewAdapter.Sections(0, "全部主題"));
                secsList.add(new SectionRecycleViewAdapter.Sections(4, "版塊主題"));
                recycleAdp = new ForumsRecyclerViewAdapter(getContext(), chatList);
                recycleAdp.setOnItemClickListener(ChatFragment.this);
                SectionRecycleViewAdapter.Sections[] secArr = new SectionRecycleViewAdapter.Sections[secsList.size()];
                SectionRecycleViewAdapter secAdp = new SectionRecycleViewAdapter(getContext(), R.layout.items_section,
                        R.id.catListSections, recycleAdp);
                secAdp.setSections(secsList.toArray(secArr));
                recyclerView.setAdapter(secAdp);
                // refreshSwiper.setRefreshing(false);
            }

            @Override
            public void stringResponse(String strResponse) {

            }

            @Override
            public void responseError(VolleyError error) {

            }
        });
    }*/

    @Override
    public void onItemClick(int position) {

    }

    private void setViewPagerImg(String imgUrls) {
        imgUrlList = new ArrayList<>();
        imgUrlList.add(imgUrls);
    }
}