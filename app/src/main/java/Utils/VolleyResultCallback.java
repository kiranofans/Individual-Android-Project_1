package Utils;

import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONObject;

public interface VolleyResultCallback{
    void jsonResponse(JSONObject response);
    void responseError(VolleyError error);
}
