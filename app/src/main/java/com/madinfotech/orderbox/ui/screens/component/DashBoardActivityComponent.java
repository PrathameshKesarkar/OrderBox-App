package com.madinfotech.orderbox.ui.screens.component;

import com.madinfotech.orderbox.ui.screens.ActivityScope;
import com.madinfotech.orderbox.ui.screens.DashboardActivity;
import com.madinfotech.orderbox.ui.screens.module.DashBoardModule;
import com.madinfotech.orderbox.ui.screens.module.DashFragmentModule;

import dagger.Subcomponent;

/**
 * Created by prathameshkesarkar on 24/07/16.
 */
@ActivityScope
@Subcomponent(modules = {DashBoardModule.class})
public interface DashBoardActivityComponent {

    DashboardActivity inject(DashboardActivity target);

    DashBoardFragmentComponent plus(DashFragmentModule module);
}

