package repository;
import models.User;
import java.util.ArrayList;
import java.util.List;
public class UserRepository {
    private List<User> users=new ArrayList<>();
    public void addUser(User u) { users.add(u); }
    public User getUserByEmail(String email) {
        for(User u:users) {
            if (u.toString().contains(email)) return u;
        }
        return null;
    }

    public List<User>getAllUsers() { return users; }
}
