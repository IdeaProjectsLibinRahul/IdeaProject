package tech.libin.rahul.ideaproject.views.detailsview.viewmodels;

import java.util.ArrayList;

import tech.libin.rahul.ideaproject.configurations.Constants;
import tech.libin.rahul.ideaproject.service.models.DetailFromUPCRoleModel;
import tech.libin.rahul.ideaproject.service.models.DetailOtherData;
import tech.libin.rahul.ideaproject.service.models.LocationModel;
import tech.libin.rahul.ideaproject.service.models.SpinnerData;

/**
 * Created by rahul on 3/5/2017.
 */

public class TdDetailModel {

    private String custNum;
    private String mobile;
    private String biller;
    private String ser;
    private String customerType;
    private String segment;
    private String myIdeaCode;
    private String curBalance;
    private String bucket;
    private String myIdeaAllocation;
    private String bill1;
    private String bill2;
    private String bill3;
    private String bill4;
    private String bill5;

    private String landLine1;
    private String landLine2;
    private String crmStatus;
    private String type;
    private String activatiomMi;
    private String zip;
    private String reminderDate;

    private LocationModel location;
    private ArrayList<DetailOtherData> other;
    private Constants.EsclateStatus esclate;
    private ArrayList<SpinnerData> visitStatus;
    private ArrayList<SpinnerData> feedback;

    private DetailFromUPCRoleModel fromZsm;
    private DetailFromUPCRoleModel fromMico;
    private DetailFromUPCRoleModel fromExecutive;

    public String getCustNum() {
        return custNum;
    }

    public void setCustNum(String custNum) {
        this.custNum = custNum;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getBiller() {
        return biller;
    }

    public void setBiller(String biller) {
        this.biller = biller;
    }

    public String getSer() {
        return ser;
    }

    public void setSer(String ser) {
        this.ser = ser;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public String getSegment() {
        return segment;
    }

    public void setSegment(String segment) {
        this.segment = segment;
    }

    public String getMyIdeaCode() {
        return myIdeaCode;
    }

    public void setMyIdeaCode(String myIdeaCode) {
        this.myIdeaCode = myIdeaCode;
    }

    public String getCurBalance() {
        return curBalance;
    }

    public void setCurBalance(String curBalance) {
        this.curBalance = curBalance;
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public String getMyIdeaAllocation() {
        return myIdeaAllocation;
    }

    public void setMyIdeaAllocation(String myIdeaAllocation) {
        this.myIdeaAllocation = myIdeaAllocation;
    }

    public String getBill1() {
        return bill1;
    }

    public void setBill1(String bill1) {
        this.bill1 = bill1;
    }

    public String getBill2() {
        return bill2;
    }

    public void setBill2(String bill2) {
        this.bill2 = bill2;
    }

    public String getBill3() {
        return bill3;
    }

    public void setBill3(String bill3) {
        this.bill3 = bill3;
    }

    public String getBill4() {
        return bill4;
    }

    public void setBill4(String bill4) {
        this.bill4 = bill4;
    }

    public String getBill5() {
        return bill5;
    }

    public void setBill5(String bill5) {
        this.bill5 = bill5;
    }

    public String getLandLine1() {
        return landLine1;
    }

    public void setLandLine1(String landLine1) {
        this.landLine1 = landLine1;
    }

    public String getLandLine2() {
        return landLine2;
    }

    public void setLandLine2(String landLine2) {
        this.landLine2 = landLine2;
    }

    public String getCrmStatus() {
        return crmStatus;
    }

    public void setCrmStatus(String crmStatus) {
        this.crmStatus = crmStatus;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getActivatiomMi() {
        return activatiomMi;
    }

    public void setActivatiomMi(String activatiomMi) {
        this.activatiomMi = activatiomMi;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getReminderDate() {
        return reminderDate;
    }

    public void setReminderDate(String reminderDate) {
        this.reminderDate = reminderDate;
    }

    public LocationModel getLocation() {
        return location;
    }

    public void setLocation(LocationModel location) {
        this.location = location;
    }

    public ArrayList<DetailOtherData> getOther() {
        return other;
    }

    public void setOther(ArrayList<DetailOtherData> other) {
        this.other = other;
    }

    public Constants.EsclateStatus getEsclate() {
        return esclate;
    }

    public void setEsclate(Constants.EsclateStatus esclate) {
        this.esclate = esclate;
    }

    public ArrayList<SpinnerData> getVisitStatus() {
        return visitStatus;
    }

    public void setVisitStatus(ArrayList<SpinnerData> visitStatus) {
        this.visitStatus = visitStatus;
    }

    public ArrayList<SpinnerData> getFeedback() {
        return feedback;
    }

    public void setFeedback(ArrayList<SpinnerData> feedback) {
        this.feedback = feedback;
    }

    public DetailFromUPCRoleModel getFromZsm() {
        return fromZsm;
    }

    public void setFromZsm(DetailFromUPCRoleModel fromZsm) {
        this.fromZsm = fromZsm;
    }

    public DetailFromUPCRoleModel getFromMico() {
        return fromMico;
    }

    public void setFromMico(DetailFromUPCRoleModel fromMico) {
        this.fromMico = fromMico;
    }

    public DetailFromUPCRoleModel getFromExecutive() {
        return fromExecutive;
    }

    public void setFromExecutive(DetailFromUPCRoleModel fromExecutive) {
        this.fromExecutive = fromExecutive;
    }
}
