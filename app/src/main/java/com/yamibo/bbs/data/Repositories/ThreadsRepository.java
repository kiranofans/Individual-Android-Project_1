package com.yamibo.bbs.data.Repositories;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import com.yamibo.bbs.Network.RetrofitApiService;
import com.yamibo.bbs.Network.RetrofitClient;
import com.yamibo.bbs.data.Model.ForumListMod.ForumsListMod;
import com.yamibo.bbs.data.Model.ForumsContentMod.ForumThreadMod;
import com.yamibo.bbs.data.Model.Variables;
import com.yamibo.bbs.data.OnDataReceivedCallback;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ThreadsRepository {
    private final String TAG = ThreadsRepository.class.getSimpleName();

   // private List<ForumThreadMod> threadList = new ArrayList<>();
    private MutableLiveData<List<ForumThreadMod>> threadLiveData =
            new MutableLiveData<>();
    private Application _application;

    public ThreadsRepository(Application application){
        _application=application;
    }
    //Api call
    public MutableLiveData<List<ForumThreadMod>> getThreadLiveData(Call<ForumsListMod> forumCall,
                                                                   int pageNum, String forumsId,
                                                                   OnDataReceivedCallback callback){
        RetrofitApiService apiService = RetrofitClient.getRetrofitService();
        forumCall= apiService.getThreadsData("4","forumdisplay",forumsId,
                "displayorder",pageNum+"");

        forumCall.enqueue(new Callback<ForumsListMod>() {
            @Override
            public void onResponse(Call<ForumsListMod> call, Response<ForumsListMod> response) {
                ForumsListMod forumListMod = response.body();
                Variables variablesObj = forumListMod.getVariables();
                if(variablesObj!=null){
                    //threadList=variablesObj.getForumThreadlist();
                    //threadLiveData.setValue(threadList);
                    Log.d(TAG,"onResponse: ");
                    callback.onForumDataReceived(variablesObj.getForumThreadlist());
                }
            }

            @Override
            public void onFailure(Call<ForumsListMod> call, Throwable t) {
                Toast.makeText(_application,"Failed to call admin API. "
                                +t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
        return threadLiveData;
    }
}
