package service;

import dao.IdeaDAO;
import models.Idea;
import java.util.List;

/**
 * Idea Service Layer
 * Handles business logic for ideas
 */
public class IdeaService {

    /**
     * Add a new idea for an entrepreneur
     * @param userId Entrepreneur user ID
     * @param title Idea title
     * @param description Idea description
     * @return true if successful
     */
    public static boolean addIdea(int userId, String title, String description) {
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("Idea title is required");
        }
        
        if (description == null || description.isEmpty()) {
            throw new IllegalArgumentException("Idea description is required");
        }
        
        if (title.length() > 100) {
            throw new IllegalArgumentException("Title cannot exceed 100 characters");
        }
        
        if (description.length() > 300) {
            throw new IllegalArgumentException("Description cannot exceed 300 characters");
        }
        
        try {
            IdeaDAO.addIdea(userId, title, description);
            return true;
        } catch (Exception e) {
            throw new RuntimeException("Failed to add idea: " + e.getMessage(), e);
        }
    }

    /**
     * Get all ideas
     * @return List of all ideas
     */
    public static List<Idea> getAllIdeas() {
        try {
            return IdeaDAO.getIdeas();
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch ideas: " + e.getMessage(), e);
        }
    }

    /**
     * Get ideas by specific user
     * @param userId Entrepreneur user ID
     * @return List of user's ideas
     */
    public static List<Idea> getUserIdeas(int userId) {
        try {
            return IdeaDAO.getIdeasByUser(userId);
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch user ideas: " + e.getMessage(), e);
        }
    }

    /**
     * Get idea by ID
     * @param ideaId Idea ID
     * @return Idea object or null
     */
    public static Idea getIdeaById(int ideaId) {
        try {
            return IdeaDAO.getIdeaById(ideaId);
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch idea: " + e.getMessage(), e);
        }
    }

    /**
     * Update idea status
     * @param ideaId Idea ID
     * @param status New status
     * @return true if successful
     */
    public static boolean updateIdeaStatus(int ideaId, String status) {
        if (status == null || status.isEmpty()) {
            throw new IllegalArgumentException("Status is required");
        }
        
        try {
            IdeaDAO.updateIdeaStatus(ideaId, status);
            return true;
        } catch (Exception e) {
            throw new RuntimeException("Failed to update idea: " + e.getMessage(), e);
        }
    }

    /**
     * Delete an idea
     * @param ideaId Idea ID
     * @return true if successful
     */
    public static boolean deleteIdea(int ideaId) {
        try {
            IdeaDAO.deleteIdea(ideaId);
            return true;
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete idea: " + e.getMessage(), e);
        }
    }
}
