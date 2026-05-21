package models;
public class Entrepreneur extends User
{ 
      public Entrepreneur(int userId,String name,String email,String pwd)  {
                 super(userId,name,email,pwd);
          }
         
      public void pitchIdea(Idea idea) {
        System.out.println(name+"'s idea : "+idea.getTitle());
          }
      public void displayRole(){
           
                   System.out.println("Role:Entrepreneur");
          }
      public String getRole() {
        return "ENTREPRENEUR";
        }
          
}