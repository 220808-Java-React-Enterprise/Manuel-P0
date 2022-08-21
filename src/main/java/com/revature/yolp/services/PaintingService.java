package com.revature.yolp.services;


import com.revature.yolp.daos.PaintingDAO;
import com.revature.yolp.models.Painting;
public class PaintingService {

    private final PaintingDAO paintingDAO;

    public PaintingService(PaintingDAO paintingDAO){
        this.paintingDAO = paintingDAO;
    }
}
