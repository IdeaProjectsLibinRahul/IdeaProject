package tech.libin.rahul.ideaproject.facade;


import tech.libin.rahul.ideaproject.service.handlers.ServiceCallback;
import tech.libin.rahul.ideaproject.service.models.UserModel;

/**
 * Created by 10945 on 10/27/2016.
 */

public interface FOSFacade {
    void registerUser(UserModel userModel, ServiceCallback<UserModel> callback);
}
