package Managers;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.fasterxml.jackson.databind.ser.Serializers;
import com.github.ybq.android.spinkit.style.FadingCircle;
import com.yamibo.bbs.splashscreen.Fragments.ForumsFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import Adapter.ForumsRecView1Adapter;
import Adapter.MyRecyclerAdapter;
import Model.Base_Items_Model;
import Model.ForumsListItemMod;
import Utils.ApiConstants;
import Utils.Utility;
import VolleyService.VolleyHelper;
import VolleyService.VolleyResultCallback;

public class ForumsManager {
    private static ForumsManager forumsInstance;

    private Context context;

    private List<ForumsListItemMod> forumsList, forumsList_1;

    private MyRecyclerAdapter recAdp, recycleAdp_1;

    /*public ForumsManager(Context context){
        this.context = context;
    }*/
    public static ForumsManager getInstance() {
        return new ForumsManager();
    }

    /*public void forumsJsonParser(final Context context,
                                 final ProgressBar progressBar, final RecyclerView recView1, final RecyclerView recView2){
        progressBar.setVisibility(View.VISIBLE);
        progressBar.setIndeterminateDrawable(new FadingCircle());
        forumsList = new ArrayList<>();
        forumsList_1 = new ArrayList<>();

        VolleyHelper.volleyGETRequest(context, ApiConstants.FORUM_NAMES_API_URL, new VolleyResultCallback() {
            @Override
            public void jsonResponse(JSONObject response) {
                try{
                    JSONObject var = response.getJSONObject("Variables");
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
                    recAdp = new ForumsRecView1Adapter(context, forumsList);
                    recView1.setAdapter(recAdp);

                    recycleAdp_1 = new MyRecyclerAdapter(context, forumsList_1);
                    recycleAdp_1.setOnItemClickListener(ForumsFragment.this);
                    recView2.setAdapter(recycleAdp_1);
                }catch (JSONException je){
                    Log.d("Volley HTTP Error: ",je.getMessage());
                }
            }

            @Override
            public void stringResponse(String strResponse) {

            }

            @Override
            public void responseError(VolleyError error) {

            }
        });
    }*/

    private void forumsFiltering(String fid, ForumsListItemMod forumsListItem) {
        if (fid.equals("16")) {
            forumsList.add(forumsListItem);
        } else {
            forumsList_1.add(forumsListItem);
        }
    }
}