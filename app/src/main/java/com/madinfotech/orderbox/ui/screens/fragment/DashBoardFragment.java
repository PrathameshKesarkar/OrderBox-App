package com.madinfotech.orderbox.ui.screens.fragment;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.madinfotech.orderbox.OrderBoxApplication;
import com.madinfotech.orderbox.R;
import com.madinfotech.orderbox.ui.adapter.DashBoardAdapter;
import com.madinfotech.orderbox.ui.screens.PredictiveLinearLayoutManager;
import com.madinfotech.orderbox.ui.screens.SimpleDividerItemDecorator;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by prathameshkesarkar on 23/07/16.
 */
public class DashBoardFragment extends BaseFragment {

    private Unbinder unbinder;

    @BindView(R.id.task_list)
    RecyclerView recyclerView;

    @Inject
    DashBoardAdapter dashBoardAdapter;

    @Inject
    PredictiveLinearLayoutManager linearLayoutManager;

    @Inject
    SimpleDividerItemDecorator itemDecorator;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_dash_board, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(dashBoardAdapter);
        recyclerView.addItemDecoration(itemDecorator);
    }

    @Override
    public void setUpFragmentComponent() {
        ((OrderBoxApplication)getActivity().getApplication()).createDashFragmentComponen().inject(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        ((OrderBoxApplication)getActivity().getApplication()).releaseDashFragmentComponent();
    }


}
