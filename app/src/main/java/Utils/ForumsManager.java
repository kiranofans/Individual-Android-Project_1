package Utils;

import android.content.Context;

import java.util.List;

import Adapter.ForumsRecView1Adapter;
import Adapter.MyRecyclerAdapter;

public class ForumsManager {
    private static ForumsManager forumsInstance;

    private Context context;

    private List<ForumsRecView1Adapter> forumsList, forumsList_1;

    private MyRecyclerAdapter recAdp, recycleAdp_1;

    /*public ForumsManager(Context context){
        this.context = context;
    }*/
    public static ForumsManager getInstance() {
        return forumsInstance = new ForumsManager();
    }

   /* public void forumsJsonParser(final Fragment fragment, final ProgressBar progressBar,
                                 final String url, final RecyclerView recyclerView,
                                 final RecyclerView recyclerView1) {
        progressBar.setVisibility(View.VISIBLE);
        progressBar.setIndeterminateDrawable(new FadingCircle());
        forumsList = new ArrayList<>();
        forumsList_1 = new ArrayList<>();
        VolleyHelper.volleyGETRequest(fragment.getContext(), url, new VolleyResultCallback() {
            @Override
            public void jsonResponse(JSONObject response) {
                try {
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
                    recAdp = new ForumsRecView1Adapter(context,forumsList);
                    recyclerView.setAdapter(recAdp);

                    recycleAdp_1 = new MyRecyclerAdapter(context, forumsList_1);
                    recycleAdp_1.setOnItemClickListener(context);
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
                Utility.showErrorMessageToast(context, error.getMessage());
            }
        });
    }*/

   /* public void forumsFiltering(String fid, ForumsListItemMod forumsListItem) {
        if (fid.equals("16")) {
            forumsList.add(forumsListItem);
        } else {
            forumsList_1.add(forumsListItem);
        }
    }*/
}
