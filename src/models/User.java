package models;

public class User {
    int id;
    String email, fname, lname, role, status;

    public User(int id, String email, String fname, String lname, String role, String status) {
        this.id = id;
        this.email = email;
        this.fname = fname;
        this.lname = lname;
        this.role = role;
        this.status = status;
    }

    public int getId() { return id; }
    public String getRole() { return role; }
    public String getEmail() { return email; }
    public String getFname() { return fname; }
    public String getLname() { return lname; }
    public String getStatus() { return status; }
}