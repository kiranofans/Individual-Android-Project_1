package com.yamibo.bbs.data;

public interface DataManager {

    void updateApiHeader(String userId, String accessToken);

    void setUserLoggedOut();

    void updateUserInfo(
            String accessToken,
            String userId,
            String userName,
            String email,
            String profilePicPath);
}
