package dao;

import db.DBConnection;
import models.Idea;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IdeaDAO {

    public static void addIdea(int userId, String title, String desc) {
        try (Connection con = DBConnection.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO idea(user_id, title, description) VALUES (?, ?, ?)"
            );
            ps.setInt(1, userId);
            ps.setString(2, title);
            ps.setString(3, desc);
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public static List<Idea> getIdeas() throws Exception {
        List<Idea> ideas = new ArrayList<>();
        try (Connection con = DBConnection.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM idea")) {
            while (rs.next()) {
                ideas.add(new Idea(
                        rs.getInt("idea_id"),
                        rs.getInt("user_id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("status")
                ));
            }
        }
        return ideas;
    }

    public static List<Idea> getIdeasByUser(int userId) throws Exception {
        List<Idea> ideas = new ArrayList<>();
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement("SELECT * FROM idea WHERE user_id = ?")) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ideas.add(new Idea(
                            rs.getInt("idea_id"),
                            rs.getInt("user_id"),
                            rs.getString("title"),
                            rs.getString("description"),
                            rs.getString("status")
                    ));
                }
            }
        }
        return ideas;
    }

    public static Idea getIdeaById(int ideaId) throws Exception {
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement("SELECT * FROM idea WHERE idea_id=?")) {
            ps.setInt(1, ideaId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Idea(
                            rs.getInt("idea_id"),
                            rs.getInt("user_id"),
                            rs.getString("title"),
                            rs.getString("description"),
                            rs.getString("status")
                    );
                }
            }
        }
        return null;
    }

    public static void updateIdeaStatus(int ideaId, String status) throws Exception {
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement("UPDATE idea SET status=? WHERE idea_id=?")) {
            ps.setString(1, status);
            ps.setInt(2, ideaId);
            ps.executeUpdate();
        }
    }

    public static void deleteIdea(int ideaId) throws Exception {
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement("DELETE FROM idea WHERE idea_id=?")) {
            ps.setInt(1, ideaId);
            ps.executeUpdate();
        }
    }
}