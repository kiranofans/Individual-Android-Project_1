package Model;

import android.content.Context;

import com.google.gson.JsonObject;
import com.yamibo.bbs.splashscreen.Activity_Login;

import org.json.JSONObject;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface Api_Retrofit {
    String BASE_URL="https://bbs.yamibo.com/";

    @Headers({"Accept-Charset: gbk","Content-Transfer-Encoding: charset=gbk",
            "Content-Type: application/x-www-form-urlencoded"})

    @POST("api/mobile/index.php?module=login&amp;loginsubmit=yes HTTP/1.1")
    @FormUrlEncoded
    Call<JsonObject> loginAuth(@Field("username")String username,
                                     @Field("password")String password);
    /**JsonObject NOT JSONObject*/

    /** @Path：所有在网址中的参数（URL的问号前面），如：
    http://102.10.10.132/api/Accounts/{accountId}
     @Query：URL问号后面的参数，如：
     http://102.10.10.132/api/Comments?access_token={access_token}
     @QueryMap：相当于多个@Query
     @Field：用于POST请求，提交单个数据
     @Body：相当于多个@Field，以对象的形式提交

     JSON Primitives: String, boolean, int(Integer), null
     * */
  //  https://bbs.yamibo.com/api/mobile/index.php?module=login&loginsubmit=yes
}

