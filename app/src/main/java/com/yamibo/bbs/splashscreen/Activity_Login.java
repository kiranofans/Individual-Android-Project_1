package com.yamibo.bbs.splashscreen;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.NetworkOnMainThreadException;
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
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import Model.Api_Retrofit;
import Model.Login;
import Model.Users;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.POST;

import static android.support.design.widget.Snackbar.make;

public class Activity_Login extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{
    /** Id to identity READ_CONTACTS permission request.*/
    //private UserLoginTask authTask=null;
    private static final int REQUEST_READ_CONTACTS = 0;
    private SQLiteHandler dbHandler;
    private SessionManager sessionMg;
    private String TEST = Registration.class.getSimpleName();
    /** A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.*/
    private static String[] CREDENTIALS;
    private AutoCompleteTextView usrnameInput;
    private EditText pswdInput;
    private View progressView;
    private static ImageButton avatarBtn;
    private static TextView usrnameTv;
    private static View loginForm;
    private static String username, avatarUrl, pswd, getUid;
    private Button forgotPswd, contactUs, logOutBtn, loginBtn;
    private static Users users;
    private static boolean isSignedIn = false;
    private static String pageLink = "https://bbs.yamibo.com/forum.php";
    private List<String> usernameList;
    private String[] urls;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        usrnameInput = (AutoCompleteTextView) findViewById(R.id.username);
        contactUs = (Button) findViewById(R.id.contactBtn);
        forgotPswd = (Button) findViewById(R.id.forgetPswdBtn);
        pswdInput = (EditText) findViewById(R.id.password);
        username = usrnameInput.getText().toString();
        pswd=pswdInput.getText().toString();
        CREDENTIALS =new String[]{usrnameInput.getText().toString()+":"+pswdInput.getText().toString(),
                usrnameInput.getText().toString()+":"+pswdInput.getText().toString()};

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

        //rqstQueue=Volley.newRequestQueue(getApplicationContext());
        loginForm = findViewById(R.id.login_form);
        progressView = findViewById(R.id.login_progress);

        //SQLite db handler
        dbHandler = new SQLiteHandler(getApplicationContext());

        //Session manager
        loginBtn = (Button) findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                //Check for empty data in the form
                userLogin();
            }
        });

        if (SessionManager.getInstance(getApplicationContext()).isLoggedIn()) {
            Toast.makeText(getApplicationContext(), "Logged In!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, MainNavTabActivity.class));
            finish();
        }
    }
    /** Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.*/
    private void attemptLogin() {

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
            cancel = true;
        } else if (TextUtils.isEmpty(pswd)) {
            pswdInput.setError("密碼不能為空");
            focusView = pswdInput;
            cancel = true;
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
            userLogin();
            //authTask.execute((Void)null);
        }
    }
    private boolean isUsernameValid(String username) {
        //TODO: Replace this with your own logic
        /** UTF-8 (Unicode)
         * \u4e00-\u9fa5: Chinese
         * \u0800-\u4e00: Japanese */
        String pattern = "([a-zA-Z0-9_\\u4e00-\\u9fa5\\u0800-\\u4e00]+$)";
        if (!username.matches(pattern)) {
            Toast.makeText(this, "用戶名不對", Toast.LENGTH_SHORT).show();
        }
        return username.matches(pattern);
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
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
    private void userLogin() {
        username=usrnameInput.getText().toString();
        pswd=pswdInput.getText().toString();
        final Retrofit retro=new Retrofit.Builder()
                .baseUrl(Api_Retrofit.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();

        final Api_Retrofit api=retro.create(Api_Retrofit.class);

        /**JsonObject NOT JSONObject*/
        final Call<JsonObject> loginCall=api.loginAuth(username,pswd);
        loginCall.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, retrofit2.Response<JsonObject> response) {
                JsonObject loginObj=response.body().getAsJsonObject();

                JsonPrimitive responseMsg=null; JsonPrimitive responseMsgtr=null;
                JsonPrimitive ver=loginObj.getAsJsonPrimitive("Version");
                JsonObject var=loginObj.getAsJsonObject("Variables");
                if(ver!=null){
                    JsonObject msg=loginObj.getAsJsonObject("Message");
                    responseMsg=msg.getAsJsonPrimitive("messageval");
                    responseMsgtr=msg.getAsJsonPrimitive("messagestr");
                    Toast.makeText(getApplicationContext(),
                            responseMsgtr+"",Toast.LENGTH_LONG).show();
                    if(msg!=null){
                        Toast.makeText(getApplicationContext(),
                                responseMsgtr+"",Toast.LENGTH_LONG).show();
                        startActivity(new Intent
                                (Activity_Login.this,MainNavTabActivity.class));
                        finish();
                    }else{
                        Toast.makeText(getApplicationContext(),
                                ""+responseMsgtr,Toast.LENGTH_LONG).show();
                        finish();
                    }

                }else{
                    Toast.makeText(getApplicationContext(),
                            "Response Error"+loginObj.getAsJsonObject("Message"),Toast.LENGTH_LONG).show();
                    finish();
                }
                Log.d("R","RETURN: "+loginObj);
            }
            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                call.cancel();
                Toast.makeText(Activity_Login.this,t.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });
    }
    private void OkHTTP(){
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



