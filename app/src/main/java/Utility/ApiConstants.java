package Utility;

import android.app.Application;

import com.yamibo.bbs.splashscreen.R;

import Model.ApiResponses;

public class ApiConstants extends Application {
    //Base urls
    public static final String BASE_URL = "https://bbs.yamibo.com/api/mobile/index.php?";
    public static final String IMG_BASE_URL="https://bbs.yamibo.com/data/attachment/";

    //Forum contents
    public static final String FORUM_CHATTING_URL = BASE_URL + "version=4&module=forumdisplay&fid=33&page=%s";
    public static final String FORUM_ADMIN_URL = BASE_URL + "version=4&module=forumdisplay&fid=16&page=%s";
    public static final String FORUM_ANIME_MANGA_URL = BASE_URL + "version=4&module=forumdisplay&fid=5&page=$s";
    public static final String FORUM_VIDEO_GAME_URL = BASE_URL + "version=4&module=forumdisplay&fid=44&page=%s";//game
    public static final String FORUM_PICTURES_URL = BASE_URL + "version=4&module=forumdisplay&fid=13&page=%s";
    public static final String FORUM_LITERATURE_URL = BASE_URL + "version=4&module=forumdisplay&fid=49&page=%s";
    public static final String FORUM_DAILY_HITS_URL = BASE_URL + "module=hot";

    //Forum names
    public static final String FORUM_NAMES_API_URL = BASE_URL + "version=4&module=forumindex";
    public static final String USER_PROFILE_API_URL = BASE_URL + "version=4&module=profile";
    public static final String LOGIN_REQUEST_API_URL = BASE_URL + "module=login&loginsubmit=yes";
    public static final String MY_POSTS_API_URL = BASE_URL + "version=4&module=mythread&page=%s";//my posts

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
