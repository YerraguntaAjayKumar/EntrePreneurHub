package models;
import java.io.Serializable;
public class Idea implements Serializable
{
 private int ideaId;
 private String title;
 private double fundingRequired;
private boolean isFunded=false;
 public Idea(int ideaId,String title,double fundingRequired)
 { 
  this.ideaId=ideaId;
  this.title=title;
  this.fundingRequired=fundingRequired;
 }
 public void submitIdea() { System.out.println("Idea " + title + " submitte successfully.");
    }
 public int getIdeaId() { return ideaId; }
    public String getTitle() { return title; }
    public double getFundingRequired() { return fundingRequired; }
 public void setFunded(boolean funded) { this.isFunded = funded; }
 public boolean isFunded() {   return isFunded; }
public String toString() {
        return "Idea[" + ideaId + "] " + title + " (₹" + fundingRequired + ") ownerId=";
    }
}