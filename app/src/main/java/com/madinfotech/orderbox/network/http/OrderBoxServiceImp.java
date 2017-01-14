package com.madinfotech.orderbox.network.http;

import com.madinfotech.orderbox.network.data.ResponseUser;
import com.madinfotech.orderbox.network.data.User;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Header;
import retrofit.client.Response;

/**
 * Created by prathameshkesarkar on 13/07/16.
 */
public class OrderBoxServiceImp implements OrderBoxService {
    RestAdapter restAdapter;
    String errorMsg;

    public OrderBoxServiceImp(RestAdapter restAdapter) {
        this.restAdapter = restAdapter;
    }

    @Override
    public void performLogin(String email, String password, final OrderBoxListener listener) {
        final User user = new User(email, password);
        restAdapter.create(OrderBoxApiService.class).login(user, new Callback<ResponseUser>() {
            @Override
            public void success(ResponseUser responseUser, Response response) {

                    List<Header> headerslist = response.getHeaders();
                    for (Header header : headerslist) {
                        if (header.getName() != null && header.getName().equals("Auth")) {
                            responseUser.setHeader(header.getValue());
                        }
                    }
                    listener.onLoginSuccessful(responseUser);
            }

            @Override
            public void failure(RetrofitError error) {
                errorMsg = error.getMessage();
                listener.onLoginFailed(errorMsg);

            }
        });

    }
}
