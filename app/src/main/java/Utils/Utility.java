package Utils;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.StringRes;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.github.ybq.android.spinkit.style.FadingCircle;

import Adapter.ForumsRecView1Adapter;
import Model.Base_Items_Model;
import Model.ForumsListItemMod;
import Model.PostListItemsMod;
import Rest.OAuthAuthenticator;

import java.util.ArrayList;
import java.util.List;

import Model.ApiResponsesMod;
import Model.TimeZoneMod;

import static Utils.ApiConstants.API_RESPONSE_CODE_UNKNOWN_ERROR;

public class Utility {
    private static final String LOG_TAG = Utility.class.getSimpleName();

    private static Context mContext;
    private static RequestQueue requestQueue;
    public static RestClientUtils restClientUtils;
    public static OAuthAuthenticator mOAuthAuthenticator;

    private enum ForumTypes {
        CHATTING, ADMIN, ANIME_DISCUSSION
    }

    ForumTypes differentForums;

    public static boolean isApiResponseSuccess(ApiResponsesMod responseMod) {
        return responseMod != null && (responseMod.getRC() == ApiConstants.API_RESPONSE_CODE_SUCCESS);
    }

    public static boolean isTZResponseSuccess(TimeZoneMod responseMod) {
        return responseMod != null && (responseMod.getStatus().equals("OK"));
    }

    public static boolean isConnectToInternet(Context context) {
        ConnectivityManager connectivityMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityMgr.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnectedOrConnecting();
    }

    public static int apiErrorHandler(ApiResponsesMod responseMod) {
        if (responseMod == null) {
            return API_RESPONSE_CODE_UNKNOWN_ERROR;
        } else {
            return responseMod.getRC();
        }
    }

    public static boolean isEmptyList(List<?> list) {
        return list == null || list.size() == 0;
    }

    public static <T> List<T> safeGetArrayList(List<T> list) {
        if (list != null) {
            return new ArrayList<>(list);
        } else {
            return new ArrayList<>();
        }
    }

    public static void setAppPreferenceItem(SharedPreferences pref, String file, String key, Object value) {
        SharedPreferences.Editor editor = pref.edit();
        if (value instanceof String) {
            editor.putString(key, (String) value);
        } else if (value instanceof Integer) {
            editor.putInt(key, (Integer) value);
        }
        editor.apply();
    }

    public static void showErrorMessageToast(Context context, @StringRes int id) {
        Toast.makeText(context, "", Toast.LENGTH_SHORT).show();
    }

    public static void showLongMessageToast(Context context, @StringRes int id) {
        Toast.makeText(context, "", Toast.LENGTH_LONG).show();
    }

    public static void showErrorMessageToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static void showLongMessageToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    public static void showLoader(ProgressBar progressBar,String text, Context context) {
        progressBar.setIndeterminateDrawable(new FadingCircle());
    }

    public void getForumTypes() {
        switch (differentForums) {
            case CHATTING:
                break;
            case ADMIN:

                break;
            case ANIME_DISCUSSION:
                break;
        }
    }

    public static RestClientUtils getRestClientUtils() {
        if (restClientUtils == null) {
            restClientUtils = new RestClientUtils(mContext, requestQueue, mOAuthAuthenticator, null);
        }
        return restClientUtils;
    }

    public static void getFixedTopThreads(List<Base_Items_Model> list,
                                          String mThreadId, PostListItemsMod postItems) {
        if (mThreadId.equals("47447") || mThreadId.equals("20425")
                || mThreadId.equals("232743") || mThreadId.equals("240477")) {
            list.add(postItems);
        } else {
            list.add(postItems);
        }
    }

    public static void apiURLStrFormat(String str){

    }
}
