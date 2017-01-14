package com.madinfotech.orderbox.network.http;

/**
 * Created by prathameshkesarkar on 12/07/16.
 */
public interface OrderBoxService {
    void performLogin(String email,String password,OrderBoxListener listener);
}
