package models;

public class Idea {
    public int id, userId;
    public String title, desc, status;

    public Idea(int id, int userId, String title, String desc, String status) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.desc = desc;
        this.status = status;
    }
}