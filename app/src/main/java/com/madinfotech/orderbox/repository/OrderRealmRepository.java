package com.madinfotech.orderbox.repository;

import android.util.Log;

import com.madinfotech.orderbox.model.Customer;
import com.madinfotech.orderbox.model.Order;
import com.madinfotech.orderbox.repository.mappers.CustomerMapper;
import com.madinfotech.orderbox.repository.mappers.OrderMapper;
import com.madinfotech.orderbox.repository.specification.RealmSpecification;
import com.madinfotech.orderbox.repository.specification.Specification;
import com.madinfotech.orderbox.ui.util.Constants;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by prathameshkesarkar on 19/07/16.
 */
public class OrderRealmRepository implements Repository<Order> {

    CustomerMapper customerMapper;
    OrderMapper orderMapper;

    public OrderRealmRepository() {
        customerMapper = new CustomerMapper();
        orderMapper = new OrderMapper(customerMapper);
    }

    @Override
    public void add(final Order item) {

        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Order order = realm.createObject(Order.class);

                order.setId(getUniqueOrderId(realm));
                order.setAmount(item.getAmount());
                order.setOrderId(item.getOrderId());
                order.setDeliveryDate(item.getDeliveryDate());
                order.setDescription(item.getDescription().trim());
                order.setStatus(Constants.Status.Code.NO_STATUS);


                if (item.getCustomer().getId() == Constants.Customer.NEW_CUSTOMER) {
                    Log.d(OrderRealmRepository.class.getSimpleName(), "Created New User");
                    item.getCustomer().setId(getUniqueCustomerId(realm));
                    order.setCustomer(realm.copyToRealm(item.getCustomer()));
                } else
                    order.setCustomer(getExistingCustomer(realm, item.getCustomer().getId()));

            }
        });
        realm.close();
    }

    public long getUniqueOrderId(Realm realm) {
        try {
            return realm.where(Order.class).max("id").longValue() + 1;
        } catch (ArrayIndexOutOfBoundsException e) {
            return 0;
        }
    }

    public Customer getExistingCustomer(Realm realm, long cust_id) {
        Log.d(OrderRealmRepository.class.getSimpleName(), "Added Existing User");
        return realm.where(Customer.class).equalTo("id", cust_id).findFirst();
    }

    public long getUniqueCustomerId(Realm realm) {
        try {
            return realm.where(Customer.class).max("id").longValue() + 1;
        } catch (ArrayIndexOutOfBoundsException e) {
            return 0;
        } catch (NullPointerException e) {
            return 0;
        }
    }


    @Override
    public void remove(Order item) {
        //TODO implement removing order
    }

    @Override
    public void update(Order item) {
        //TODO implement updating an Order
    }

    @Override
    public List<Order> searchOrders(Specification specification) {
        RealmSpecification realmSpecification = (RealmSpecification) specification;
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Order> orderRealmResults = realmSpecification.ordersRealmResult(realm);
        List<Order> orders = new ArrayList<>();
        for (Order order : orderRealmResults) {
            orders.add(orderMapper.map(order));
        }
        realm.close();
        return orders;
    }

    @Override
    public List<Customer> searchCustomer(Specification specification) {
        RealmSpecification realmSpecification = (RealmSpecification) specification;
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Customer> customerResult = realmSpecification.customersRealmResult(realm);
        List<Customer> customers = new ArrayList<>();
        for (Customer customer : customerResult) {
            customers.add(customerMapper.map(customer));
        }
        realm.close();
        return customers;
    }

    @Override
    public Order serarchOrder(Specification specification) {
        RealmSpecification realmSpecification = (RealmSpecification) specification;
        Realm realm= Realm.getDefaultInstance();

        return null;
    }
}
