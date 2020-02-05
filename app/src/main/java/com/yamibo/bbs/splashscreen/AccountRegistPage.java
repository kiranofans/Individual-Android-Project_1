package com.yamibo.bbs.splashscreen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class AccountRegistPage extends AppCompatActivity {
private Button regBtn,cancelBtn;
private EditText usrName,usrPswd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_regist);
    }
}
