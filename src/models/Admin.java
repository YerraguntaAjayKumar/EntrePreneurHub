class Admin extends User
{
    Admin(int userId,String name,String email)
    {
            super(userId,name,email);
    }
    void approveUser()
    {
        System.out.println("Aprooved successfully");
    }
      boolean login()
                   {
                       return true;
                    }
        void displayRole()
          {
           
                   System.out.println("Role: Admin");
          }
}