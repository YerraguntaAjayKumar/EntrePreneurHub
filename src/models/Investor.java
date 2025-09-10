class Investor extends User
{
     Investor(int userId,String name,String email)
          {
                 super(userId,name,email);
          }
         
      void Invest(){
               system.out.println("Investing");
                       }
      boolean login()
                   {
                       return true;
                    }
        void displayRole()
          {
           
                   System.out.println("Role: Investor");
           }
}