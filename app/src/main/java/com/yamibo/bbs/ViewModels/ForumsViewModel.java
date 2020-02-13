package com.yamibo.bbs.ViewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import com.yamibo.bbs.data.Model.ForumListMod.ForumListMod;
import com.yamibo.bbs.data.Model.ForumsContentMod.ForumThreadMod;
import com.yamibo.bbs.data.Repositories.AdminThreadRepository;

import retrofit2.Call;

public class ForumsViewModel extends AndroidViewModel {
    private AdminThreadRepository threadRepo;

    //Forums Variables class
    private Call<ForumListMod> forumListCall;

    public ForumsViewModel(@NonNull Application application) {
        super(application);
        threadRepo = new AdminThreadRepository(application);

    }
    public LiveData<List<ForumThreadMod>> getForumThreads(String forumsId, int threadPageNum){
        return threadRepo.getThreadLiveData(forumListCall,threadPageNum,forumsId);
    }
}
