package com.yamibo.bbs.data.Repositories;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;
import android.widget.Toast;

import com.yamibo.bbs.Network.RetrofitApiService;
import com.yamibo.bbs.Network.RetrofitClient;
import com.yamibo.bbs.data.Model.LoginMod.LoginResponseMod;
import com.yamibo.bbs.data.Model.LoginMod.LoginVariables;

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
                LoginResponseMod loginResponseMod=response.body();
                if(response.isSuccessful()&& loginResponseMod!=null){
                    LoginVariables loginDataMod =response.body().getVariables();
                    if(loginDataMod.getAuth()==null){
                        Toast.makeText(_application,"Logged in failed.\n"
                                +response.body().getMessage().getMessagestr(),Toast.LENGTH_LONG).show();
                    }else{
                        Log.d(TAG,"Logged in successful!");
                        Toast.makeText(_application,"Logged in successfully.\n"
                                +response.body(),Toast.LENGTH_LONG).show();
                        loggedInDataList.add(loginDataMod);
                        loginLiveData.setValue(loggedInDataList);
                    }

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
