package VolleyService;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import Utils.JSONUtils;

public class VolleySingleton {

    //Add volatile to the instance variable for thread-safe
    private static volatile VolleySingleton instance;

    private RequestQueue requestQueue;
    private static Context context;

    public static VolleySingleton getInstance(Context context) {
        //Double check locking method for getInstance to get thread-safe
        if (instance == null) {
            synchronized (VolleySingleton.class) {
                if (instance == null) instance = new VolleySingleton(context);
            }
        }
        return instance;
    }

    public static VolleySingleton getInstance() {
        if (instance == null) {
            throw new IllegalStateException(VolleySingleton.class.getSimpleName() +
                    " is not initialized, call getInstance(...)");
        }
        return instance;
    }

    private VolleySingleton(Context context) {
        this.context = context;
        requestQueue = getRequestQueue();

        /** Adding reflection proof by throwing RuntimeException
         if the user doesn't get single instance */
        if (instance != null) {
            throw new RuntimeException("Call getInstance() method to get the single" +
                    " instance of VolleySingleton class");
        }
    }

    public static String errorStringFromVolleyError(VolleyError volleyError) {
        JSONObject jsonObj = volleyErrorToJSON(volleyError);
        if (jsonObj == null) {
            return "";
        }
        return JSONUtils.getString(jsonObj, "error");
    }

    public static int volleyStatusCodeError(VolleyError volleyError) {
        if (volleyError == null || volleyError.networkResponse == null) {
            return 0;
        }
        return volleyError.networkResponse.statusCode;
    }

    public static String messageStringFromVolleyError(VolleyError volleyError) {
        JSONObject jsonObject = volleyErrorToJSON(volleyError);
        if (jsonObject == null) {
            return "";
        }
        return JSONUtils.getString(jsonObject, "message");
    }

    public static JSONObject volleyErrorToJSON(VolleyError volleyError) {
        if (volleyError == null || volleyError.networkResponse == null || volleyError.networkResponse.data == null
                || volleyError.networkResponse.headers == null) {
            return null;
        }

        String contentType = volleyError.networkResponse.headers.get("Content-Type");
        if (contentType == null || !contentType.equals("application/json")) {
            return null;
        }

        try {
            String response = new String(volleyError.networkResponse.data, "UTF-8");
            JSONObject json = new JSONObject(response);
            return json;
        } catch (UnsupportedEncodingException e) {
            return null;
        } catch (JSONException e) {
            return null;
        }
    }

    /*
     * Cancel all Volley requests that aren't for images
     */
    public static void cancelAllNonImageRequests(RequestQueue requestQueue) {
        if (requestQueue == null) {
            return;
        }
        RequestQueue.RequestFilter filter = new RequestQueue.RequestFilter() {
            @Override
            public boolean apply(Request<?> request) {
                if (request instanceof ImageRequest) {
                    return false;
                }
                return true;
            }
        };
        requestQueue.cancelAll(filter);
    }


    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            /**getApplicationContext() is key,it keeps you from leaking the
             activity or BroadcastReceiver if someone passes one in.*/
            requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }
        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> request) {
        getRequestQueue().add(request);
    }

    /*
     * Cancel all Volley requests
     */
    public static void cancelAllRequests(RequestQueue requestQueue) {
        if (requestQueue == null) {
            return;
        }
        RequestQueue.RequestFilter filter = new RequestQueue.RequestFilter() {
            @Override
            public boolean apply(Request<?> request) {
                return true;
            }
        };
        requestQueue.cancelAll(filter);
    }
}