package tech.libin.rahul.ideaproject.events;

import tech.libin.rahul.ideaproject.events.base.BaseEvent;

/**
 * Created by libin on 24/03/17.
 */

public class OTPEvent extends BaseEvent {
    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
