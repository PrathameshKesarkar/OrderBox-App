package com.madinfotech.orderbox.ui.screens.module;

import android.content.Context;
import android.view.LayoutInflater;

import com.madinfotech.orderbox.R;
import com.madinfotech.orderbox.model.Customer;
import com.madinfotech.orderbox.repository.OrderRealmRepository;
import com.madinfotech.orderbox.repository.specification.FindAllCustomerSpecification;
import com.madinfotech.orderbox.ui.adapter.EditTextAdapter;
import com.madinfotech.orderbox.ui.screens.ActivityScope;
import com.madinfotech.orderbox.ui.screens.presenter.AddTaskPresenter;
import com.madinfotech.orderbox.ui.screens.presenter.AddTaskPresenterImp;

import java.util.Calendar;
import java.util.List;

import dagger.Module;
import dagger.Provides;

/**
 * Created by prathameshkesarkar on 19/07/16.
 */

@Module
public class AddTaskActivityModule {

    @Provides
    @ActivityScope
    public Calendar provideCalendarInstance(){
       return Calendar.getInstance();
    }

    @Provides
    @ActivityScope
    public OrderRealmRepository provideOrderRealmRepo(){
        return new OrderRealmRepository();
    }


    @Provides
    @ActivityScope
    public AddTaskPresenter  provideAddTaskPresenter(OrderRealmRepository repository){
        return new AddTaskPresenterImp(repository);
    }


}
