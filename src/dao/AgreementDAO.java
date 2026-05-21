package dao;

import db.DBConnection;
import java.sql.*;

public class AgreementDAO {

    public static void createAgreement(int projectId, String terms, Date startDate, Date endDate) {
        try (Connection con = DBConnection.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO agreement(project_id, terms, start_date, end_date) VALUES (?, ?, ?, ?)"
            );
            ps.setInt(1, projectId);
            ps.setString(2, terms);
            ps.setDate(3, startDate);
            ps.setDate(4, endDate);
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }
}