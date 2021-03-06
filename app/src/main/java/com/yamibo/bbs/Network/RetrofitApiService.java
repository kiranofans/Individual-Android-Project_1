package com.yamibo.bbs.Network;

import com.yamibo.bbs.data.Model.ForumListMod.ForumsListMod;
import com.yamibo.bbs.data.Model.Variables;
import com.yamibo.bbs.data.Model.LoginMod.LoginResponseMod;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

import static com.yamibo.bbs.Network.ApiConstants.HEADER_COOKIE_AUTH;

public interface RetrofitApiService {
    final String[] loginCredential = new String[]{"usernameEditText", "pswdEditText"};
    final String FORUM_ADMIN_URL="api/mobile/index.php?";

    String accessToken = "";

    @GET(FORUM_ADMIN_URL)
    Call<ForumsListMod> getThreadsData(@Query("version") String version, @Query("module") String module,
                                       @Query("fid") String forumsId, @Query("sort")String sort,
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
    Call<ForumsListMod> getAllForums(@Query("version") String version, @Query("module") String module);

    //Don't need "Authorization" header
    @GET(ApiConstants.USER_PROFILE_API_URL)
    Call<Variables> getUserProfileData(@Query("uid") int userId,
                                       @Query("version") int version, @Query("module") String module);

    @GET(ApiConstants.MY_POSTS_API_URL)
    Call<Variables> getUserPosts(@Query("usernameEditText") String username, @Query("version") int version,
                                 @Query("module") String module, @Query("page")int pageNum);

    @Headers({"Content-Transfer-Encoding:charset=gbk","Accept-Charset:gbk"})
    @FormUrlEncoded//module=login&loginsubmit=yes
    @POST(ApiConstants.LOGIN_REQUEST_API_URL)
    Call<LoginResponseMod> getUserLogin(@Field("username") String username, @Field("password") String password,
                                        @Query("module") String module,@Query("loginsubmit")String loginSubmit,
                                        @Query("version")String version);
}
