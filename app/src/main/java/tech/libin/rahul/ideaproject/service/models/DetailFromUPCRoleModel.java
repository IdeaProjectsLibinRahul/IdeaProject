package tech.libin.rahul.ideaproject.service.models;

/**
 * Created by rahul on 3/5/2017.
 */

public class DetailFromUPCRoleModel {
   String myIdea;
   String myIdeaCode;
   int status;
   String remarks;
   String visitedDate;

    public String getMyIdea() {
        return myIdea;
    }

    public void setMyIdea(String myIdea) {
        this.myIdea = myIdea;
    }

    public String getMyIdeaCode() {
        return myIdeaCode;
    }

    public void setMyIdeaCode(String myIdeaCode) {
        this.myIdeaCode = myIdeaCode;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getVisitedDate() {
        return visitedDate;
    }

    public void setVisitedDate(String visitedDate) {
        this.visitedDate = visitedDate;
    }
}