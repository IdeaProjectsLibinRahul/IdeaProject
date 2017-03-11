package tech.libin.rahul.ideaproject.service.mapper;

import tech.libin.rahul.ideaproject.configurations.Config;
import tech.libin.rahul.ideaproject.service.mapper.base.FOSBaseMapper;
import tech.libin.rahul.ideaproject.service.requests.OtherFormSubmitRequest;
import tech.libin.rahul.ideaproject.views.detailsview.viewmodels.OtherFormSubmitModel;
import tech.libin.rahul.ideaproject.views.models.User;

/**
 * Created by libin on 01/03/17.
 */

public class OtherFormSubmitMapper implements FOSBaseMapper<OtherFormSubmitModel, OtherFormSubmitRequest, String, String> {

    @Override
    public OtherFormSubmitRequest getRequest(OtherFormSubmitModel model) {
        User user = Config.getInstance().getUser();
        String sessionKey = user.getSessionKey();

        OtherFormSubmitRequest request = new OtherFormSubmitRequest();
        request.setUserId(user.getUserId());
        request.setSessionKey(sessionKey);
        request.setObjectId(model.getObjectId());
        request.setStatus(model.getStatus());
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
