package com.revature.yolp.daos;

import com.revature.yolp.models.Warehouse;
import com.revature.yolp.models.User;
import com.revature.yolp.utils.custom_exceptions.InvalidSQLException;
import com.revature.yolp.utils.database.ConnectionFactory;

import java.io.IOException;
import java.util.List;

public class WarehouseDAO implements CrudDAO<Warehouse>{

    @Override
    public void save(Warehouse obj) throws IOException {

    }

    @Override
    public void update(Warehouse obj) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public Warehouse getById(String id) {
        return null;
    }

    @Override
    public List<Warehouse> getAll() {
        return null;
    }
}
