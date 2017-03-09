package tech.libin.rahul.ideaproject.service;

import java.util.List;

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

public interface FOSService {
    void doLogin(Login login, ServiceCallback<User> callback);

    void getActivity(ActivityRequestModel model, ServiceCallback<List<ActivityModel>> callback);

    void getUpcDetail(ActivityDetailRequestModel model, ServiceCallback<UpcDetailModel> callback);

    void getSmeDetail(ActivityDetailRequestModel model, ServiceCallback<SmeDetailModel> callback);

    void getTdDetail(ActivityDetailRequestModel model, ServiceCallback<TdDetailModel> callback);

    void getCollectionDetail(ActivityDetailRequestModel model, ServiceCallback<CollectionDetailModel> callback);

    void doSubmitSmeVisitDetails(SmeFormSubmitModel model, ServiceCallback<String> callback);

    void doSubmitOtherVisitDetails(OtherFormSubmitModel model, ServiceCallback<String> callback);
}
