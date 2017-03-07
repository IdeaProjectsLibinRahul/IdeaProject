package tech.libin.rahul.ideaproject.service.responses;

import tech.libin.rahul.ideaproject.configurations.Constants;
import tech.libin.rahul.ideaproject.service.responses.base.FOSBaseResponse;

/**
 * Created by rahul on 2/26/2017.
 */

public class LoginResponse extends FOSBaseResponse {

    private Response response;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public class Response {
        private Long userId;
        private String sessionKey;
        private Constants.Role role;
        private Constants.Type type;
        private String name;
        private String phoneNo;

        public Long getUserId() {
            return userId;
        }

        public void setUserId(Long userId) {
            this.userId = userId;
        }

        public String getSessionKey() {
            return sessionKey;
        }

        public void setSessionKey(String sessionKey) {
            this.sessionKey = sessionKey;
        }

        public Constants.Role getRole() {
            return role;
        }

        public void setRole(Constants.Role role) {
            this.role = role;
        }

        public Constants.Type getType() {
            return type;
        }

        public void setType(Constants.Type type) {
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhoneNo() {
            return phoneNo;
        }

        public void setPhoneNo(String phoneNo) {
            this.phoneNo = phoneNo;
        }
    }
}
