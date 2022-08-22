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

    public void paintingToCart(String person_id,Painting paint){
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO painting_in_cart (painting_id, cart_id) VALUES (?, ?)");
            ps.setString(1, paint.getId());
            ps.setString(2, person_id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new InvalidSQLException("An error occurred when tyring to save to the database.");
        }
    }
    public void removeFromCart(Cart cart, Painting paint){
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("delete from painting_in_cart WHERE painting_id = ? and cart_id = ?");
            ps.setString(1, paint.getId());
            ps.setString(2, cart.getId());

            ps.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
            throw new InvalidSQLException("An error occurred when tyring to save to the database.");
        }
    }

    public void removeAllFromCart(){

    }
    @Override
    public Cart getById(String id) {
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM cart WHERE person_id = ?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Cart(rs.getString("id"), rs.getInt("no_items"), rs.getString("person_id"));
            }

        } catch (SQLException e) {
            throw new InvalidSQLException("An error occurred when tyring to save to the database.");
        }

        return null;
    }

    @Override
    public List<Cart> getAll() {
        return null;
    }

    public static List<Painting> getAllInCart(String id){
        List<Painting> paintings = new ArrayList<Painting>();

        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM painting p, painting_in_cart pc, cart c WHERE p.id = pc.painting_id and pc.cart_id = c.id and c.person_id = ?;");
            ps.setString(1,id);
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

    public Painting getPaintingInCart(String person_id, String painting_id){
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            //Gets here

            PreparedStatement ps = con.prepareStatement("select * from painting p, painting_in_cart pc, cart c where p.id = pc.painting_id and pc.cart_id = c.id and c.person_id = ? and p.id = ?;");
            ps.setString(1, person_id);
            ps.setString(2, painting_id);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
               Painting paint = new Painting(rs.getString("id"),rs.getString("name"),rs.getString("author"),rs.getString("image"),rs.getBoolean("is_available"),rs.getDouble("cost"));
               return paint;
            }
                

        } catch (SQLException e) {
            e.printStackTrace();
            throw new InvalidSQLException("An error occurred when trying to save to the database.");
        }



        return null;
    }




}
