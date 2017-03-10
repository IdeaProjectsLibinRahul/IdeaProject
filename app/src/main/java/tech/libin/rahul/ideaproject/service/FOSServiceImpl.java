package tech.libin.rahul.ideaproject.service;

import com.android.volley.Request;

import java.util.List;

import tech.libin.rahul.ideaproject.configurations.Config;
import tech.libin.rahul.ideaproject.configurations.Constants;
import tech.libin.rahul.ideaproject.network.FOSNetworkRequest;
import tech.libin.rahul.ideaproject.network.FOSNetworkRequestImpl;
import tech.libin.rahul.ideaproject.network.constants.ServiceURLs;
import tech.libin.rahul.ideaproject.network.handlers.NetworkCallback;
import tech.libin.rahul.ideaproject.service.handlers.ServiceCallback;
import tech.libin.rahul.ideaproject.service.mapper.ActivityMapper;
import tech.libin.rahul.ideaproject.service.mapper.CollectionDetailMapper;
import tech.libin.rahul.ideaproject.service.mapper.OtherFormSubmitMapper;
import tech.libin.rahul.ideaproject.service.mapper.SmeDetailMapper;
import tech.libin.rahul.ideaproject.service.mapper.SmeFormSubmitMapper;
import tech.libin.rahul.ideaproject.service.mapper.TdDetailMapper;
import tech.libin.rahul.ideaproject.service.mapper.UpcDetailMapper;
import tech.libin.rahul.ideaproject.service.requests.ActivityDetailRequest;
import tech.libin.rahul.ideaproject.service.requests.ActivityRequest;
import tech.libin.rahul.ideaproject.service.requests.LoginRequest;
import tech.libin.rahul.ideaproject.service.requests.LogoutRequest;
import tech.libin.rahul.ideaproject.service.requests.OtherFormSubmitRequest;
import tech.libin.rahul.ideaproject.service.requests.SmeFormSubmitRequest;
import tech.libin.rahul.ideaproject.service.responses.ActivityResponse;
import tech.libin.rahul.ideaproject.service.responses.CollectionDetailResponse;
import tech.libin.rahul.ideaproject.service.responses.FormSubmitResponse;
import tech.libin.rahul.ideaproject.service.responses.LoginResponse;
import tech.libin.rahul.ideaproject.service.responses.LogoutResponse;
import tech.libin.rahul.ideaproject.service.responses.SmeDetailResponse;
import tech.libin.rahul.ideaproject.service.responses.TdDetailResponse;
import tech.libin.rahul.ideaproject.service.responses.UpcDetailResponse;
import tech.libin.rahul.ideaproject.service.responses.base.FOSError;
import tech.libin.rahul.ideaproject.views.detailsview.viewmodels.CollectionDetailModel;
import tech.libin.rahul.ideaproject.views.detailsview.viewmodels.OtherFormSubmitModel;
import tech.libin.rahul.ideaproject.views.detailsview.viewmodels.SmeDetailModel;
import tech.libin.rahul.ideaproject.views.detailsview.viewmodels.SmeFormSubmitModel;
import tech.libin.rahul.ideaproject.views.detailsview.viewmodels.TdDetailModel;
import tech.libin.rahul.ideaproject.views.detailsview.viewmodels.UpcDetailModel;
import tech.libin.rahul.ideaproject.views.homescreen.viewmodels.ActivityModel;
import tech.libin.rahul.ideaproject.views.models.ActivityDetailRequestModel;
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

    @Override
    public void getCollectionDetail(ActivityDetailRequestModel model, final ServiceCallback<CollectionDetailModel> callback) {

        final CollectionDetailMapper mapper = new CollectionDetailMapper();
        ActivityDetailRequest activityDetailRequest = mapper.getRequest(model);

        FOSNetworkRequest<CollectionDetailResponse> request = new FOSNetworkRequestImpl<>(activityDetailRequest, ServiceURLs.COLLECTION_DETAIL, CollectionDetailResponse.class);
        request.request(Request.Method.POST, new NetworkCallback<CollectionDetailResponse>() {
            @Override
            public void onSuccess(CollectionDetailResponse response) {

                if (response.getStatus() != Constants.Status.SUCCESS) {
                    FOSError error = new FOSError();
                    error.setErrorMessage(response.getMessage());
                    callback.onRequestFail(error);
                } else {
                    CollectionDetailModel responseData = mapper.getResponse(response);
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

    @Override
    public void getTdDetail(ActivityDetailRequestModel model, final ServiceCallback<TdDetailModel> callback) {
        final TdDetailMapper mapper = new TdDetailMapper();
        ActivityDetailRequest activityDetailRequest = mapper.getRequest(model);

        FOSNetworkRequest<TdDetailResponse> request = new FOSNetworkRequestImpl<>(activityDetailRequest, ServiceURLs.TD_DETAIL, TdDetailResponse.class);
        request.request(Request.Method.POST, new NetworkCallback<TdDetailResponse>() {
            @Override
            public void onSuccess(TdDetailResponse response) {

                if (response.getStatus() != Constants.Status.SUCCESS) {
                    FOSError error = new FOSError();
                    error.setErrorMessage(response.getMessage());
                    callback.onRequestFail(error);
                } else {
                    TdDetailModel responseData = mapper.getResponse(response);
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

    @Override
    public void getSmeDetail(ActivityDetailRequestModel model, final ServiceCallback<SmeDetailModel> callback) {
        final SmeDetailMapper mapper = new SmeDetailMapper();
        ActivityDetailRequest activityDetailRequest = mapper.getRequest(model);

        FOSNetworkRequest<SmeDetailResponse> request = new FOSNetworkRequestImpl<>(activityDetailRequest, ServiceURLs.SME_DETAIL, SmeDetailResponse.class);
        request.request(Request.Method.POST, new NetworkCallback<SmeDetailResponse>() {
            @Override
            public void onSuccess(SmeDetailResponse response) {

                if (response.getStatus() != Constants.Status.SUCCESS) {
                    FOSError error = new FOSError();
                    error.setErrorMessage(response.getMessage());
                    callback.onRequestFail(error);
                } else {
                    SmeDetailModel responseData = mapper.getResponse(response);
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

    @Override
    public void getUpcDetail(ActivityDetailRequestModel model, final ServiceCallback<UpcDetailModel> callback) {
        final UpcDetailMapper mapper = new UpcDetailMapper();
        ActivityDetailRequest activityDetailRequest = mapper.getRequest(model);

        FOSNetworkRequest<UpcDetailResponse> request = new FOSNetworkRequestImpl<>(activityDetailRequest, ServiceURLs.UPC_DETAIL, UpcDetailResponse.class);
        request.request(Request.Method.POST, new NetworkCallback<UpcDetailResponse>() {
            @Override
            public void onSuccess(UpcDetailResponse response) {

                if (response.getStatus() != Constants.Status.SUCCESS) {
                    FOSError error = new FOSError();
                    error.setErrorMessage(response.getMessage());
                    callback.onRequestFail(error);
                } else {
                    UpcDetailModel responseData = mapper.getResponse(response);
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


    @Override
    public void doSubmitSmeVisitDetails(SmeFormSubmitModel model, final ServiceCallback<String> callback) {
        final SmeFormSubmitMapper mapper = new SmeFormSubmitMapper();
        SmeFormSubmitRequest smeFormSubmitRequest = mapper.getRequest(model);


        FOSNetworkRequest<FormSubmitResponse> request = new FOSNetworkRequestImpl<>(smeFormSubmitRequest, ServiceURLs.FORM_SUBMIT, FormSubmitResponse.class);
        request.request(Request.Method.POST, new NetworkCallback<FormSubmitResponse>() {
            @Override
            public void onSuccess(FormSubmitResponse response) {

                if (response.getStatus() != Constants.Status.SUCCESS) {
                    FOSError error = new FOSError();
                    error.setErrorMessage(response.getMessage());
                    callback.onRequestFail(error);
                } else {
                    callback.onResponse(response.getMessage());
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
    public void doSubmitOtherVisitDetails(OtherFormSubmitModel model, final ServiceCallback<String> callback) {
        final OtherFormSubmitMapper mapper = new OtherFormSubmitMapper();
        OtherFormSubmitRequest otherFormSubmitRequest = mapper.getRequest(model);

        FOSNetworkRequest<FormSubmitResponse> request = new FOSNetworkRequestImpl<>(otherFormSubmitRequest, ServiceURLs.FORM_SUBMIT, FormSubmitResponse.class);
        request.request(Request.Method.POST, new NetworkCallback<FormSubmitResponse>() {
            @Override
            public void onSuccess(FormSubmitResponse response) {

                if (response.getStatus() != Constants.Status.SUCCESS) {
                    FOSError error = new FOSError();
                    error.setErrorMessage(response.getMessage());
                    callback.onRequestFail(error);
                } else {
                    callback.onResponse(response.getMessage());
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
    public void doLogout(String userId, final ServiceCallback<String> callback) {
        User user = Config.getInstance().getUser();
        LogoutRequest logoutRequest = new LogoutRequest();
        logoutRequest.setUserId(userId);
        logoutRequest.setSessionKey(user.getSessionKey());
        FOSNetworkRequest<LogoutResponse> request = new FOSNetworkRequestImpl<>(logoutRequest, ServiceURLs.LOGOUT, LogoutResponse.class);
        request.request(Request.Method.POST, new NetworkCallback<LogoutResponse>() {
            @Override
            public void onSuccess(LogoutResponse response) {

                if (response.getStatus() != Constants.Status.SUCCESS) {
                    FOSError error = new FOSError();
                    error.setErrorMessage(response.getMessage());
                    callback.onRequestFail(error);
                } else {
                    callback.onResponse(response.getMessage());
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
