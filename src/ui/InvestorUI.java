package ui;

import dao.AgreementDAO;
import dao.IdeaDAO;
import dao.ProjectDAO;
import models.Idea;
import models.User;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;
import java.util.List;

public class InvestorUI {

    JFrame frame;
    JTextArea ideasArea;
    JTextField ideaIdField;
    JTextArea termsArea;
    JTextField startDateField, endDateField;
    User user;
    int currentProjectId = -1;

    public InvestorUI(User user) {
        this.user = user;

        frame = new JFrame("Investor Dashboard - " + user.getFname());
        frame.setSize(700, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Top panel for ideas
        ideasArea = new JTextArea();
        ideasArea.setEditable(false);
        JScrollPane ideasScroll = new JScrollPane(ideasArea);
        ideasScroll.setBorder(BorderFactory.createTitledBorder("Available Ideas"));

        JButton loadBtn = new JButton("Load Ideas");
        ideaIdField = new JTextField(10);
        JButton investBtn = new JButton("Invest");

        JPanel ideasPanel = new JPanel(new BorderLayout());
        JPanel ideasBottom = new JPanel(new FlowLayout());
        ideasBottom.add(loadBtn);
        ideasBottom.add(new JLabel("Idea ID:"));
        ideasBottom.add(ideaIdField);
        ideasBottom.add(investBtn);
        ideasPanel.add(ideasScroll, BorderLayout.CENTER);
        ideasPanel.add(ideasBottom, BorderLayout.SOUTH);

        // Bottom panel for agreement
        JPanel agreementPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        agreementPanel.setBorder(BorderFactory.createTitledBorder("Create Agreement"));
        agreementPanel.add(new JLabel("Terms:"));
        termsArea = new JTextArea(3, 20);
        JScrollPane termsScroll = new JScrollPane(termsArea);
        agreementPanel.add(termsScroll);
        agreementPanel.add(new JLabel("Start Date (YYYY-MM-DD):"));
        startDateField = new JTextField();
        agreementPanel.add(startDateField);
        agreementPanel.add(new JLabel("End Date (YYYY-MM-DD):"));
        endDateField = new JTextField();
        agreementPanel.add(endDateField);
        JButton createAgreementBtn = new JButton("Create Agreement");
        agreementPanel.add(new JLabel()); // Empty
        agreementPanel.add(createAgreementBtn);

        // Chat button
        JPanel chatPanel = new JPanel(new FlowLayout());
        JButton chatBtn = new JButton("Open Chat");
        chatPanel.add(chatBtn);

        frame.add(ideasPanel, BorderLayout.CENTER);
        frame.add(agreementPanel, BorderLayout.SOUTH);
        frame.add(chatPanel, BorderLayout.NORTH);

        loadBtn.addActionListener(e -> loadIdeas());
        investBtn.addActionListener(e -> invest());
        createAgreementBtn.addActionListener(e -> createAgreement());
        chatBtn.addActionListener(e -> openChat());

        loadIdeas();

        frame.setVisible(true);
    }

    private void loadIdeas() {
        try {
            List<Idea> ideas = IdeaDAO.getIdeas();
            ideasArea.setText("");
            for (Idea idea : ideas) {
                ideasArea.append("ID: " + idea.id + " - " + idea.title + " - " + idea.desc + " - " + idea.status + "\n");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Error loading ideas: " + ex.getMessage());
        }
    }

    private void invest() {
        String ideaText = ideaIdField.getText();
        if (ideaText.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please enter Idea ID!");
            return;
        }
        try {
            int ideaId = Integer.parseInt(ideaText);
            ProjectDAO.createProject(ideaId, user.getId());
            currentProjectId = ProjectDAO.getLatestProjectId();
            JOptionPane.showMessageDialog(frame, "Investment Successful! Project Created. ID: " + currentProjectId);
            ideaIdField.setText("");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Invalid Idea ID!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage());
        }
    }

    private void createAgreement() {
        try {
            if (currentProjectId == -1) {
                JOptionPane.showMessageDialog(frame, "Please invest in an idea first!");
                return;
            }
            String terms = termsArea.getText();
            String start = startDateField.getText();
            String end = endDateField.getText();
            if (terms.isEmpty() || start.isEmpty() || end.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please fill all fields!");
                return;
            }
            Date startDate = Date.valueOf(start);
            Date endDate = Date.valueOf(end);
            AgreementDAO.createAgreement(currentProjectId, terms, startDate, endDate);
            JOptionPane.showMessageDialog(frame, "Agreement Created!");
            termsArea.setText("");
            startDateField.setText("");
            endDateField.setText("");
            currentProjectId = -1; // Reset
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(frame, "Invalid date format. Use YYYY-MM-DD");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage());
        }
    }

    private void openChat() {
        new ChatUI(user);
    }
}