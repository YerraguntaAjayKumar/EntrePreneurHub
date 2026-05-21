package service;

import dao.ProjectDAO;

/**
 * Project Service Layer
 * Handles business logic for projects
 */
public class ProjectService {

    /**
     * Create a new project from an idea
     * @param ideaId Idea ID
     * @param investorId Investor user ID
     * @return Project ID if successful
     */
    public static int createProject(int ideaId, int investorId) {
        if (ideaId <= 0 || investorId <= 0) {
            throw new IllegalArgumentException("Invalid idea or investor ID");
        }
        
        try {
            ProjectDAO.createProject(ideaId, investorId);
            return ProjectDAO.getLatestProjectId();
        } catch (Exception e) {
            throw new RuntimeException("Failed to create project: " + e.getMessage(), e);
        }
    }

    /**
     * Get latest project ID
     * @return Latest project ID
     */
    public static int getLatestProjectId() {
        try {
            return ProjectDAO.getLatestProjectId();
        } catch (Exception e) {
            throw new RuntimeException("Failed to get project ID: " + e.getMessage(), e);
        }
    }

    /**
     * Update project status
     * @param projectId Project ID
     * @param status New status
     * @return true if successful
     */
    public static boolean updateProjectStatus(int projectId, String status) {
        if (projectId <= 0 || status == null || status.isEmpty()) {
            throw new IllegalArgumentException("Invalid project ID or status");
        }
        
        try {
            ProjectDAO.updateProjectStatus(projectId, status);
            return true;
        } catch (Exception e) {
            throw new RuntimeException("Failed to update project: " + e.getMessage(), e);
        }
    }
}
