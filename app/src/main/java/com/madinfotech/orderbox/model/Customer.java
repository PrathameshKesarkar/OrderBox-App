package com.madinfotech.orderbox.model;

import java.math.BigInteger;

import io.realm.RealmModel;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

/**
 * Created by prathameshkesarkar on 19/07/16.
 */
@RealmClass
public class Customer implements RealmModel{

    @PrimaryKey
    private long id;

    private String phone_no;

    private String customer_name;

    public String getPhoneNo() {
        return phone_no;
    }

    public void setPhoneNo(String phone_no) {
        this.phone_no = phone_no;
    }

    public String getCustomerName() {
        return customer_name;
    }

    public long getId() {
        return id;
    }

    public void setCustomerName(String customer_name) {
        this.customer_name = customer_name;
    }

    public void setId(long id) {
        this.id = id;
    }
}
