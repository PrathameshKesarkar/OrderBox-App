package com.madinfotech.orderbox.ui.screens.component;

import com.madinfotech.orderbox.ui.screens.FragmentScope;
import com.madinfotech.orderbox.ui.screens.fragment.DashBoardFragment;
import com.madinfotech.orderbox.ui.screens.module.DashFragmentModule;

import dagger.Subcomponent;

/**
 * Created by prathameshkesarkar on 24/07/16.
 */
@FragmentScope
@Subcomponent(modules = DashFragmentModule.class)
public interface DashBoardFragmentComponent {
     DashBoardFragment inject(DashBoardFragment target);
}
