package com.yamibo.bbs.Network;

import com.yamibo.bbs.data.Model.ForumListMod.ForumListMod;
import com.yamibo.bbs.data.Model.Variables;
import com.yamibo.bbs.data.Model.LoginMod.LoginResponseMod;

import Utils.ApiConstants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

import static Utils.ApiConstants.HEADER_COOKIE_AUTH;

public interface RetrofitApiService {
    final String[] loginCredential = new String[]{"username", "password"};
    final String FORUM_ADMIN_URL="api/mobile/index.php?";

    String accessToken = "";

    @GET(FORUM_ADMIN_URL)
    Call<ForumListMod> getAdminForumData(@Query("version") String version, @Query("module") String module,
                                         @Query("fid") String forumsId, @Query("sort")String sort,
                                         @Query("page") String pageNum);

    @GET(ApiConstants.FORUM_ANIME_MANGA_URL)
    Call<Variables> getAnimeMangaForum(@Query("version") int version, @Query("module") String module,
                                       @Query("fid") String forumsId, @Query("page") String pageNum);

    @GET(ApiConstants.FORUM_CHATTING_URL)
    Call<Variables> getChattingForum(@Query("version") int version, @Query("module") String module,
                                     @Query("fid") String forumsId, @Query("page") String pageNum);

    @GET(ApiConstants.FORUM_VIDEO_GAME_URL)
    Call<Variables> getVideoGameForum(@Header("Authorization") String authorization,
                                      @Query("version") int version, @Query("module") String module,
                                      @Query("fid") String forumsId, @Query("page") String pageNum);

    @GET(ApiConstants.FORUM_LITERATURE_URL)
    Call<Variables> getLiteratrueForum(@Header("Authorization") @Query("version") int version,
                                       @Query("module") String module, @Query("fid") String forumsId,
                                       @Query("page") String pageNum);

    @GET(ApiConstants.FORUM_DAILY_HITS_URL)
    Call<Variables> getDailyHits(@Query("version") int version, @Query("module") String module,
                                 @Query("fid") String forumsId, @Query("page") String pageNum);

    @GET(ApiConstants.FORUM_PICTURES_URL)
    Call<Variables> getPictrueForum(@Header("Authorization") String authorization,
                                    @Query("version") int version, @Query("module") String module,
                                    @Query("fid") String forumsId, @Query("page") String pageNum);

    //Put session token into authCookie
    @Headers("cookie:"+HEADER_COOKIE_AUTH+"="+accessToken)
    @POST(ApiConstants.FORUMS_API_URL)
    Call<Variables> getAllForums(@Query("version") int version, @Query("module") String module,
                                 @Query("fid") String forumsId, @Query("page") String pageNum);

    //Don't need "Authorization" header
    @GET(ApiConstants.USER_PROFILE_API_URL)
    Call<Variables> getUserProfileData(@Query("uid") int userId,
                                       @Query("version") int version, @Query("module") String module);

    @GET(ApiConstants.MY_POSTS_API_URL)
    Call<Variables> getUserPosts(@Query("username") String username, @Query("version") int version,
                                 @Query("module") String module, @Query("page")int pageNum);

    @FormUrlEncoded
    @POST(ApiConstants.LOGIN_REQUEST_API_URL)
    void loginAccount(@Field("username") String username,
                      @Field("password") String password,
                      Callback<LoginResponseMod> loginResult);
}
