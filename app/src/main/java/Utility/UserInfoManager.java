package Utility;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.yamibo.bbs.splashscreen.R;

import org.json.JSONObject;

public class UserInfoManager extends AppCompatActivity{
    private String[] yamiboUrls=getResources().getStringArray(R.array.yamibo_api_urls);
    private Context context;
    public static UserInfoManager userInfo;
    public static synchronized UserInfoManager getInstance(){
        if (userInfo==null){
            userInfo=new UserInfoManager();
        }
        return userInfo;
    }

    public void userLogout(final OnUserLoginFinishListener listener){
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, yamiboUrls[3], null, new
                Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if (listener != null) {
                            listener.OnUserLoginFinish(ApiConstants.API_RESPONSE_CODE_SUCCESS);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(request);
    }
   /* @SuppressLint("StaticFieldLeak")
    public void startTaskUserLogout(final OnUserLoginFinishListener listener) {
        new AsyncTask<Void, Void, Boolean>() {
            @Override
            protected Boolean doInBackground(Void... params) {
                HttpClient httpClient = new HttpClient();
                httpClient.requestGetString(yamiboUrls[3], true);
                return true;
            }

            @Override
            protected void onPostExecute(Boolean aBoolean) {
                super.onPostExecute(aBoolean);

                if (listener != null) {
                    listener.OnUserLoginFinish(ApiConstants.API_RESPONSE_CODE_SUCCESS);
                }
            }
        }.execute();
    }*//**Try to use volley here*/

    public interface OnUserLoginFinishListener {
        void OnUserLoginFinish(int RC);
    }
}
