package tech.libin.rahul.ideaproject.views.credentialviews.viewmodels;

import tech.libin.rahul.ideaproject.configurations.Constants;

/**
 * Created by libin on 24/03/17.
 */

public class ForgotPasswordModel {
    private Constants.Status status;
    private String message;
    private Response response;

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

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public class Response {
        private Long userId;

        public Long getUserId() {
            return userId;
        }

        public void setUserId(Long userId) {
            this.userId = userId;
        }
    }
}
