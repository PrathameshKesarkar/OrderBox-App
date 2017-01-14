package com.madinfotech.orderbox;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;

import com.madinfotech.orderbox.ui.util.Constants;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by PrathamK on 7/9/2016.
 */
@Module
public class AppModule {

    Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return application.getApplicationContext();
    }

    @Provides
    @Named(Constants.Injection.Named.LOGIN_HEADER)
    public String provideHeader(){
        return "Login_Header";
    }

    @Provides
    @Singleton
    public SharedPreferences provideSharedPreference(Context context, @Named(Constants.Injection.Named.LOGIN_HEADER) String header_key) {
        return context.getSharedPreferences(header_key,Context.MODE_PRIVATE);
    }

}
