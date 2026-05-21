package ui.components;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;

/**
 * Reusable Header Component
 * Displays application title and user information
 */
public class Header extends JPanel {
    
    private JLabel titleLabel;
    private JLabel userInfoLabel;
    private JButton logoutBtn;
    
    public Header(String title, String userEmail, String userRole, Runnable onLogout) {
        setLayout(new BorderLayout(10, 0));
        setBackground(new Color(41, 128, 185));
        setPreferredSize(new Dimension(800, 60));
        setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        
        // Add bottom border
        setBorder(BorderFactory.createCompoundBorder(
            new MatteBorder(0, 0, 2, 0, new Color(30, 100, 180)),
            BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));
        
        // Title on left
        titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(Color.WHITE);
        add(titleLabel, BorderLayout.WEST);
        
        // User info and logout on right
        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 0));
        rightPanel.setOpaque(false);
        
        userInfoLabel = new JLabel(userRole + " | " + userEmail);
        userInfoLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        userInfoLabel.setForeground(Color.WHITE);
        rightPanel.add(userInfoLabel);
        
        logoutBtn = new JButton("Logout");
        logoutBtn.setBackground(new Color(192, 57, 43));
        logoutBtn.setForeground(Color.WHITE);
        logoutBtn.setFocusPainted(false);
        logoutBtn.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        logoutBtn.setFont(new Font("Arial", Font.PLAIN, 11));
        logoutBtn.addActionListener(e -> onLogout.run());
        rightPanel.add(logoutBtn);
        
        add(rightPanel, BorderLayout.EAST);
    }
    
    public void updateUserInfo(String userEmail, String userRole) {
        userInfoLabel.setText(userRole + " | " + userEmail);
    }
}
