package com.madinfotech.orderbox.repository;

import com.madinfotech.orderbox.model.Customer;
import com.madinfotech.orderbox.model.Order;
import com.madinfotech.orderbox.repository.specification.Specification;

import java.util.List;

import io.realm.RealmResults;

/**
 * Created by prathameshkesarkar on 19/07/16.
 */
public interface Repository<T> {
    void add(T item);

    void remove(T item);

    void update(T item);

    List<Order> searchOrders(Specification specification);

    List<Customer> searchCustomer(Specification specification);

    Order serarchOrder(Specification specification);
}
