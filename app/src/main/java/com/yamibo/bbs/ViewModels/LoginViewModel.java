package com.yamibo.bbs.ViewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.yamibo.bbs.data.Model.LoginMod.LoginResponseMod;
import com.yamibo.bbs.data.Model.LoginMod.LoginVariables;
import com.yamibo.bbs.data.Repositories.LoginRepository;

import java.util.List;

import retrofit2.Call;

public class LoginViewModel extends AndroidViewModel {
    private LoginRepository loginRepo;

    private Call<LoginResponseMod> loginCall;

    public LoginViewModel(@NonNull Application application) {
        super(application);
        loginRepo= new LoginRepository(application);
    }

    public LiveData<List<LoginVariables>> getLoggedInData(String username, String password){
        return loginRepo.getLoggedInData(loginCall,username,password);
    }

}
