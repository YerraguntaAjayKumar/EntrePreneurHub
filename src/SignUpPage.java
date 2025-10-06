package pages;
import models.*;
import repository.UserRepository;
import java.util.Scanner;
import utils.ValidationUtil;
public class SignUpPage extends Page {
    private UserRepository userRepo;
    private static int idCounter = 1;

    public SignUpPage(UserRepository repo) {
        this.userRepo = repo;
    }
    public void render() {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter Name:");
        String name=sc.nextLine();
        System.out.println("Enter Email:");
        String email=sc.nextLine();
        if (!ValidationUtil.isValidEmail(email)) {
            System.out.println("Invalid Email!");
            return;
        }
        System.out.println("Enter Password:");
        String pwd=sc.nextLine();
        if (!ValidationUtil.isValidPassword(pwd)) {
            System.out.println("Password too short!");
            return;
        }
        System.out.println("Select Role: 1. Entrepreneur 2. Investor 3. Admin");
        int role=sc.nextInt();
        User user=null;
        switch (role) {
            case 1: user=new Entrepreneur(idCounter++,name,email,pwd); break;
            case 2: user=new Investor(idCounter++, name, email, pwd); break;
            case 3: user=new Admin(idCounter++, name, email, pwd); break;
            default: System.out.println("Invalid Role"); return;
        }
        userRepo.addUser(user);
        System.out.println("User Registered Successfully: " + user.toString());
    }
}
