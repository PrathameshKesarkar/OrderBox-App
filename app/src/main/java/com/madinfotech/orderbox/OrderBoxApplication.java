package com.madinfotech.orderbox;

import android.app.Application;

import com.madinfotech.orderbox.ui.screens.component.AddTaskActivityComponent;
import com.madinfotech.orderbox.ui.screens.component.DashBoardActivityComponent;
import com.madinfotech.orderbox.ui.screens.component.DashBoardFragmentComponent;
import com.madinfotech.orderbox.ui.screens.component.LoginActivityComponent;
import com.madinfotech.orderbox.ui.screens.module.AddTaskActivityModule;
import com.madinfotech.orderbox.ui.screens.module.DashBoardModule;
import com.madinfotech.orderbox.ui.screens.module.DashFragmentModule;
import com.madinfotech.orderbox.ui.screens.module.LoginActivityModule;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmModel;

/**
 * Created by PrathamK on 7/9/2016.
 */
public class OrderBoxApplication extends Application {


    AppComponent appComponent;
    LoginActivityComponent loginComponent;
    AddTaskActivityComponent addTaskComponent;
    DashBoardActivityComponent dashBoardActivityComponent;
    DashBoardFragmentComponent dashBoardFragmentComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(this)
                .name("orderbox.realm")
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
        initAppComponent();
    }

    public void initAppComponent() {
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    public LoginActivityComponent createLoginComponent() {
        loginComponent = appComponent.plus(new LoginActivityModule());
        return loginComponent;
    }

    public AddTaskActivityComponent createAddTaskComponent() {
        addTaskComponent = appComponent.plus(new AddTaskActivityModule());

        return addTaskComponent;
    }

    public DashBoardActivityComponent createDashComponent() {
        dashBoardActivityComponent = appComponent.plus(new DashBoardModule());
        return dashBoardActivityComponent;
    }

    public DashBoardFragmentComponent createDashFragmentComponen() {
        dashBoardFragmentComponent = dashBoardActivityComponent.plus(new DashFragmentModule());
        return dashBoardFragmentComponent;
    }

    public  void  releaseDashActivityComponent(){
        dashBoardActivityComponent = null;
    }
    public  void  releaseDashFragmentComponent(){
        dashBoardFragmentComponent=null;
    }

    public void releaseLoginComponent() {
        loginComponent = null;
    }

    public void releaseAddTaskComponent() {
        addTaskComponent = null;
    }
}
