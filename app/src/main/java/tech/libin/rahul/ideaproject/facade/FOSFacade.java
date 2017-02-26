package tech.libin.rahul.ideaproject.facade;


import tech.libin.rahul.ideaproject.service.handlers.ServiceCallback;
import tech.libin.rahul.ideaproject.service.models.UserModel;
import tech.libin.rahul.ideaproject.views.models.Login;
import tech.libin.rahul.ideaproject.views.models.User;

/**
 * Created by 10945 on 10/27/2016.
 */

public interface FOSFacade {
    void doLogin(Login login, ServiceCallback<User> callback);
}
