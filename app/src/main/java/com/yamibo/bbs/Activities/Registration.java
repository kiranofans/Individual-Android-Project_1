package com.yamibo.bbs.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import Managers.SessionManager;

public class Registration extends AppCompatActivity {
    private static final String TAG = Registration.class.getSimpleName();

    private EditText inputUserName;
    private EditText inputPassword;
    private EditText inputAnswer, pswdConfirmInput, inputEmail;

    private SessionManager session;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_regist);

        inputUserName = (EditText) findViewById(R.id.nameInput);
        inputPassword = (EditText) findViewById(R.id.pswdInput);
        inputEmail = (EditText) findViewById(R.id.emailInput);
        inputAnswer = (EditText) findViewById(R.id.secAnswInput);
        //btnLinkToLogin = (Button) findViewById(R.id.);

        if (session.isLoggedIn()) {
            //take the user to main activity
            startActivity(new Intent(Registration.this, MainNavTabActivity.class));
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed(); //back to main
    }

}
