package com.yamibo.bbs.data.Repositories;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;
import android.widget.Toast;

import com.yamibo.bbs.Network.RetrofitApiService;
import com.yamibo.bbs.Network.RetrofitClient;
import com.yamibo.bbs.data.Model.LoginMod.LoginResponseMod;
import com.yamibo.bbs.data.Model.LoginMod.LoginVariables;
import com.yamibo.bbs.data.Model.Notice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginRepository {
    private String TAG = LoginRepository.class.getSimpleName();
    private List<LoginVariables> loggedInDataList = new ArrayList<>();

    private MutableLiveData<List<LoginVariables>> loginLiveData = new MutableLiveData<>();
    private Application _application;

    public LoginRepository(Application application) {
        this._application = application;
    }

    public MutableLiveData<List<LoginVariables>> getLoggedInData(Call<LoginResponseMod> loginCall,
                                                                 String username, String password) {
        RetrofitApiService apiService = RetrofitClient.getRetrofitService();
        loginCall = apiService.getUserLogin(username, password,"login","yes","4");

        loginCall.enqueue(new Callback<LoginResponseMod>() {
            @Override
            public void onResponse(Call<LoginResponseMod> call, Response<LoginResponseMod> response) {
                if(response.isSuccessful()&&response.body()!=null){
                    Log.d(TAG,"Logged in successful!");
                    LoginVariables loginDataMod =response.body().getVariables();
                    loggedInDataList.add(loginDataMod);
                    loginLiveData.setValue(loggedInDataList);
                }
            }

            @Override
            public void onFailure(Call<LoginResponseMod> call, Throwable t) {
                Toast.makeText(_application,"Failed to call login response. "
                        +t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
        return loginLiveData;
    }
}
