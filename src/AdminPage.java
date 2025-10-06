package pages;
import models.Admin;
import models.User;
import repository.UserRepository;
public class AdminPage extends Page {
    private Admin admin;
    private UserRepository userRepo;

    public AdminPage(Admin a, UserRepository repo) {
        this.admin = a;
        this.userRepo = repo;
    }
    public void render() {
        for (User u : userRepo.getAllUsers()) {
            admin.approveUser(u);
        }
    }
}
