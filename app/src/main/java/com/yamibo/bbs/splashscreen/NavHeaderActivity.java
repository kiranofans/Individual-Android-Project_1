package com.yamibo.bbs.splashscreen;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.HashMap;

public class NavHeaderActivity extends AppCompatActivity{
    private SQLiteHandler dbHandler;
    private Button logoutBtn;
    private SessionManager session;
    private TextView usernameTv;
    private ImageButton usrAvatar;
    private Button plsLogBtn,regBtn;
    @Override
    protected void onCreate(Bundle savedInsttanceState){
        super.onCreate(savedInsttanceState);
        setContentView(R.layout.nav_header_main);
        usrAvatar=(ImageButton)findViewById(R.id.avartarImgBtn);
        logoutBtn=(Button)findViewById(R.id.loginReqstBtn);

        usernameTv=(TextView)findViewById(R.id.usrNameTxt);
        //SQLdb handler
        dbHandler=new SQLiteHandler(getApplicationContext());

        //Session Manager
        session=new SessionManager(getApplicationContext());

        if(!session.isLoggedIn()){
            logoutUser();
        }

        //Fetching user details from SQLite or JSON Api
        HashMap<String,String> user=dbHandler.getUserDetails();

        String username=user.get("username");
        String email=user.get("email");

        usernameTv.setText(username);
        setBtnOnClicks();


    }
    private void setBtnOnClicks(){
        usrAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Will start a new activity
            }
        });
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
