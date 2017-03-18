package tech.libin.rahul.ideaproject.service.mapper;

import tech.libin.rahul.ideaproject.configurations.Config;
import tech.libin.rahul.ideaproject.service.mapper.base.FOSBaseMapper;
import tech.libin.rahul.ideaproject.service.requests.ActivityDetailRequest;
import tech.libin.rahul.ideaproject.service.responses.UpcDetailResponse;
import tech.libin.rahul.ideaproject.views.detailsview.viewmodels.UpcDetailModel;
import tech.libin.rahul.ideaproject.views.models.ActivityDetailRequestModel;
import tech.libin.rahul.ideaproject.views.models.User;

/**
 * Created by libin on 01/03/17.
 */

public class UpcDetailMapper implements FOSBaseMapper<ActivityDetailRequestModel, ActivityDetailRequest, UpcDetailResponse, UpcDetailModel> {

    @Override
    public ActivityDetailRequest getRequest(ActivityDetailRequestModel model) {
        User user = Config.getInstance().getUser();
        String sessionKey = user.getSessionKey();

        ActivityDetailRequest request = new ActivityDetailRequest();
        request.setUserId(user.getUserId());
        request.setSessionKey(sessionKey);
        request.setObjectId(model.getObjectId());
        request.setRecordType(model.getRecordType());
        return request;
    }

    @Override
    public UpcDetailModel getResponse(UpcDetailResponse model) {

        UpcDetailModel models = new UpcDetailModel();

        models.setUpc(model.getResponse().getUpc());
        models.setMsisdn(model.getResponse().getMsisdn());
        models.setSubscriberType(model.getResponse().getSubscriberType());
        models.setCreatedDateTime(model.getResponse().getCreatedDateTime());
        models.setAddress1(model.getResponse().getAddress1());
        models.setAddress2(model.getResponse().getAddress2());
        models.setAddress3(model.getResponse().getAddress3());
        models.setAlternateNumber(model.getResponse().getAlternateNumber());
        models.setCsCreditCode(model.getResponse().getCsCreditCode());
        models.setCustomerName(model.getResponse().getCustomerName());
        models.setCustomerSubsType(model.getResponse().getCustomerSubsType());
        models.setCustomerType(model.getResponse().getCustomerType());
        models.setReminderDate(model.getResponse().getReminderDate());
        models.setEsclate(model.getResponse().getEsclate());
        models.setFromExecutive(model.getResponse().getFromExecutive());
        models.setFromMico(model.getResponse().getFromMico());
        models.setFromZsm(model.getResponse().getFromZsm());
        models.setLocation(model.getResponse().getLocation());
        models.setOther(model.getResponse().getOther());
        models.setSegment(model.getResponse().getSegment());
        models.setServSeg(model.getResponse().getServSeg());
        models.setZip(model.getResponse().getZip());
        models.setZip(model.getResponse().getZip());
        models.setVisitStatus(model.getResponse().getVisitStatus());
        models.setFeedbackNotRetained(model.getResponse().getFeedbackNotRetained());
        models.setFeedbackRetained(model.getResponse().getFeedbackRetained());

        return models;
    }
}
