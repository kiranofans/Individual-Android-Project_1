package com.yamibo.bbs.splashscreen.Fragments;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.*;
import android.support.annotation.*;
import android.support.v4.app.*;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.*;
import android.view.*;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import Utility.AlertDialogManager;

import com.yamibo.bbs.splashscreen.MainNavTabActivity;
import com.yamibo.bbs.splashscreen.R;

import Utility.ApiConstants;
import Utility.VolleySingleton;

import org.json.*;

import java.util.*;

import Adapter.MyRecyclerAdapter;
import Adapter.ForumsRecView1Adapter;
import Model.*;

import static Utility.ApiConstants.FORUM_NAMES_API_URL;

public class ForumsFragment extends Fragment implements MyRecyclerAdapter.OnItemClickListener {
    private static RecyclerView recyclerView, recyclerView1;
    private static MyRecyclerAdapter recycleAdp_1;
    private static ForumsRecView1Adapter recAdp;
    private static List<Base_Items_Model> forumsList_1;
    private static List<ForumsListItem> forumsList;
    private static int pos = 0;
    private String imgUrl = "https://bbs.yamibo.com/template/oyeeh_com_baihe/img/shdm1020/forum_new.gif";

    private static View v;
    private Dialog dialog;
    private ViewGroup.LayoutParams layoutPrams;
    private ProgressBar progressBar;

    private FragmentManager fragMg;
    private SwipeRefreshLayout swiper;
    private MainNavTabActivity main = new MainNavTabActivity();
    private static AlertDialogManager dialogMgr;
    private static ApiResponses apiResponses;

    @TargetApi(Build.VERSION_CODES.N)

    public ForumsFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        /**This method defines the xml layout file for the fragment*/
        v = inflater.inflate(R.layout.tab_forums, container, false);

        dialogMgr = AlertDialogManager.getInstance();
        dialog = new Dialog(getContext());
        progressBar = (ProgressBar) v.findViewById(R.id.progressBar_cyclic);
        // dialog.addContentView(progressBar,layoutPrams);

        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        /**The onViewCreated method is called after onCreateView method
         * to avoid null rootView exception*/

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        recyclerView1 = (RecyclerView) view.findViewById(R.id.recycler_view_2);
        recyclerView1.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        recyclerView1.setLayoutManager(new LinearLayoutManager(getContext()));

        forumsJsonParser();

    }

    public void forumsJsonParser() {
        //dialog.setTitle("Loading...");
        dialogMgr.showDialog();
        forumsList = new ArrayList<>();
        forumsList_1 = new ArrayList<>();
        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, FORUM_NAMES_API_URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject var = response.getJSONObject("Variables");
                            JSONArray forumsArr = var.getJSONArray("forumlist");
                            for (int i = 0; i < forumsArr.length(); i++) {
                                JSONObject forumObj = forumsArr.getJSONObject(i);
                                String fid = forumObj.getString("fid");
                                ForumsListItem forumsListItem = new ForumsListItem
                                        (forumObj.getString("name"),
                                                (forumObj.getString("description")),
                                                "(" + forumObj.getString("todayposts") + ")");
                                if (fid.equals("16")) {
                                    forumsList.add(forumsListItem);
                                } else {
                                    forumsList_1.add(forumsListItem);
                                }

                            }
                            recAdp = new ForumsRecView1Adapter(getContext(), forumsList);
                            recyclerView.setAdapter(recAdp);

                            recycleAdp_1 = new MyRecyclerAdapter(getContext(), forumsList_1);
                            recycleAdp_1.setOnItemClickListener(ForumsFragment.this);
                            recyclerView1.setAdapter(recycleAdp_1);
                            dialog.dismiss();
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
}

