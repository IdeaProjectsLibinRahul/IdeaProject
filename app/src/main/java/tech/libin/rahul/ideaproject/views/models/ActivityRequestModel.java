package tech.libin.rahul.ideaproject.views.models;

import tech.libin.rahul.ideaproject.configurations.Constants;

/**
 * Created by libin on 01/03/17.
 */

public class ActivityRequestModel {

    private Long userId;

    private int pageNo;

    private int pageSize;

    private Constants.Type type;

    private Constants.RecordType recordType;

    private Constants.ActivityType activityType;

    private String zip;

    private String msisdn;

    private String name;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public Constants.Type getType() {
        return type;
    }

    public void setType(Constants.Type type) {
        this.type = type;
    }

    public Constants.ActivityType getActivityType() {
        return activityType;
    }

    public void setActivityType(Constants.ActivityType activityType) {
        this.activityType = activityType;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Constants.RecordType getRecordType() {
        return recordType;
    }

    public void setRecordType(Constants.RecordType recordType) {
        this.recordType = recordType;
    }
}
