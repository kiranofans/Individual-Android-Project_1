package com.yamibo.bbs.data;

import Utils.Login.LoggedInMode;

public interface DataManager {

    void updateApiHeader(String userId, String accessToken);

    void setUserLoggedOut();

    void updateUserInfo(
            String accessToken,
            String userId,
            LoggedInMode loggedInMode,
            String userName,
            String email,
            String profilePicPath);
}
