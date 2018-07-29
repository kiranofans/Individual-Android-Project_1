package Utility;

import android.app.Application;

import com.yamibo.bbs.splashscreen.R;

import Model.ApiResponses;

public class ApiConstants extends Application{

    //API Response codes
    public static final int API_RESPONSE_CODE_UNKNOWN_ERROR = 0;
    public static final int API_RESPONSE_CODE_HTTP_INVALID = 1;
    public static final int API_RESPONSE_CODE_SUCCESS = 200;
    public static final int API_RESPONSE_CODE_NO_DATA = 204;
    public static final int API_RESPONSE_CODE_ACCOUNT_LOCKED = 243;
    public static final int API_RESPONSE_CODE_INVALID_REQUEST_BODY = 400;
    public static final int API_RESPONSE_CODE_HTTP_INVALID_KEY_PAIR = 401;
    public static final int API_RESPONSE_CODE_ACCESS_DENIED = 403;
    public static final int API_RESPONSE_CODE_UNKNOWN_HOST = 404;
    public static final int API_RESPONSE_CODE_VERIFICATION_CODE_EXPIRE = 408;
    public static final int API_RESPONSE_CODE_TOO_MANY_REQUESTS = 429;
    public static final int API_RESPONSE_CODE_CUSTOMER_ALREADY_EXIST = 452;
    public static final int API_RESPONSE_CODE_INTERNAL_SERVER_ERROR = 500;
    public static final int API_RESPONSE_CODE_INVALID_KEY_PAIR = 4011;


}
