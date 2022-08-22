package com.revature.yolp.services;
import com.revature.yolp.daos.CartDAO;
import com.revature.yolp.models.Painting;
import com.revature.yolp.models.User;
import com.revature.yolp.models.Cart;
import com.revature.yolp.utils.custom_exceptions.InvalidUserException;


import java.util.*;


public class CartService {

    private final CartDAO cartDAO;

    public CartService(CartDAO cartDAO){
        this.cartDAO = cartDAO;
    }

    public List<Painting> getAllFromCart(String id){
        return CartDAO.getAllInCart(id);
    }
    public boolean paintingInCart(User user, Painting p){
        if (cartDAO.getPaintingInCart(user.getId(),p.getId()) != null) throw new InvalidUserException("\nThat painting is already in your cart!");
        return false;
    }

    public Cart getById(String id){
        return cartDAO.getById(id);
    }

    public void addPaintingToCart(String person_id, Painting paint){
        cartDAO.paintingToCart(person_id,paint);
    }
    public void removePaintingFromCart(Cart cart,Painting paint){
        cartDAO.removeFromCart(cart,paint);
    }

    public void emptyCart(Cart cart,List<Painting> paintings){
        for(Painting p : paintings){
            cartDAO.removeFromCart(cart,p);
        }
    }

}