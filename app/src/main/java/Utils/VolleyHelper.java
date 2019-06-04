package Utils;

import android.content.Context;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class VolleyHelper {
    public static void volleyGETRequest(Context context, String requestUrl, final VolleyResultCallback mResultCallback){
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, requestUrl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if (response != null)
                    mResultCallback.jsonResponse(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error != null)
                    mResultCallback.responseError(error);
            }
        }){
            @Override
            protected Response<JSONObject> parseNetworkResponse(NetworkResponse networkResponse){
                try{
                    String jsonStr = new String(networkResponse.data, HttpHeaderParser
                            .parseCharset(networkResponse.headers,PROTOCOL_CHARSET));
                    return Response.success(new JSONObject(jsonStr), HttpHeaderParser.parseCacheHeaders(networkResponse));

                }catch(UnsupportedEncodingException e){
                    return Response.error(new ParseError());
                }catch (JSONException jExp){
                    return Response.error(new ParseError(jExp));
                }
            }
        };
        // Access the RequestQueue through singleton class.
        VolleySingleton.getInstance(context).addToRequestQueue(jsonObjectRequest);
    }

    public void volleyPOSTRequest(Context context,String requestUrl,final VolleyResultCallback mResultCallback) throws JSONException {
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObj = new JsonObjectRequest(Request.Method.POST, requestUrl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if (mResultCallback != null)
                    mResultCallback.jsonResponse(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (mResultCallback != null)
                    mResultCallback.responseError(error);
            }
        });
        VolleySingleton.getInstance(context).addToRequestQueue(jsonObj);
    }
}
