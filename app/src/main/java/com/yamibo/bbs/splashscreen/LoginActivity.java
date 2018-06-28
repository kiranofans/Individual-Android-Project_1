package com.yamibo.bbs.splashscreen;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
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
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;
import com.yamibo.bbs.splashscreen.Fragments.ForumsFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import Model.Base_Items_Model;
import Model.Users;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    /**
     * Id to identity READ_CONTACTS permission request.
     */
    private String mAuthTask;
    private static final int REQUEST_READ_CONTACTS = 0;
    private SQLiteHandler dbHandler;
    private SessionManager sessionMg;
    private String TEST = Registration.class.getSimpleName();
    private RequestQueue rqstQueue;
    /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     */
    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "foo@example.com:hello", "bar@example.com:world"
    };

    // UI references.
    private AutoCompleteTextView usrnameInput;
    private EditText pswdInput;
    private View progressView;
    private static ImageButton avatarBtn;
    private static TextView usrnameTv;
    private static View loginForm;
    private static String names,avatarUrl,pswd;
    private Button forgotPswd, contactUs,logOutBtn,loginBtn;
    private static Users listOfUsers;
    private static boolean isSignedIn=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        usrnameInput = (AutoCompleteTextView) findViewById(R.id.username);

        contactUs = (Button) findViewById(R.id.contactBtn);
        forgotPswd = (Button) findViewById(R.id.forgetPswdBtn);
        pswdInput = (EditText) findViewById(R.id.password);

        pswdInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });
        rqstQueue=Volley.newRequestQueue(getApplicationContext());
        loginForm = findViewById(R.id.login_form);
        progressView = findViewById(R.id.login_progress);

        //SQLite db handler
        dbHandler = new SQLiteHandler(getApplicationContext());

        //Session manager
        sessionMg = new SessionManager(getApplicationContext());
        loginBtn = (Button) findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                pswd = pswdInput.getText().toString();

                //Check for empty data in the form
                attemptLogin(); JasonLogin();
            }
        });
    }

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {
        if (mAuthTask != null) {
            //login user
            return;
        }
        // Reset errors.
        usrnameInput.setError(null);
        pswdInput.setError(null);

        // Store values at the time of the login attempt.
        String username = usrnameInput.getText().toString();
        String password = pswdInput.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            pswdInput.setError(getString(R.string.error_invalid_password));
            focusView = pswdInput;
            cancel = true;
        }else if(TextUtils.isEmpty(password)){
            pswdInput.setError("密碼不能為空");
            focusView=pswdInput;
            cancel=true;
        }

        // Check for a valid username.
        if (TextUtils.isEmpty(username)) {
            usrnameInput.setError(getString(R.string.error_field_required));
            focusView = usrnameInput;
            cancel = true;
        } else if (!isUsernameValid(username)) {
            usrnameInput.setError(getString(R.string.invalid_username));
            focusView = usrnameInput;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {

            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);

        }
    }

    private boolean isUsernameValid(String username) {
        //TODO: Replace this with your own logic
        /** UTF-8 (Unicode)
         * \u4e00-\u9fa5: Chinese
         * \u0800-\u4e00: Japanese */
        username = usrnameInput.getText().toString();
        String pattern="([a-zA-Z0-9_\\u4e00-\\u9fa5\\u0800-\\u4e00]+$)";
        if(!username.matches(pattern)){
            Toast.makeText(this,"用戶名不對",Toast.LENGTH_SHORT).show();

            return false;
        }
        return username.matches(pattern);
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        String pattern = "([a-zA-Z0-9].{5,40})";
      //  Log.d("T5", "isMatched: "+password.matches(pattern));

        if(password.length()<6){
            Toast.makeText(this,"密碼長度需在6個字節或以上", Toast.LENGTH_LONG).show();
        }else if(!password.matches(pattern)){
            Toast.makeText(this,"密碼不包含數字或字母以外的符號",Toast.LENGTH_LONG).show();
        }
        return password.matches(pattern);
    }

    /**
     * Shows the progress UI and hides the login form.
     */
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

    private void addEmailsToAutoComplete(List<String> emailAddressCollection) {
        //Create adapter to tell the AutoCompleteTextView what to show in its dropdown list.
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(LoginActivity.this,
                        android.R.layout.simple_dropdown_item_1line, emailAddressCollection);

      //  emailInput.setAdapter(adapter);
    }

    private void addUsernameToAutoComplete(List<String> usernames){
        ArrayAdapter<String> adp=new ArrayAdapter<>
                (LoginActivity.this,android.R.layout.simple_dropdown_item_1line,usernames);
        usrnameInput.setAdapter(adp);
    }

    private void JasonLogin(){
        String[] urls = getResources().getStringArray(R.array.yamibo_api_urls);
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.POST, urls[4], null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject msgObj=response.getJSONObject("Message");
                            JSONObject varObj=response.getJSONObject("Variables");
                            String welcomeBack=msgObj.getString("messagestr");
                            String isLoggedIn=msgObj.getString("messageval");
                            String username=varObj.getString("member_username");

                            if(usrnameInput.getText().toString().equals(username)){
                                //add user details to nav header
                                MainNavTabActivity main= new MainNavTabActivity();
                            }
                            Log.d("T7","Is Username: "+varObj.has("member_username"));

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
        rqstQueue.add(request);
    }
    @Override
    protected void onResume(){
        super.onResume();
        if(sessionMg.isLoggedIn()){
            startActivity(new Intent(this,ChatSec_Activity.class));
            finish();
        }
    }
}