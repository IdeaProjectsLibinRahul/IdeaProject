package tech.libin.rahul.ideaproject.service.requests;

import tech.libin.rahul.ideaproject.configurations.Constants;
import tech.libin.rahul.ideaproject.service.requests.base.FOSBaseRequest;

/**
 * Created by rahul on 3/5/2017.
 */

public class ActivityDetailRequest extends FOSBaseRequest {

    Long userId;
    String objectId;
    Constants.RecordType recordType;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
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
