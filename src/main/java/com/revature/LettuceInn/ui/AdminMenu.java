package com.revature.LettuceInn.ui;

import com.revature.LettuceInn.models.*;
import com.revature.LettuceInn.services.*;
import java.util.*;

import com.revature.LettuceInn.utils.custom_exceptions.InvalidPaintingException;


import java.util.Scanner;

public class AdminMenu implements IMenu {
    private final User user;
    private final UserService userService;
    private final PaintingService paintingService;
    private final CartService cartService;
    private final OrderService orderService;
    private final WarehouseService warehouseService;

    public AdminMenu(User user, UserService userService, PaintingService paintingService, CartService cartService, OrderService orderService, WarehouseService warehouseService) {
        this.user = user;
        this.userService = userService;
        this.paintingService = paintingService;
        this.cartService = cartService;
        this.orderService = orderService;
        this.warehouseService = warehouseService;
    }

    @Override
    public void start() {
        Scanner scan = new Scanner(System.in);
        System.out.println("\nWelcome to the admin menu " + user.getUsername() + "!");
        exit:
        {
            while (true) {
                System.out.println("\n[1] Add painting to warehouse");
                System.out.println("[2] ");
                System.out.println("[x] Exit Admin Menu");
                switch (scan.nextLine()) {
                    case "1":
                        addPainting();
                        break;
                    case "2":
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

    private void addPainting() {
        String name = "";
        String author = "";
        String location = "";
        double cost = 0;
        Warehouse selected = new Warehouse();

        User user;
        Scanner scan = new Scanner(System.in);
        List<Warehouse> warehouses = warehouseService.getWarehouses();



        nameExit:
        {
            while (true) {
                System.out.print("\nEnter Product Name: ");
                name = scan.nextLine();

                try {
                    paintingService.isValidName(name);
                    break nameExit;
                } catch (InvalidPaintingException e) {
                    System.out.println(e.getMessage());
                }
            }
        }

        authorExit:
        {
            while (true) {
                System.out.print("\nEnter Product Creator: ");
                author = scan.nextLine();

                try {
                    paintingService.isValidCreator(author);
                    break authorExit;
                } catch (InvalidPaintingException e) {
                    System.out.println(e.getMessage());
                }
            }
        }



        exitCost:{
            while(true) {
                System.out.println("\nEnter Cost: ");
                String tempCost = scan.nextLine();
                try {
                    if (isNumeric(tempCost)) {
                        cost = Double.parseDouble(tempCost);
                        paintingService.isValidCost(cost);
                        break exitCost;
                    }
                }catch(InvalidPaintingException e){
                    System.out.println(e.getMessage());
                }
            }
        }

        exitWarehouse:
        {
            while(true) {
                for (int i = 0; i < warehouses.size(); i++) {
                    System.out.println("[" + i + "] " + warehouses.get(i).getName());
                }
                System.out.println("\nPlease select a warehouse location, or [x] to return: ");
                String input = scan.nextLine();
                if(input == "x"){
                    break exitWarehouse;
                }
                else if(isNumeric(input) && Integer.parseInt(input) >= 0 && Integer.parseInt(input) < warehouses.size()){
                    System.out.println(Integer.parseInt(input));
                    Warehouse warehouseSelected = warehouses.get(Integer.parseInt(input));
                    System.out.println(warehouseSelected.getName());
                    selected = warehouseSelected;

                    System.out.println("Warehouse Selected");
                    System.out.println(warehouseSelected.getId());
                    System.out.println(selected.getId());
                    location = warehouseSelected.getId();
                    break exitWarehouse;
                }
                else{
                    System.out.println("\nInvalid input!");
                }

            }
        }

        confirmExit: {
            while(true){
                System.out.println("Is this correct? (y/n)");
                System.out.println("Name: " + name);
                System.out.println("Creator: " + author);
                System.out.println("Location: " + selected.getName());
                System.out.println("Cost: " + cost);

                switch (scan.nextLine().toLowerCase()) {
                    case "y":
                        Painting paint = new Painting(UUID.randomUUID().toString(),name,author,"placeholder",true,selected.getId(),cost);
                        paintingService.newPainting(paint);
                        //Go into paintingDAO to actually add it
                        System.out.println(selected.getId());
                        System.out.println("\npainting done!");
                        break confirmExit;
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
    private static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

}
