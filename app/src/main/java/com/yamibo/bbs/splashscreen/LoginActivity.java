package com.yamibo.bbs.splashscreen;

import android.annotation.TargetApi;
import android.app.LoaderManager;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.github.ybq.android.spinkit.style.FadingCircle;
import com.yamibo.bbs.ViewModels.LoginViewModel;
import com.yamibo.bbs.data.Model.LoginMod.LoginVariables;
import com.yamibo.bbs.splashscreen.databinding.ActivityLoginBinding;

import java.util.ArrayList;
import java.util.List;

import Managers.SessionManager;
import com.yamibo.bbs.data.AppConstants;

import static com.yamibo.bbs.data.AppConstants.PREF_FILE_GLOBAL;
import static com.yamibo.bbs.data.AppConstants.PREF_KEY_PASSWORD;
import static com.yamibo.bbs.data.AppConstants.PREF_KEY_USERNAME;
import static com.yamibo.bbs.data.AppConstants.PREF_KEY_USERNAME_PASSWORD;

public class LoginActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    private static final String LOG_TAG = LoginActivity.class.getSimpleName();

    private static final String PREF_FILE = AppConstants.PREF_FILE_GLOBAL;

    private SessionManager sessionMg;

    private LoginViewModel loginViewModel;
    private ActivityLoginBinding loginBinding;
    private List<LoginVariables> loggedInDataList = new ArrayList<>();

    private ProgressBar progressBar;

    private String username, pswd;
    private SharedPreferences mPreference;
    private List<String> usernameList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);

        mPreference = getSharedPreferences(PREF_FILE, Context.MODE_PRIVATE);

        initContentView();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void initContentView() {
        // Init the login form.
        username = loginBinding.usernameEditText.getText().toString();
        pswd = loginBinding.pswdEditText.getText().toString();

        loginBinding.pswdEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                    return true;
                }
                return false;
            }
        });

        progressBar = findViewById(R.id.login_loader);

        //Session manager and login
        sessionMg = new SessionManager(getApplicationContext(), PREF_FILE_GLOBAL);
        setBtnOnClicks();
        updateContentView();
    }

    private void updateContentView() {
        //Declaration and initialization the usernameEditText and pswdEditText sharePreferences
        String username = mPreference.getString(PREF_KEY_USERNAME, loginBinding.usernameEditText.getText().toString());
        String password = mPreference.getString(PREF_KEY_PASSWORD, loginBinding.pswdEditText.getText().toString());
        int storedUsernamePasssword = mPreference.getInt(PREF_KEY_USERNAME_PASSWORD, -1);

        //Assign the input field value from sharePreferences
        if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)) {
            loginBinding.usernameEditText.setText(username);
            if (storedUsernamePasssword > 0) {
                loginBinding.pswdEditText.setText(password);
            } else {

            }
        }
        if (storedUsernamePasssword > 0) {

        }
        loginBinding.pswdEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (TextUtils.isEmpty(s)) {
                    //show pswdEditText
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(s)) {
                    //hide pswdEditText
                }
                //mPasswordClearButton.setVisibility((TextUtils.isEmpty(mPasswordInput.getText())) ? View.INVISIBLE : View.VISIBLE)
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

    }

    private boolean attemptLogin() {
        // Reset errors.
        loginBinding.usernameEditText.setError(null);
        loginBinding.pswdEditText.setError(null);

        // Store values at the time of the login attempt.
        username = loginBinding.usernameEditText.getText().toString();
        pswd = loginBinding.pswdEditText.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid pswdEditText, if the user entered one.
        if (!TextUtils.isEmpty(pswd) && !isPasswordValid(pswd)) {
            loginBinding.pswdEditText.setError(getString(R.string.error_invalid_password));
            focusView = loginBinding.pswdEditText;
            return cancel = true;

        } else if (TextUtils.isEmpty(pswd)) {
            loginBinding.pswdEditText.setError(getString(R.string.error_empty_password));
            focusView = loginBinding.pswdEditText;
            return cancel = true;
        }

        // Check for a valid usernameEditText.
        if (TextUtils.isEmpty(username)) {
            loginBinding.usernameEditText.setError(getString(R.string.error_field_required));
            focusView = loginBinding.usernameEditText;
            cancel = true;
            return false;
        } else if (!isUsernameValid(username)) {
            loginBinding.usernameEditText.setError(getString(R.string.invalid_username));
            focusView = loginBinding.usernameEditText;
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
            return cancel = false;
        }
        return cancel;
    }

    private boolean isUsernameValid(String username) {
        /** UTF-8 (Unicode)
         * \u4e00-\u9fa5: Chinese
         * \u0800-\u4e00: Japanese */
        String pattern = "([a-zA-Z0-9_\\u4e00-\\u9fa5\\u0800-\\u4e00]+$)";
        if (!username.matches(pattern)) {
            Toast.makeText(this, getResources().getString(R.string.error_incorrect_password), Toast.LENGTH_SHORT).show();
        }
        return username.matches(pattern);
    }

    private void setBtnOnClicks() {
        loginBinding.loginButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                //Check for empty data in the form
                userLogin();
                startActivity(new Intent(LoginActivity.this, MainNavTabActivity.class));
                finish();
                /*progressBar.setVisibility(View.VISIBLE);
                progressBar.setIndeterminateDrawable(new FadingCircle());*/
            }
        });
    }

    private boolean isPasswordValid(String password) {
        String pattern = "([a-zA-Z0-9].{5,40})";
        if (password.length() < 6) {
            Toast.makeText(this, getString(R.string.required_password_length),
                    Toast.LENGTH_LONG).show();
        } else if (!password.matches(pattern)) {
            Toast.makeText(this, getString(R.string.does_not_contain_symbol_for_password),
                    Toast.LENGTH_LONG).show();
        }
        return password.matches(pattern);
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        if (show) {
            progressBar.setIndeterminateDrawable(new FadingCircle());
            progressBar.setVisibility(View.VISIBLE);
        }
        progressBar.setVisibility(View.GONE);
    }

    /*Add Email and usernameEditText to autocompleteTextView*/
    private void addEmailsToAutoComplete(List<String> emailAddressCollection) {
        //Create adapter to tell the AutoCompleteTextView what to show in its dropdown list.
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(LoginActivity.this,
                        android.R.layout.simple_dropdown_item_1line, emailAddressCollection);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("S", "isNull: " + sessionMg);
        if (sessionMg != null && sessionMg.isLoggedIn()) {

            Toast.makeText(getApplicationContext(),
                    "You're logged in", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        progressBar.setVisibility(View.GONE);
        loginBinding.loginButton.setOnClickListener(null);
        loginBinding.forgetPswdBtn.setOnClickListener(null);
        loginBinding.contactBtn.setOnClickListener(null);
        super.onDestroy();
    }


    @Override
    public android.content.Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(this,
                // Retrieve data rows for the device user's 'profile' contact.
                Uri.withAppendedPath(ContactsContract.Profile.CONTENT_URI,
                        ContactsContract.Contacts.Data.CONTENT_DIRECTORY),
                ProfileQuery.PROJECTION,
                // Select only email addresses.
                ContactsContract.Contacts.Data.MIMETYPE + " = ?",
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
        //addUsernameToAutoComplete(usernameList);
        Log.d("LIST", "UsernameList: " + usernameList.size());
    }

    @Override
    public void onLoaderReset(android.content.Loader<Cursor> loader) {
    }

    private void userLogin() {
        loginViewModel.getLoggedInData(username, pswd).observe(this, new Observer<List<LoginVariables>>() {
            @Override
            public void onChanged(@Nullable List<LoginVariables> loginVariables) {
                if (attemptLogin() == false) {//cancel = false
                    loggedInDataList.addAll(loginVariables);

                }
            }
        });
    }

    private interface ProfileQuery {
        String[] PROJECTION = {
                ContactsContract.CommonDataKinds.Email.ADDRESS,
                ContactsContract.CommonDataKinds.Email.IS_PRIMARY,
                ContactsContract.CommonDataKinds.Nickname.DISPLAY_NAME,
        };
        int ADDRESS = 0;
        int IS_PRIMARY = 1;
        int DISPLAY_NAME = 2;
    }
}



