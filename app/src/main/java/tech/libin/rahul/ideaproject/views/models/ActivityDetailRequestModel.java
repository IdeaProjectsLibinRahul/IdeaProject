package tech.libin.rahul.ideaproject.views.models;

import tech.libin.rahul.ideaproject.configurations.Constants;

/**
 * Created by rahul on 3/5/2017.
 */

public class ActivityDetailRequestModel {

    private String userId;
    private String objectId;
    private Constants.RecordType recordType;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public Constants.RecordType getRecordType() {
        return recordType;
    }

    public void setRecordType(Constants.RecordType recordType) {
        this.recordType = recordType;
    }
}
