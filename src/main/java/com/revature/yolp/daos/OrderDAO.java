package com.revature.yolp.daos;

import com.revature.yolp.models.Order;
import com.revature.yolp.models.Painting;
import com.revature.yolp.models.User;
import com.revature.yolp.utils.custom_exceptions.InvalidSQLException;
import com.revature.yolp.utils.database.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import java.io.IOException;
import java.util.List;

public class OrderDAO implements CrudDAO<Order>{
    @Override
    public void save(Order obj) {
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO \"order\" (id, no_items,total_cost,date,person_id,warehouse_id) VALUES (?, ?, ?, ?, ?, ?)");
            ps.setString(1, obj.getId());
            ps.setInt(2, obj.getNumItems());
            ps.setDouble(3, obj.getTotalCost());
            ps.setString(4, obj.getDate());
            ps.setString(5, obj.getPerson());
            ps.setString(6, obj.getLocation());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new InvalidSQLException("An error occurred when tyring to save to the database.");
        }

    }

    @Override
    public void update(Order obj) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public Order getById(String id) {
        return null;
    }

    @Override
    public List<Order> getAll() {
        return null;
    }

    public List<Order> getAllById(String id){
        List<Order> orders = new ArrayList<Order>();

        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            //PreparedStatement ps = con.prepareStatement("select * from painting p, painting_ordered po, \"order\" o where p.id = po.painting_id and po.order_id = o.id and o.person_id = '1';");
            PreparedStatement ps = con.prepareStatement("select * from \"order\" o where o.person_id = ?");
            ps.setString(1, id);


            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                //We are calling the painting with the cost constructor here because if we are checking cart it means that the painting HAS to be available and therefore have a cost
                //Painting paint = new Painting(rs.getString("id"),rs.getString("name"),rs.getString("author"),rs.getString("image"),rs.getBoolean("is_available"),rs.getDouble("cost"));
                Order order = new Order(rs.getString("id"),rs.getInt("no_items"),rs.getDouble("total_cost"),rs.getString("date"),rs.getString("person_id"),rs.getString("warehouse_id"));
                 orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new InvalidSQLException("An error occurred when tyring to save to the database.");
        }


        return orders;
    }

    public Order getOrderByPainting(String painting_id){
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            //Gets here
            PreparedStatement ps = con.prepareStatement("SELECT * FROM \"order\" o, painting_ordered po WHERE po.order_id = o.id and po.painting_id = ?");
            ps.setString(1, painting_id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                Order order = new Order(rs.getString("id"),rs.getInt("no_items"),rs.getDouble("total_cost"),rs.getString("date"),rs.getString("person_id"),rs.getString("warehouse_id"));
                return order;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new InvalidSQLException("An error occurred when trying to save to the database.");
        }

        return null;
    }

   /* public static List<Painting> getAllInOrder(String id){
        List<Painting> paintings = new ArrayList<Painting>();

        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM painting p, painting_ordered po, order o WHERE p.id = po.painting_id and po.order_id = o.id and o.id = ?;");
            ps.setString(1,id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                //We are calling the painting with the cost constructor here because if we are checking cart it means that the painting HAS to be available and therefore have a cost
                Painting paint = new Painting(rs.getString("id"),rs.getString("name"),rs.getString("author"),rs.getString("image"),rs.getBoolean("is_available"),rs.getString("warehouse_id"),rs.getDouble("cost"));
                paintings.add(paint);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new InvalidSQLException("An error occurred when tyring to save to the database.");
        }


        return paintings;
    }
*/
}
