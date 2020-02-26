package com.yamibo.bbs.ViewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.yamibo.bbs.data.Model.ForumListMod.ForumsListInfoMod;
import com.yamibo.bbs.data.Repositories.ForumsListRepository;

public class ForumsViewModel extends AndroidViewModel {
    private final String TAG=ForumsViewModel.class.getSimpleName();

    private ForumsListRepository repo;

    private MutableLiveData<ForumsListInfoMod> forumsListLiveData;

    public ForumsViewModel(@NonNull Application application) {
        super(application);
        repo=new ForumsListRepository(application);
    }
}
