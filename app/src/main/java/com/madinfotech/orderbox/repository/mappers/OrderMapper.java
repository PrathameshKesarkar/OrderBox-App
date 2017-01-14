package com.madinfotech.orderbox.repository.mappers;

import com.madinfotech.orderbox.model.Order;

/**
 * Created by prathameshkesarkar on 23/07/16.
 */
public class OrderMapper implements Mapper<Order, Order> {

    CustomerMapper customerMapper;

    public OrderMapper(CustomerMapper customerMapper) {
        this.customerMapper = customerMapper;
    }

    @Override
    public Order map(Order order) {
        Order pojoOrder = new Order();
        pojoOrder.setCustomer(customerMapper.map(order.getCustomer()));
        pojoOrder.setAmount(order.getAmount());
        pojoOrder.setStatus(order.getStatus());
        pojoOrder.setId(order.getId());
        pojoOrder.setDescription(order.getDescription());
        pojoOrder.setDeliveryDate(order.getDeliveryDate());
        pojoOrder.setOrderId(order.getOrderId());
        return pojoOrder;
    }
}
