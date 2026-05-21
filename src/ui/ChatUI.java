package ui;

import dao.MessageDAO;
import dao.UserDAO;
import models.Message;
import models.User;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ChatUI {

    JFrame frame;
    JTextArea chatArea;
    JTextField messageField;
    JButton sendBtn;
    JComboBox<String> userCombo;
    User currentUser;
    int selectedUserId = -1;

    public ChatUI(User user) {
        this.currentUser = user;

        frame = new JFrame("Chat - " + user.getFname());
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        chatArea = new JTextArea();
        chatArea.setEditable(false);
        JScrollPane chatScroll = new JScrollPane(chatArea);

        // Initialize components first
        userCombo = new JComboBox<>();
        messageField = new JTextField();
        sendBtn = new JButton("Send");
        JButton refreshBtn = new JButton("Refresh");

        // Load users
        loadUsers();

        JPanel bottomPanel = new JPanel(new BorderLayout());
        JPanel topPanel = new JPanel(new FlowLayout());
        topPanel.add(new JLabel("Chat with:"));
        topPanel.add(userCombo);
        bottomPanel.add(topPanel, BorderLayout.NORTH);

        JPanel msgPanel = new JPanel(new BorderLayout());
        msgPanel.add(messageField, BorderLayout.CENTER);
        JPanel btnPanel = new JPanel(new FlowLayout());
        btnPanel.add(sendBtn);
        btnPanel.add(refreshBtn);
        msgPanel.add(btnPanel, BorderLayout.EAST);
        bottomPanel.add(msgPanel, BorderLayout.SOUTH);

        frame.add(chatScroll, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        userCombo.addActionListener(e -> selectUser());
        sendBtn.addActionListener(e -> sendMessage());
        refreshBtn.addActionListener(e -> loadMessages());

        frame.setVisible(true);
    }

    private void loadUsers() {
        try {
            List<User> users = UserDAO.getAllApprovedUsers();
            userCombo.addItem("Select User");
            for (User u : users) {
                if (u.getId() != currentUser.getId()) {
                    userCombo.addItem(u.getId() + " - " + u.getFname() + " " + u.getLname() + " (" + u.getRole() + ")");
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void selectUser() {
        String selected = (String) userCombo.getSelectedItem();
        if (selected != null && !selected.equals("Select User")) {
            try {
                selectedUserId = Integer.parseInt(selected.split(" - ")[0]);
                loadMessages();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private void loadMessages() {
        if (selectedUserId == -1) return;
        try {
            List<Message> messages = MessageDAO.getMessages(currentUser.getId(), selectedUserId);
            chatArea.setText("");
            for (Message msg : messages) {
                String sender = msg.senderId == currentUser.getId() ? "You" : "Them";
                chatArea.append(sender + ": " + msg.message + " (" + msg.timestamp + ")\n");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void sendMessage() {
        if (selectedUserId == -1) {
            JOptionPane.showMessageDialog(frame, "Please select a user to chat with!");
            return;
        }
        String msg = messageField.getText();
        if (msg.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please enter a message!");
            return;
        }
        try {
            MessageDAO.sendMessage(currentUser.getId(), selectedUserId, msg);
            messageField.setText("");
            loadMessages();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage());
        }
    }
}