package com.madinfotech.orderbox.ui.screens.presenter;

import com.madinfotech.orderbox.model.Customer;
import com.madinfotech.orderbox.ui.screens.viewInterface.AddTaskView;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by prathameshkesarkar on 19/07/16.
 */
public interface AddTaskPresenter {


    void saveOrder();
    void setView(AddTaskView addTaskView);
    void unBindView();
    void setCustomer(Customer customer);
    void removeCustomer();
    void setDate(Date date);
}

