package com.madinfotech.orderbox.network.http;

import retrofit.Endpoint;

/**
 * Created by prathameshkesarkar on 12/07/16.
 */
public class OrderBoxEndpoint implements Endpoint {

    private static final String BASE= "https://madinfo-orderbox-api.herokuapp.com/";

    private String URL;

    public  OrderBoxEndpoint(String path){
            URL = BASE+path;
    }

    @Override
    public String getUrl() {
       if(URL==null)
        throw new IllegalStateException("Path not specified");
        return URL;
    }

    @Override
    public String getName() {
        return "default Order-Box.com endpoint";
    }
}
