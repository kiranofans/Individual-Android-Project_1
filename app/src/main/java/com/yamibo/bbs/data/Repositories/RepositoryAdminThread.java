package com.yamibo.bbs.data.Repositories;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import com.yamibo.bbs.Network.RetrofitApiService;
import com.yamibo.bbs.Network.RetrofitClient;
import com.yamibo.bbs.data.Model.ForumListMod.ForumListMod;
import com.yamibo.bbs.data.Model.ForumsContentMod.ForumThreadMod;
import com.yamibo.bbs.data.Model.Variables;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RepositoryAdminThread {

    private List<ForumThreadMod> threadList = new ArrayList<>();

    private MutableLiveData<List<ForumThreadMod>> threadLiveData =
            new MutableLiveData<>();
    private Application _application;

   /* enum class ForumId{
        ADMIN,ANIME_DISCUSS,CHAT,VIDEO_GAMES,
    }*/

    public RepositoryAdminThread(Application application){
        _application=application;
    }
    //Api call
    public MutableLiveData<List<ForumThreadMod>> getThreadLiveData(Call<ForumListMod> forumCall, int pageNum, String forumsId){
        RetrofitApiService apiService = RetrofitClient.getRetrofitService();
        forumCall= apiService.getAdminForumData("4","forumdisplay",forumsId,pageNum+"");

        forumCall.enqueue(new Callback<ForumListMod>() {
            @Override
            public void onResponse(Call<ForumListMod> call, Response<ForumListMod> response) {
                ForumListMod forumListMod = response.body();
                Variables variablesObj = forumListMod.getVariables();
                if(variablesObj!=null){
                    threadList=variablesObj.getForumThreadlist();
                   // Utility.getFixedTopThreads(threadList, variablesResponse.getT, threadList);

                    threadLiveData.setValue(threadList);

                }
            }

            @Override
            public void onFailure(Call<ForumListMod> call, Throwable t) {
                Toast.makeText(_application,"Failed to call admin API. "
                                +t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
        return threadLiveData;
    }
}
