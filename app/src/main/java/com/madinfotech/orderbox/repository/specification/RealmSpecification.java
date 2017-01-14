package com.madinfotech.orderbox.repository.specification;

import com.madinfotech.orderbox.model.Customer;
import com.madinfotech.orderbox.model.Order;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by prathameshkesarkar on 19/07/16.
 */
public abstract class RealmSpecification implements Specification {
    public RealmResults<Order> ordersRealmResult(Realm realm){
        return null;
    }
    public RealmResults<Customer> customersRealmResult(Realm realm){
        return null;
    }



}
