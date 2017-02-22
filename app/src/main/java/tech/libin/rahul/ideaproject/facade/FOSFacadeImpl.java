package tech.libin.rahul.ideaproject.facade;

import tech.libin.rahul.ideaproject.service.FOSService;
import tech.libin.rahul.ideaproject.service.FOSServiceImpl;
import tech.libin.rahul.ideaproject.service.handlers.ServiceCallback;
import tech.libin.rahul.ideaproject.service.models.UserModel;

/**
 * Created by 10945 on 10/27/2016.
 */

public class FOSFacadeImpl implements FOSFacade {
    FOSService fosService;

    public FOSFacadeImpl() {
        fosService = new FOSServiceImpl();
    }

    @Override
    public void registerUser(UserModel userModel, ServiceCallback<UserModel> callback) {
        fosService.registerUser(userModel, callback);
    }
}
