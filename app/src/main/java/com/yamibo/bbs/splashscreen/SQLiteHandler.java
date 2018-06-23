package com.yamibo.bbs.splashscreen;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.HashMap;

public class SQLiteHandler extends SQLiteOpenHelper
{
    private static final String TEST=SQLiteHandler.class.getSimpleName();

    //All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 5719;

    // Database Name
    private static final String DATABASE_NAME = "yamibo_android";

    // Login table name
    private static final String TABLE_USER = "users";

    // Login Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "username";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_UID = "unique_id";
    private static final String KEY_CREATED_AT = "created_at";

    public SQLiteHandler(Context context) {
        super(context,DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override//Creating tables
    public void onCreate(SQLiteDatabase db) {
        String CREATE_LOGIN_TABLE="create table "+TABLE_USER+"("
                +KEY_ID+" INTEGER PRIMARY KEY,"+KEY_NAME+" TEXT,"
                +KEY_EMAIL+" TEXT UNIQUE,"+KEY_UID+" TEXT,"+KEY_CREATED_AT
                +" TEXT"+")";

        Log.d(TEST,"Database tables created");


    }

    //Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Drop older table if existed

        //Create tables agian
        onCreate(db);
    }
    /**Storing user details in database*/
    public void addUserDetais(String username,String email,String uniqId,String added_date){
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues values=new ContentValues();
        values.put(KEY_NAME,username);
        values.put(KEY_EMAIL,email);
        values.put(KEY_UID,uniqId);
        values.put(KEY_CREATED_AT,added_date);

        //Inserting Row
        long id=db.insert(TABLE_USER,null,values);
        db.close();

        Log.d(TEST,"New user "+id+" inserted into SQLite");

    }
    /**Getting user data from database*/
    public HashMap<String,String> getUserDetails(){
        HashMap<String,String> users=new HashMap<String,String>();
        String selectQuery="SELECT * FROM "+TABLE_USER;

        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(selectQuery,null);

        //Move to first row
        cursor.moveToFirst();
        if(cursor.getCount()>0){
            users.put("username",cursor.getString(1));
            users.put("email",cursor.getString(1));
            users.put("unique_id",cursor.getString(1));
            users.put("created_at",cursor.getString(1));
        }
        cursor.close();
        db.close();

        //return user
        Log.d(TEST,"Fetching user "+users.toString()+" from SQLite.");
        return users;
    }
    /**Recreate database, delete all tables, and, create them again*/
    public void deleteUsers(){
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(TABLE_USER,null ,null);
        db.close();
        Log.d(TEST,"Deleted all user info from SQLite");

    }
}
