package com.yamibo.bbs.splashscreen;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.app.Application;

import java.util.HashMap;

import static Utils.AppConstants.PREF_KEY_AVATAR;
import static Utils.AppConstants.PREF_KEY_GROUPID;
import static Utils.AppConstants.PREF_KEY_NOTICES;
import static Utils.AppConstants.PREF_KEY_READ_AUTH;
import static Utils.AppConstants.PREF_KEY_UID;
import static Utils.AppConstants.PREF_KEY_USERNAME;

public class SessionManager extends Application{
    private static String LOGCAT_TAG=SessionManager.class.getSimpleName();
    private static SharedPreferences sharedPrefs;
    private static SharedPreferences.Editor editor;
    private Context _context;
    private static SessionManager instance;
    private static final String SHARED_NAME="com.yamibo.bbs.splashscreen";
    private static final String IS_KEY_LOGGEDIN="login_succeed";
    private String defAvatarURL="https://bbs.yamibo.com/uc_server/avatar.php?uid=330107&size=small";

    public static final SessionManager getInstance(){
        return new SessionManager();
    }
    private SessionManager(){
        //Empty constructor needed
    }
    // Constructor
    public SessionManager(Context context){
        this._context=context;

    }
    public static synchronized SessionManager getInstance(Context context){
        if(instance==null){
            instance=new SessionManager(context);
        }
        return instance;
    }
    public void createLoginSession(boolean isLoggedIn,String notice,String groupId,
                                   String avatarUrl,String readAuth,String usrName,String uid){
        sharedPrefs =_context.getSharedPreferences(SHARED_NAME,Context.MODE_PRIVATE);

        editor= sharedPrefs.edit();
        String[] notices={"newmypost","newpm","newprompt","newpush"};
        //Storing login (state) value as TRUE
        editor.putBoolean(IS_KEY_LOGGEDIN,true);
        String[] noticeArr={"newmypost","newpm","newprompt","newpush"};
        for(int i=0;i<noticeArr.length;i++){
            editor.putString(PREF_KEY_NOTICES, sharedPrefs.getString(noticeArr[i],notice));
        }
        editor.putString(PREF_KEY_AVATAR,avatarUrl);
        editor.putString(PREF_KEY_GROUPID,groupId);
        editor.putString(PREF_KEY_READ_AUTH,readAuth);
        editor.putString(PREF_KEY_USERNAME,usrName);
        editor.putString(PREF_KEY_UID,uid);

        editor.commit();
    }
    public boolean checkIfLoggedIn(){
        sharedPrefs =_context.getSharedPreferences(SHARED_NAME,Context.MODE_PRIVATE);
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
        return sharedPrefs.getBoolean(IS_KEY_LOGGEDIN,false);
    }

    /**Get stored session data*/
    public HashMap<String,String> getUserDetails(){
        sharedPrefs =_context.getSharedPreferences(SHARED_NAME,Context.MODE_PRIVATE);

        //Notice Array to hold child objects of notice
        String[] noticeArr={"newmypost","newpm","newprompt","newpush"};
        HashMap<String,String> userData=new HashMap<>();
        for(int i=0;i<noticeArr.length;i++){
           userData.put(PREF_KEY_NOTICES, sharedPrefs.getString(noticeArr[i],null));
        }
        userData.put(PREF_KEY_AVATAR, sharedPrefs.getString(PREF_KEY_AVATAR,defAvatarURL));
        userData.put(PREF_KEY_USERNAME, sharedPrefs.getString(PREF_KEY_USERNAME,null));
        userData.put(PREF_KEY_UID, sharedPrefs.getString(PREF_KEY_UID,null));
        userData.put(PREF_KEY_GROUPID, sharedPrefs.getString(PREF_KEY_GROUPID,null));
        userData.put(PREF_KEY_READ_AUTH, sharedPrefs.getString(PREF_KEY_READ_AUTH,null));

        return userData;
    }
    public void logoutUser(){
        //Clearing all data frm shared preferences
        sharedPrefs =_context.getSharedPreferences(SHARED_NAME,Context.MODE_PRIVATE);

        editor.clear();
        editor.commit();

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
        sharedPrefs =_context.getSharedPreferences(SHARED_NAME,Context.MODE_PRIVATE);
        return sharedPrefs.getBoolean(IS_KEY_LOGGEDIN,false);
    }
}

