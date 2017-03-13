package tech.libin.rahul.ideaproject.events;

import tech.libin.rahul.ideaproject.configurations.Constants;
import tech.libin.rahul.ideaproject.events.base.BaseEvent;
import tech.libin.rahul.ideaproject.views.homescreen.viewmodels.ActivityModel;

/**
 * Created by libin on 26/02/17.
 */

public class DetailsEvent extends BaseEvent {
    private ActivityModel model;
    private Constants.ActivityType activityType;

    public ActivityModel getModel() {
        return model;
    }

    public void setModel(ActivityModel model) {
        this.model = model;
    }

    public Constants.ActivityType getActivityType() {
        return activityType;
    }

    public void setActivityType(Constants.ActivityType activityType) {
        this.activityType = activityType;
    }
}
