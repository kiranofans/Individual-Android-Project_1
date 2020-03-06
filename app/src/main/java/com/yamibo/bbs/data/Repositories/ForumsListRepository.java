package com.yamibo.bbs.data.Repositories;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.yamibo.bbs.Network.RetrofitApiService;
import com.yamibo.bbs.Network.RetrofitClient;
import com.yamibo.bbs.ViewModels.ForumContentViewModel;
import com.yamibo.bbs.data.Model.ForumListMod.CatlistMod;
import com.yamibo.bbs.data.Model.ForumListMod.ForumsListInfoMod;
import com.yamibo.bbs.data.Model.ForumListMod.ForumsListMod;
import com.yamibo.bbs.data.Model.ForumListMod.ForumsVariables;
import com.yamibo.bbs.data.Model.Variables;
import com.yamibo.bbs.data.OnDataReceivedCallback;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForumsListRepository {
    private final String TAG = ForumContentViewModel.class.getSimpleName();

    private Application _application;
    private MutableLiveData<List<ForumsListInfoMod>> forumsListLiveData = new MutableLiveData<>();

    public ForumsListRepository(Application application){
        _application=application;
    }

    public MutableLiveData<List<ForumsListInfoMod>> getForumsListLiveData(Call<ForumsListMod> forumsListCall,
                                                                   int pageNum,
                                                                   OnDataReceivedCallback dataCallback){
        RetrofitApiService apiService = RetrofitClient.getRetrofitService();
        forumsListCall=apiService.getAllForums("4","forumindex");
        forumsListCall.enqueue(new Callback<ForumsListMod>() {
            @Override
            public void onResponse(Call<ForumsListMod> call, Response<ForumsListMod> response) {
                ForumsListMod forumsListMod = response.body();

                ForumsVariables forumsVar = forumsListMod.getForumsVariables();
               List<ForumsListInfoMod> listInfoMod=forumsVar.getForumlist();

                if(forumsVar!=null){
                    Log.d(TAG,"onResponse");
                    dataCallback.onForumsListDataReceived(listInfoMod);
                }
            }

            @Override
            public void onFailure(Call<ForumsListMod> call, Throwable t) {

            }
        });
        return forumsListLiveData;
    }
}
