package tech.libin.rahul.ideaproject.views.homescreen.viewmodels;

import tech.libin.rahul.ideaproject.configurations.Constants;

/**
 * Created by libin on 26/02/17.
 */

public class ActivityModel {

    private String id;
    private String name;
    private String phoneNo;
    private String topRight;
    private String bottomCenter;
    private String bottomRight;
    private Constants.RecordType type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getTopRight() {
        return topRight;
    }

    public void setTopRight(String topRight) {
        this.topRight = topRight;
    }

    public String getBottomCenter() {
        return bottomCenter;
    }

    public void setBottomCenter(String bottomCenter) {
        this.bottomCenter = bottomCenter;
    }

    public String getBottomRight() {
        return bottomRight;
    }

    public void setBottomRight(String bottomRight) {
        this.bottomRight = bottomRight;
    }

    public Constants.RecordType getType() {
        return type;
    }

    public void setType(Constants.RecordType type) {
        this.type = type;
    }
}
