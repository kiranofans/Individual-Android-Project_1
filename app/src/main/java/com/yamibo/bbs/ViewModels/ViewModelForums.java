package com.yamibo.bbs.ViewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import com.yamibo.bbs.data.Model.ForumListMod.ForumListInfo;
import com.yamibo.bbs.data.Model.ForumListMod.ForumListMod;
import com.yamibo.bbs.data.Model.ForumsContentMod.ForumThreadMod;
import com.yamibo.bbs.data.Model.Variables;
import com.yamibo.bbs.data.Repositories.RepositoryAdminThread;

import retrofit2.Call;

public class ViewModelForums extends AndroidViewModel {
    private RepositoryAdminThread threadRepo;
    //private RepositoryForums forumsRepo;

    //Forums Variables class
    private Call<ForumListMod> forumListCall;

    public ViewModelForums(@NonNull Application application) {
        super(application);

        threadRepo = new RepositoryAdminThread(application);

    }
    public LiveData<List<ForumThreadMod>> getForumThreads(String forumsId, int threadPageNum){
        return threadRepo.getThreadLiveData(forumListCall,threadPageNum,forumsId);
    }

   /* public LiveData<List<ForumListInfo>> getForums(){
        return forumsRepo.getForums();
    }*/
}
