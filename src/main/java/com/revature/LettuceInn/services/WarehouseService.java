package com.revature.LettuceInn.services;


import com.revature.LettuceInn.daos.WarehouseDAO;
import com.revature.LettuceInn.models.Warehouse;
import java.util.*;
public class WarehouseService {

    private final WarehouseDAO warehouseDAO;

    public WarehouseService(WarehouseDAO warehouseDAO){
        this.warehouseDAO = warehouseDAO;
    }

    public List<Warehouse> getWarehouses(){
        return warehouseDAO.getAll();
    }
}
