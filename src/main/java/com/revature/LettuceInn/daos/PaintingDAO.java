package com.revature.LettuceInn.daos;

import com.revature.LettuceInn.models.Painting;
import com.revature.LettuceInn.utils.custom_exceptions.InvalidSQLException;
import com.revature.LettuceInn.utils.database.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class PaintingDAO implements CrudDAO<Painting>{
    @Override
    public void save(Painting obj){
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO painting (id, name, author, image, is_available, cost, warehouse_id, person_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, obj.getId());
            ps.setString(2, obj.getName());
            ps.setString(3, obj.getAuthor());
            ps.setString(4, obj.getImage());
            ps.setBoolean(5, obj.isAvailable());
            ps.setDouble(6, obj.getCost());
            ps.setString(7, obj.getLocation());
            ps.setString(8, obj.getLocation());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new InvalidSQLException("An error occurred when tyring to save to the database.");
        }
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

    public void makeUnavailable(Painting p){
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("UPDATE painting SET is_available='false' WHERE id= ?");
            ps.setString(1, p.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new InvalidSQLException("An error occurred when tyring to save to the database.");
        }
    }

    public List<Painting> getAllAvailable()
    {
        List<Painting> available = new ArrayList<Painting>();

        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("select * from painting p where p.is_available = 'true';");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                //We are calling the painting with the cost constructor here because if we are checking cart it means that the painting HAS to be available and therefore have a cost
                Painting paint = new Painting(rs.getString("id"),rs.getString("name"),rs.getString("author"),rs.getString("image"),rs.getBoolean("is_available"),rs.getString("warehouse_id"),rs.getDouble("cost"));
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
