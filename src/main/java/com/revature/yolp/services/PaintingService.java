package com.revature.yolp.services;


import com.revature.yolp.daos.PaintingDAO;
import com.revature.yolp.models.Painting;
import com.revature.yolp.utils.custom_exceptions.InvalidPaintingException;

import java.math.BigDecimal;

import java.util.*;
public class PaintingService {

    private final PaintingDAO paintingDAO;

    public PaintingService(PaintingDAO paintingDAO){
        this.paintingDAO = paintingDAO;
    }

    public List<Painting> getAllAvailable(){
        return paintingDAO.getAllAvailable();
    }

    public void addToCart(Painting p){
        paintingDAO.addToCart(p);
    }

    public void makeUnavailable(List<Painting> paintings){
        for(Painting p: paintings){
            paintingDAO.makeUnavailable(p);
        }
    }

    public void newPainting(Painting p){
        paintingDAO.save(p);
    }

    public boolean isValidName(String name) {
        if (!name.matches("^(?=[a-zA-Z0-9._\\s]{2,30}$)(?!.*[_.]{2})[^_.].*[^_.]$")) throw new InvalidPaintingException("\nInvalid username! username is 8-20 characters long. no _ or . at the beginning. no __ or _. or ._ or .. inside");
        return true;
    }

    public boolean isValidCost(Double cost) {
        if (BigDecimal.valueOf(cost).scale()>2) throw new InvalidPaintingException("\nInvalid Cost! The cost must be a number with decimal places no greater than 2");
        return true;
    }

    public boolean isValidCreator(String name){
        if (!name.matches("^(?=[a-zA-Z0-9._\\s]{1,20}$)(?!.*[_.]{2})[^_.].*[^_.]$")) throw new InvalidPaintingException("\nInvalid Creator Name! Name must contain some letters no greater than 20");
        return true;
    }

}
