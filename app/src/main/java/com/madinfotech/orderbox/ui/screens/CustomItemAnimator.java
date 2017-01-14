package com.madinfotech.orderbox.ui.screens;

import android.animation.ObjectAnimator;
import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.view.animation.AccelerateInterpolator;

/**
 * Created by prathameshkesarkar on 21/08/16.
 */
public class CustomItemAnimator extends DefaultItemAnimator {
    AccelerateInterpolator mAccelatorInterpolator = new AccelerateInterpolator();

}
