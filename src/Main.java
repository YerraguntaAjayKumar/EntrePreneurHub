import java.util.Scanner;
import pages.SignUpPage;
import pages.LoginPage;
import pages.EntrepreneurPage;
import pages.InvestorPage;
import pages.AdminPage;
import repository.UserRepository;
import repository.IdeaRepository;
import models.Admin;
import models.User;
import models.Entrepreneur;
import models.Investor;

public class Main {

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        UserRepository userRepo=new UserRepository();
        IdeaRepository ideaRepo=new IdeaRepository();
        Admin admin = new Admin(0, "sys-admin", "admin@entrepreneurhub.com", "admin1234");
        userRepo.addUser(admin);

        System.out.println("========== Welcome to EntrepreneurHub ==========");

        while (true) {
            System.out.println("\n1. Sign Up\n2. Login\n3. Exit");
            System.out.print("Enter choice: ");
            
            int ch;
            try {
                ch = Integer.parseInt(sc.nextLine());
               } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
               }

            switch (ch) {
                case 1:
                    new SignUpPage(userRepo).render();
                    break;
                case 2:
                    LoginPage log = new LoginPage(userRepo);
                    log.render();
                    if(log.user!=null) {
                        System.out.println("\nLogin successful!");
                        navigateToRolePage(log.user, ideaRepo, userRepo);
                    }
                    break;
                case 3:
                    System.out.println("Exiting EntrepreneurHub... Goodbye!");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void navigateToRolePage(User user, IdeaRepository ideaRepo, UserRepository userRepo) {
        if (user instanceof models.Entrepreneur entrepreneur) {
            new pages.EntrepreneurPage(entrepreneur, ideaRepo).render();
        } else if (user instanceof models.Investor investor) {
            new pages.InvestorPage(investor, ideaRepo).render();
        } else if (user instanceof models.Admin admin) { 
            new pages.AdminPage(admin, userRepo).render();
        } else {
            System.out.println("Unknown user role.");
        }
    }
}
