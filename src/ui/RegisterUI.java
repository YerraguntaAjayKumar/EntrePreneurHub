package ui;

import service.AuthService;

import javax.swing.*;
import java.awt.*;

/**
 * Enhanced Registration UI with validation
 */
public class RegisterUI extends JFrame {

    private JTextField emailField;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JPasswordField passwordField;
    private JComboBox<String> roleCombo;
    private JButton submitBtn;
    private JLabel statusLabel;

    public RegisterUI() {
        setTitle("EntrepreneurHub - Register");
        setSize(450, 450);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        
        // Main panel
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gradient = new GradientPaint(
                    0, 0, new Color(41, 128, 185),
                    0, getHeight(), new Color(52, 73, 94)
                );
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        mainPanel.setLayout(new BorderLayout());
        
        // Header
        JPanel headerPanel = new JPanel();
        headerPanel.setOpaque(false);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JLabel titleLabel = new JLabel("Create Account");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel);
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        
        // Form panel
        JPanel formPanel = new JPanel();
        formPanel.setOpaque(false);
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        
        // Email
        JLabel emailLabel = new JLabel("Email Address");
        emailLabel.setForeground(Color.WHITE);
        formPanel.add(emailLabel);
        formPanel.add(Box.createVerticalStrut(5));
        emailField = new JTextField();
        emailField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        formPanel.add(emailField);
        formPanel.add(Box.createVerticalStrut(10));
        
        // Password
        JLabel passLabel = new JLabel("Password");
        passLabel.setForeground(Color.WHITE);
        formPanel.add(passLabel);
        formPanel.add(Box.createVerticalStrut(5));
        passwordField = new JPasswordField();
        passwordField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        formPanel.add(passwordField);
        formPanel.add(Box.createVerticalStrut(10));
        
        // First Name
        JLabel fnameLabel = new JLabel("First Name");
        fnameLabel.setForeground(Color.WHITE);
        formPanel.add(fnameLabel);
        formPanel.add(Box.createVerticalStrut(5));
        firstNameField = new JTextField();
        firstNameField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        formPanel.add(firstNameField);
        formPanel.add(Box.createVerticalStrut(10));
        
        // Last Name
        JLabel lnameLabel = new JLabel("Last Name");
        lnameLabel.setForeground(Color.WHITE);
        formPanel.add(lnameLabel);
        formPanel.add(Box.createVerticalStrut(5));
        lastNameField = new JTextField();
        lastNameField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        formPanel.add(lastNameField);
        formPanel.add(Box.createVerticalStrut(10));
        
        // Role selection
        JLabel roleLabel = new JLabel("Select Role");
        roleLabel.setForeground(Color.WHITE);
        formPanel.add(roleLabel);
        formPanel.add(Box.createVerticalStrut(5));
        roleCombo = new JComboBox<>(new String[]{"ENTREPRENEUR", "INVESTOR"});
        roleCombo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        formPanel.add(roleCombo);
        formPanel.add(Box.createVerticalStrut(15));
        
        // Status label
        statusLabel = new JLabel("");
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 11));
        formPanel.add(statusLabel);
        formPanel.add(Box.createVerticalStrut(10));
        
        // Buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        
        submitBtn = new JButton("Register");
        submitBtn.setFont(new Font("Arial", Font.BOLD, 12));
        submitBtn.setBackground(new Color(46, 204, 113));
        submitBtn.setForeground(Color.WHITE);
        submitBtn.setFocusPainted(false);
        submitBtn.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));
        submitBtn.addActionListener(e -> handleRegister());
        
        JButton cancelBtn = new JButton("Cancel");
        cancelBtn.setFont(new Font("Arial", Font.PLAIN, 12));
        cancelBtn.setBackground(new Color(192, 57, 43));
        cancelBtn.setForeground(Color.WHITE);
        cancelBtn.setFocusPainted(false);
        cancelBtn.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));
        cancelBtn.addActionListener(e -> dispose());
        
        buttonPanel.add(submitBtn);
        buttonPanel.add(Box.createHorizontalStrut(10));
        buttonPanel.add(cancelBtn);
        
        formPanel.add(buttonPanel);
        mainPanel.add(formPanel, BorderLayout.CENTER);
        
        add(mainPanel);
        setVisible(true);
    }
    
    private void handleRegister() {
        String email = emailField.getText().trim();
        String password = new String(passwordField.getPassword());
        String firstName = firstNameField.getText().trim();
        String lastName = lastNameField.getText().trim();
        String role = (String) roleCombo.getSelectedItem();
        
        // Validation
        if (email.isEmpty() || password.isEmpty() || firstName.isEmpty() || lastName.isEmpty()) {
            showError("All fields are required");
            return;
        }
        
        if (!email.contains("@") || !email.contains(".")) {
            showError("Invalid email format");
            return;
        }
        
        if (password.length() < 5) {
            showError("Password must be at least 5 characters");
            return;
        }
        
        try {
            statusLabel.setText("Checking email...");
            statusLabel.setForeground(new Color(255, 200, 100));
            submitBtn.setEnabled(false);
            
            // Check if email exists
            if (AuthService.emailExists(email)) {
                showError("Email already registered");
                submitBtn.setEnabled(true);
                return;
            }
            
            // Register user
            AuthService.registerUser(email, password, firstName, lastName, role);
            
            statusLabel.setText("✓ Registration successful! Awaiting admin approval.");
            statusLabel.setForeground(new Color(46, 204, 113));
            
            // Show confirmation
            JOptionPane.showMessageDialog(this, 
                    "Registration successful!\n\nYour account is pending admin approval.\n" +
                    "You will receive notification once approved.",
                    "Success", JOptionPane.INFORMATION_MESSAGE);
            
            dispose();
            new LoginUI();
            
        } catch (Exception ex) {
            showError("Error: " + ex.getMessage());
            submitBtn.setEnabled(true);
        }
    }
    
    private void showError(String message) {
        statusLabel.setText("❌ " + message);
        statusLabel.setForeground(new Color(231, 76, 60));
    }
}