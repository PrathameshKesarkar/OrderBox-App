package com.madinfotech.orderbox.ui.screens.component;

import com.madinfotech.orderbox.ui.screens.ActivityScope;
import com.madinfotech.orderbox.ui.screens.AddTaskActivity;
import com.madinfotech.orderbox.ui.screens.module.AddTaskActivityModule;

import dagger.Component;
import dagger.Subcomponent;

/**
 * Created by prathameshkesarkar on 19/07/16.
 */

@ActivityScope
@Subcomponent(modules = {AddTaskActivityModule.class})
public interface AddTaskActivityComponent {
    AddTaskActivity inject(AddTaskActivity target);
}
