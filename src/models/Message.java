package models;

import java.sql.Timestamp;

public class Message {
    public int id, senderId, receiverId;
    public String message;
    public Timestamp timestamp;

    public Message(int id, int senderId, int receiverId, String message, Timestamp timestamp) {
        this.id = id;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.message = message;
        this.timestamp = timestamp;
    }
}