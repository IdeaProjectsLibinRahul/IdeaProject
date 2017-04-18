package tech.libin.rahul.ideaproject.service.requests;

import tech.libin.rahul.ideaproject.configurations.Constants;
import tech.libin.rahul.ideaproject.service.requests.base.FOSBaseRequest;

/**
 * Created by libin on 18/04/17.
 */

public class OTPRequest extends FOSBaseRequest {
    private Long userId;
    private String otp;
    private Constants.OtpVerificationType type;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public Constants.OtpVerificationType getType() {
        return type;
    }

    public void setType(Constants.OtpVerificationType type) {
        this.type = type;
    }
}
