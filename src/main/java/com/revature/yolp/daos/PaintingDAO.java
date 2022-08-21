package com.revature.yolp.daos;

import com.revature.yolp.models.Painting;
import com.revature.yolp.models.User;
import com.revature.yolp.utils.custom_exceptions.InvalidSQLException;
import com.revature.yolp.utils.database.ConnectionFactory;

import java.io.IOException;
import java.util.List;

public class PaintingDAO implements CrudDAO<Painting>{
    @Override
    public void save(Painting obj) throws IOException {

    }

    @Override
    public void update(Painting obj) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public Painting getById(String id) {
        return null;
    }

    @Override
    public List<Painting> getAll() {
        return null;
    }
}
