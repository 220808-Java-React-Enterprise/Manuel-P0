package com.revature.yolp.daos;

import com.revature.yolp.models.Order;
import com.revature.yolp.models.Painting;
import com.revature.yolp.models.User;
import com.revature.yolp.utils.custom_exceptions.InvalidSQLException;
import com.revature.yolp.utils.database.ConnectionFactory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

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
    public List<Painting> getAll(){
        return null;
    }

    public List<Painting> getAllAvailable()
    {
        List<Painting> available = new ArrayList<Painting>();

        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("select * from painting p where p.is_available = 'true';");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                //We are calling the painting with the cost constructor here because if we are checking cart it means that the painting HAS to be available and therefore have a cost
                Painting paint = new Painting(rs.getString("id"),rs.getString("name"),rs.getString("author"),rs.getString("image"),rs.getBoolean("is_available"),rs.getDouble("cost"));
                available.add(paint);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new InvalidSQLException("An error occurred when tyring to save to the database.");
        }

        return available;
    }

    public void addToCart(Painting p){

    }

}
