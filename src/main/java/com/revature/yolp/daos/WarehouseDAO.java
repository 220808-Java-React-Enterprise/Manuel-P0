package com.revature.yolp.daos;

import com.revature.yolp.models.Warehouse;
import com.revature.yolp.models.User;
import com.revature.yolp.utils.custom_exceptions.InvalidSQLException;
import com.revature.yolp.utils.database.ConnectionFactory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

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
        List<Warehouse> locations = new ArrayList<Warehouse>();
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM warehouse");
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                Warehouse w = new Warehouse(rs.getString("id"),rs.getString("name"),rs.getString("address_street"),rs.getString("address_city"),rs.getString("address_city"),rs.getInt("address_zip"));
                locations.add(w);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new InvalidSQLException("An error occurred when trying to save to the database.");
        }


        return locations;
    }
}
