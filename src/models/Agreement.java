package models;

import java.sql.Date;

public class Agreement {
    public int id, projectId;
    public String terms;
    public Date startDate, endDate;

    public Agreement(int id, int projectId, String terms, Date startDate, Date endDate) {
        this.id = id;
        this.projectId = projectId;
        this.terms = terms;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}