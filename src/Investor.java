package models;
public class Investor extends User {
    public Investor(int userId,String name,String email,String pwd) {
        super(userId,name,email,pwd);
       }
    public void invest(Idea idea,double amount) {
        synchronized(idea) { 
            if (!idea.isFunded()) {
                idea.setFunded(true);
                System.out.println(name+" invests ₹"+amount+" in idea: " +idea.getTitle());
            } else 
                System.out.println("Idea already funded");
            
        }
    }
   public void displayRole() {
        System.out.println("Role: Investor");
    }
    public String getRole() {
        return "INVESTOR";
    }
}
