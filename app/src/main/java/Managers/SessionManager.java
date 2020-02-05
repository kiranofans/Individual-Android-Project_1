package Managers;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.yamibo.bbs.Activities.MainNavTabActivity;

import java.util.HashMap;

import javax.inject.Inject;

import com.yamibo.bbs.Annotations.ApplicationContext;
import com.yamibo.bbs.Annotations.PreferenceInfo;
import Utils.AppConstants;
import Utils.Login.LoggedInMode;
import com.yamibo.bbs.data.prefs.PrefsHelper;

import static Utils.AppConstants.PREF_KEY_AVATAR;
import static Utils.AppConstants.PREF_KEY_FIRST_TIME;
import static Utils.AppConstants.PREF_KEY_GROUPID;
import static Utils.AppConstants.PREF_KEY_LOGGED_IN_MODE;
import static Utils.AppConstants.PREF_KEY_LOGIN_TOKEN;
import static Utils.AppConstants.PREF_KEY_NOTICES;
import static Utils.AppConstants.PREF_KEY_READ_AUTH;
import static Utils.AppConstants.PREF_KEY_UID;
import static Utils.AppConstants.PREF_KEY_USERNAME;

public class SessionManager extends Application implements PrefsHelper {
    private static String LOGCAT_TAG = SessionManager.class.getSimpleName();

    private static SharedPreferences sharedPrefs;
    private static SharedPreferences.Editor editor;
    private Context _context;

    private static volatile SessionManager instance;
    private static final String SHARED_NAME = "com.yamibo.bbs.splashscreen";
    private static final String IS_KEY_LOGGED_IN = "login_succeed";
    private String defAvatarURL = "https://bbs.yamibo.com/uc_server/avatar.php?uid=330107&size=small";

    @Inject
    public SessionManager(@ApplicationContext Context context, @PreferenceInfo String prefFileName) {
        sharedPrefs = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE);
        this._context = context;

    }

    public void createLoginSession(boolean isLoggedIn,
                                   String authToken, String notice, String groupId,
                                   String avatarUrl, String readAuth, String usrName, String uid) {
        sharedPrefs = _context.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);

        editor = sharedPrefs.edit();

        //Storing login (state) value as TRUE
        editor.putBoolean(IS_KEY_LOGGED_IN, true);
        String[] noticeArr = {"newmypost", "newpm", "newprompt", "newpush"};
        for (int i = 0; i < noticeArr.length; i++) {
            editor.putString(PREF_KEY_NOTICES, sharedPrefs.getString(noticeArr[i], notice));
        }
        editor.putString(PREF_KEY_AVATAR, avatarUrl);
        editor.putString(PREF_KEY_GROUPID, groupId);
        editor.putString(PREF_KEY_READ_AUTH, readAuth);
        editor.putString(PREF_KEY_USERNAME, usrName);
        editor.putString(PREF_KEY_UID, uid);
        editor.putString(PREF_KEY_LOGIN_TOKEN, authToken);
        editor.putString(PREF_KEY_NOTICES, notice);

        editor.commit();
    }


    public boolean checkIfLoggedIn() {
        sharedPrefs = _context.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        if (!this.isLoggedIn()) {
            //user is not logged in redirect the user to Main
            Intent intent = new Intent(_context, MainNavTabActivity.class);

            //Closing all the activities
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            //Add new Flag to start new Activity
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            //Starting login activity
            _context.startActivity(intent);
        }
        return sharedPrefs.getBoolean(IS_KEY_LOGGED_IN, true);
    }

    /**
     * Get stored session data
     */
    public HashMap<String, String> getUserDetails() {
        sharedPrefs = _context.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);

        //Notice Array to hold child objects of notice
        String[] noticeArr = {"newmypost", "newpm", "newprompt", "newpush"};
        HashMap<String, String> userData = new HashMap<>();
        for (int i = 0; i < noticeArr.length; i++) {
            userData.put(PREF_KEY_NOTICES, sharedPrefs.getString(noticeArr[i], null));
        }
        userData.put(PREF_KEY_AVATAR, sharedPrefs.getString(PREF_KEY_AVATAR, defAvatarURL));
        userData.put(PREF_KEY_USERNAME, sharedPrefs.getString(PREF_KEY_USERNAME, null));
        userData.put(PREF_KEY_UID, sharedPrefs.getString(PREF_KEY_UID, null));
        userData.put(PREF_KEY_GROUPID, sharedPrefs.getString(PREF_KEY_GROUPID, null));
        userData.put(PREF_KEY_READ_AUTH, sharedPrefs.getString(PREF_KEY_READ_AUTH, null));

        return userData;
    }

    @Override
    public int getUserLoggedInMode() {
        //Set Logout by default
        return sharedPrefs.getInt(PREF_KEY_LOGGED_IN_MODE,
                LoggedInMode.LOGGED_IN_MODE_LOGOUT.getType());
    }

    @Override
    public void setUserLoggedIn(LoggedInMode mode) {
        sharedPrefs.edit().putInt(PREF_KEY_LOGGED_IN_MODE, mode.getType()).apply();
    }

    @Override
    public String getUserId() {
        return sharedPrefs.getString(PREF_KEY_UID, "");
    }

    @Override
    public void setUserId(String userId) {
        sharedPrefs.edit().putString(PREF_KEY_UID, userId).apply();
    }

    @Override
    public String getUserName() {
        return sharedPrefs.getString(PREF_KEY_USERNAME, "");
    }

    @Override
    public void setUserName(String userName) {
        sharedPrefs.edit().putString(PREF_KEY_USERNAME, userName).apply();
    }

    @Override
    public String getUserEmail() {
        return null;
    }

    @Override
    public void setUserEmail(String email) {

    }

    @Override
    public String getUserProfilePicUrl() {
        return sharedPrefs.getString(PREF_KEY_AVATAR, defAvatarURL);
    }

    @Override
    public void setUserProfilePicUrl(String profilePicUrl) {
        sharedPrefs.edit().putString(profilePicUrl, defAvatarURL);
    }

    @Override
    public String getAccessToken() {
        return sharedPrefs.getString(PREF_KEY_LOGIN_TOKEN, null);
    }

    @Override
    public void setAccessToken(String accessToken) {
        sharedPrefs.edit().putString(PREF_KEY_LOGIN_TOKEN, null);
    }

    @Override
    public String getUserMobile() {
        return null;
    }

    @Override
    public void setUserMobile(String mobileNumber) {

    }

    @Override
    public boolean isCoachMarkView() {
        return false;
    }

    @Override
    public void setCoachMarkView(boolean coachMark) {

    }

    @Override
    public boolean isFirstTime() {
        SharedPreferences pref = _context.getSharedPreferences
                (AppConstants.PREF_KEY_SHARED_PREF, MODE_PRIVATE);
        return pref.getBoolean(PREF_KEY_FIRST_TIME, true);
    }

    @Override
    public void setFirstTime(boolean firstTime) {
        SharedPreferences pref = _context.getSharedPreferences
                (AppConstants.PREF_KEY_SHARED_PREF, MODE_PRIVATE);
        SharedPreferences.Editor firstTimeEditor = pref.edit();
        firstTimeEditor.putBoolean(PREF_KEY_FIRST_TIME, firstTime);
        firstTimeEditor.apply();
    }

    public void logoutUser() {
        //Clearing all data frm shared preferences
        sharedPrefs = _context.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);

        sharedPrefs.edit().clear().apply();

        //After logout redirect user to login activity
        Intent intent = new Intent(_context, MainNavTabActivity.class);

        //closing all the activities
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        //Add new Flag to start new activity
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        //Starting login Activity
        _context.startActivity(intent);
    }

    /**
     * Quick check for login
     */
    public boolean isLoggedIn() {
        sharedPrefs = _context.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        return sharedPrefs.getBoolean(IS_KEY_LOGGED_IN, false);
    }
}

