package com.revature.yolp.services;


import com.revature.yolp.daos.OrderDAO;
import com.revature.yolp.models.Painting;
import com.revature.yolp.models.Warehouse;
import com.revature.yolp.models.Order;
import java.util.*;
import com.revature.yolp.utils.custom_exceptions.InvalidOrderException;


public class OrderService {

    private final OrderDAO orderDAO;

    public OrderService(OrderDAO orderDAO){
        this.orderDAO = orderDAO;
    }

    public List<Order> getOrdersById(String id){
        return orderDAO.getAllById(id);
    }
    public boolean notAvailable(String painting_id){
        if(orderDAO.getOrderByPainting(painting_id)!=null){
            throw new InvalidOrderException("\nOne or more paintings has been purchased already! Removing items from cart...");
        }
        return false;
    }
    public void placeOrder(Order order){
        orderDAO.save(order);
    }


}
