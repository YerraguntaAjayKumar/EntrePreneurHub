package dao;

import db.DBConnection;
import models.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    public static void register(String email, String pass, String fname, String lname, String role) {
        try (Connection con = DBConnection.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO users(email, password, fname, lname, role) VALUES (?, ?, ?, ?, ?)"
            );
            ps.setString(1, email);
            ps.setString(2, pass);
            ps.setString(3, fname);
            ps.setString(4, lname);
            ps.setString(5, role);
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public static User login(String email, String pass) {
        try (Connection con = DBConnection.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM users WHERE email=? AND password=? AND status='APPROVED'"
            );
            ps.setString(1, email);
            ps.setString(2, pass);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                User u = new User(
                        rs.getInt("user_id"),
                        rs.getString("email"),
                        rs.getString("fname"),
                        rs.getString("lname"),
                        rs.getString("role"),
                        rs.getString("status")
                );
                rs.close();
                ps.close();
                return u;
            }
            rs.close();
            ps.close();
        } catch (Exception e) { e.printStackTrace(); }
        return null;
    }

    public static List<User> getPendingUsers() throws Exception {
        List<User> users = new ArrayList<>();
        try (Connection con = DBConnection.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM users WHERE status='PENDING'")) {
            while (rs.next()) {
                users.add(new User(
                        rs.getInt("user_id"),
                        rs.getString("email"),
                        rs.getString("fname"),
                        rs.getString("lname"),
                        rs.getString("role"),
                        rs.getString("status")
                ));
            }
        }
        return users;
    }

    public static List<User> getAllApprovedUsers() throws Exception {
        List<User> users = new ArrayList<>();
        try (Connection con = DBConnection.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM users WHERE status='APPROVED'")) {
            while (rs.next()) {
                users.add(new User(
                        rs.getInt("user_id"),
                        rs.getString("email"),
                        rs.getString("fname"),
                        rs.getString("lname"),
                        rs.getString("role"),
                        rs.getString("status")
                ));
            }
        }
        return users;
    }

    public static void approve(int userId) throws Exception {
        try (Connection con = DBConnection.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "UPDATE users SET status='APPROVED' WHERE user_id=?"
            );
            ps.setInt(1, userId);
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public static boolean emailExists(String email) throws Exception {
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement("SELECT COUNT(*) FROM users WHERE email=?")) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }

    public static User getUserById(int userId) throws Exception {
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement("SELECT * FROM users WHERE user_id=?")) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new User(
                            rs.getInt("user_id"),
                            rs.getString("email"),
                            rs.getString("fname"),
                            rs.getString("lname"),
                            rs.getString("role"),
                            rs.getString("status")
                    );
                }
            }
        }
        return null;
    }
}