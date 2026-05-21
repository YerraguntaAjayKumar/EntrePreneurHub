package service;

import dao.AgreementDAO;
import java.sql.Date;

/**
 * Agreement Service Layer
 * Handles business logic for agreements
 */
public class AgreementService {

    /**
     * Create a new agreement
     * @param projectId Project ID
     * @param terms Agreement terms
     * @param startDate Start date
     * @param endDate End date
     * @return true if successful
     */
    public static boolean createAgreement(int projectId, String terms, Date startDate, Date endDate) {
        if (projectId <= 0) {
            throw new IllegalArgumentException("Invalid project ID");
        }
        
        if (terms == null || terms.isEmpty()) {
            throw new IllegalArgumentException("Agreement terms are required");
        }
        
        if (terms.length() > 300) {
            throw new IllegalArgumentException("Terms cannot exceed 300 characters");
        }
        
        if (startDate == null || endDate == null) {
            throw new IllegalArgumentException("Start and end dates are required");
        }
        
        if (endDate.before(startDate)) {
            throw new IllegalArgumentException("End date must be after start date");
        }
        
        try {
            AgreementDAO.createAgreement(projectId, terms, startDate, endDate);
            return true;
        } catch (Exception e) {
            throw new RuntimeException("Failed to create agreement: " + e.getMessage(), e);
        }
    }

    /**
     * Validate date format (YYYY-MM-DD)
     * @param dateString Date string to validate
     * @return Date object if valid
     * @throws IllegalArgumentException if invalid format
     */
    public static Date validateDateFormat(String dateString) {
        try {
            return Date.valueOf(dateString);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid date format. Use YYYY-MM-DD");
        }
    }
}
