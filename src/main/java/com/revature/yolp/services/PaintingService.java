package com.revature.yolp.services;


import com.revature.yolp.daos.PaintingDAO;
import com.revature.yolp.models.Painting;
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

    }

}
