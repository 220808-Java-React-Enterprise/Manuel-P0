package com.revature.yolp.ui;

import com.revature.yolp.models.Painting;
import com.revature.yolp.models.User;
import com.revature.yolp.models.Cart;

import com.revature.yolp.services.UserService;
import com.revature.yolp.services.CartService;
import com.revature.yolp.services.PaintingService;

import java.util.List;
import java.util.Scanner;


public class CartMenu implements IMenu{

    private final User user;
    private final Cart cart;
    private final UserService userService;
    private final CartService cartService;
    private final PaintingService paintingService;



    public CartMenu(User user, Cart cart, UserService userService, CartService cartService, PaintingService paintingService){
        this.user = user;
        this.cart = cart;
        this.userService = userService;
        this.cartService = cartService;
        this.paintingService = paintingService;

    }


    @Override
    public void start() {
        Scanner scan = new Scanner(System.in);

        System.out.println("Welcome to the checkout menu!");
        List<Painting> paintings = cartService.getAllFromCart(user.getId());
        if (paintings.size() == 0) {
            System.out.println("Your cart is empty!");
        } else {
            for (Painting p : paintings) {
                System.out.println("\nName: " + p.getName());
                System.out.println("Author: " + p.getAuthor());
                System.out.println("Cost: " + p.getCost());
            }
        }



        exit: {
            while (true) {
            System.out.println("\n[1] To Place Order");
                System.out.println("[2] To Remove Item from Cart");
                System.out.println("[x] To Return to Main Menu");

            }

        }

    }
}
