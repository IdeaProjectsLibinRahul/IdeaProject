package tech.libin.rahul.ideaproject.service;

import com.android.volley.Request;

import tech.libin.rahul.ideaproject.network.FOSNetworkRequest;
import tech.libin.rahul.ideaproject.network.FOSNetworkRequestImpl;
import tech.libin.rahul.ideaproject.network.constants.ServiceURLs;
import tech.libin.rahul.ideaproject.network.handlers.NetworkCallback;
import tech.libin.rahul.ideaproject.service.handlers.ServiceCallback;
import tech.libin.rahul.ideaproject.service.models.UserModel;
import tech.libin.rahul.ideaproject.service.requests.RegisterRequest;
import tech.libin.rahul.ideaproject.service.responses.RegisterResponse;
import tech.libin.rahul.ideaproject.service.responses.base.FOSError;

/**
 * Created by 10945 on 10/27/2016.
 */

public class FOSServiceImpl implements FOSService {

    @Override
    public void registerUser(UserModel userModel, final ServiceCallback<UserModel> callback) {

        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setPhoneNo(userModel.getPhoneNumber());

        FOSNetworkRequest<RegisterResponse> networkRequest = new FOSNetworkRequestImpl<>(registerRequest, ServiceURLs.REGISTER, RegisterResponse.class);
        networkRequest.request(Request.Method.POST, new NetworkCallback<RegisterResponse>() {
            @Override
            public void onSuccess(RegisterResponse response) {
                UserModel model = new UserModel();
                model.setId(response.getUserId());
                model.setPhoneNumber(response.getPhoneNo());

                callback.onResponse(model);
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
