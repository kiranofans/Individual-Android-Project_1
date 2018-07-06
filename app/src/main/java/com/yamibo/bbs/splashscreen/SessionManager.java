package com.yamibo.bbs.splashscreen;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.HashMap;

import Model.Users;

public class SessionManager {
    private static String LOGCAT_TAG=SessionManager.class.getSimpleName();
    private static SharedPreferences sharePrefs;
    SharedPreferences.Editor editor;
    private Context _context;
    private static SessionManager instance;
    private static final String SHARED_NAME="com.yamibo.bbs.splashscreen";
    private static final String IS_KEY_LOGGEDIN="1f11YOJkZaB/lKtA4fznjlW3ZkwVmRm1wMleFHKIgZtJvvnUtxMkGqMZ2Lemq1+CrpBbcekwBunxG+IczOVPaare9g";
    public static final String KEY_USERNAME="member_username";
    public static final String KEY_COOKIEPRE="cookiepre";
    public static final String KEY_EMAIL="email";
    public static final String KEY_GROUPID="groupid";
    // Constructor
    private SessionManager(Context context){
        this._context=context;

    }
    public static synchronized SessionManager getInstance(Context context){
        if(instance==null){
            instance=new SessionManager(context);
        }
        return instance;
    }
    public void createLoginSession(String username){
        sharePrefs=_context.getSharedPreferences(SHARED_NAME,Context.MODE_PRIVATE);
        editor=sharePrefs.edit();

        //Storing login value as TRUE
        editor.putBoolean(IS_KEY_LOGGEDIN,true);
        editor.putString(KEY_USERNAME,username);
        editor.apply();
        Log.d(LOGCAT_TAG,"User login session modified");
    }
    public boolean checkIfLoggedIn(){
        sharePrefs=_context.getSharedPreferences(SHARED_NAME,Context.MODE_PRIVATE);
        if(!this.isLoggedIn()){
            //user is not logged in redirect the user to login page
            Intent intent=new Intent(_context,Activity_Login.class);

            //Closing all the activities
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            //Add new Flag to start new Activity
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            //Starting login activity
            _context.startActivity(intent);
        }
        return sharePrefs.getBoolean(IS_KEY_LOGGEDIN,false);
    }

    /**Get stored session data*/
    public HashMap<String, String> getUserDetails(Users user){
        HashMap<String,String> users=new HashMap<>();
        sharePrefs=_context.getSharedPreferences(SHARED_NAME,Context.MODE_PRIVATE);

        //username
        users.put(KEY_USERNAME,sharePrefs.getString(KEY_USERNAME,null));
        users.put(KEY_EMAIL,sharePrefs.getString(KEY_EMAIL,null));
        users.put(KEY_GROUPID,sharePrefs.getString(KEY_GROUPID,null));
        users.put(KEY_COOKIEPRE,sharePrefs.getString(KEY_COOKIEPRE,null));

        return users;
    }
    public void logoutUser(){
        //Clearing all data frm shared preferences
        sharePrefs=_context.getSharedPreferences(SHARED_NAME,Context.MODE_PRIVATE);

        editor.clear();
        editor.apply();

        //After logout redirect user to login activity
        Intent intent=new Intent(_context,Activity_Login.class);

        //closing all the activities
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        //Add new Flag to start new activity
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        //Starting login Activity
        _context.startActivity(intent);
    }

    /**Quick check for login*/
    public boolean isLoggedIn(){
        sharePrefs=_context.getSharedPreferences(SHARED_NAME,Context.MODE_PRIVATE);
        return sharePrefs.getBoolean(IS_KEY_LOGGEDIN,false);
    }
}

