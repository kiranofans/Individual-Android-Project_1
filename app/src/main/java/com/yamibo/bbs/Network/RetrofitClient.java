package com.yamibo.bbs.Network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.yamibo.bbs.Network.ApiConstants.BASE_URL;

public class RetrofitClient {
    private static Retrofit retrofit = null;

    public static synchronized RetrofitApiService getRetrofitService(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit.create(RetrofitApiService.class);
    }
}
