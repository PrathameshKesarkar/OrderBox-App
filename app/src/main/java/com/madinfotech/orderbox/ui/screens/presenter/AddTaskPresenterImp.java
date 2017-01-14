package com.madinfotech.orderbox.ui.screens.presenter;

import android.util.Log;
import android.widget.Toast;

import com.madinfotech.orderbox.model.Customer;
import com.madinfotech.orderbox.model.Order;
import com.madinfotech.orderbox.repository.OrderRealmRepository;
import com.madinfotech.orderbox.ui.screens.viewInterface.AddTaskView;
import com.madinfotech.orderbox.ui.util.Constants;

import java.util.Date;

/**
 * Created by prathameshkesarkar on 19/07/16.
 */
public class AddTaskPresenterImp implements AddTaskPresenter {

    private AddTaskView addTaskView;
    private Customer customer;
    private  Date deliveryDate;
    private OrderRealmRepository realmRepository;
    public AddTaskPresenterImp(OrderRealmRepository realmRepository) {
        this.realmRepository = realmRepository;
    }

    @Override
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public void removeCustomer() {
        customer = null;
    }

    @Override
    public void setDate(Date date) {
        deliveryDate = date;
    }

    @Override
    public void saveOrder() {
        Order order = new Order();


        String order_id = addTaskView.getOrderId();
        String particulars = addTaskView.getParticular().trim();
        String date = addTaskView.getDeliveryDate();
        long orderId;
        if (order_id != null||!order_id.isEmpty())
            orderId = Long.parseLong(order_id.trim());
        else {
            addTaskView.showMessage("OrderId cannot be blank");
            return;
        }
        if (particulars == null||particulars.isEmpty()) {
            addTaskView.showParticularErrorMessage();
            return;
        }
        if (date == null||date.isEmpty()) {
            addTaskView.deliverDateErrorMessage();
            return;
        }
        else {

        }
        order.setOrderId(orderId);
        order.setDescription(particulars.trim());
        order.setDeliveryDate(deliveryDate);
        String amount = addTaskView.getAmount();
        if (amount != null&&!amount.isEmpty())
            order.setAmount(Integer.parseInt(amount));
        else
            order.setAmount(0);

        if (customer == null) {
            customer = new Customer();
            //Check to Define the Customer is new and We
            customer.setId(Constants.Customer.NEW_CUSTOMER);
            String customer_name = addTaskView.getCustomerName();
            String phone_number = addTaskView.getPhoneNumber();

            if (customer_name==null||customer_name.isEmpty()){
                addTaskView.showCustomerNameErrorMessage();
                return;
            }
            if (phone_number==null||phone_number.isEmpty()){
                addTaskView.showPhoneNumberErrorMessage();
                return;
            }
            customer.setCustomerName(customer_name.trim());
            customer.setPhoneNo(phone_number.trim());
        }
        order.setCustomer(customer);

        realmRepository.add(order);
        addTaskView.showMessage("Order Added Successfully");
    }

    @Override
    public void setView(AddTaskView addTaskView) {
        this.addTaskView = addTaskView;
    }

    @Override
    public void unBindView() {
        addTaskView = null;
    }
}
