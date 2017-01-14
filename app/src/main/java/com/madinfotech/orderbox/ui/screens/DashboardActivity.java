package com.madinfotech.orderbox.ui.screens;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.madinfotech.orderbox.OrderBoxApplication;
import com.madinfotech.orderbox.R;
import com.madinfotech.orderbox.ui.screens.fragment.DashBoardFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by prathameshkesarkar on 13/07/16.
 */
public class DashboardActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.new_task_floating_button)
    FloatingActionButton newTaskFloatingButton;

    Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        unbinder = ButterKnife.bind(this);
        toolbar.setTitle("Dash Board");
        setSupportActionBar(toolbar);
        DashBoardFragment dashBoardFragment = new DashBoardFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.content_view,dashBoardFragment).commit();

        newTaskFloatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, AddTaskActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        ((OrderBoxApplication)getApplication()).releaseDashActivityComponent();
    }

    @Override
    public void setupActivityComponent() {
        ((OrderBoxApplication)getApplication()).createDashComponent().inject(this);
    }

}
