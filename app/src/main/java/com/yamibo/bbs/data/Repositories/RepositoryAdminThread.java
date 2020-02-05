package com.yamibo.bbs.data.Repositories;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import com.yamibo.bbs.Network.RetrofitApiService;
import com.yamibo.bbs.Network.RetrofitClient;
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
    public MutableLiveData<List<ForumThreadMod>> getThreadLiveData(Call<Variables> forumCall, int pageNum){
        RetrofitApiService apiService = RetrofitClient.getRetrofitService();
        forumCall= apiService.getAdminForum("4","forumdisplay","16",pageNum+"");

        forumCall.enqueue(new Callback<Variables>() {
            @Override
            public void onResponse(Call<Variables> call, Response<Variables> response) {
                Variables variablesResponse = response.body();
                if(variablesResponse!=null && variablesResponse.getForumThreadlist()!=null){
                    threadList=variablesResponse.getForumThreadlist();
                   // Utility.getFixedTopThreads(threadList, variablesResponse.getT, threadList);

                    threadLiveData.setValue(threadList);

                }
            }

            @Override
            public void onFailure(Call<Variables> call, Throwable t) {
                Toast.makeText(_application,"Failed to call admin API. "
                                +t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
        return threadLiveData;
    }
}
