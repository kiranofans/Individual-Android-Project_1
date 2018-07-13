package Model;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface Api_Retrofit {
    String BASE_URL="https://bbs.yamibo.com";

    @Headers("Accept: application/json")
    @GET("/api/mobile/index.php")
    Call<List<PostListItems>> getLists(@Query("version") String version,
                                       @Query("module") String module,
                                       @Query("fid")String fid,
                                       @Query("page")String pageNum);
}
