package com.yamibo.bbs.splashscreen;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.LoaderManager;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import Model.Users;
import Utility.AppConstants;

import static android.support.design.widget.Snackbar.make;

public class Activity_Login extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{
    /** Id to identity READ_CONTACTS permission request.*/
    private static final String LOG_TAG=Activity_Login.class.getSimpleName();
    private static final String PREF_FILE = AppConstants.PREF_FILE_GLOBAL;
    private static SessionManager sessionMg;
    private MainNavTabActivity main;

    private static AutoCompleteTextView usrnameInput;
    private static EditText pswdInput;
    private Button forgotPswd, contactUs, logOutBtn, loginBtn;
    private View progressView;
    private static ImageView avatarImgBtn;
    private static TextView usrnameTv;
    private static View loginForm;

    private static String username, avatarUrl, pswd, getUid;
    private String[] urls;

    private static Users users;
    private List<String> usernameList;

    private static JSONObject jObj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Init the login form.
        usrnameInput = (AutoCompleteTextView) findViewById(R.id.username);
        contactUs = (Button) findViewById(R.id.contactBtn);
        forgotPswd = (Button) findViewById(R.id.forgetPswdBtn);
        pswdInput = (EditText) findViewById(R.id.password);

        username = usrnameInput.getText().toString();
        pswd=pswdInput.getText().toString();

        pswdInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                    userLogin();
                    return true;
                }
                return false;
            }
        });

        loginForm = findViewById(R.id.login_form);
        progressView = findViewById(R.id.login_progress);

        //Session manager and login
        sessionMg =new SessionManager(getApplicationContext());
        loginBtn = (Button) findViewById(R.id.loginBtn);
        setBtnOnClicks();

    }
    /** Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.*/
    private boolean attemptLogin() {
        // Reset errors.
        usrnameInput.setError(null);
        pswdInput.setError(null);

        // Store values at the time of the login attempt.
        username = usrnameInput.getText().toString();
        pswd = pswdInput.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(pswd) && !isPasswordValid(pswd)) {
            pswdInput.setError(getString(R.string.error_invalid_password));
            focusView = pswdInput;
            return cancel = true;

        } else if (TextUtils.isEmpty(pswd)) {
            pswdInput.setError("密碼不能為空");
            focusView = pswdInput;
            return cancel = true;
        }

        // Check for a valid username.
        if (TextUtils.isEmpty(username)) {
            usrnameInput.setError(getString(R.string.error_field_required));
            focusView = usrnameInput;
            cancel = true;
            return false;
        } else if (!isUsernameValid(username)) {
            usrnameInput.setError(getString(R.string.invalid_username));
            focusView = usrnameInput;
            cancel = true;
            return false;
        }
        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);
            userLogin();
            return cancel=false;
        }
        return cancel;
    }
    private boolean isUsernameValid(String username) {
        /** UTF-8 (Unicode)
         * \u4e00-\u9fa5: Chinese
         * \u0800-\u4e00: Japanese */
        String pattern = "([a-zA-Z0-9_\\u4e00-\\u9fa5\\u0800-\\u4e00]+$)";
        if (!username.matches(pattern)) {
            Toast.makeText(this, "用戶名不對", Toast.LENGTH_SHORT).show();
        }
        return username.matches(pattern);
    }
    private void setBtnOnClicks(){
        loginBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                //Check for empty data in the form
                attemptLogin();
                if(attemptLogin()==false){//if cancel=false
                    startActivity(new Intent(Activity_Login.this,
                            MainNavTabActivity.class));
                    finish();
                }
            }
        });
    }

    private boolean isPasswordValid(String password) {
        String pattern = "([a-zA-Z0-9].{5,40})";
        if (password.length() < 6) {
            Toast.makeText(this, "密碼長度需在6個字節或以上",
                    Toast.LENGTH_LONG).show();
        } else if (!password.matches(pattern)) {
            Toast.makeText(this, "密碼不包含數字或字母以外的符號",
                    Toast.LENGTH_LONG).show();
        }
        return password.matches(pattern);
    }

    /** Shows the progress UI and hides the login form.*/
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            loginForm.setVisibility(show ? View.GONE : View.VISIBLE);
            loginForm.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    loginForm.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            progressView.setVisibility(show ? View.VISIBLE : View.GONE);
            progressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    progressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            progressView.setVisibility(show ? View.VISIBLE : View.GONE);
            loginForm.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    /*Add Email and username to autocompleteTextView*/
    private void addEmailsToAutoComplete(List<String> emailAddressCollection) {
        //Create adapter to tell the AutoCompleteTextView what to show in its dropdown list.
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(Activity_Login.this,
                        android.R.layout.simple_dropdown_item_1line, emailAddressCollection);
        //emailInput.setAdapter(adapter);
    }
    private void addUsernameToAutoComplete(List<String> usernames) {
        ArrayAdapter<String> adp = new ArrayAdapter<>
                (Activity_Login.this, android.R.layout.simple_dropdown_item_1line, usernames);
        usrnameInput.setAdapter(adp);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("S","isNull: "+sessionMg);
        if (sessionMg != null && sessionMg.isLoggedIn()) {
            Toast.makeText(getApplicationContext(),
                    "Session ok", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public android.content.Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(this,
                // Retrieve data rows for the device user's 'profile' contact.
                Uri.withAppendedPath(ContactsContract.Profile.CONTENT_URI,
                        ContactsContract.Contacts.Data.CONTENT_DIRECTORY),
                ProfileQuery.PROJECTION,
                // Select only email addresses.
                ContactsContract.Contacts.Data.MIMETYPE +" = ?",
                new String[]{ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE},

                //Show primary usrename first. Note that there won't be
                // a primary email if the user hasn't specified one.
                ContactsContract.Contacts.Data.IS_SUPER_PRIMARY + " DESC");
    }

    @Override
    public void onLoadFinished(android.content.Loader<Cursor> loader, Cursor cursor) {
        usernameList = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            usernameList.add(cursor.getString(ProfileQuery.DISPLAY_NAME));
            cursor.moveToNext();
        }
        addUsernameToAutoComplete(usernameList);
        Log.d("LIST","UsernameList: "+usernameList.size());
    }
    @Override
    public void onLoaderReset(android.content.Loader<Cursor> loader) { }
    public JSONObject userLogin(){
        main=new MainNavTabActivity();
        urls=getResources().getStringArray(R.array.yamibo_api_urls);
        final StringRequest request=new StringRequest(Request.Method.POST, urls[3],
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            jObj=new JSONObject(response);
                            JSONObject loginMsg=jObj.getJSONObject("Message");
                            String msgVal=loginMsg.getString("messageval");
                            String msgTr=loginMsg.getString("messagestr");
                            if(msgVal.equals("login_succeed")){
                                Toast.makeText(Activity_Login.this,
                                        msgTr,Toast.LENGTH_SHORT).show();
                                JSONObject varObj = jObj.getJSONObject("Variables");
                                JSONObject notice = varObj.getJSONObject("notice");
                                Iterator<String> iter=notice.keys();

                                //Loop through the notice JSONObjects
                                String notices="";
                                while(iter.hasNext()){
                                    String key=iter.next();
                                    notices=notice.getString(key);
                                }
                                String avatarUrl = varObj.getString("member_avatar");
                                String userName = varObj.getString("member_username");
                                String uid = varObj.getString("member_uid");
                                String groupId = varObj.getString("groupid");
                                String readAuth = varObj.getString("readaccess");

                                sessionMg.createLoginSession(true,
                                        notices,groupId,avatarUrl,readAuth,userName,uid);

                            }else{
                                Toast.makeText(Activity_Login.this,
                                        msgTr,Toast.LENGTH_SHORT).show();
                            }
                        }catch (JSONException je){
                            Log.d("JSON ERROR",je.getMessage());
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Volley Error",error.getMessage());
            }
        }){
            @Override
            public Map<String,String> getHeaders() throws AuthFailureError{
                Map<String,String> headers=new HashMap<>();
                headers.put("Accept-Charset","gbk");
                headers.put("Content-Transfer-Encoding","charset=gbk");
                headers.put("Content-Type","application/x-www-form-urlencoded");
                return headers;
            }
            @Override
            protected Map<String,String> getParams() throws AuthFailureError{
                Map<String,String> params=new HashMap<>();
                params.put("username",username);
                params.put("password",pswd);
                return params;
            }
        };
        VolleySingleton.getInstance(this).addToRequestQueue(request);
        return jObj;
    }

    private interface ProfileQuery {
        String[] PROJECTION = {
                ContactsContract.CommonDataKinds.Email.ADDRESS,
                ContactsContract.CommonDataKinds.Email.IS_PRIMARY,
                ContactsContract.CommonDataKinds.Nickname.DISPLAY_NAME,
        };
        int ADDRESS = 0;
        int IS_PRIMARY = 1;
        int DISPLAY_NAME=2;
    }
}



