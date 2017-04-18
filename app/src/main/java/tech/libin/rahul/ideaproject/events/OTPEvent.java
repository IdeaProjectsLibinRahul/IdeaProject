package tech.libin.rahul.ideaproject.events;

import tech.libin.rahul.ideaproject.configurations.Constants;
import tech.libin.rahul.ideaproject.events.base.BaseEvent;

/**
 * Created by libin on 24/03/17.
 */

public class OTPEvent extends BaseEvent {
    private Long userId;
    private Constants.OtpVerificationType otpType;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Constants.OtpVerificationType getOtpType() {
        return otpType;
    }

    public void setOtpType(Constants.OtpVerificationType otpType) {
        this.otpType = otpType;
    }
}
