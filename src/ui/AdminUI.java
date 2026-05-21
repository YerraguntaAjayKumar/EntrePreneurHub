package ui;

import dao.UserDAO;
import models.User;
import ui.components.Header;
import ui.components.Sidebar;
import ui.components.TableView;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Admin Dashboard with advanced UI components
 */
public class AdminUI extends JFrame {

    private JFrame frame;
    private Header header;
    private Sidebar sidebar;
    private TableView usersTable;
    private JTextField userIdField;
    private JButton approveBtn;
    private User currentUser;

    public AdminUI(User admin) {
        this.currentUser = admin;
        
        frame = new JFrame("EntrepreneurHub - Admin Dashboard");
        frame.setSize(900, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());
        
        // Header
        header = new Header("Admin Dashboard - User Management", 
                           admin.getEmail(), admin.getRole(), 
                           () -> logout());
        frame.add(header, BorderLayout.NORTH);
        
        // Main content panel
        JPanel contentPanel = new JPanel(new BorderLayout());
        
        // Sidebar
        sidebar = new Sidebar();
        sidebar.addMenuItem("Dashboard", () -> loadUsers());
        sidebar.addMenuItem("Pending Users", () -> showPendingUsers());
        sidebar.addMenuItem("All Users", () -> showAllUsers());
        sidebar.addMenuItem("System Stats", () -> showStats());
        sidebar.setActiveItem("Dashboard");
        contentPanel.add(sidebar, BorderLayout.WEST);
        
        // Center panel
        JPanel centerPanel = new JPanel(new BorderLayout(10, 10));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        // Title
        JLabel titleLabel = new JLabel("Pending User Approvals");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        centerPanel.add(titleLabel, BorderLayout.NORTH);
        
        // Table
        usersTable = new TableView(new String[]{"User ID", "Email", "First Name", "Last Name", "Role", "Status"});
        usersTable.setColumnWidth(0, 60);
        usersTable.setColumnWidth(1, 150);
        usersTable.setColumnWidth(2, 100);
        usersTable.setColumnWidth(3, 100);
        usersTable.setColumnWidth(4, 100);
        usersTable.setColumnWidth(5, 80);
        centerPanel.add(usersTable, BorderLayout.CENTER);
        
        // Action panel
        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        actionPanel.setBackground(new Color(245, 245, 245));
        actionPanel.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));
        
        JLabel idLabel = new JLabel("User ID to Approve:");
        userIdField = new JTextField(10);
        approveBtn = new JButton("Approve User");
        approveBtn.setBackground(new Color(46, 204, 113));
        approveBtn.setForeground(Color.WHITE);
        approveBtn.setFocusPainted(false);
        approveBtn.addActionListener(e -> approveUser());
        
        JButton refreshBtn = new JButton("Refresh");
        refreshBtn.setBackground(new Color(52, 152, 219));
        refreshBtn.setForeground(Color.WHITE);
        refreshBtn.setFocusPainted(false);
        refreshBtn.addActionListener(e -> loadUsers());
        
        actionPanel.add(idLabel);
        actionPanel.add(userIdField);
        actionPanel.add(approveBtn);
        actionPanel.add(refreshBtn);
        
        centerPanel.add(actionPanel, BorderLayout.SOUTH);
        contentPanel.add(centerPanel, BorderLayout.CENTER);
        
        frame.add(contentPanel, BorderLayout.CENTER);
        frame.setVisible(true);
        
        // Load initial data
        loadUsers();
    }
    
    private void loadUsers() {
        sidebar.setActiveItem("Dashboard");
        showPendingUsers();
    }
    
    private void showPendingUsers() {
        try {
            usersTable.clearTable();
            List<User> users = UserDAO.getPendingUsers();
            for (User u : users) {
                usersTable.addRow(new Object[]{
                    u.getId(),
                    u.getEmail(),
                    u.getFname(),
                    u.getLname(),
                    u.getRole(),
                    u.getStatus()
                });
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Error loading users: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void showAllUsers() {
        try {
            usersTable.clearTable();
            List<User> users = UserDAO.getAllApprovedUsers();
            for (User u : users) {
                usersTable.addRow(new Object[]{
                    u.getId(),
                    u.getEmail(),
                    u.getFname(),
                    u.getLname(),
                    u.getRole(),
                    u.getStatus()
                });
            }
            // Also add pending users
            List<User> pending = UserDAO.getPendingUsers();
            for (User u : pending) {
                usersTable.addRow(new Object[]{
                    u.getId(),
                    u.getEmail(),
                    u.getFname(),
                    u.getLname(),
                    u.getRole(),
                    u.getStatus()
                });
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Error loading users: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void showStats() {
        try {
            List<User> pending = UserDAO.getPendingUsers();
            List<User> approved = UserDAO.getAllApprovedUsers();
            
            String stats = "System Statistics\n\n" +
                          "Total Users: " + (pending.size() + approved.size()) + "\n" +
                          "Pending Approvals: " + pending.size() + "\n" +
                          "Approved Users: " + approved.size() + "\n\n" +
                          "Entrepreneurs: " + countByRole(approved, "ENTREPRENEUR") + " \n" +
                          "Investors: " + countByRole(approved, "INVESTOR");
            
            JOptionPane.showMessageDialog(frame, stats, "System Statistics", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private int countByRole(List<User> users, String role) {
        return (int) users.stream().filter(u -> u.getRole().equals(role)).count();
    }
    
    private void approveUser() {
        String idText = userIdField.getText().trim();
        
        if (idText.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please enter User ID");
            return;
        }
        
        try {
            int userId = Integer.parseInt(idText);
            
            // Confirmation dialog
            int confirm = JOptionPane.showConfirmDialog(frame, 
                    "Approve user with ID: " + userId + "?",
                    "Confirm Approval", JOptionPane.YES_NO_OPTION);
            
            if (confirm == JOptionPane.YES_OPTION) {
                UserDAO.approve(userId);
                JOptionPane.showMessageDialog(frame, "User approved successfully!");
                userIdField.setText("");
                loadUsers();
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Please enter a valid User ID",
                    "Invalid Input", JOptionPane.WARNING_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void logout() {
        int confirm = JOptionPane.showConfirmDialog(frame, 
                "Are you sure you want to logout?",
                "Logout", JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            frame.dispose();
            new LoginUI();
        }
    }
}