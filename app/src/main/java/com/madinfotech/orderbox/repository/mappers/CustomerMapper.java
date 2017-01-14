package com.madinfotech.orderbox.repository.mappers;

import android.util.Log;

import com.madinfotech.orderbox.model.Customer;

/**
 * Created by prathameshkesarkar on 21/07/16.
 */
public class CustomerMapper implements Mapper<Customer,Customer> {

    //Converts the RealmCustomer Object to Normal Pojo
    @Override
    public Customer map(Customer customer) {
        Customer pojoCustomer =new Customer();
        pojoCustomer.setCustomerName(customer.getCustomerName());
        pojoCustomer.setPhoneNo(customer.getPhoneNo());
        pojoCustomer.setId(customer.getId());
        return pojoCustomer;
    }
}
