package com.madinfotech.orderbox.network.http;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import retrofit.ErrorHandler;
import retrofit.RetrofitError;

/**
 * Created by prathameshkesarkar on 13/07/16.
 */
public class RetrofitErrorHandler implements ErrorHandler {
    @Override
    public Throwable handleError(RetrofitError cause) {
            if(cause.getResponse()!=null&&cause.getResponse().getStatus()==401){
               return new Exception("Invalid credentials. Please verify login info.");
            }
            else if (cause.getCause() instanceof SocketTimeoutException){
                return new SocketTimeoutException("Connection Timeout:"+"Please verify your internet connection.");

            }
            else{
                return new ConnectException("No Connection. " +
                        "Please verify your internet connection.");
            }

    }
}
