package Utils;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class RestRequest extends Request<JSONObject> {
    public static final String USER_AGENT_HEADER = "User-Agent";
    public static final String REST_AUTHORIZATION_HEADER = "Authorization";
    public static final String REST_AUTHORIZATION_FORMAT = "Bearer %s";
    public static final String ORIGINAL_RESPONSE = "originalResponse";

    private static OnAuthFailedListener mOnAuthFailedListener;

    private final com.android.volley.Response.Listener<JSONObject> mVolleyListener;
    private final Map<String, String> mParams;
    private final Map<String, String> mHeaders = new HashMap<String, String>(2);

    public RestRequest(int method, String url, Response.ErrorListener listener,
                       Response.Listener<JSONObject> mVolleyListener, Map<String, String> mParams) {
        super(method, url, listener);
        this.mVolleyListener = mVolleyListener;
        this.mParams = mParams;
    }

    public void removeAccessToken() {
        setAccessToken(null);
    }

    public void setAccessToken(String token) {
        if (token == null) {
            mHeaders.remove(REST_AUTHORIZATION_HEADER);
        } else {
            mHeaders.put(REST_AUTHORIZATION_HEADER, String.format(REST_AUTHORIZATION_FORMAT, token));
        }
    }

    public interface Listener extends Response.Listener<JSONObject> {
    }

    public interface ErrorListener extends Response.ErrorListener {
    }

    public interface OnAuthFailedListener {
        void onAuthFailed();
    }

    public void setUserAgent(String userAgent) {
        mHeaders.put(USER_AGENT_HEADER, userAgent);
    }

    public void setOnAuthFailedListener(OnAuthFailedListener onAuthFailedListener) {
        mOnAuthFailedListener = onAuthFailedListener;
    }

    @Override
    public Map<String, String> getHeaders() {
        return mHeaders;
    }

    @Override
    protected void deliverResponse(JSONObject response) {
        if (mVolleyListener != null) {
            mVolleyListener.onResponse(response);
        }
    }

    @Override
    protected Map<String, String> getParams() {
        return mParams;
    }

    @Override
    public void deliverError(VolleyError error) {
        super.deliverError(error);

        // Fire OnAuthFailedListener if we receive an invalid token error
        if (error.networkResponse != null && error.networkResponse.statusCode >= 400 && mOnAuthFailedListener != null) {
            String jsonString;
            try {
                jsonString = new String(error.networkResponse.data, HttpHeaderParser.parseCharset(error.networkResponse.headers));
            } catch (UnsupportedEncodingException e) {
                jsonString = "";
            }

            JSONObject responseObject;
            try {
                responseObject = new JSONObject(jsonString);
            } catch (JSONException e) {
                responseObject = new JSONObject();
            }

            String restError = responseObject.optString("error", "");
            if (restError.equals("authorization_required") || restError.equals("invalid_token")) {
                mOnAuthFailedListener.onAuthFailed();
            }
        }
    }

    @Override
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
        try {
            String jsonString = new String(response.data, HttpHeaderParser.parseCharset(response.headers));

            JSONObject jsonObject;
            try {
                jsonObject = jsonObjectFromResponse(jsonString);
            } catch (JSONException jsonException) {
                try {
                    jsonObject = jsonArrayObjectFromResponse(jsonString);
                } catch (JSONException jsonArrayException) {
                    try {
                        jsonObject = jsonBooleanObjectFromResponse(jsonString);
                    } catch (JSONException jsonBooleanException) {
                        return Response.error(new ParseError(jsonBooleanException));
                    }
                }
            }

            return Response.success(jsonObject, HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        }
    }

    protected JSONObject jsonObjectFromResponse(String jsonString) throws JSONException {
        return new JSONObject(jsonString);
    }

    protected JSONObject jsonArrayObjectFromResponse(String jsonString) throws JSONException {
        JSONArray responseArray = new JSONArray(jsonString);
        JSONObject wrapper = new JSONObject();
        wrapper.put(ORIGINAL_RESPONSE, responseArray);

        return wrapper;
    }

    protected JSONObject jsonBooleanObjectFromResponse(String jsonString) throws JSONException {
        JSONObject jsonObject = new JSONObject();

        if (jsonString.equals(Boolean.TRUE.toString())) {
            jsonObject.put(ORIGINAL_RESPONSE, true);
            return jsonObject;
        } else if (jsonString.equals(Boolean.FALSE.toString())) {
            jsonObject.put(ORIGINAL_RESPONSE, false);
            return jsonObject;
        } else {
            throw new JSONException("Not a valid JSON response: " + jsonString);
        }
    }

}
