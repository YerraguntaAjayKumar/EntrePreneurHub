package pages;
import java.util.Scanner;
public class HomePage extends Page {
    public void render() {
        Scanner sc=new Scanner(System.in);
        System.out.println("Welcome to Entrepreneur Hub");
        System.out.println("1. Login");
        System.out.println("2. Sign Up");
        System.out.println("Enter choice:");
    }
}
