package com.revature.yolp.daos;

import com.revature.yolp.models.Cart;
import com.revature.yolp.models.Painting;

import com.revature.yolp.models.Restaurant;
import com.revature.yolp.models.User;
import com.revature.yolp.utils.custom_exceptions.InvalidSQLException;
import com.revature.yolp.utils.database.ConnectionFactory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


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

    public static List<Painting> getAllInCart(String id){
        List<Painting> paintings = new ArrayList<Painting>();

        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM painting p, painting_in_cart pc, cart c WHERE p.id = pc.painting_id and pc.cart_id = c.id and p.id='1';");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                //We are calling the painting with the cost constructor here because if we are checking cart it means that the painting HAS to be available and therefore have a cost
                Painting paint = new Painting(rs.getString("id"),rs.getString("name"),rs.getString("author"),rs.getString("image"),rs.getBoolean("is_available"),rs.getDouble("cost"));
                paintings.add(paint);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new InvalidSQLException("An error occurred when tyring to save to the database.");
        }


        return paintings;
    }
}
