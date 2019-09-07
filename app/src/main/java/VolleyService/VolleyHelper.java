package VolleyService;

import android.content.Context;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import javax.inject.Inject;

public class VolleyHelper {

    public static void volleyGETRequest(Context context, String requestUrl, final VolleyResultCallback mResultCallback) {
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
        }) {
            @Override
            protected Response<JSONObject> parseNetworkResponse(NetworkResponse networkResponse) {
                try {
                    String jsonStr = new String(networkResponse.data, HttpHeaderParser
                            .parseCharset(networkResponse.headers, PROTOCOL_CHARSET));
                    return Response.success(new JSONObject(jsonStr), HttpHeaderParser.parseCacheHeaders(networkResponse));

                } catch (UnsupportedEncodingException e) {
                    return Response.error(new ParseError());
                } catch (JSONException jExp) {
                    return Response.error(new ParseError(jExp));
                }
            }
        };
        // Access the RequestQueue through singleton class.
        VolleySingleton.getInstance(context).addToRequestQueue(jsonObjectRequest);
    }

   /* public static void volleyPOSTRequest(Context context, String requestUrl, final String username, final String password,
                                         final VolleyResultCallback mResultCallback) throws JSONException {
        // RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, requestUrl,null, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (mResultCallback != null) {
                    mResultCallback.stringResponse(response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error != null)
                    mResultCallback.responseError(error);
            }
        }) {
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
                params.put("password", password);
                return params;
            }
        };
        VolleySingleton.getInstance(context).addToRequestQueue(stringRequest);
    }*/
}
