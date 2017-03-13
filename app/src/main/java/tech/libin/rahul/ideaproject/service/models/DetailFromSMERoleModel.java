package tech.libin.rahul.ideaproject.service.models;

/**
 * Created by rahul on 3/5/2017.
 */

public class DetailFromSMERoleModel {
    String myIdea;
    String myIdeaCode;
    String name;
    String phoneNum;
    int visitStatus;
    int feedback;
    int reason;
    String visitedDate;
    String remarks;

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

    public int getVisitStatus() {
        return visitStatus;
    }

    public void setVisitStatus(int visitStatus) {
        this.visitStatus = visitStatus;
    }

    public int getFeedback() {
        return feedback;
    }

    public void setFeedback(int feedback) {
        this.feedback = feedback;
    }

    public int getReason() {
        return reason;
    }

    public void setReason(int reason) {
        this.reason = reason;
    }

    public String getVisitedDate() {
        return visitedDate;
    }

    public void setVisitedDate(String visitedDate) {
        this.visitedDate = visitedDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
