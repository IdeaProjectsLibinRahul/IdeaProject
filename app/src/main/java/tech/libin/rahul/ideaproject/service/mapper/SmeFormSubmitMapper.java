package tech.libin.rahul.ideaproject.service.mapper;

import tech.libin.rahul.ideaproject.configurations.Config;
import tech.libin.rahul.ideaproject.service.mapper.base.FOSBaseMapper;
import tech.libin.rahul.ideaproject.service.requests.ActivityDetailRequest;
import tech.libin.rahul.ideaproject.service.requests.SmeFormSubmitRequest;
import tech.libin.rahul.ideaproject.service.responses.SmeDetailResponse;
import tech.libin.rahul.ideaproject.views.detailsview.viewmodels.SmeDetailModel;
import tech.libin.rahul.ideaproject.views.detailsview.viewmodels.SmeFormSubmitModel;
import tech.libin.rahul.ideaproject.views.models.ActivityDetailRequestModel;
import tech.libin.rahul.ideaproject.views.models.User;

/**
 * Created by libin on 01/03/17.
 */

public class SmeFormSubmitMapper implements FOSBaseMapper<SmeFormSubmitModel, SmeFormSubmitRequest, String, String> {

    @Override
    public SmeFormSubmitRequest getRequest(SmeFormSubmitModel model) {
        User user = Config.getInstance().getUser();
        String sessionKey = user.getSessionKey();

        SmeFormSubmitRequest request = new SmeFormSubmitRequest();
        request.setUserId(user.getUserId());
        request.setSessionKey(sessionKey);
        request.setObjectId(model.getObjectId());
        request.setStatus(model.getStatus());
        request.setFeedback(model.getFeedback());
        request.setReason(model.getReason());
        request.setRemarks(model.getRemarks());
        request.setLatitude(model.getLatitude());
        request.setLongitude(model.getLongitude());
        request.setReminder(model.getReminder());
        request.setRecordType(model.getRecordType());

        return request;
    }

    @Override
    public String getResponse(String model) {

        return model;
    }
}
