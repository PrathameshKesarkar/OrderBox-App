package com.madinfotech.orderbox.ui.screens.component;

import com.madinfotech.orderbox.network.OrderBoxApiModule;
import com.madinfotech.orderbox.ui.screens.ActivityScope;
import com.madinfotech.orderbox.ui.screens.LoginActivity;
import com.madinfotech.orderbox.ui.screens.module.LoginActivityModule;

import dagger.Subcomponent;

/**
 * Created by PrathamK on 7/9/2016.
 */
@ActivityScope
@Subcomponent(
        modules = {
                LoginActivityModule.class,
                OrderBoxApiModule.class
        }
)
public interface LoginActivityComponent {
    LoginActivity inject(LoginActivity target);

}
