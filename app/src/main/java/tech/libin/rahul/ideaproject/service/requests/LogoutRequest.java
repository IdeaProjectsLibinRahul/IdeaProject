package tech.libin.rahul.ideaproject.service.requests;

import tech.libin.rahul.ideaproject.service.requests.base.FOSBaseRequest;

/**
 * Created by libin on 09/03/17.
 */

public class LogoutRequest extends FOSBaseRequest {
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
