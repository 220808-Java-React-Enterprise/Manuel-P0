package com.revature.yolp.services;


import com.revature.yolp.daos.WarehouseDAO;
import com.revature.yolp.models.Warehouse;
public class WarehouseService {

    private final WarehouseDAO warehouseDAO;

    public WarehouseService(WarehouseDAO warehouseDAO){
        this.warehouseDAO = warehouseDAO;
    }
}
