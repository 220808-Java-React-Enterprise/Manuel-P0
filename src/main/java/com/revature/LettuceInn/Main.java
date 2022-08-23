package com.revature.LettuceInn;

import com.revature.LettuceInn.daos.UserDAO;
import com.revature.LettuceInn.services.UserService;
import com.revature.LettuceInn.ui.LoginMenu;

public class Main {
    public static void main(String[] args) {
        /* dependency injection */
//        UserDAO userDAO = new UserDAO();
//        UserService userService = new UserService(userDAO);
//        new LoginMenu(userService).start();

        new LoginMenu(new UserService(new UserDAO())).start();
    }
}
