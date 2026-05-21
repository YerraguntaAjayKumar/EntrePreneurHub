package models;
import java.io.Serializable;
public class Agreement implements Serializable {
    private Entrepreneur entrepreneur;
    private Investor investor;
    private Idea idea;
    private double amount;
    public Agreement(Entrepreneur entrepreneur, Investor investor, Idea idea, double amount) {
        this.entrepreneur=entrepreneur;
        this.investor=investor;
        this.idea=idea;
        this.amount=amount;
    }

    public void showAgreement() {
        System.out.println("Agreement:");
        System.out.println("Entrepreneur: " + entrepreneur.getName());
        System.out.println("Investor: " + investor.getName());
        System.out.println("Idea: " + idea.getTitle());
        System.out.println("Funded Amount: ₹" + amount);
    }
}