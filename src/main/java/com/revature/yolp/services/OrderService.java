package com.revature.yolp.services;


import com.revature.yolp.daos.OrderDAO;
import com.revature.yolp.models.Warehouse;
public class OrderService {

    private final OrderDAO orderDAO;

    public OrderService(OrderDAO orderDAO){
        this.orderDAO = orderDAO;
    }
}
