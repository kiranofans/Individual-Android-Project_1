package com.yamibo.bbs.ViewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.yamibo.bbs.data.Model.ForumListMod.ForumsListInfoMod;
import com.yamibo.bbs.data.Model.ForumListMod.ForumsListMod;
import com.yamibo.bbs.data.Repositories.ForumsListRepository;

import java.util.List;

import retrofit2.Call;

public class ForumsViewModel extends AndroidViewModel {
    private final String TAG=ForumsViewModel.class.getSimpleName();

    private ForumsListRepository repo;
    private MutableLiveData<List<ForumsListInfoMod>> forumsListLiveData=new MutableLiveData<>();

    private Call<ForumsListMod> forumsListCall;

    public ForumsViewModel(@NonNull Application application) {
        super(application);
        repo=new ForumsListRepository(application);
    }
    public void getForumsList(){//for pagination
        repo.getForumsListLiveData(forumsListCall,forumsCallBack->forumsListLiveData.setValue(forumsCallBack));
    }
    public MutableLiveData<List<ForumsListInfoMod>> getForumsListLiveData(){
        return forumsListLiveData;
    }
}
