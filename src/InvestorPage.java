package pages;

import models.Idea;
import models.Investor;
import repository.IdeaRepository;
import java.util.List;
import java.util.Scanner;

public class InvestorPage extends Page {
    private Investor investor;
    private IdeaRepository ideaRepo;

    public InvestorPage(Investor investor, IdeaRepository repo) {
        this.investor = investor;
        this.ideaRepo = repo;
    }

    public void render() {
        List<Idea> ideas = ideaRepo.getAllIdeas();
        for (Idea i : ideas) {
            System.out.println(i.toString());
        }
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Idea Id to invest:");
        int id = sc.nextInt();
        Idea idea = ideaRepo.getIdeaById(id);
        if (idea != null) {
            System.out.println("Enter amount to invest:");
            double amt = sc.nextDouble();
            investor.invest(idea, amt);
        }
    }
}
