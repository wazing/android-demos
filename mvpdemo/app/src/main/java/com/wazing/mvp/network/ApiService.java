package com.wazing.mvp.network;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {

    @GET("users/{username}")
    Observable<ResponseBody> getUserInfo(@Path("username") String username);

}
