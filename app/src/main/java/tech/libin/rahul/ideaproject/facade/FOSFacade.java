package tech.libin.rahul.ideaproject.facade;


import android.net.Uri;

import java.util.List;
import java.util.Map;

import tech.libin.rahul.ideaproject.configurations.Constants;
import tech.libin.rahul.ideaproject.service.handlers.ServiceCallback;
import tech.libin.rahul.ideaproject.service.responses.OTPResponse;
import tech.libin.rahul.ideaproject.service.responses.RegisterResponse;
import tech.libin.rahul.ideaproject.views.credentialviews.viewmodels.ForgotPasswordModel;
import tech.libin.rahul.ideaproject.views.detailsview.viewmodels.CollectionDetailModel;
import tech.libin.rahul.ideaproject.views.detailsview.viewmodels.FormSubmitModel;
import tech.libin.rahul.ideaproject.views.detailsview.viewmodels.SmeDetailModel;
import tech.libin.rahul.ideaproject.views.detailsview.viewmodels.TdDetailModel;
import tech.libin.rahul.ideaproject.views.detailsview.viewmodels.UpcDetailModel;
import tech.libin.rahul.ideaproject.views.homescreen.viewmodels.ActivityModel;
import tech.libin.rahul.ideaproject.views.models.ActivityDetailRequestModel;
import tech.libin.rahul.ideaproject.views.models.ActivityRequestModel;
import tech.libin.rahul.ideaproject.views.models.Login;
import tech.libin.rahul.ideaproject.views.models.RegisterModel;
import tech.libin.rahul.ideaproject.views.models.User;

/**
 * Created by 10945 on 10/27/2016.
 */

public interface FOSFacade {
    void doLogin(Login login, ServiceCallback<User> callback);

    void getActivity(ActivityRequestModel model, ServiceCallback<List<ActivityModel>> callback);

    void getUpcDetail(ActivityDetailRequestModel model, ServiceCallback<UpcDetailModel> callback);

    void getSmeDetail(ActivityDetailRequestModel model, ServiceCallback<SmeDetailModel> callback);

    void getTdDetail(ActivityDetailRequestModel model, ServiceCallback<TdDetailModel> callback);

    void getCollectionDetail(ActivityDetailRequestModel model, ServiceCallback<CollectionDetailModel> callback);

    void doSubmitVisitDetails(FormSubmitModel model, ServiceCallback<String> callback);

    void doRegistration(Map<String, String> data, final Map<String, Uri> files, ServiceCallback<RegisterResponse> callback);

    void doLogout(String userId, ServiceCallback<String> callback);

    void forgotPassword(String miCode, String mobileNum, ServiceCallback<ForgotPasswordModel> callback);

    void resetPassword(Long userId,String password, ServiceCallback<String> callback);

    void submitOTP(Long userId, Constants.OtpVerificationType otpType, String otp, ServiceCallback<OTPResponse> callback);

    void regenerateOTP(Long userId,String password, ServiceCallback<String> callback);

}
