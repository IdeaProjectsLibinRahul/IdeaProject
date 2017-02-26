package tech.libin.rahul.ideaproject.service.responses.base;

import tech.libin.rahul.ideaproject.configurations.Constants;

/**
 * Created by 10945 on 10/27/2016.
 */

public abstract class FOSBaseResponse {
    private Constants.Status status;
    private String message;

    public Constants.Status getStatus() {
        return status;
    }

    public void setStatus(Constants.Status status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
