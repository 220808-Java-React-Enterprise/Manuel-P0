package com.revature.yolp.ui;

import com.revature.yolp.daos.*;
import com.revature.yolp.models.Restaurant;
import com.revature.yolp.models.Review;
import com.revature.yolp.models.User;
import com.revature.yolp.models.Painting;
import com.revature.yolp.models.Order;
import com.revature.yolp.models.Cart;



import com.revature.yolp.services.RestaurantService;
import com.revature.yolp.services.ReviewService;
import com.revature.yolp.services.UserService;
import com.revature.yolp.services.OrderService;
import com.revature.yolp.services.CartService;
import com.revature.yolp.services.PaintingService;

import com.revature.yolp.utils.custom_exceptions.InvalidUserException;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class MainMenu implements IMenu {
    private final User user;
    private final UserService userService;
    private final RestaurantService restoService;
    private final ReviewService reviewService;

    private final PaintingService paintingService;
    private final CartService cartService;
    private final OrderService orderService;

    public MainMenu(User user, UserService userService, RestaurantService restoService, ReviewService reviewService, PaintingService paintingService, CartService cartService, OrderService orderService) {
        this.user = user;
        this.userService = userService;
        this.restoService = restoService;
        this.reviewService =reviewService;
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
                System.out.println("[1] View all restaurants");
                System.out.println("[2] View all available paintings");
                System.out.println("[3] View your cart");
                System.out.println("[4] View your past orders");
                System.out.println("[x] Sign out!");
                System.out.print("\nEnter: ");

                switch (scan.nextLine()) {
                    case "1":
                        viewRestaurants();
                        break;
                    case "2":
                        viewPaintings();
                        break;
                    case "3":
                        viewCart();
                        break;
                    case "4":
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

        System.out.println("Viewing Cart...");
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
    private void viewOrders(){
        System.out.println("Viewing Orders...");
        Scanner scan = new Scanner(System.in);

        List<Order> orders = orderService.getOrdersById(user.getId());
        if (orders.size() == 0) {
            System.out.println("You have no orders!");
        } else {
            for (Order o : orders) {
                System.out.println("\nItems Purchased: " + o.getNumItems());
                System.out.println("Total Cost: " + o.getTotalCost());
                System.out.println("Date: " + o.getDate());
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
        System.out.println("You're checking out (NEEDS IMPLEMENTATION)");
        new CartMenu(user, cartService.getById(user.getId()),userService,cartService,paintingService,orderService).start();
    }
    private void viewRestaurants() {
        Scanner scan = new Scanner(System.in);

        exit: {
            while (true) {
                System.out.println("\nViewing all restaurants...");
                List<Restaurant> restaurants = restoService.getAllRestaurants();

                for (int i = 0; i < restaurants.size(); i++) {
                    System.out.println("[" + (i + 1) + "] " + restaurants.get(i).getName());
                }

                System.out.print("\nSelect a restaurant: ");
                int index = scan.nextInt() - 1;

                try {
                    Restaurant selectedResto = restaurants.get(index);

                    List<Review> reviews = reviewService.getAllReviewsByRestaurantId(selectedResto.getId());

                    System.out.println("\nName: " + selectedResto.getName());
                    for (Review r : reviews) {
                        System.out.println("Comment: " + r.getComment());
                        System.out.println("Rating: " + r.getRating());
                        System.out.println("User: " + userService.getUserById(r.getUser_id()).getUsername());
                    }

                    System.out.print("\nComment: ");
                    scan.nextLine();
                    String comment = scan.nextLine();

                    System.out.print("\nRating [1 - 5]: ");
                    int rating = scan.nextInt();

                    Review review = new Review(UUID.randomUUID().toString(), comment, rating, user.getId(), selectedResto.getId());
                    reviewService.saveReview(review);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("\nInvalid input!");
                }

                break exit;
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
