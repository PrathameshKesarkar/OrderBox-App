package com.madinfotech.orderbox.network.http;

import com.madinfotech.orderbox.network.data.ResponseUser;

import retrofit.RetrofitError;

/**
 * Created by prathameshkesarkar on 12/07/16.
 */
public interface OrderBoxListener {
    void onLoginSuccessful(ResponseUser user);
    void onLoginFailed(String msg);
}
