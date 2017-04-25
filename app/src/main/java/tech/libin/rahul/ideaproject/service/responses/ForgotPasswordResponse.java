package tech.libin.rahul.ideaproject.service.responses;

import tech.libin.rahul.ideaproject.service.responses.base.FOSBaseResponse;

/**
 * Created by libin on 24/03/17.
 */

public class ForgotPasswordResponse extends FOSBaseResponse {

    private Response response;

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
