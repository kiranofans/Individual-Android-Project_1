package Model;

import com.yamibo.bbs.splashscreen.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
/**interface for API with HTTP links */
public interface ApiInterface {

    String ROOT_URL="https://bbs.yamibo.com/api/";
    @GET("mobile")
    Call<List<PostsListItems>> getPostLists();
    /**Return type is Call*/
}
