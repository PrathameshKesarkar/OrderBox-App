package com.madinfotech.orderbox.ui.screens;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

/**
 * Created by prathameshkesarkar on 21/08/16.
 */
public class PredictiveLinearLayoutManager extends LinearLayoutManager {
    public PredictiveLinearLayoutManager(Context context) {
        super(context);
    }

    @Override
    public boolean supportsPredictiveItemAnimations() {
        return true;
    }
}
