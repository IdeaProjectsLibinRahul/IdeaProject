package tech.libin.rahul.ideaproject.service.responses;

import tech.libin.rahul.ideaproject.configurations.Constants;
import tech.libin.rahul.ideaproject.service.responses.base.FOSBaseResponse;

/**
 * Created by 10945 on 10/27/2016.
 */

public class RegisterResponse extends FOSBaseResponse {
String response;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
