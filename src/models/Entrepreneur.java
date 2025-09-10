class Entrepreneur extends User
{ 
      Entrepreneur(int userId,String name,String email)
          {
                 super(userId,name,email);
          }
         
      void pitchIdea(){
                  System.out.ptintln("My Idea");
                       }
      boolean login()
                   {
                       return true;
                    }
        void displayRole()
          {
           
                   System.out.println("Role:Entrepreneur");
           }
          
}