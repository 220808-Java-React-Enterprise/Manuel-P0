package com.revature.yolp.daos;

import com.revature.yolp.models.Cart;
import com.revature.yolp.models.User;
import com.revature.yolp.utils.custom_exceptions.InvalidSQLException;
import com.revature.yolp.utils.database.ConnectionFactory;

import java.io.IOException;
import java.util.List;


public class CartDAO implements CrudDAO<Cart> {
    @Override
    public void save(Cart obj) throws IOException {

    }

    @Override
    public void update(Cart obj) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public Cart getById(String id) {
        return null;
    }

    @Override
    public List<Cart> getAll() {
        return null;
    }
}
