package Parser;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.yamibo.bbs.splashscreen.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import Model.ForumsListItem;

public class JSON_URLs_Holder extends AsyncTask<Void,Void,List<JSONObject>> {
    private Context context;
    private List<String> urlList;
    private static List<ForumsListItem> forumsList;
    private static JSONObject parentObj;
    private static String[] urls;
    public JSON_URLs_Holder(Context context , List<String> urlList){
        this.context=context;
        this.urlList=urlList;
    }
    @Override
    protected List<JSONObject> doInBackground(Void... voids) {
        HttpURLConnection httpUrlConnect = null;
        BufferedReader buffReader = null;
        urls=context.getResources().getStringArray(R.array.yamibo_api_urls);
        List<JSONObject> jsonURLs=new ArrayList<>();

        try {
            //Read JSON URLs
            for(String each_url: urlList){
                URL link=new URL(each_url);
                httpUrlConnect=(HttpURLConnection)link.openConnection();
                httpUrlConnect.setRequestProperty("Content-Type","application/json");
                httpUrlConnect.setRequestMethod("GET");
                httpUrlConnect.connect();
                InputStream inStream = httpUrlConnect.getInputStream();
                buffReader = new BufferedReader(new InputStreamReader((inStream)));
                String lines = null;
                StringBuilder strBuilder=new StringBuilder();
                while ((lines = buffReader.readLine())!= null) {
                    strBuilder.append(lines);
                }
                buffReader.close();
                parentObj=new JSONObject(strBuilder.toString());
                jsonURLs.add(parentObj);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    protected void onPostExecute(List<JSONObject> jsonObjs){
        super.onPostExecute(jsonObjs);
        displayJson();
    }

    private void displayJson(){
        int urlPos;
        for(urlPos=0;urlPos<urls.length;urlPos++){
            urlList.add(urls[urlPos]);
        }
        try {
            switch (urlPos){
                case 0:
                    JSONObject var=parentObj.getJSONObject("Variables");
                    JSONArray forumsArr=var.getJSONArray("forumlist");
                    for(int i=0;i<forumsArr.length();i++){
                        JSONObject forumsObj=forumsArr.getJSONObject(i);
                        ForumsListItem forumItems=new ForumsListItem
                                (""+forumsObj.get("name"),
                                        ""+forumsObj.get("description"),
                                        ""+forumsObj.get("todayposts"));
                        forumsList.add(forumItems);
                    }
                    Log.d("TESTING", "Items: "+forumsList.size());
                    break;
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}