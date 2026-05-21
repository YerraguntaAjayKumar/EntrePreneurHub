package ui.components;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Reusable Sidebar Navigation Component
 * Provides clickable menu items for navigation
 */
public class Sidebar extends JPanel {
    
    private JPanel menuPanel;
    private Map<String, JButton> menuButtons;
    private JButton activeButton;
    
    public Sidebar() {
        setLayout(new BorderLayout());
        setBackground(new Color(52, 73, 94));
        setPreferredSize(new Dimension(200, 600));
        setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        
        // Title
        JLabel titleLabel = new JLabel("EntrepreneurHub");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        add(titleLabel, BorderLayout.NORTH);
        
        // Menu items panel
        menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        menuPanel.setOpaque(false);
        menuPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JScrollPane scrollPane = new JScrollPane(menuPanel);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(null);
        add(scrollPane, BorderLayout.CENTER);
        
        menuButtons = new HashMap<>();
    }
    
    /**
     * Add a menu item to the sidebar
     * @param itemName Display name of the menu item
     * @param onClickAction Action to perform when clicked
     */
    public void addMenuItem(String itemName, Runnable onClickAction) {
        JButton btn = createMenuButton(itemName, onClickAction);
        menuButtons.put(itemName, btn);
        menuPanel.add(btn);
        menuPanel.add(Box.createVerticalStrut(5));
        menuPanel.revalidate();
        menuPanel.repaint();
    }
    
    /**
     * Set a menu item as active (highlighted)
     * @param itemName Name of the menu item to activate
     */
    public void setActiveItem(String itemName) {
        if (activeButton != null) {
            activeButton.setBackground(new Color(52, 73, 94));
        }
        
        if (menuButtons.containsKey(itemName)) {
            activeButton = menuButtons.get(itemName);
            activeButton.setBackground(new Color(41, 128, 185));
        }
    }
    
    /**
     * Create a styled menu button
     */
    private JButton createMenuButton(String text, Runnable action) {
        JButton btn = new JButton(text);
        btn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        btn.setForeground(Color.WHITE);
        btn.setBackground(new Color(52, 73, 94));
        btn.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        btn.setFocusPainted(false);
        btn.setAlignmentX(Component.LEFT_ALIGNMENT);
        btn.setHorizontalAlignment(JButton.LEFT);
        btn.setFont(new Font("Arial", Font.PLAIN, 12));
        
        btn.addActionListener(e -> {
            setActiveItem(text);
            action.run();
        });
        
        return btn;
    }
    
    /**
     * Clear all menu items
     */
    public void clearMenu() {
        menuPanel.removeAll();
        menuButtons.clear();
        menuPanel.revalidate();
        menuPanel.repaint();
    }
}
