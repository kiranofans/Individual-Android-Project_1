package Utility;

import android.app.Application;
import android.support.v7.app.AppCompatActivity;

import com.yamibo.bbs.splashscreen.MainNavTabActivity;
import com.yamibo.bbs.splashscreen.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AppConstants extends Application{
    //Shared Preferences Keys
    public static final String PREF_FILE_GLOBAL = "pref_file_global";
    public static final String KEY_USERNAME="member_username";
    public static final String KEY_COOKIEPRE="cookiepre";
    public static final String KEY_EMAIL="email";
    public static final String KEY_AVATAR="member_avatar";
    public static final String KEY_UID="member_uid";
    public static final String KEY_NOTICES="notice";
    public static final String KEY_READ_AUTH="readaccess";
    public static final String KEY_GROUPID="groupid";
    public static final String KEY_CREDITS="credits";

    public static final String MIME_HTML_UTF8 = "text/html; charset=utf-8";
    public static final String ENCODING_UTF8 = "UTF-8";
    public static final String CHECK_MARK = "\u2713";
}
