package com.yamibo.bbs.Fragments;

import android.annotation.TargetApi;
import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.*;
import android.support.annotation.*;
import android.support.v4.app.*;
import android.support.v7.widget.*;
import android.util.Log;
import android.view.*;
import android.widget.ProgressBar;

import com.yamibo.bbs.Adapter.ForumsRecyclerViewAdapter;
import com.yamibo.bbs.ViewModels.ForumsViewModel;
import com.yamibo.bbs.data.Model.Base_Items_Model;
import com.yamibo.bbs.data.Model.ForumListMod.ForumsListInfoMod;
import com.yamibo.bbs.splashscreen.MainNavTabActivity;
import com.yamibo.bbs.splashscreen.R;
import Managers.SessionManager;

import Managers.ForumsManager;

import java.util.*;

import com.yamibo.bbs.Adapter.ForumsRecView1Adapter;
import com.yamibo.bbs.splashscreen.databinding.TabForumsBinding;

import static com.yamibo.bbs.data.AppConstants.PREF_FILE_GLOBAL;

public class ForumsFragment extends Fragment implements ForumsRecyclerViewAdapter.OnItemClickListener {
    private final static String TAG = ForumsFragment.class.getSimpleName();

    private static RecyclerView recyclerView, recyclerView1;
    private static ForumsRecyclerViewAdapter recycleAdp_1;
    private static ForumsRecView1Adapter recAdp;

    private static List<Base_Items_Model> forumsList_1;
    //private static List<ForumsListItemMod> forumsList;
    private static int pos = 0;

    private static View v;
    private ViewGroup.LayoutParams layoutPrams;
    private ProgressBar progressBar;

    private MainNavTabActivity main = new MainNavTabActivity();

    private ForumsManager forumsMgr;

    private SessionManager sessionManager;

    private TabForumsBinding mForumsBinding;
    private ForumsViewModel forumsViewModel;

    private boolean isLoading = false;
    private int currentPageNum=1;
    private List<ForumsListInfoMod> forumsList = new ArrayList<>();
    @TargetApi(Build.VERSION_CODES.N)

    public ForumsFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        /**This method defines the xml layout file for the fragment*/
        mForumsBinding = DataBindingUtil.inflate(inflater,R.layout.tab_forums,container,false);
        forumsViewModel = ViewModelProviders.of(requireActivity()).get(ForumsViewModel.class);

        return v=mForumsBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        /**The onViewCreated method is called after onCreateView method
         * to avoid null rootView exception*/
        sessionManager = new SessionManager(view.getContext(),PREF_FILE_GLOBAL);
        //progressBar = (ProgressBar) v.findViewById(R.id.forums_loader);

        recycleAdp_1=new ForumsRecyclerViewAdapter(view.getContext(), forumsList);
        //forumsMgr = ForumsManager.getInstance();
        mForumsBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mForumsBinding.recyclerView.setItemAnimator(new DefaultItemAnimator());

        mForumsBinding.recyclerView.setAdapter(recycleAdp_1);
        setForumsObserver();
        loadPage();
        swipeToRefreshListener();
    }

    private void swipeToRefreshListener(){
        mForumsBinding.swipeRefreshLayout.setOnRefreshListener(()->{
            currentPageNum=1;
            recycleAdp_1.clear();
            loadPage();
        });
    }

    private void loadPage(){
        mForumsBinding.swipeRefreshLayout.setRefreshing(true);
        forumsViewModel.getForumsList();
    }
    private void setForumsObserver(){
        forumsViewModel.getForumsListLiveData().observe(this, new Observer<List<ForumsListInfoMod>>() {
            @Override
            public void onChanged(@Nullable List<ForumsListInfoMod> forumsListInfoMods) {
                isLoading=false;
                forumsList.addAll(forumsListInfoMods);
                Log.d(TAG,"onChanged: "+forumsList.size());
                //swipe to refresh
                recycleAdp_1.notifyDataSetChanged();
            }
        });
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

                    recycleAdp_1 = new ForumsRecyclerViewAdapter(getContext(), forumsList_1);
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

