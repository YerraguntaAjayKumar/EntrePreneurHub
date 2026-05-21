package ui;

import service.AuthService;
import models.User;

import javax.swing.*;
import java.awt.*;

/**
 * Enhanced Login UI with modern design and proper validation
 */
public class LoginUI extends JFrame {
    
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginBtn;
    private JButton registerBtn;
    private JLabel statusLabel;
    
    public LoginUI() {
        setTitle("EntrepreneurHub - Login");
        setSize(450, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        
        // Main panel with gradient-like background
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                
                GradientPaint gradient = new GradientPaint(
                    0, 0, new Color(41, 128, 185),
                    0, getHeight(), new Color(52, 73, 94)
                );
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        mainPanel.setLayout(new BorderLayout());
        
        // Header Panel
        JPanel headerPanel = new JPanel();
        headerPanel.setOpaque(false);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(30, 20, 20, 20));
        
        JLabel appTitle = new JLabel("EntrepreneurHub");
        appTitle.setFont(new Font("Arial", Font.BOLD, 32));
        appTitle.setForeground(Color.WHITE);
        
        JLabel tagline = new JLabel("Connect Ideas with Investment");
        tagline.setFont(new Font("Arial", Font.PLAIN, 14));
        tagline.setForeground(new Color(200, 200, 200));
        
        JPanel headerWrapper = new JPanel(new BorderLayout());
        headerWrapper.setOpaque(false);
        headerWrapper.add(appTitle, BorderLayout.NORTH);
        headerWrapper.add(tagline, BorderLayout.SOUTH);
        headerPanel.add(headerWrapper);
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        
        // Form Panel
        JPanel formPanel = new JPanel();
        formPanel.setOpaque(false);
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        
        // Email field
        JLabel emailLabel = new JLabel("Email Address");
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        emailLabel.setForeground(Color.WHITE);
        formPanel.add(emailLabel);
        formPanel.add(Box.createVerticalStrut(5));
        
        emailField = new JTextField();
        emailField.setPreferredSize(new Dimension(200, 35));
        emailField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        emailField.setFont(new Font("Arial", Font.PLAIN, 12));
        emailField.setBorder(BorderFactory.createEmptyBorder(8, 10, 8, 10));
        emailField.setText("admin@hub.com"); // Pre-filled for testing
        formPanel.add(emailField);
        formPanel.add(Box.createVerticalStrut(15));
        
        // Password field
        JLabel passLabel = new JLabel("Password");
        passLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        passLabel.setForeground(Color.WHITE);
        formPanel.add(passLabel);
        formPanel.add(Box.createVerticalStrut(5));
        
        passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(200, 35));
        passwordField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        passwordField.setFont(new Font("Arial", Font.PLAIN, 12));
        passwordField.setBorder(BorderFactory.createEmptyBorder(8, 10, 8, 10));
        passwordField.setText("admin123"); // Pre-filled for testing
        formPanel.add(passwordField);
        formPanel.add(Box.createVerticalStrut(20));
        
        // Status label
        statusLabel = new JLabel("");
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 11));
        statusLabel.setForeground(new Color(255, 200, 100));
        formPanel.add(statusLabel);
        formPanel.add(Box.createVerticalStrut(10));
        
        // Buttons panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        
        loginBtn = new JButton("Login");
        loginBtn.setFont(new Font("Arial", Font.BOLD, 12));
        loginBtn.setBackground(new Color(46, 204, 113));
        loginBtn.setForeground(Color.WHITE);
        loginBtn.setFocusPainted(false);
        loginBtn.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));
        loginBtn.addActionListener(e -> handleLogin());
        
        registerBtn = new JButton("Register New Account");
        registerBtn.setFont(new Font("Arial", Font.PLAIN, 12));
        registerBtn.setBackground(new Color(52, 152, 219));
        registerBtn.setForeground(Color.WHITE);
        registerBtn.setFocusPainted(false);
        registerBtn.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));
        registerBtn.addActionListener(e -> handleRegister());
        
        buttonPanel.add(loginBtn);
        buttonPanel.add(Box.createHorizontalStrut(10));
        buttonPanel.add(registerBtn);
        
        formPanel.add(buttonPanel);
        formPanel.add(Box.createVerticalStrut(20));
        
        // Test credentials info
        JLabel infoLabel = new JLabel("<html><small>Test Credentials:<br/>Admin: admin@hub.com / admin123<br/>" +
                                      "Entrepreneur: john@test.com / pass123<br/>" +
                                      "Investor: jane@test.com / pass123</small></html>");
        infoLabel.setForeground(new Color(200, 200, 200));
        infoLabel.setFont(new Font("Arial", Font.PLAIN, 10));
        formPanel.add(infoLabel);
        
        mainPanel.add(formPanel, BorderLayout.CENTER);
        add(mainPanel);
        
        // Make visible
        setVisible(true);
    }
    
    /**
     * Handle login action
     */
    private void handleLogin() {
        String email = emailField.getText().trim();
        String password = new String(passwordField.getPassword());
        
        // Validation
        if (email.isEmpty()) {
            showError("Please enter email address");
            return;
        }
        
        if (password.isEmpty()) {
            showError("Please enter password");
            return;
        }
        
        if (!email.contains("@")) {
            showError("Please enter a valid email address");
            return;
        }
        
        try {
            statusLabel.setText("Checking credentials...");
            statusLabel.setForeground(new Color(255, 200, 100));
            loginBtn.setEnabled(false);
            registerBtn.setEnabled(false);
            
            // Use AuthService for validation
            User user = AuthService.validateLogin(email, password);
            
            if (user != null) {
                // Check user role for redirection
                String role = user.getRole().toUpperCase();
                if (!role.equals("ADMIN") && !role.equals("ENTREPRENEUR") && !role.equals("INVESTOR")) {
                    showError("Invalid user role detected");
                    loginBtn.setEnabled(true);
                    registerBtn.setEnabled(true);
                    return;
                }
                
                statusLabel.setText("Login successful! Opening dashboard...");
                statusLabel.setForeground(new Color(46, 204, 113));
                
                // Delay before closing and opening dashboard
                Timer timer = new Timer(800, e -> {
                    dispose();
                    openDashboard(user);
                });
                timer.setRepeats(false);
                timer.start();
            } else {
                showError("Invalid credentials or user not approved");
                loginBtn.setEnabled(true);
                registerBtn.setEnabled(true);
            }
        } catch (Exception ex) {
            showError("Error: " + ex.getMessage());
            loginBtn.setEnabled(true);
            registerBtn.setEnabled(true);
        }
    }
    
    /**
     * Open appropriate dashboard based on user role
     */
    private void openDashboard(User user) {
        try {
            String role = user.getRole().toUpperCase();
            switch (role) {
                case "ADMIN":
                    new AdminUI(user);
                    break;
                case "ENTREPRENEUR":
                    new EntrepreneurUI(user);
                    break;
                case "INVESTOR":
                    new InvestorUI(user);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Unknown role: " + user.getRole(),
                            "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error opening dashboard: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
    
    /**
     * Handle registration action
     */
    private void handleRegister() {
        dispose();
        new RegisterUI();
    }
    
    /**
     * Show error message
     */
    private void showError(String message) {
        statusLabel.setText("❌ " + message);
        statusLabel.setForeground(new Color(231, 76, 60));
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginUI());
    }
}