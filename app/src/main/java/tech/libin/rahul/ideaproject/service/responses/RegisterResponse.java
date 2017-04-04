package tech.libin.rahul.ideaproject.service.responses;

import tech.libin.rahul.ideaproject.configurations.Constants;
import tech.libin.rahul.ideaproject.service.responses.base.FOSBaseResponse;

/**
 * Created by 10945 on 10/27/2016.
 */

public class RegisterResponse extends FOSBaseResponse {
    private Constants.Status status;
    private String message;

    @Override
    public Constants.Status getStatus() {
        return status;
    }

    @Override
    public void setStatus(Constants.Status status) {
        this.status = status;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }
}
