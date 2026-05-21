package models;

public class Project {
    public int id, ideaId, investorId;
    public String status;

    public Project(int id, int ideaId, int investorId, String status) {
        this.id = id;
        this.ideaId = ideaId;
        this.investorId = investorId;
        this.status = status;
    }
}