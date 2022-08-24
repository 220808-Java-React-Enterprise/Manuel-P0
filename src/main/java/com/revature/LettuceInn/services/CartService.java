package com.revature.LettuceInn.services;
import com.revature.LettuceInn.daos.CartDAO;
import com.revature.LettuceInn.models.Painting;
import com.revature.LettuceInn.models.User;
import com.revature.LettuceInn.models.Cart;
import com.revature.LettuceInn.utils.custom_exceptions.InvalidUserException;


import java.util.*;


public class CartService {

    private final CartDAO cartDAO;

    public CartService(CartDAO cartDAO){
        this.cartDAO = cartDAO;
    }

    public void newCart(Cart cart){
    cartDAO.save(cart);
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

    public void addPaintingToCart(String cart_id, Painting paint){

        cartDAO.paintingToCart(cart_id,paint);
    }
    public void removePaintingFromCart(Cart cart,Painting paint){
        cartDAO.removeFromCart(cart,paint);
    }

    public void emptyCart(Cart cart,List<Painting> paintings){
        for(Painting p : paintings){
            cartDAO.removeFromCart(cart,p);
        }
    }
    public Painting getPaintingFromCart(String id){
        List<Painting> paintings = cartDAO.getAllInCart(id);
        //In future- this would be replaced by finding the painting warehouse closest to the user.
        return paintings.get(0);
    }


}