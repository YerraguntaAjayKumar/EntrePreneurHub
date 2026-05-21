package repository;
import models.Idea;
import java.util.ArrayList;
import java.util.List;
public class IdeaRepository {
    private List<Idea> ideas=new ArrayList<>();
    public void addIdea(Idea idea) {
        ideas.add(idea);
    }
    public List<Idea> getAllIdeas() {
        return ideas;
    }
    public Idea getIdeaById(int id) {
        for (Idea i:ideas) {
            if (i.getIdeaId()==id) return i;
        }
        return null;
    }
}
