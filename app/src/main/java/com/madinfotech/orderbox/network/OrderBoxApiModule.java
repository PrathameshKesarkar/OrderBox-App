package com.madinfotech.orderbox.network;

import com.madinfotech.orderbox.network.http.OrderBoxEndpoint;
import com.madinfotech.orderbox.network.http.RetrofitErrorHandler;
import com.madinfotech.orderbox.ui.util.Constants;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import retrofit.Endpoint;
import retrofit.RestAdapter;

/**
 * Created by prathameshkesarkar on 12/07/16.
 */
@Module
public class OrderBoxApiModule {

    @Provides
    @Named(Constants.Injection.Named.API_USER_PATH)
    public String providePath() {
        return "users";
    }

    @Provides
    public Endpoint provideEndpoint(@Named(Constants.Injection.Named.API_USER_PATH) String path) {
        return new OrderBoxEndpoint(path);
    }

    @Provides
    public RetrofitErrorHandler provideErrorHandler() {
        return new RetrofitErrorHandler();
    }

    @Provides
    public RestAdapter provideRestAdapter(Endpoint endpoint, RetrofitErrorHandler errorHandler) {
        return new RestAdapter.Builder().setEndpoint(endpoint).setErrorHandler(errorHandler).setLogLevel(RestAdapter.LogLevel.FULL).build();
    }
}
