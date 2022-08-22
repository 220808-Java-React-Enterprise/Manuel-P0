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
    public void save(Order obj) throws IOException {

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
            PreparedStatement ps = con.prepareStatement("select * from painting p, painting_ordered po, \"order\" o where p.id = po.painting_id and po.order_id = o.id and o.person_id = '1';");
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
}
