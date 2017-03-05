package tech.libin.rahul.ideaproject.facade;

import java.util.List;

import tech.libin.rahul.ideaproject.service.FOSService;
import tech.libin.rahul.ideaproject.service.FOSServiceImpl;
import tech.libin.rahul.ideaproject.service.handlers.ServiceCallback;
import tech.libin.rahul.ideaproject.views.homescreen.viewmodels.ActivityModel;
import tech.libin.rahul.ideaproject.views.models.ActivityRequestModel;
import tech.libin.rahul.ideaproject.views.models.Login;
import tech.libin.rahul.ideaproject.views.models.User;

/**
 * Created by 10945 on 10/27/2016.
 */

public class FOSFacadeImpl implements FOSFacade {
    FOSService fosService;

    public FOSFacadeImpl() {
        fosService = new FOSServiceImpl();
    }

    @Override
    public void doLogin(Login login, ServiceCallback<User> callback) {
        fosService.doLogin(login, callback);
    }

    @Override
    public void getActivity(ActivityRequestModel model, ServiceCallback<List<ActivityModel>> callback) {
        fosService.getActivity(model, callback);
    }
}
