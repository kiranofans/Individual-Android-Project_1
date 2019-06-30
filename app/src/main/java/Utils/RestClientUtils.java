package Utils;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import Rest.Authenticator;
import Rest.AuthenticatorRequest;
import Rest.JsonRestRequest;
import Rest.RestClient;
import Rest.RestClientFactory;
import Rest.RestRequest;

/**
 * Interface to the WordPress.com REST API.
 */

public class RestClientUtils {
    public static final String NOTIFICATION_FIELDS = "id,type,unread,body,subject,timestamp,meta";
    private static String sUserAgent = "WordPress Networking Android";

    private RestClient mRestClient;
    private Rest.Authenticator mAuthenticator;
    private Context mContext;

    /**
     * Socket timeout in milliseconds for rest requests
     */
    private static final int REST_TIMEOUT_MS = 30000;

    /**
     * Default number of retries for POST rest requests
     */
    private static final int REST_MAX_RETRIES_POST = 0;

    /**
     * Default number of retries for GET rest requests
     */
    private static final int REST_MAX_RETRIES_GET = 3;

    /**
     * Default backoff multiplier for rest requests
     */
    private static final float REST_BACKOFF_MULT = 2f;

    public static void setUserAgent(String userAgent) {
        sUserAgent = userAgent;
    }

    public RestClientUtils(Context context, RequestQueue queue, Rest.Authenticator authenticator,
                           RestRequest.OnAuthFailedListener onAuthFailedListener) {
        this(context, queue, authenticator, onAuthFailedListener, RestClient.REST_CLIENT_VERSIONS.V1);
    }

    public RestClientUtils(Context context, RequestQueue queue, Rest.Authenticator authenticator,
                           RestRequest.OnAuthFailedListener onAuthFailedListener,
                           RestClient.REST_CLIENT_VERSIONS version) {
        // load an existing access token from prefs if we have one
        mContext = context;
        mAuthenticator = authenticator;
        mRestClient = RestClientFactory.instantiate(queue, version);
        if (onAuthFailedListener != null) {
            mRestClient.setOnAuthFailedListener(onAuthFailedListener);
        }
        mRestClient.setUserAgent(sUserAgent);
    }

    public Authenticator getAuthenticator() {
        return mAuthenticator;
    }

    public RestClient getRestClient() {
        return mRestClient;
    }

 /*   public void getCategories(long siteId, Listener listener, ErrorListener errorListener) {
        String path = String.format(Locale.US, "sites/%d/categories", siteId);
        get(path, null, null, listener, errorListener);
    }*/

    /**
     * Get notifications with the provided params.
     * <p/>
     * https://developer.wordpress.com/docs/api/1/get/notifications/
     */
    /*public void getNotifications(Map<String, String> params, Listener listener, ErrorListener errorListener) {
        get("notifications", params, null, listener, errorListener);
    }*/

    /**
     * Get a specific notification given its noteId.
     * <p/>
     */
    public void getNotification(Map<String, String> params, String noteId,
                                RestRequest.Listener listener, RestRequest.ErrorListener errorListener) {
        params.put("fields", NOTIFICATION_FIELDS);
        String path = String.format(Locale.US, "notifications/%s/", noteId);
        get(path, params, null, listener, errorListener);
    }

    /**
     * Get the notification identified by ID with default params.
     * <p/>
     * https://developer.wordpress.com/docs/api/1/get/notifications/%s
     */
    public void getNotification(String noteId, RestRequest.Listener listener, RestRequest.ErrorListener errorListener) {
        HashMap<String, String> params = new HashMap<>();
        params.put("fields", NOTIFICATION_FIELDS);
        String path = String.format("notifications/%s", noteId);
        get(path, params, null, listener, errorListener);
    }

    /**
     * Update the seen timestamp.
     * <p/>
     * https://developer.wordpress.com/docs/api/1/post/notifications/seen
     */
    public void markNotificationsSeen(String timestamp, RestRequest.Listener listener, RestRequest.ErrorListener errorListener) {
        Map<String, String> params = new HashMap<>();
        params.put("time", timestamp);
        post("notifications/seen", params, null, listener, errorListener);
    }

    /**
     * Mark a notification as read
     * Decrement the unread count for a notification. Key=note_ID, Value=decrement amount.
     *
     * <p/>
     * https://developer.wordpress.com/docs/api/1/post/notifications/read/
     */
    public void decrementUnreadCount(String noteId, String decrementAmount,
                                     RestRequest.Listener listener, RestRequest.ErrorListener errorListener) {
        String path = "notifications/read";
        Map<String, String> params = new HashMap<>();
        params.put(String.format("counts[%s]", noteId), decrementAmount);
        post(path, params, null, listener, errorListener);
    }

    public void getJetpackSettings(long siteId, RestRequest.Listener listener, RestRequest.ErrorListener errorListener) {
        String path = String.format(Locale.US, "jetpack-blogs/%d/rest-api/?path=/jetpack/v4/settings", siteId);
        get(path, listener, errorListener);
    }

    public void getGeneralSettings(long siteId, RestRequest.Listener listener, RestRequest.ErrorListener errorListener) {
        String path = String.format(Locale.US, "sites/%d/settings", siteId);
        get(path, listener, errorListener);
    }

    public void getJetpackMonitorSettings(long siteId, RestRequest.Listener listener, RestRequest.ErrorListener errorListener) {
        String path = String.format(Locale.US, "jetpack-blogs/%d", siteId);
        get(path, listener, errorListener);
    }


    public void getJetpackModuleSettings(long siteId, RestRequest.Listener listener, RestRequest.ErrorListener errorListener) {
        String path = String.format(Locale.US, "sites/%d/jetpack/modules", siteId);
        get(path, listener, errorListener);
    }

    public void setGeneralSiteSettings(long siteId, JSONObject params, RestRequest.Listener listener, RestRequest.ErrorListener errorListener) {
        String path = String.format(Locale.US, "sites/%d/settings", siteId);
        post(path, params, null, listener, errorListener);
    }

    public void setJetpackSettings(long siteId, Map<String, Object> bodyData,
                                   RestRequest.Listener listener, RestRequest.ErrorListener errorListener) {
        String path = String.format(Locale.US, "jetpack-blogs/%d/rest-api/", siteId);
        JSONObject params = new JSONObject();
        JSONObject body = new JSONObject();
        try {
            for (String key : bodyData.keySet()) {
                body.putOpt(key, bodyData.get(key));
            }
            params.put("path", "/jetpack/v4/settings/");
            params.put("body", body.toString());
            post(path, params, null, listener, errorListener);
        } catch (JSONException e) {
            AppLog.e(AppLog.T.API, "Error updating Jetpack settings: " + e);
            // make sure to invoke error listener, caller will be expecting it
            if (errorListener != null) {
                errorListener.onErrorResponse(
                        new VolleyError("Error: Attempted to update Jetpack settings with malformed body data", e));
            }
        }
    }

    public void setJetpackMonitorSettings(long siteId, Map<String, String> params,
                                          RestRequest.Listener listener, RestRequest.ErrorListener errorListener) {
        String path = String.format(Locale.US, "jetpack-blogs/%d", siteId);
        post(path, params, null, listener, errorListener);
    }

    public void setJetpackModuleSettings(long siteId, String module, boolean active,
                                         RestRequest.Listener listener, RestRequest.ErrorListener errorListener) {
        String path = String.format(Locale.US, "sites/%d/jetpack/modules/%s", siteId, module);
        HashMap<String, String> params = new HashMap<>();
        params.put("active", String.valueOf(active));
        post(path, params, null, listener, errorListener);
    }

    public void getSitePurchases(long siteId, RestRequest.Listener listener, RestRequest.ErrorListener errorListener) {
        String path = String.format(Locale.US, "sites/%d/purchases", siteId);
        get(path, listener, errorListener);
    }

    public void exportContentAll(long siteId, RestRequest.Listener listener, RestRequest.ErrorListener errorListener) {
        String path = String.format(Locale.US, "sites/%d/exports/start", siteId);
        post(path, listener, errorListener);
    }

    public void getSharingButtons(String siteId, RestRequest.Listener listener, RestRequest.ErrorListener errorListener) {
        String path = String.format("sites/%s/sharing-buttons", siteId);
        get(path, listener, errorListener);
    }

    public void setSharingButtons(String siteId, JSONObject params, RestRequest.Listener listener, RestRequest.ErrorListener errorListener) {
        String path = String.format("sites/%s/sharing-buttons", siteId);
        post(path, params, null, listener, errorListener);
    }

    public void sendLoginEmail(Map<String, String> params, RestRequest.Listener listener, RestRequest.ErrorListener errorListener) {
        post("auth/send-login-email", params, null, listener, errorListener);
    }

    /**
     * Make GET request
     */
    public Request<JSONObject> get(String path, RestRequest.Listener listener, RestRequest.ErrorListener errorListener) {
        return get(path, null, null, listener, errorListener);
    }

    /**
     * Make GET request with params
     */
    public Request<JSONObject> get(String path, Map<String, String> params, RetryPolicy retryPolicy, RestRequest.Listener listener,
                                   RestRequest.ErrorListener errorListener) {
        // turn params into query string
        HashMap<String, String> paramsWithLocale = getRestLocaleParams(mContext);
        if (params != null) {
            paramsWithLocale.putAll(params);
        }

        String realPath = getSanitizedPath(path);
        if (TextUtils.isEmpty(realPath)) {
            realPath = path;
        }
        paramsWithLocale.putAll(getSanitizedParameters(path));

        RestRequest request = mRestClient.makeRequest(Method.GET, mRestClient
                .getAbsoluteURL(realPath, paramsWithLocale), null, listener, errorListener);

        if (retryPolicy == null) {
            retryPolicy = new DefaultRetryPolicy(REST_TIMEOUT_MS, REST_MAX_RETRIES_GET, REST_BACKOFF_MULT);
        }
        request.setRetryPolicy(retryPolicy);
        AuthenticatorRequest authCheck = new AuthenticatorRequest(request, errorListener, mRestClient, mAuthenticator);
        authCheck.send();
        return request;
    }

    /**
     * Make POST request
     */
    public void post(String path, RestRequest.Listener listener, RestRequest.ErrorListener errorListener) {
        post(path, (Map<String, String>) null, null, listener, errorListener);
    }

    /**
     * Make POST request with params
     */
    public void post(final String path, Map<String, String> params, RetryPolicy retryPolicy, RestRequest.Listener listener,
                     RestRequest.ErrorListener errorListener) {
        final RestRequest request = mRestClient.makeRequest(Method.POST, mRestClient
                .getAbsoluteURL(path, getRestLocaleParams(mContext)), params, listener, errorListener);
        if (retryPolicy == null) {
            retryPolicy = new DefaultRetryPolicy(REST_TIMEOUT_MS, REST_MAX_RETRIES_POST,
                    REST_BACKOFF_MULT); // Do not retry on failure
        }
        request.setRetryPolicy(retryPolicy);
        AuthenticatorRequest authCheck = new AuthenticatorRequest(request, errorListener, mRestClient, mAuthenticator);
        authCheck.send();
    }


    /**
     * Make a JSON POST request
     */
    public void post(final String path, JSONObject params, RetryPolicy retryPolicy, RestRequest.Listener listener,
                     RestRequest.ErrorListener errorListener) {
        final JsonRestRequest request = mRestClient.makeRequest(mRestClient
                .getAbsoluteURL(path, getRestLocaleParams(mContext)), params, listener, errorListener);
        if (retryPolicy == null) {
            retryPolicy = new DefaultRetryPolicy(REST_TIMEOUT_MS, REST_MAX_RETRIES_POST,
                    REST_BACKOFF_MULT); // Do not retry on failure
        }
        request.setRetryPolicy(retryPolicy);
        AuthenticatorRequest authCheck = new AuthenticatorRequest(request, errorListener, mRestClient, mAuthenticator);
        authCheck.send();
    }

    /**
     * Takes a URL and returns the path within, or an empty string (not null)
     */
    private static String getSanitizedPath(String unsanitizedPath) {
        if (unsanitizedPath != null) {
            int qmarkPos = unsanitizedPath.indexOf('?');
            if (qmarkPos > -1) { // strip any query string params off this to obtain the path
                return unsanitizedPath.substring(0, qmarkPos + 1);
            } else {
                // return the string as is, consider the whole string as the path
                return unsanitizedPath;
            }
        }
        return "";
    }

    /**
     * Takes a URL with query strings and returns a Map of query string values.
     */
    private static HashMap<String, String> getSanitizedParameters(String unsanitizedPath) {
        HashMap<String, String> queryParams = new HashMap<>();

        Uri uri = Uri.parse(unsanitizedPath);

        if (uri.getHost() == null) {
            uri = Uri.parse("://" + unsanitizedPath); // path may contain a ":" leading to Uri.parse to misinterpret
            // it as opaque so, try it with a empty scheme in front
        }

        if (uri.getQueryParameterNames() != null) {
            for (String paramName : uri.getQueryParameterNames()) {
                String value = uri.getQueryParameter(paramName);
                queryParams.put(paramName, value);
            }
        }

        return queryParams;
    }

    /**
     * Returns locale parameter used in REST calls which require the response to be localized
     */
    public static HashMap<String, String> getRestLocaleParams(Context context) {
        HashMap<String, String> params = new HashMap<>();
        String deviceLanguageCode = LanguageUtils.getCurrentDeviceLanguageCode(context);
        if (!TextUtils.isEmpty(deviceLanguageCode)) {
            // patch locale if it's any of the deprecated codes as can be read in Locale.java source code:
            deviceLanguageCode = LanguageUtils.patchDeviceLanguageCode(deviceLanguageCode);
            params.put("locale", deviceLanguageCode);
        }
        return params;
    }
}
