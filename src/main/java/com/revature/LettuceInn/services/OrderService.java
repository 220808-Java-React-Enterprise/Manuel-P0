package com.revature.LettuceInn.services;


import com.revature.LettuceInn.daos.CartDAO;
import com.revature.LettuceInn.daos.OrderDAO;
import com.revature.LettuceInn.models.Order;
import java.util.*;

import com.revature.LettuceInn.models.Painting;
import com.revature.LettuceInn.utils.custom_exceptions.InvalidOrderException;


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

    public void paintingOrdered(List<Painting> pa, Order order){
        for(Painting p : pa){
            orderDAO.paintingOrdered(p.getId(),order.getId());
        }
    }
    public List<Painting> getAllFromOrder(String id){
        return orderDAO.getAllInOrder(id);
    }



}
