package tech.libin.rahul.ideaproject.events;

import tech.libin.rahul.ideaproject.events.base.BaseEvent;
import tech.libin.rahul.ideaproject.views.homescreen.viewmodels.ActivityModel;

/**
 * Created by libin on 26/02/17.
 */

public class DetailsEvent extends BaseEvent {
    private ActivityModel model;

    public ActivityModel getModel() {
        return model;
    }

    public void setModel(ActivityModel model) {
        this.model = model;
    }
}
