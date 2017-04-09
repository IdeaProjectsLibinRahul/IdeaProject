package tech.libin.rahul.ideaproject.service.models;

/**
 * Created by rahul on 3/5/2017.
 */

public class DetailFromUPCRoleModel {

    String name;
    String phoneNum;
    String myIdea;
    String myIdeaCode;
    String amountPaid;
    int visitStatus;
    int feedback;
    int totalVisit;
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

    public int getVisitStatus() {
        return visitStatus;
    }

    public void setVisitStatus(int status) {
        this.visitStatus = status;
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

    public String getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(String amountPaid) {
        this.amountPaid = amountPaid;
    }

    public int getFeedback() {
        return feedback;
    }

    public void setFeedback(int feedback) {
        this.feedback = feedback;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public int getTotalVisit() {
        return totalVisit;
    }

    public void setTotalVisit(int totalVisit) {
        this.totalVisit = totalVisit;
    }
}
