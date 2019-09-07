package data;

import android.content.Context;

import javax.inject.Inject;

import Annotations.ApplicationContext;
import Utils.Login.LoggedInMode;
import VolleyService.VolleyHelper;
import data.prefs.PrefsHelper;

public class BaseDataManager implements DataManager {

    private final Context context;
    private final PrefsHelper prefHelper;
   // private final VolleyHelper volleyHelper;

    @Inject
    public BaseDataManager(@ApplicationContext Context context,
                           PrefsHelper helper) {
        this.context = context;
        this.prefHelper = helper;
    }

    @Override
    public void updateApiHeader(String userId, String accessToken) {

    }

    @Override
    public void setUserLoggedOut() {

    }

    @Override
    public void updateUserInfo(String accessToken, String userId, LoggedInMode loggedInMode,
                               String userName, String email, String profilePicPath) {
        prefHelper.setAccessToken(accessToken);
        prefHelper.setUserId(userId);
        prefHelper.setUserLoggedIn(loggedInMode);
        prefHelper.setUserName(userName);
        prefHelper.setUserEmail(email);
        prefHelper.setUserProfilePicUrl(profilePicPath);
    }
}
