package pages;
import models.Entrepreneur;
import models.Idea;
import repository.IdeaRepository;
import java.util.Scanner;
public class EntrepreneurPage extends Page {
    private Entrepreneur entrepreneur;
    private IdeaRepository ideaRepo;
    public EntrepreneurPage(Entrepreneur e, IdeaRepository repo) {
        this.entrepreneur=e;
        this.ideaRepo=repo;
    }
    public void render() {
        Scanner sc = new Scanner(System.in);
        System.out.println("1. Post Idea");
        int choice=sc.nextInt();
        if (choice==1) {
            sc.nextLine();
            System.out.println("Enter Idea Title:");
            String title=sc.nextLine();
            System.out.println("Enter Funding Required:");
            double fund=sc.nextDouble();
            Idea idea=new Idea(ideaRepo.getAllIdeas().size()+1,title,fund);
            ideaRepo.addIdea(idea);
            entrepreneur.pitchIdea(idea);
            idea.submitIdea();
        }
    }
}
