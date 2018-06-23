package Fragments;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.*;
import android.support.annotation.*;
import android.support.v4.app.*;
import android.support.v7.widget.*;
import android.view.*;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.yamibo.bbs.splashscreen.ChatSec_Activity;
import com.yamibo.bbs.splashscreen.MainNavTabActivity;
import com.yamibo.bbs.splashscreen.R;

import org.json.*;

import java.util.*;

import Adapter.MyRecyclerAdapter;
import Model.*;

public class ForumsFragment extends Fragment implements MyRecyclerAdapter.OnItemClickListener{
    private static RecyclerView recyclerView,recyclerView1;
    private static MyRecyclerAdapter recycleAdp,recycleAdp_1;
    private static List<Base_Items_Model> forumsList,forumsList_1;
    public static String[] urls;
    private RequestQueue rqstQueue; private int pos;
    private String imgUrl="https://bbs.yamibo.com/template/oyeeh_com_baihe/img/shdm1020/forum_new.gif";
    private static View v;

    @TargetApi(Build.VERSION_CODES.N)

    public ForumsFragment(){}

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       /**This method defines the xml layout file for the fragment*/
        v=inflater.inflate(R.layout.tab_forums,container,false);
        return v;
    }
    @Override
    public void onViewCreated(View v,Bundle savedInstanceState){
        /**The onViewCreated method is called after onCreateView method
         * to avoid null rootView exception*/
        recyclerView=(RecyclerView)v.findViewById(R.id.recycler_view);
        recyclerView1=(RecyclerView)v.findViewById(R.id.recycler_view_2);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView1.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),
                LinearLayoutManager.VERTICAL));


        rqstQueue= Volley.newRequestQueue(getContext());
        forumsJsonParser();
    }
    private int forumsJsonParser() {
        forumsList=new ArrayList<Base_Items_Model>();
        forumsList_1=new ArrayList<>();
        urls=getResources().getStringArray(R.array.yamibo_api_urls);
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, urls[0], null,
                new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject var = response.getJSONObject("Variables");
                    JSONArray forumsArr = var.getJSONArray("forumlist");
                    for (int i = 0; i < forumsArr.length(); i++) {
                        JSONObject forumObj = forumsArr.getJSONObject(i);
                        String id=forumObj.getString("fid");
                        if (id.equals("16")) {
                            ForumsListItem forumsListItem = new ForumsListItem
                                    (forumObj.getString("name"),
                                            (forumObj.getString("description")),
                                            "(" + forumObj.getString("todayposts") + ")");
                            forumsList.add(forumsListItem);
                        }else if(!(id.equals("16"))){
                            ForumsListItem forumsItems=new ForumsListItem
                                    (forumObj.getString("name"),forumObj.getString("description"),
                                            "("+forumObj.getString("todayposts")+")");
                            forumsList_1.add(forumsItems);
                        }
                    }
                    recycleAdp=new MyRecyclerAdapter(getContext(),forumsList);
                    recyclerView.setAdapter(recycleAdp);

                    recycleAdp_1=new MyRecyclerAdapter(getContext(),forumsList_1);
                    recyclerView1.setAdapter(recycleAdp_1);
                    recycleAdp_1.setOnItemClickListener(ForumsFragment.this);
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
        rqstQueue.add(request);
        return pos;
    }
    @Override
    public void onItemClick(int position) {
        if(position==0){
            Toast.makeText(getContext(), "Hi,it's index "+position,
                    Toast.LENGTH_SHORT).show();
        }else{
            Intent chatIntent=new Intent(getActivity(),ChatSec_Activity.class);
            ((MainNavTabActivity)getActivity()).startActivity(chatIntent);

        }
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        Activity main=new MainNavTabActivity();
        if(context instanceof Activity){
            main=(Activity)context;
        }
    }


    public static ForumsFragment newInstance(int sectionNumber) {
        ForumsFragment forumsFragment = new ForumsFragment();
        Bundle args = new Bundle();
        args.putInt("section_number", sectionNumber);
        forumsFragment.setArguments(args);
        return forumsFragment;
    }

}

