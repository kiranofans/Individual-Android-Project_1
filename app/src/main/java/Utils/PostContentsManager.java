package Utils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.android.volley.VolleyError;
import com.github.ybq.android.spinkit.style.FadingCircle;
import com.yamibo.bbs.splashscreen.R;

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
import static Utils.ApiConstants.FORUM_CHATTING_URL;

public class PostContentsManager {
    private MyRecyclerAdapter recAdp;
    private List<Base_Items_Model> postContentList;
    private List<SectionRecycleViewAdapter.Sections> sections;

    private PostURLs postUrls;
    private String postURLs;

    private static PostContentsManager postContentInstance;

    public static PostContentsManager getInstance() {
        if (postContentInstance == null) {
            postContentInstance = new PostContentsManager();
        }
        return postContentInstance;
    }

    public void volleyGetPostContent(final Context context, String url, final ProgressBar progressBar, final RecyclerView recView) {
        progressBar.setVisibility(View.VISIBLE);
        progressBar.setIndeterminateDrawable(new FadingCircle());
        postContentList = new ArrayList<>();
        sections = new ArrayList<>();

        VolleyHelper.volleyGETRequest(context, url, new VolleyResultCallback() {
            @Override
            public void jsonResponse(JSONObject response) {
                try {
                    JSONObject var = response.getJSONObject("Variables");
                    JSONArray threadArr = var.getJSONArray("forum_threadlist");
                    for (int i = 0; i < threadArr.length(); i++) {
                        JSONObject tObj = threadArr.getJSONObject(i);
                        PostListItemsMod postListItems = new PostListItemsMod(tObj.getString
                                ("subject"), tObj.getString("author"),
                                tObj.getString("lastposter"),
                                tObj.getString("dateline"));
                        Utility.getSpecialThreadIds(postContentList, tObj.getString("tid"), postListItems);
                    }
                    sections.add(new SectionRecycleViewAdapter.Sections(0, "全部主題"));
                    sections.add(new SectionRecycleViewAdapter.Sections(5, "版塊主題"));
                    recAdp = new MyRecyclerAdapter(context, postContentList);

                    SectionRecycleViewAdapter.Sections[] secArr = new SectionRecycleViewAdapter.Sections[sections.size()];
                    SectionRecycleViewAdapter secAdp = new SectionRecycleViewAdapter(context, R.layout.items_section,
                            R.id.catListSections, recAdp);
                    secAdp.setSections(sections.toArray(secArr));
                    recView.setAdapter(secAdp);
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
    }

    //enum
    enum PostURLs {
        ADMIN_URL, CHAT_URL, ANIME_URL
    }

    public String getPostUrls() {
        switch (postUrls) {
            case ADMIN_URL:
                postURLs = FORUM_ADMIN_URL;
                break;
            case CHAT_URL:
                postURLs = FORUM_CHATTING_URL;
                break;
            case ANIME_URL:
                postURLs = FORUM_ADMIN_URL;
                break;
        }
        return postURLs;
    }
}
