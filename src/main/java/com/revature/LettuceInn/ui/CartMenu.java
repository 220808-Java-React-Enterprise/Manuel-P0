package com.revature.LettuceInn.ui;

import com.revature.LettuceInn.daos.*;
import com.revature.LettuceInn.models.Order;
import com.revature.LettuceInn.models.Painting;
import com.revature.LettuceInn.models.User;
import com.revature.LettuceInn.models.Cart;

import com.revature.LettuceInn.services.*;
import com.revature.LettuceInn.utils.custom_exceptions.InvalidOrderException;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;


public class CartMenu implements IMenu{

    private final User user;
    private final Cart cart;
    private final UserService userService;
    private final CartService cartService;
    private final PaintingService paintingService;
    private final OrderService orderService;



    public CartMenu(User user, Cart cart, UserService userService, CartService cartService, PaintingService paintingService, OrderService orderService){
        this.user = user;
        this.cart = cart;
        this.userService = userService;
        this.cartService = cartService;
        this.paintingService = paintingService;
        this.orderService = orderService;

    }


    @Override
    public void start() {
        Scanner scan = new Scanner(System.in);

        System.out.println("Welcome to the checkout menu!");
        List<Painting> paintings = cartService.getAllFromCart(user.getId());
        displayPaintings(paintings);



        exit: {
            while (true) {
            System.out.println("\n[1] To Place Order");
                System.out.println("[2] To Remove Item from Cart");
                System.out.println("[x] To Return to Main Menu");

                switch (scan.nextLine()) {
                    case "1":
                        placeOrder(paintings);
                        break exit;
                    case "2":
                        removeItem(paintings);
                        break exit;
                    case "x":
                        new MainMenu(user, userService, new RestaurantService(new RestaurantDAO()), new ReviewService(new ReviewDAO()),paintingService,cartService,orderService).start();
                        break exit;
                    default:
                        System.out.println("\nInvalid input!");
                        break;
                }


            }

        }

    }



    private void placeOrder(List<Painting> pa) {
        double totalCost = 0;
        orderExit:
            while(true){
                Painting dupe = null;
                try{
                for(Painting p : pa) {
                    dupe = p;
                    orderService.notAvailable(p.getId());
                    totalCost += p.getCost();
                }
            }
            catch(InvalidOrderException e){
                System.out.println(e.getMessage());
                cartService.removePaintingFromCart(cart,dupe);
                break orderExit;
            }
                //ORDER IS CREATED! Note: NEEDS IMPLEMENTATION: date, warehouse
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                LocalDateTime now = LocalDateTime.now();
                Painting p = cartService.getPaintingFromCart(user.getId());
                Order order = new Order(UUID.randomUUID().toString(),pa.size(),totalCost,dtf.format(now),user.getId(),p.getLocation());

                orderService.placeOrder(order);
                cartService.emptyCart(cart,pa);
                paintingService.makeUnavailable(pa);
                System.out.println("Item has been purchased!");
                break orderExit;
            }
    }

    private void removeItem(List<Painting> p) {
        Scanner scan = new Scanner(System.in);
        displayPaintings(p);

        exitRemove: {
            while (true) {
            System.out.println("Select a painting to remove or return to the previous menu with [x]: ");
                String input = scan.nextLine();
                if(input == "x"){
                    break exitRemove;
                }
                else if(isNumeric(input) && Integer.parseInt(input) >= 0 && Integer.parseInt(input) < p.size()) {
                    Painting paintingToRemove = p.get(Integer.parseInt(input));

                    cartService.removePaintingFromCart(cart,paintingToRemove);
                    System.out.println("Painting removed from cart!");
                    break exitRemove;
                }
                else{
                    System.out.println("Invalid Input!");
                    break exitRemove;
                }
            }
        }
    }
    private void displayPaintings(List<Painting> pa){
        if (pa.size() == 0) {
            System.out.println("Your cart is empty!");
        } else {
            for (int i = 0; i < pa.size();i++) {
                System.out.println("\nName: " + pa.get(i).getName());
                System.out.println("ID: [" + i + "]");
                System.out.println("Author: " + pa.get(i).getAuthor());
                System.out.println("Cost: " + pa.get(i).getCost());
            }
        }
    }

        private static boolean isNumeric(String str) {
            try {
                Double.parseDouble(str);
                return true;
            } catch(NumberFormatException e){
                return false;
            }
        }

}
