package tech.libin.rahul.ideaproject.service;

import com.android.volley.Request;

import java.util.List;

import tech.libin.rahul.ideaproject.configurations.Constants;
import tech.libin.rahul.ideaproject.network.FOSNetworkRequest;
import tech.libin.rahul.ideaproject.network.FOSNetworkRequestImpl;
import tech.libin.rahul.ideaproject.network.constants.ServiceURLs;
import tech.libin.rahul.ideaproject.network.handlers.NetworkCallback;
import tech.libin.rahul.ideaproject.service.handlers.ServiceCallback;
import tech.libin.rahul.ideaproject.service.mapper.ActivityMapper;
import tech.libin.rahul.ideaproject.service.requests.ActivityRequest;
import tech.libin.rahul.ideaproject.service.requests.LoginRequest;
import tech.libin.rahul.ideaproject.service.responses.ActivityResponse;
import tech.libin.rahul.ideaproject.service.responses.LoginResponse;
import tech.libin.rahul.ideaproject.service.responses.base.FOSError;
import tech.libin.rahul.ideaproject.views.homescreen.viewmodels.ActivityModel;
import tech.libin.rahul.ideaproject.views.models.ActivityRequestModel;
import tech.libin.rahul.ideaproject.views.models.Login;
import tech.libin.rahul.ideaproject.views.models.User;

/**
 * Created by 10945 on 10/27/2016.
 */

public class FOSServiceImpl implements FOSService {

    @Override
    public void doLogin(Login login, final ServiceCallback<User> callback) {

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername(login.getUsername());
        loginRequest.setPassword(login.getPassword());
        loginRequest.setImei1(login.getImei1());
        loginRequest.setImei2(login.getImei2());

        FOSNetworkRequest<LoginResponse> request = new FOSNetworkRequestImpl<LoginResponse>(loginRequest, ServiceURLs.LOGIN, LoginResponse.class);
        request.request(Request.Method.POST, new NetworkCallback<LoginResponse>() {
            @Override
            public void onSuccess(LoginResponse response) {

                if (response.getStatus() != Constants.Status.SUCCESS) {
                    FOSError error = new FOSError();
                    error.setErrorMessage(response.getMessage());
                    callback.onRequestFail(error);
                } else {
                    User user = new User(response.getResponse());
                    callback.onResponse(user);
                }
            }

            @Override
            public void onTimeout() {
                callback.onRequestTimout();
            }

            @Override
            public void onFail(FOSError error) {
                callback.onRequestFail(error);
            }
        });
    }

    @Override
    public void getActivity(ActivityRequestModel model, final ServiceCallback<List<ActivityModel>> callback) {
        final ActivityMapper mapper = new ActivityMapper();
        ActivityRequest activityRequest = mapper.getRequest(model);

        FOSNetworkRequest<ActivityResponse> request = new FOSNetworkRequestImpl<>(activityRequest, ServiceURLs.ACTIVITY, ActivityResponse.class);
        request.request(Request.Method.POST, new NetworkCallback<ActivityResponse>() {
            @Override
            public void onSuccess(ActivityResponse response) {

                if (response.getStatus() != Constants.Status.SUCCESS) {
                    FOSError error = new FOSError();
                    error.setErrorMessage(response.getMessage());
                    callback.onRequestFail(error);
                } else {
                    List<ActivityModel> responseData = mapper.getResponse(response);
                    callback.onResponse(responseData);
                }
            }

            @Override
            public void onTimeout() {
                callback.onRequestTimout();
            }

            @Override
            public void onFail(FOSError error) {
                callback.onRequestFail(error);
            }
        });
    }
}
