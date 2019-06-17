package Utils;

import android.app.Application;
import android.media.audiofx.BassBoost;
import android.util.SparseArray;

public class AppConstants extends Application{
    //Shared Preferences Keys
    public static final String PREF_FILE_GLOBAL = "pref_file_global";
    public static final String PREF_KEY_USERNAME ="member_username";
    public static final String PREF_KEY_PASSWORD = "password";
    public static final String PREF_KEY_USERNAME_PASSWORD ="";
    public static final String PREF_KEY_COOKIEPRE ="cookiepre";
    public static final String PREF_KEY_EMAIL ="email";
    public static final String PREF_KEY_AVATAR ="member_avatar";
    public static final String PREF_KEY_UID ="member_uid";
    public static final String PREF_KEY_NOTICES ="notice";
    public static final String PREF_KEY_READ_AUTH ="readaccess";
    public static final String PREF_KEY_GROUPID ="groupid";
    public static final String PREF_KEY_CREDITS ="credits";
    public static final String PREF_KEY_LOGIN_TOKEN = "authtoken";

    public static final String MIME_HTML_UTF8 = "text/html; charset=utf-8";
    public static final String ENCODING_UTF8 = "UTF-8";
    public static final String CHECK_MARK = "\u2713";

    public static final int USER_PERMISSION_LEVEL_TOURIST = 0;
    public static final int USER_PERMISSION_LEVEL_SEED = 1;
    public static final int USER_PERMISSION_LEVEL_2 = 2;
    public static final int USER_PERMISSION_LEVEL_3 = 3;
    public static final int USER_PERMISSION_LEVEL_4 = 4;
    public static final int USER_PERMISSION_LEVEL_5 = 5;
    public static final int USER_PERMISSION_LEVEL_6 = 6;
    public static final int USER_PERMISSION_LEVEL_7 = 7;
    public static final int USER_PERMISSION_LEVEL_8 = 8;
    public static final int USER_PERMISSION_LEVEL_9 = 9;


    public static final SparseArray<boolean[]> USER_PERMISSION_ACCESS_MAP = new SparseArray<>();
    static{
        USER_PERMISSION_ACCESS_MAP.append(USER_PERMISSION_LEVEL_TOURIST, new boolean[]{
                true, false, false, false, false
        });
        USER_PERMISSION_ACCESS_MAP.append(USER_PERMISSION_LEVEL_SEED,new boolean[]{
                true,true,true,false
        });
        USER_PERMISSION_ACCESS_MAP.append(USER_PERMISSION_LEVEL_2, new boolean[]{
                true,true,true,true
        });
    }
}
