package Utils;

import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONObject;

public interface VolleyResultCallback<T> {
    void jsonResponse(T response);
    void responseError(VolleyError error);
}
