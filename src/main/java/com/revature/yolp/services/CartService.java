package com.revature.yolp.services;
import com.revature.yolp.daos.CartDAO;
import com.revature.yolp.models.Painting;

import java.util.*;


public class CartService {

    private final CartDAO cartDAO;

    public CartService(CartDAO cartDAO){
        this.cartDAO = cartDAO;
    }

    public List<Painting> getAllFromCart(String id){
        return CartDAO.getAllInCart(id);

    }
}