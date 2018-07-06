package com.yamibo.bbs.splashscreen;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.String;

public class Registration extends AppCompatActivity {
    private static final String TAG = Registration.class.getSimpleName();
    private Button regSubmitBtn;
    private Button btnLinkToLogin;
    private EditText inputUserName;
    private EditText inputPassword;
    private EditText inputAnswer,pswdConfirmInput,inputEmail;
    private String usernameTxt,emailTxt,pswdTxt,pswdConfirmTxt,answerTxt;
    private ProgressDialog pDialog;
    private SessionManager session;
    private SQLiteHandler dbHandler;
    private  String []  regElement;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_regist);

        inputUserName = (EditText) findViewById(R.id.nameInput);
        inputPassword = (EditText) findViewById(R.id.pswdInput);
        inputEmail=(EditText)findViewById(R.id.emailInput);
        inputAnswer=(EditText)findViewById(R.id.secAnswInput);
        //btnLinkToLogin = (Button) findViewById(R.id.);

        //SQLite db handler
        dbHandler=new SQLiteHandler(getApplicationContext());

        if(session.isLoggedIn()){
            //take the user to main activity
            startActivity(new Intent(Registration.this,MainNavTabActivity.class));
            finish();
        }

        setBtnOnClicks();

    }
    public void setBtnOnClicks(){
        //Register button onClick
        usernameTxt=inputUserName.getText().toString();
        emailTxt=inputEmail.getText().toString();
        pswdTxt=inputPassword.getText().toString();
        pswdConfirmTxt=pswdConfirmInput.getText().toString();
        answerTxt=inputAnswer.getText().toString();
        regSubmitBtn = (Button) findViewById(R.id.submitBtn);
        regSubmitBtn.setOnClickListener(new View.OnClickListener() {
            final String[] regElement={usernameTxt,emailTxt,pswdTxt,
                    pswdConfirmTxt,answerTxt};
            @Override
            public void onClick(View v) {
                for(int i=0;i< regElement.length;i++) {
                    if (!regElement[i].isEmpty()) {
                        registerUser(regElement[0],regElement[1],regElement[2]);
                        Toast.makeText(getApplicationContext(),"註冊成功",
                                Toast.LENGTH_LONG).show();
                        startActivity(new Intent
                                (Registration.this,Activity_Login.class));
                    }else{
                        Toast.makeText(getApplicationContext(),"還有空沒填呢",
                                Toast.LENGTH_LONG).show();
                    }
                }
                startActivity(new Intent
                        (Registration.this,Activity_Login.class));
               // finish();
            }
        });
    }

    /**
     * Function to store user in MySQL database will post params(tag, name,
     * email, password) to register url*/
    private void registerUser(final String username,final String email,final String password){
        //Tag used to cancel the request
        String cancel_reqst="req_register";
        StringRequest strReqst=new StringRequest(Request.Method.POST, AppConfig.URL_REGISTER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, "Register Response" + response.toString());
                        try {
                            JSONObject jobj=new JSONObject(response);
                            boolean isError=jobj.getBoolean("error");
                            if(!isError){
                                //User stored in MySQL successfully
                                //And we will store the user in SQLite too
                                String uniqId=jobj.getString("uid");

                                JSONObject user=jobj.getJSONObject("user");
                                String username=user.getString("username");
                                String email=user.getString("email");
                                String addedDate=user.getString("created_at");

                                //Inserting row in users table
                                dbHandler.addUserDetais(username,email,
                                        uniqId,addedDate);

                                Toast.makeText(getApplicationContext(), "User added.",
                                        Toast.LENGTH_SHORT).show();

                                //Launch login activity
                                startActivity(new Intent(getApplicationContext(),Activity_Login.class));
                                finish();
                            }else {
                                //Error occurred in registration
                                String errorMsg=jobj.optString("error_msg");
                                Toast.makeText(Registration.this,errorMsg,
                                        Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException je) {
                            je.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
    }
}
