package com.madinfotech.orderbox.ui.screens.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

/**
 * Created by prathameshkesarkar on 23/07/16.
 */
public abstract class BaseFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUpFragmentComponent();
    }
    public abstract void setUpFragmentComponent();
}
