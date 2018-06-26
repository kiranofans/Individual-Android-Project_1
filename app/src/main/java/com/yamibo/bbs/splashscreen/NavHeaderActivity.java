package com.yamibo.bbs.splashscreen;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;
import com.yamibo.bbs.splashscreen.Fragments.ChatFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class NavHeaderActivity extends MainNavTabActivity{
    private SQLiteHandler dbHandler;

    private static Button plsLogBtn,regBtn;
    private NavigationView navView;
    private static View headerView;
    private SessionManager session;
    @Override
    protected void onCreate(Bundle savedInsttanceState){
        super.onCreate(savedInsttanceState);
        setContentView(R.layout.nav_header_main);
        navView=(NavigationView)findViewById(R.id.nav_view);
        headerView=navView.getHeaderView(0);

        //SQLdb handler
        dbHandler=new SQLiteHandler(getApplicationContext());

        //Session Manager
        session=new SessionManager(getApplicationContext());

        //Fetching user details from SQLite or JSON Api

        if(!session.isLoggedIn()){
            logoutUser();
        }


        //HashMap<String,String> user=dbHandler.getUserDetails();


        //String username=user.get("username");
        //String email=user.get("email");

        //usernameTv.setText(username);

    }

    private void logoutUser(){
        session.setLogin(false);
        dbHandler.deleteUsers();

        //Launching the main activity and contents before login
        startActivity(new Intent(NavHeaderActivity.this,MainNavTabActivity.class));
        finish();
    }
    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

}
