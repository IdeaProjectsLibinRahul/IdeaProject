package tech.libin.rahul.ideaproject.service.responses;

import tech.libin.rahul.ideaproject.service.responses.base.FOSBaseResponse;

/**
 * Created by rahul on 3/21/2017.
 */

public class UserRegisterResponse extends FOSBaseResponse {
    String response;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
