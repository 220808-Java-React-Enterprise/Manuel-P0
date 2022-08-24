package com.revature.LettuceInn.daos;

import com.revature.LettuceInn.models.User;
import com.revature.LettuceInn.utils.custom_exceptions.InvalidSQLException;
import com.revature.LettuceInn.utils.database.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDAO implements CrudDAO<User> {

    @Override
    public void save(User obj) {
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO person (id, username, password, name, email, is_admin) VALUES (?, ?, ?, ?, ?, ?)");
            ps.setString(1, obj.getId());
            ps.setString(2, obj.getUsername());
            ps.setString(3, obj.getPassword());
            ps.setString(4, obj.getName());
            ps.setString(5, obj.getEmail());
            ps.setBoolean(6, obj.getAdmin());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new InvalidSQLException("An error occurred when tyring to save to the database.");
        }
    }

    @Override
    public void update(User obj) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public User getById(String id) {
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM users WHERE id = ?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new User(rs.getString("id"), rs.getString("username"), rs.getString("password"), rs.getBoolean("isAdmin"));
            }

        } catch (SQLException e) {
            throw new InvalidSQLException("An error occurred when tyring to save to the database.");
        }

        return null;
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    public String getUsername(String username) {
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT username FROM person WHERE username = ?");
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) return rs.getString("username");

        } catch (SQLException e) {
            e.printStackTrace();
            throw new InvalidSQLException("An error occurred when trying to save to the database.");
        }

        return null;
    }

    public String getEmail(String email) {
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            //Gets here
            PreparedStatement ps = con.prepareStatement("SELECT email FROM person WHERE email = ?");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) return rs.getString("email");

        } catch (SQLException e) {
            e.printStackTrace();
            throw new InvalidSQLException("An error occurred when trying to save to the database.");
        }

        return null;
    }

    public User getUserByUsernameAndPassword(String username, String password) {
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM person WHERE username = ? AND password = ?");
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next())
                return new User(rs.getString("id"), rs.getString("username"), rs.getString("password"), rs.getBoolean("is_admin"));
        } catch (SQLException e) {
            e.printStackTrace();
            throw new InvalidSQLException("An error occurred when tyring to save to the database.");
        }

        return null;
    }
}
