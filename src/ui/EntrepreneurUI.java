package ui;

import dao.IdeaDAO;
import models.Idea;
import models.User;
import service.IdeaService;
import ui.components.Header;
import ui.components.Sidebar;
import ui.components.TableView;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Entrepreneur Dashboard with advanced UI components
 */
public class EntrepreneurUI extends JFrame {

    private JFrame frame;
    private Header header;
    private Sidebar sidebar;
    private TableView ideasTable;
    private JTextField titleField;
    private JTextArea descArea;
    private JButton postBtn;
    private User currentUser;

    public EntrepreneurUI(User user) {
        this.currentUser = user;
        
        frame = new JFrame("EntrepreneurHub - Entrepreneur Dashboard");
        frame.setSize(1000, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());
        
        // Header
        header = new Header("Entrepreneur Dashboard - Manage Your Ideas", 
                           user.getEmail(), user.getRole(), 
                           () -> logout());
        frame.add(header, BorderLayout.NORTH);
        
        // Main content panel
        JPanel contentPanel = new JPanel(new BorderLayout());
        
        // Sidebar
        sidebar = new Sidebar();
        sidebar.addMenuItem("Dashboard", () -> showDashboard());
        sidebar.addMenuItem("My Ideas", () -> loadIdeas());
        sidebar.addMenuItem("Chat", () -> openChat());
        sidebar.setActiveItem("Dashboard");
        contentPanel.add(sidebar, BorderLayout.WEST);
        
        // Center panel with CardLayout for multiple views
        JPanel centerPanel = new JPanel(new BorderLayout(10, 10));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        // Idea post form panel
        JPanel postPanel = new JPanel();
        postPanel.setLayout(new BoxLayout(postPanel, BoxLayout.Y_AXIS));
        postPanel.setBackground(new Color(245, 245, 245));
        postPanel.setBorder(BorderFactory.createTitledBorder("Post New Business Idea"));
        postPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder("Post New Business Idea"),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        
        JLabel titleLabel = new JLabel("Idea Title:");
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        postPanel.add(titleLabel);
        titleField = new JTextField();
        titleField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        postPanel.add(titleField);
        postPanel.add(Box.createVerticalStrut(10));
        
        JLabel descLabel = new JLabel("Description:");
        descLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        postPanel.add(descLabel);
        descArea = new JTextArea(3, 40);
        descArea.setLineWrap(true);
        descArea.setWrapStyleWord(true);
        JScrollPane descScroll = new JScrollPane(descArea);
        postPanel.add(descScroll);
        postPanel.add(Box.createVerticalStrut(10));
        
        postBtn = new JButton("Post Idea");
        postBtn.setBackground(new Color(46, 204, 113));
        postBtn.setForeground(Color.WHITE);
        postBtn.setFocusPainted(false);
        postBtn.addActionListener(e -> postIdea());
        postPanel.add(postBtn);
        
        centerPanel.add(postPanel, BorderLayout.NORTH);
        
        // Ideas table
        JLabel myIdeasLabel = new JLabel("Your Business Ideas");
        myIdeasLabel.setFont(new Font("Arial", Font.BOLD, 14));
        ideasTable = new TableView(new String[]{"ID", "Title", "Description", "Status", "Posted"});
        ideasTable.setColumnWidth(0, 40);
        ideasTable.setColumnWidth(1, 120);
        ideasTable.setColumnWidth(2, 250);
        ideasTable.setColumnWidth(3, 80);
        ideasTable.setColumnWidth(4, 80);
        
        JPanel tableWrapper = new JPanel(new BorderLayout());
        tableWrapper.add(myIdeasLabel, BorderLayout.NORTH);
        tableWrapper.add(ideasTable, BorderLayout.CENTER);
        centerPanel.add(tableWrapper, BorderLayout.CENTER);
        
        contentPanel.add(centerPanel, BorderLayout.CENTER);
        frame.add(contentPanel, BorderLayout.CENTER);
        frame.setVisible(true);
        
        // Load initial data
        loadIdeas();
    }
    
    private void showDashboard() {
        sidebar.setActiveItem("Dashboard");
        // Show welcome or dashboard info
    }
    
    private void postIdea() {
        String title = titleField.getText().trim();
        String description = descArea.getText().trim();
        
        if (title.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please enter idea title");
            return;
        }
        
        if (description.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please enter description");
            return;
        }
        
        try {
            IdeaService.addIdea(currentUser.getId(), title, description);
            JOptionPane.showMessageDialog(frame, "Idea posted successfully!");
            titleField.setText("");
            descArea.setText("");
            loadIdeas();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void loadIdeas() {
        try {
            ideasTable.clearTable();
            List<Idea> ideas = IdeaService.getUserIdeas(currentUser.getId());
            for (Idea idea : ideas) {
                ideasTable.addRow(new Object[]{
                    idea.id,
                    idea.title,
                    idea.desc.length() > 40 ? idea.desc.substring(0, 40) + "..." : idea.desc,
                    idea.status,
                    "-"
                });
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Error loading ideas: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void openChat() {
        new ChatUI(currentUser);
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