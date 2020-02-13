package com.yamibo.bbs.Fragments;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.*;
import android.support.annotation.*;
import android.support.v4.app.*;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.*;
import android.util.Log;
import android.view.*;
import android.widget.ProgressBar;

import com.android.volley.VolleyError;
import com.github.ybq.android.spinkit.style.FadingCircle;
import com.yamibo.bbs.data.Model.Base_Items_Model;
import com.yamibo.bbs.splashscreen.MainNavTabActivity;
import com.yamibo.bbs.splashscreen.R;
import Managers.SessionManager;

import Managers.ForumsManager;

import org.json.*;

import java.util.*;

import com.yamibo.bbs.Adapter.MyRecyclerAdapter;
import com.yamibo.bbs.Adapter.ForumsRecView1Adapter;

import static com.yamibo.bbs.Network.ApiConstants.FORUMS_API_URL;
import static com.yamibo.bbs.data.AppConstants.PREF_FILE_GLOBAL;

public class ForumsFragment extends Fragment implements MyRecyclerAdapter.OnItemClickListener {
    private static RecyclerView recyclerView, recyclerView1;
    private static MyRecyclerAdapter recycleAdp_1;
    private static ForumsRecView1Adapter recAdp;

    private static List<Base_Items_Model> forumsList_1;
    //private static List<ForumsListItemMod> forumsList;
    private static int pos = 0;

    private static View v;
    private ViewGroup.LayoutParams layoutPrams;
    private ProgressBar progressBar;
    private SwipeRefreshLayout swiper;

    private MainNavTabActivity main = new MainNavTabActivity();

    private ForumsManager forumsMgr;

    private SessionManager sessionManager;

    @TargetApi(Build.VERSION_CODES.N)

    public ForumsFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        /**This method defines the xml layout file for the fragment*/
        v = inflater.inflate(R.layout.tab_forums, container, false);

        progressBar = (ProgressBar) v.findViewById(R.id.forums_loader);
        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        /**The onViewCreated method is called after onCreateView method
         * to avoid null rootView exception*/
        sessionManager = new SessionManager(view.getContext(),PREF_FILE_GLOBAL);

        forumsMgr = ForumsManager.getInstance();
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        recyclerView1 = (RecyclerView) view.findViewById(R.id.recycler_view_2);
        recyclerView1.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        recyclerView1.setLayoutManager(new LinearLayoutManager(getContext()));


        //forumsJsonParser();

    }

    /*public void forumsJsonParser() {
        progressBar.setVisibility(View.VISIBLE);
        progressBar.setIndeterminateDrawable(new FadingCircle());
        forumsList = new ArrayList<>();
        forumsList_1 = new ArrayList<>();
        VolleyHelper.volleyGETRequest(getContext(), FORUMS_API_URL, new VolleyResultCallback() {
            @Override
            public void jsonResponse(JSONObject response) {
                try {
                    JSONObject var = response.getJSONObject("Variables");
                    Log.d("Response Check: ", response.toString());
                    JSONArray forumsArr = var.getJSONArray("forumlist");
                    for (int i = 0; i < forumsArr.length(); i++) {
                        JSONObject forumObj = forumsArr.getJSONObject(i);
                        String fid = forumObj.getString("fid");

                        ForumsListItemMod forumsListItem = new ForumsListItemMod
                                (forumObj.getString("name"),
                                        (forumObj.getString("description")),
                                        "(" + forumObj.getString("todayposts") + ")");
                        forumsFiltering(fid, forumsListItem);
                    }
                    recAdp = new ForumsRecView1Adapter(getContext(), forumsList);
                    recyclerView.setAdapter(recAdp);

                    recycleAdp_1 = new MyRecyclerAdapter(getContext(), forumsList_1);
                    recycleAdp_1.setOnItemClickListener(ForumsFragment.this);
                    recyclerView1.setAdapter(recycleAdp_1);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void stringResponse(String strResponse) {

            }

            @Override
            public void responseError(VolleyError error) {
                Utility.showErrorMessageToast(getContext(), error.getMessage());
            }
        });
    }*/

    @Override
    public void onItemClick(int position) {
        /** String[] ids = {"5", "13", "16", "33", "44", "49"}; - 版塊fids*/
        int pos = position + 1;
        if (pos == 1) {
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.rootViewPage, new AnimeDiscussFragment())
                    .addToBackStack(null).commit();
        } else if (pos == 2) {
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.rootViewPage, new ChatFragment())
                    .addToBackStack(null).commit();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity main = new MainNavTabActivity();
        if (context instanceof Activity) {
            main = (Activity) context;
        }
    }

    public static ForumsFragment newInstance(int sectionNumber) {
        ForumsFragment forumsFragment = new ForumsFragment();
        Bundle args = new Bundle();
        args.putInt("Forums", sectionNumber);
        forumsFragment.setArguments(args);
        return forumsFragment;
    }

    private void progressBarHandler() {
  /*      new Thread(new Runnable() {
            @Override
            public void run() {
                while()
            }
        })
    }*/
    }

    /*private void forumsFiltering(String fid, ForumsListItemMod forumsListItem) {
        if (fid.equals("16")) {
            forumsList.add(forumsListItem);
        } else {
            forumsList_1.add(forumsListItem);
        }
    }*/
}

