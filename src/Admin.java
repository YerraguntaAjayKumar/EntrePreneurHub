package models;
public class Admin extends User
{
    public Admin(int userId,String name,String email,String pwd)
    {
            super(userId,name,email,pwd);
    }
    public void approveUser(User u) {
        System.out.println("Admin "+name+" approved user: "+u.getName());
    }
        public void displayRole()
          {
           
                   System.out.println("Role: Admin");
          }
     public String getRole() {
        return "ADMIN";
    }
}