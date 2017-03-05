package tech.libin.rahul.ideaproject.service;

import java.util.List;

import tech.libin.rahul.ideaproject.service.handlers.ServiceCallback;
import tech.libin.rahul.ideaproject.views.homescreen.viewmodels.ActivityModel;
import tech.libin.rahul.ideaproject.views.models.ActivityRequestModel;
import tech.libin.rahul.ideaproject.views.models.Login;
import tech.libin.rahul.ideaproject.views.models.User;

/**
 * Created by 10945 on 10/27/2016.
 */

public interface FOSService {
    void doLogin(Login login, ServiceCallback<User> callback);

    void getActivity(ActivityRequestModel model, ServiceCallback<List<ActivityModel>> callback);
}
