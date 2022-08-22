package com.revature.yolp.services;


import com.revature.yolp.daos.OrderDAO;
import com.revature.yolp.models.Warehouse;
import com.revature.yolp.models.Order;
import java.util.*;

public class OrderService {

    private final OrderDAO orderDAO;

    public OrderService(OrderDAO orderDAO){
        this.orderDAO = orderDAO;
    }

    public List<Order> getOrdersById(String id){
        return orderDAO.getAllById(id);
    }
}
