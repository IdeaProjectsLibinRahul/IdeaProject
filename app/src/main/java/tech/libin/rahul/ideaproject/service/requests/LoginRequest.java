package tech.libin.rahul.ideaproject.service.requests;

import tech.libin.rahul.ideaproject.service.requests.base.FOSBaseRequest;

/**
 * Created by rahul on 2/26/2017.
 */

public class LoginRequest extends FOSBaseRequest {
    private String username;
    private String password;
    private String imei1;
    private String imei2;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImei1() {
        return imei1;
    }

    public void setImei1(String imei1) {
        this.imei1 = imei1;
    }

    public String getImei2() {
        return imei2;
    }

    public void setImei2(String imei2) {
        this.imei2 = imei2;
    }
}
