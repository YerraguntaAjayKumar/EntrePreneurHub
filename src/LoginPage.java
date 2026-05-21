package pages;
import models.User;
import repository.UserRepository;
import java.util.Scanner;
public class LoginPage extends Page{
    private UserRepository userRepo;
    public boolean succcess=false;
    public User user;
    public LoginPage(UserRepository repo) {
        this.userRepo=repo;
    }
    public void render() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Email:");
        String email=sc.nextLine();
        System.out.println("Enter Password:");
        String pwd=sc.nextLine();
        user=userRepo.getUserByEmail(email);
        if(user!=null&&user.login(pwd)) {
            System.out.println("Login Successful!");
       
        } else {
            System.out.println("Login Failed!");
            
        }
    } 
}
