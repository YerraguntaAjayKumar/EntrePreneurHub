import ui.LoginUI;

import javax.swing.*;

/**
 * EntrepreneurHub Main Application Entry Point
 * A complete DBMS-based role-based collaboration platform
 */
public class Main {
    public static void main(String[] args) {
        // Set look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Launch login UI
        SwingUtilities.invokeLater(() -> new LoginUI());
    }
}