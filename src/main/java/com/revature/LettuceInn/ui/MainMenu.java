package com.revature.LettuceInn.ui;

import com.revature.LettuceInn.models.User;
import com.revature.LettuceInn.models.Painting;
import com.revature.LettuceInn.models.Order;


import com.revature.LettuceInn.services.UserService;
import com.revature.LettuceInn.services.OrderService;
import com.revature.LettuceInn.services.CartService;
import com.revature.LettuceInn.services.PaintingService;

import com.revature.LettuceInn.utils.custom_exceptions.InvalidUserException;

import java.util.List;
import java.util.Scanner;

public class MainMenu implements IMenu {
    private final User user;
    private final UserService userService;


    private final PaintingService paintingService;
    private final CartService cartService;
    private final OrderService orderService;

    public MainMenu(User user, UserService userService, PaintingService paintingService, CartService cartService, OrderService orderService) {
        this.user = user;
        this.userService = userService;
        this.paintingService = paintingService;
        this.cartService = cartService;
        this.orderService = orderService;
    }

    @Override
    public void start() {
        Scanner scan = new Scanner(System.in);

        exit: {
            while (true) {
                System.out.println("\nWelcome to the main menu " + user.getUsername() + "!");
                System.out.println("[1] View all available paintings");
                System.out.println("[2] View your cart");
                System.out.println("[3] View your past orders");
                System.out.println("[x] Sign out!");
                System.out.print("\nEnter: ");

                switch (scan.nextLine()) {
                    case "1":
                        viewPaintings();
                        break;
                    case "2":
                        viewCart();
                        break;
                    case "3":
                        viewOrders();
                        break;
                    case "x":
                        break exit;
                    default:
                        System.out.println("\nInvalid input!");
                        break;
                }
            }
        }
    }

    private void viewPaintings(){

        System.out.println("Viewing Paintings...");

        Scanner scan = new Scanner(System.in);

        List<Painting> paintings = paintingService.getAllAvailable();
        if (paintings.size() == 0) {
            System.out.println("No current paintings available! Please contact support if you believe this is an error!");
        } else {
            for (int i = 0; i < paintings.size();i++) {
                System.out.println("\nName: " + paintings.get(i).getName());
                System.out.println("ID: [" + i + "]");
                System.out.println("Author: " + paintings.get(i).getAuthor());
                System.out.println("Cost: " + paintings.get(i).getCost());
            }
        }

        exit:
        {
            while (true) {

                System.out.println("\n[1] Add Item to Cart");
                System.out.println("[x] return to main menu");

                switch (scan.nextLine()) {
                    case "x":
                        break exit;
                    case "1":
                        exitAddCart:{
                            while(true){
                                System.out.println("\nInput ID of Painting, or x to return: ");
                                String input = scan.nextLine();
                                if(input == "x"){
                                    break exit;
                                }
                                else if(isNumeric(input) && Integer.parseInt(input) >= 0 && Integer.parseInt(input) < paintings.size()){
                                    Painting paintingToAdd = paintings.get(Integer.parseInt(input));
                                    try{
                                        cartService.paintingInCart(user,paintingToAdd);
                                    }catch(InvalidUserException e){
                                        System.out.println(e.getMessage());
                                        break exit;
                                    }

                                    System.out.println("Painting added to cart!");
                                    cartService.addPaintingToCart(user.getId(),paintingToAdd);
                                    break exit;
                                }
                                else{
                                    break exitAddCart;
                                }
                                /*switch(scan.nextLine()){
                                    case "x":
                                        break exit;

                                    default:
                                        System.out.println("\nInvalid input!");
                                        break exitAddCart;

                                }

                                 */
                            }
                        }
                    default:
                        System.out.println("\nInvalid input!");
                        break;
                }
            }
        }

    }
    private void viewCart(){
        Scanner scan = new Scanner(System.in);
        exitAll:
        {
            System.out.println("Viewing Cart...");
            List<Painting> paintings = cartService.getAllFromCart(user.getId());
            if (paintings.size() == 0) {
                System.out.println("\nYour cart is empty!");
                break exitAll;
            } else {
                for (Painting p : paintings) {
                    System.out.println("\nName: " + p.getName());
                    System.out.println("Author: " + p.getAuthor());
                    System.out.println("Cost: " + p.getCost());
                }
            }

            exit:
            {
                while (true) {
                    System.out.println("\n[1] to go to checkout");
                    System.out.println("[x] return to main menu");
                    switch (scan.nextLine()) {
                        case "1":
                            checkout();
                            break exit;
                        case "x":
                            break exit;
                        default:
                            System.out.println("\nInvalid input!");
                            break;
                    }
                }
            }
        }
    }
    private void viewOrders(){
        System.out.println("Viewing Orders...");
        Scanner scan = new Scanner(System.in);

        List<Order> orders = orderService.getOrdersById(user.getId());
        if (orders.size() == 0) {
            System.out.println("\nYou have no orders!");
        } else {
            for (int i = 0; i < orders.size(); i++) {
                System.out.println("\nItems Purchased: " + orders.get(i).getNumItems());
                System.out.println("Total Cost: " + orders.get(i).getTotalCost());
                System.out.println("Date: " + orders.get(i).getDate());
                System.out.println("ID: [" + i + "]");
            }
            System.out.println("Input ID to view details of order, or [x] to return to main menu");
            exitOrder: {
                while(true){
                    String input = scan.nextLine();
                    if(input == "x"){
                        break exitOrder;
                    }
                    else if(isNumeric(input) && Integer.parseInt(input) >= 0 && Integer.parseInt(input) < orders.size()){
                        List<Painting> paintings = orderService.getAllFromOrder(orders.get(Integer.parseInt(input)).getId());
                        for(Painting p : paintings){
                            System.out.println("\nName: " + p.getName());
                            System.out.println("Author: " + p.getAuthor());
                            System.out.println("Cost: " + p.getCost());
                        }
                        System.out.println("\nPress anything to continue: ");
                        input = scan.nextLine();
                        break exitOrder;
                    }
                    else{
                        System.out.println("Invalid Input!");
                    }
                }
            }

        }

        exit:
        {
            while (true) {

                System.out.println("\n[x] return to main menu");
                switch (scan.nextLine()) {
                    case "x":
                        break exit;
                    default:
                        System.out.println("\nInvalid input!");
                        break;
                }
            }
        }
    }
    private void checkout(){
        System.out.println("Checking out..)");
        new CartMenu(user, cartService.getById(user.getId()),userService,cartService,paintingService,orderService).start();
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
