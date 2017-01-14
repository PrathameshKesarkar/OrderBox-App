package com.madinfotech.orderbox;

import com.madinfotech.orderbox.ui.screens.AddTaskActivity;
import com.madinfotech.orderbox.ui.screens.component.AddTaskActivityComponent;
import com.madinfotech.orderbox.ui.screens.component.DashBoardActivityComponent;
import com.madinfotech.orderbox.ui.screens.component.LoginActivityComponent;
import com.madinfotech.orderbox.ui.screens.module.AddTaskActivityModule;
import com.madinfotech.orderbox.ui.screens.module.DashBoardModule;
import com.madinfotech.orderbox.ui.screens.module.DashFragmentModule;
import com.madinfotech.orderbox.ui.screens.module.LoginActivityModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by PrathamK on 7/9/2016.
 */
@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    LoginActivityComponent plus(LoginActivityModule profileModule);
    AddTaskActivityComponent plus(AddTaskActivityModule addTaskModule);
    DashBoardActivityComponent plus(DashBoardModule module);
}
