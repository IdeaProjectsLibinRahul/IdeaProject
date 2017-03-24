package tech.libin.rahul.ideaproject.facade;

import android.net.Uri;

import java.util.List;
import java.util.Map;

import tech.libin.rahul.ideaproject.service.FOSService;
import tech.libin.rahul.ideaproject.service.FOSServiceImpl;
import tech.libin.rahul.ideaproject.service.handlers.ServiceCallback;
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

    @Override
    public void getUpcDetail(ActivityDetailRequestModel model, ServiceCallback<UpcDetailModel> callback) {
        fosService.getUpcDetail(model, callback);
    }

    @Override
    public void getSmeDetail(ActivityDetailRequestModel model, ServiceCallback<SmeDetailModel> callback) {
        fosService.getSmeDetail(model, callback);
    }

    @Override
    public void getTdDetail(ActivityDetailRequestModel model, ServiceCallback<TdDetailModel> callback) {
        fosService.getTdDetail(model, callback);
    }

    @Override
    public void getCollectionDetail(ActivityDetailRequestModel model, ServiceCallback<CollectionDetailModel> callback) {
        fosService.getCollectionDetail(model, callback);
    }

    @Override
    public void doSubmitVisitDetails(FormSubmitModel model, ServiceCallback<String> callback) {
        fosService.doSubmitVisitDetails(model, callback);

    }

    @Override
    public void doRegistration(Map<String, String> data, Map<String, Uri> files, ServiceCallback<String> callback) {
        fosService.doRegistration(data, files, callback);
    }

    @Override
    public void doRegistrationDummy(RegisterModel model, ServiceCallback<String> callback) {
        fosService.doRegistrationDummy(model, callback);
    }

    @Override
    public void doLogout(String userId, ServiceCallback<String> callback) {
        fosService.doLogout(userId, callback);
    }

    @Override
    public void forgotPassword(String miCode, String mobileNum, ServiceCallback<ForgotPasswordModel> callback) {
        fosService.forgotPassword(miCode, mobileNum, callback);
    }
}
