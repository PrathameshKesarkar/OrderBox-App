package com.madinfotech.orderbox.repository.specification;

import com.madinfotech.orderbox.model.Customer;
import com.madinfotech.orderbox.model.Order;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by prathameshkesarkar on 20/07/16.
 */
public class FindAllCustomerSpecification extends RealmSpecification {

    @Override
    public RealmResults<Customer> customersRealmResult(Realm realm) {
        return realm.where(Customer.class).findAll();
    }
}
