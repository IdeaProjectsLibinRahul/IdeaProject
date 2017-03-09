package tech.libin.rahul.ideaproject.facade;

import java.util.List;

import tech.libin.rahul.ideaproject.service.FOSService;
import tech.libin.rahul.ideaproject.service.FOSServiceImpl;
import tech.libin.rahul.ideaproject.service.handlers.ServiceCallback;
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
    public void doSubmitSmeVisitDetails(SmeFormSubmitModel model, ServiceCallback<String> callback) {
        fosService.doSubmitSmeVisitDetails(model, callback);

    }

    @Override
    public void doSubmitOtherVisitDetails(OtherFormSubmitModel model, ServiceCallback<String> callback) {
        fosService.doSubmitOtherVisitDetails(model, callback);
    }

}
