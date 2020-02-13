package com.yamibo.bbs.data.prefs;

import com.yamibo.bbs.data.Model.LoginMod.LoginResponseMod;

public interface PrefsHelper {

    int getUserLoggedInMode();
    void setUserLoggedIn(LoginResponseMod mode);

    String getUserId();

    void setUserId(String userId);

    String getUserName();

    void setUserName(String userName);

    String getUserEmail();

    void setUserEmail(String email);

    String getUserProfilePicUrl();

    void setUserProfilePicUrl(String profilePicUrl);

    String getAccessToken();

    void setAccessToken(String accessToken);

    String getUserMobile();

    void setUserMobile(String mobileNumber);

    boolean isCoachMarkView();

    void setCoachMarkView(boolean coachMark);

    boolean isFirstTime();

    void setFirstTime(boolean firstTime);

    void logoutUser();
}
