package tech.libin.rahul.ideaproject.service.requests;

import tech.libin.rahul.ideaproject.service.requests.base.FOSBaseRequest;

/**
 * Created by 10945 on 10/27/2016.
 */

public class RegisterRequest extends FOSBaseRequest {
    private String phoneNo;

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
}
