package com.madinfotech.orderbox.ui.screens.module;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;

import com.madinfotech.orderbox.model.Order;
import com.madinfotech.orderbox.repository.OrderRealmRepository;
import com.madinfotech.orderbox.repository.specification.FindAllOrderSpecification;
import com.madinfotech.orderbox.ui.adapter.DashBoardAdapter;
import com.madinfotech.orderbox.ui.screens.FragmentScope;
import com.madinfotech.orderbox.ui.screens.PredictiveLinearLayoutManager;
import com.madinfotech.orderbox.ui.screens.SimpleDividerItemDecorator;

import java.util.List;

import dagger.Module;
import dagger.Provides;

/**
 * Created by prathameshkesarkar on 24/07/16.
 */
@Module
public class DashFragmentModule {

    @Provides
    @FragmentScope
    public LayoutInflater provideLayoutInflater(Context context) {
        return LayoutInflater.from(context);
    }

    @Provides
    @FragmentScope
    public FindAllOrderSpecification provideOrderSpecification() {
        return new FindAllOrderSpecification();
    }

    @Provides
    @FragmentScope
    public OrderRealmRepository provideOrderRepository() {
        return new OrderRealmRepository();
    }

    @Provides
    @FragmentScope
    public List<Order> provideOrderList(OrderRealmRepository realmRepository, FindAllOrderSpecification orderSpecification) {
        return realmRepository.searchOrders(orderSpecification);
    }

    @Provides
    @FragmentScope
    public DashBoardAdapter provideDashBoardAdapter(LayoutInflater inflater, List<Order> orderList) {
        return new DashBoardAdapter(inflater, orderList);
    }


    @Provides
    @FragmentScope
    public PredictiveLinearLayoutManager provideLayoutManager(Context context) {
        return new PredictiveLinearLayoutManager(context);
    }

    @Provides
    @FragmentScope
    public SimpleDividerItemDecorator provideDivider(Context context){
        return  new SimpleDividerItemDecorator(context);
    }
}
