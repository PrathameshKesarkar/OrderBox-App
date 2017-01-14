package com.madinfotech.orderbox.network.http;

import com.madinfotech.orderbox.network.data.ResponseUser;
import com.madinfotech.orderbox.network.data.User;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.POST;

/**
 * Created by prathameshkesarkar on 12/07/16.
 */
public interface OrderBoxApiService {

    @POST("/login")
    void login(@Body User user, Callback<ResponseUser> responseUserCallback);
}
