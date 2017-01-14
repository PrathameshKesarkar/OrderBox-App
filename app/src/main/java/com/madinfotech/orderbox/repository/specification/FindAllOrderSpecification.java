package com.madinfotech.orderbox.repository.specification;

import com.madinfotech.orderbox.model.Order;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * Created by prathameshkesarkar on 23/07/16.
 */
public class FindAllOrderSpecification extends RealmSpecification {
    @Override
    public RealmResults<Order> ordersRealmResult(Realm realm) {
        return realm.where(Order.class).findAllSorted("deliveryDate", Sort.ASCENDING);
    }
}
