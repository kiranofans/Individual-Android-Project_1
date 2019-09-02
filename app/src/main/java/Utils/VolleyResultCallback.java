package Utils;

import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONObject;

public interface VolleyResultCallback{

    public void jsonResponse(JSONObject response);
    public void stringResponse(String strResponse);
    public void responseError(VolleyError error);
}
