package tech.libin.rahul.ideaproject.service.mapper;

import tech.libin.rahul.ideaproject.configurations.Config;
import tech.libin.rahul.ideaproject.service.mapper.base.FOSBaseMapper;
import tech.libin.rahul.ideaproject.service.requests.SmeFormSubmitRequest;
import tech.libin.rahul.ideaproject.views.detailsview.viewmodels.FormSubmitModel;
import tech.libin.rahul.ideaproject.views.models.User;

/**
 * Created by libin on 01/03/17.
 */

public class FormSubmitMapper implements FOSBaseMapper<FormSubmitModel, SmeFormSubmitRequest, String, String> {

    @Override
    public SmeFormSubmitRequest getRequest(FormSubmitModel model) {
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
        request.setAmountPaid(model.getAmountPaid());
        request.setLandmark(model.getLandmark());

        return request;
    }

    @Override
    public String getResponse(String model) {

        return model;
    }
}
