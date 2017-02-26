package tech.libin.rahul.ideaproject.service.requests.base;

/**
 * Created by 10945 on 10/27/2016.
 */

public abstract class FOSBaseRequest {
    private String sessionKey;

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }
}
