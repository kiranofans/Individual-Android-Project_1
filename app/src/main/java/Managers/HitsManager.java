package Managers;

import com.yamibo.bbs.Adapter.ImgViewPagerAdapter;
import com.yamibo.bbs.Adapter.ForumsRecyclerViewAdapter;
import com.yamibo.bbs.data.Model.Base_Items_Model;

import java.util.List;

public class HitsManager {
    private static volatile HitsManager hitsInstance;

    private List<String> imgUrlList;
    private List<Base_Items_Model> hitsList;

    private ForumsRecyclerViewAdapter vpRecAdp;
    private ImgViewPagerAdapter vpAdp;

    private HitsManager(){
    }

    public static HitsManager getInstance(){
        if(hitsInstance == null){
            hitsInstance = new HitsManager();
            synchronized (HitsManager.class){
                if(hitsInstance == null) hitsInstance = new HitsManager();
            }
        }
        return hitsInstance;
    }

   /* public void getHitsData(final Context context,
                            final RecyclerView recView, final ViewPager imgVp){
        imgUrlList = new ArrayList<>();
        hitsList = new ArrayList<>();
        VolleyHelper.volleyGETRequest(context, FORUM_DAILY_HITS_URL, new VolleyResultCallback() {
            @Override
            public void jsonResponse(JSONObject response) {
                try {
                    JSONObject var = response.getJSONObject("Variables");
                    JSONArray hitsImgArr = var.getJSONArray("data_img");
                    JSONArray hitsTxtArr = var.getJSONArray("data_txt");

                    for (int i = 0; i < hitsImgArr.length(); i++) {
                        JSONObject imgObj = hitsImgArr.getJSONObject(i);
                        String pic = imgObj.getString("pic");
                        imgUrlList.add(IMG_BASE_URL + pic);
                    }
                    for (int i = 0; i < hitsTxtArr.length(); i++) {
                        JSONObject txtObj = hitsTxtArr.getJSONObject(i);
                        String title = txtObj.getString("fulltitle");
                        String date = txtObj.getString("lastpost");
                        String author = txtObj.getString("author");
                        HitsMod posts = new HitsMod(title, date, author);
                        hitsList.add(posts);
                    }
                    vpRecAdp = new ForumsRecyclerViewAdapter(context, hitsList);
                    recView.setAdapter(vpRecAdp);
                    vpAdp = new ImgViewPagerAdapter(context, imgUrlList);
                    imgVp.setAdapter(vpAdp);
                } catch (JSONException je) {
                    Toast.makeText(context, je.getMessage()
                            , Toast.LENGTH_LONG).show();
                    //index 3 out of range 0 to 3
                }
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
}
