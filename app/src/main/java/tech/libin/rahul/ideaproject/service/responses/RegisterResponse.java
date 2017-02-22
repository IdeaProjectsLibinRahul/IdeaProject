package tech.libin.rahul.ideaproject.service.responses;

import tech.libin.rahul.ideaproject.service.responses.base.FOSBaseResponse;

/**
 * Created by 10945 on 10/27/2016.
 */

public class RegisterResponse extends FOSBaseResponse {
    private Long userId;
    private String phoneNo;
    private String result;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

}
