package service;

import dao.UserDAO;
import models.User;

/**
 * Authentication Service Layer
 * Handles all business logic for authentication
 */
public class AuthService {

    /**
     * Validates user login credentials
     * @param email User email
     * @param password User password
     * @return User object if valid, null otherwise
     */
    public static User validateLogin(String email, String password) {
        if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Email and password cannot be empty");
        }
        
        try {
            User user = UserDAO.login(email, password);
            if (user != null && user.getStatus().equals("APPROVED")) {
                return user;
            }
            return null;
        } catch (Exception e) {
            throw new RuntimeException("Login failed: " + e.getMessage(), e);
        }
    }

    /**
     * Registers a new user
     * @param email User email
     * @param password User password
     * @param firstName First name
     * @param lastName Last name
     * @param role User role (ENTREPRENEUR, INVESTOR)
     * @return true if registration successful
     */
    public static boolean registerUser(String email, String password, String firstName, 
                                       String lastName, String role) {
        if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Email and password are required");
        }
        
        if (firstName == null || firstName.isEmpty() || lastName == null || lastName.isEmpty()) {
            throw new IllegalArgumentException("First and last names are required");
        }
        
        if (!role.equals("ENTREPRENEUR") && !role.equals("INVESTOR")) {
            throw new IllegalArgumentException("Invalid role. Use ENTREPRENEUR or INVESTOR");
        }
        
        try {
            UserDAO.register(email, password, firstName, lastName, role);
            return true;
        } catch (Exception e) {
            throw new RuntimeException("Registration failed: " + e.getMessage(), e);
        }
    }

    /**
     * Checks if email already exists
     * @param email Email to check
     * @return true if exists, false otherwise
     */
    public static boolean emailExists(String email) {
        try {
            return UserDAO.emailExists(email);
        } catch (Exception e) {
            throw new RuntimeException("Error checking email: " + e.getMessage(), e);
        }
    }

    /**
     * Get user details by ID
     * @param userId User ID
     * @return User object or null
     */
    public static User getUserById(int userId) {
        try {
            return UserDAO.getUserById(userId);
        } catch (Exception e) {
            throw new RuntimeException("Error fetching user: " + e.getMessage(), e);
        }
    }
}
