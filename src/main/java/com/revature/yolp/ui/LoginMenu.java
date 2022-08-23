package com.revature.yolp.ui;

import com.revature.yolp.daos.RestaurantDAO;
import com.revature.yolp.daos.ReviewDAO;
import com.revature.yolp.daos.UserDAO;
import com.revature.yolp.daos.PaintingDAO;
import com.revature.yolp.daos.CartDAO;
import com.revature.yolp.daos.OrderDAO;
import com.revature.yolp.daos.*;

import com.revature.yolp.models.User;
import com.revature.yolp.services.RestaurantService;
import com.revature.yolp.services.ReviewService;
import com.revature.yolp.services.UserService;
import com.revature.yolp.services.PaintingService;
import com.revature.yolp.services.CartService;
import com.revature.yolp.services.*;
import com.revature.yolp.utils.custom_exceptions.InvalidUserException;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class LoginMenu implements IMenu {
    private final UserService userService;

    public LoginMenu(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void start() {
        String userInput = "";
        Scanner scan = new Scanner(System.in);

        exit:
        {
            while (true) {
                System.out.println("\nWelcome to the LettuceInn Antique Painting Shop!");
                System.out.println("[1] Login");
                System.out.println("[2] Signup");
                System.out.println("[x] Exit!");

                System.out.print("\nEnter: ");
                userInput = scan.nextLine();

                switch (userInput) {
                    case "1":
                        login();
                        break;
                    case "2":
                        User user = signup();
                        System.out.println("checkpoint");
                        userService.register(user);
                        //new MainMenu(user, new UserService(new UserDAO()), new RestaurantService(new RestaurantDAO()), new ReviewService(new ReviewDAO())).start();
                        new MainMenu(user, new UserService(new UserDAO()), new RestaurantService(new RestaurantDAO()), new ReviewService(new ReviewDAO()),new PaintingService(new PaintingDAO()),new CartService(new CartDAO()),new OrderService(new OrderDAO())).start();
                        break;
                    case "x":
                        System.out.println("\nGoodbye!");
                        break exit;
                    default:
                        System.out.println("\nInvalid input!");
                        break;
                }
            }
        }
    }

    private void login() {
        String username = "";
        String password = "";
        Scanner scan = new Scanner(System.in);

        System.out.println("\nLogging in...");

        exit: {
            while (true) {
                System.out.print("\nEnter username: ");
                username = scan.nextLine();

                System.out.print("\nEnter password: ");
                password = scan.nextLine();

                try {
                    User user = userService.login(username, password);
                    if (user.getAdmin().equals(true)) new AdminMenu(user, new UserService(new UserDAO()),new PaintingService(new PaintingDAO()),new CartService(new CartDAO()),new OrderService(new OrderDAO()),new WarehouseService(new WarehouseDAO())).start();
                    else new MainMenu(user, new UserService(new UserDAO()), new RestaurantService(new RestaurantDAO()), new ReviewService(new ReviewDAO()),new PaintingService(new PaintingDAO()),new CartService(new CartDAO()),new OrderService(new OrderDAO())).start();
                    break exit;
                } catch (InvalidUserException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    private User signup() {
        String username = "";
        String password = "";
        String password2 = "";
        String firstName = "";
        String lastName = "";
        String fullName = "";
        String email = "";


        User user;
        Scanner scan = new Scanner(System.in);

        System.out.println("\nCreating account...");

        exit:
        {
            while (true) {
                usernameExit:
                {
                    while (true) {
                        System.out.print("\nEnter a username: ");
                        username = scan.nextLine();

                        try {
                            userService.isValidUsername(username);
                            userService.isDuplicateUsername(username);
                            break usernameExit;
                        } catch (InvalidUserException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }

                passwordExit:
                {
                    while (true) {
                        try {
                            System.out.print("\nEnter a password: ");
                            password = scan.nextLine();

                            userService.isValidPassword(password);

                            System.out.print("\nRe enter password: ");
                            password2 = scan.nextLine();

                            userService.isSamePassword(password, password2);
                            break passwordExit;
                        } catch (InvalidUserException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }

                NameExit:
                {
                    while (true) {
                        try {
                            System.out.print("\nEnter first name: ");
                            firstName = scan.nextLine();


                            System.out.print("\nEnter last name: ");
                            lastName = scan.nextLine();

                            fullName = firstName + " " + lastName;

                            break NameExit;
                        } catch (InvalidUserException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }

                EmailExit:
                {
                    while (true) {
                        try {
                            System.out.print("\nEnter email: ");
                            email = scan.nextLine();

                            userService.isValidEmail(email);
                            userService.isDuplicateEmail(email);
                            break EmailExit;
                        } catch (InvalidUserException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }


               confirmExit: {
                   while (true) {
                       System.out.println("\nIs this correct (y/n):");
                       System.out.println("Username: " + username + "\nPassword: " + password + "\nFull Name: " + fullName + "\nEmail: " + email);
                       System.out.print("\nEnter: ");

                       switch (scan.nextLine().toLowerCase()) {
                           case "y":
                               user = new User(UUID.randomUUID().toString(), username, password, fullName, email, false);
                               return user;
                           case "n":
                               System.out.println("\nRestarting...");
                               break confirmExit;
                           default:
                               System.out.println("\nInvalid input!");
                               break;
                       }
                   }
               }

            }
        }
    }
}
