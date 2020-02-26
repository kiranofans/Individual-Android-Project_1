package com.yamibo.bbs.ViewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import java.util.List;

import com.yamibo.bbs.data.Model.ForumListMod.ForumsListMod;
import com.yamibo.bbs.data.Model.ForumsContentMod.ForumThreadMod;
import com.yamibo.bbs.data.Repositories.ThreadsRepository;

import retrofit2.Call;

public class ForumContentViewModel extends AndroidViewModel {
    private final String TAG = ForumContentViewModel.class.getSimpleName();

    private ThreadsRepository threadRepo;
    private MutableLiveData<List<ForumThreadMod>> forumLiveData = new MutableLiveData<>();

    //Forums Variables class
    private Call<ForumsListMod> forumListCall;

    public ForumContentViewModel(@NonNull Application application) {
        super(application);
        threadRepo = new ThreadsRepository(application);

    }
    public void getForumThreads(String forumsId, int threadPageNum){
        threadRepo.getThreadLiveData(forumListCall,threadPageNum,forumsId,callback->forumLiveData.setValue(callback));
    }
    public MutableLiveData<List<ForumThreadMod>> getForumLiveData(){
        return forumLiveData;
    }
}
