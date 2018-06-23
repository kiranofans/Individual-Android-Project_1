package com.yamibo.bbs.splashscreen;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class SessionManager {
    private static String LOGCAT_TAG=SessionManager.class.getSimpleName();

    SharedPreferences sharePrefs;

    SharedPreferences.Editor editor;
    Context _context;

    int PRIVATE_MODE=0;

    private static final String SHARED_NAME="AndroidLogin";
    private static final String IS_KEY_LOGGEDIN="isLoggedIn";

    public SessionManager(Context context){
        this._context=context;
        sharePrefs=context.getSharedPreferences(SHARED_NAME,PRIVATE_MODE);
        editor=sharePrefs.edit();
    }
    public void setLogin(boolean isLoggedIn){
        editor.putBoolean(IS_KEY_LOGGEDIN,isLoggedIn);
        editor.commit();
        Log.d(LOGCAT_TAG,"User login session modified@");
    }
    public boolean isLoggedIn(){
        return sharePrefs.getBoolean(IS_KEY_LOGGEDIN,false);
    }
}

