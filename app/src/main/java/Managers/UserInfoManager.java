package Managers;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import Model.UsersMod;
import Utils.ApiConstants;
import VolleyService.VolleySingleton;

import static Utils.ApiConstants.LOGIN_REQUEST_API_URL;

public class UserInfoManager extends AppCompatActivity {
    private Context context;
    public static UserInfoManager userInfo;

    private String mAccessToken = "";
    private String mAccessSecrete = "";
    private UsersMod.UserProfile mCurrentUserProfile;

    private static UserInfoManager mUserInfoMgrInstance;

    public static UserInfoManager getInstance() {
        if (userInfo == null) {
            userInfo = new UserInfoManager();
        }
        return userInfo;
    }
    public String getAccessToken(){
        return mAccessToken;
    }
    public String getAccessSecrete(){
        return mAccessSecrete;
    }
    public String getUserId(){
        String userId = "";
        if(mCurrentUserProfile != null){
            userId = mCurrentUserProfile.getUsrID();
        }
        return userId;
    }
    public String getUsername(){
        String username = "";
        if(mCurrentUserProfile != null){
            username = mCurrentUserProfile.getUsername();
        }
        return username;
    }

    public void setUserName(String userName){
        if(mCurrentUserProfile != null){
            mCurrentUserProfile.setUsername(userName);
        }
    }

    /*public boolean getUserPermission(int access){
        boolean allow = false;
        if(mCurrentUserProfile != null){
            boolean[] list = AppConstants.USER_PERMISSION_ACCESS_MAP.get(Integer.parseInt(mCurrentUserProfile.getReadAccess()));
            if(list != null  && list.length > access){
                allow = AppConstants.USER_PERMISSION_ACCESS_MAP.get(Integer.parseInt(mCurrentUserProfile.getReadAccess())[access]);
            }
        }
        return allow;
    }*/
    /*public Map<String, String> startJsonUserLogin(final Context context, String username, String pswd, OnUserLoginFinishListener listener) throws JSONException {
        VolleyHelper.volleyPOSTRequest(context, LOGIN_REQUEST_API_URL, new VolleyResultCallback() {
            @Override
            public void jsonResponse(JSONObject response) {
                SharedPreferences pref = context.getSharedPreferences(AppConstants.PREF_FILE_GLOBAL,MODE_PRIVATE);
                String token = pref.getString(AppConstants.PREF_KEY_LOGIN_TOKEN,"");
                if(TextUtils.isEmpty(token)){
                    //do something
                }
            }

            @Override
            public void responseError(VolleyError error) {

            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Accept-Charset", "gbk");
                headers.put("Content-Transfer-Encoding", "charset=gbk");
                headers.put("Content-Type", "application/x-www-form-urlencoded");
                return headers;
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("username", username);
                params.put("password", pswd);
                params.put("authtoken",mAccessToken);
                return params;
            }
        };
    }*/
    public void jsonUserLogout(final OnUserLoginFinishListener listener) {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, LOGIN_REQUEST_API_URL, null, new
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

    public interface OnUserLoginFinishListener {
        void OnUserLoginFinish(int RC);
    }
}
