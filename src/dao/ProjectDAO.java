package dao;

import db.DBConnection;
import java.sql.*;

public class ProjectDAO {

    public static void createProject(int ideaId, int investorId) {
        try (Connection con = DBConnection.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO project(idea_id, investor_id, status) VALUES (?, ?, 'STARTED')"
            );
            ps.setInt(1, ideaId);
            ps.setInt(2, investorId);
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public static int getLatestProjectId() throws Exception {
        try (Connection con = DBConnection.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT MAX(project_id) FROM project")) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        return 0;
    }

    public static void updateProjectStatus(int projectId, String status) throws Exception {
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement("UPDATE project SET status=? WHERE project_id=?")) {
            ps.setString(1, status);
            ps.setInt(2, projectId);
            ps.executeUpdate();
        }
    }
}