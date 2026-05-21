package models;
import java.util.ArrayList;
import java.util.List;
public class Project{
 private Idea idea;
 private List<String> updates;
   public Project(Idea idea) {
     this.idea=idea;
     this.updates=new ArrayList<>();
   }
  public void addUpdate(String update) {
           updates.add(update);
          System.out.println("project updated Added is :"+update);
      }
  public void showUpdate()
  {
    System.out.println("project updates for IDEA : "+idea.getTitle());
     for(String UP:updates)
        System.out.println(UP);
   }
}