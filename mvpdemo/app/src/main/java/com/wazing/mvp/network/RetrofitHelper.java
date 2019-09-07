package com.wazing.mvp.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public final class RetrofitHelper {

    private static volatile RetrofitHelper INSTANCE = null;

    private final Retrofit mRetrofit;

    private RetrofitHelper() {
        final Gson gson = new GsonBuilder().setPrettyPrinting().create();
        mRetrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public ApiService getApiService() {
        return getInstance().mRetrofit.create(ApiService.class);
    }

    public static RetrofitHelper getInstance() {
        if (INSTANCE == null) {
            synchronized (RetrofitHelper.class) {
                if (INSTANCE == null) {
                    INSTANCE = new RetrofitHelper();
                }
            }
        }
        return INSTANCE;
    }


}
