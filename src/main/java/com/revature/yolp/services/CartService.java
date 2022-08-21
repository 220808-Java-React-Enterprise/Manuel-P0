package com.revature.yolp.services;
import com.revature.yolp.daos.CartDAO;

public class CartService {

    private final CartDAO cartDAO;

    public CartService(CartDAO cartDAO){
        this.cartDAO = cartDAO;
    }
}