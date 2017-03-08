package tech.libin.rahul.ideaproject.service.mapper;

import tech.libin.rahul.ideaproject.configurations.Config;
import tech.libin.rahul.ideaproject.service.mapper.base.FOSBaseMapper;
import tech.libin.rahul.ideaproject.service.requests.ActivityDetailRequest;
import tech.libin.rahul.ideaproject.service.responses.SmeDetailResponse;
import tech.libin.rahul.ideaproject.views.detailsview.viewmodels.SmeDetailModel;
import tech.libin.rahul.ideaproject.views.models.ActivityDetailRequestModel;
import tech.libin.rahul.ideaproject.views.models.User;

/**
 * Created by libin on 01/03/17.
 */

public class SmeDetailMapper implements FOSBaseMapper<ActivityDetailRequestModel, ActivityDetailRequest, SmeDetailResponse, SmeDetailModel> {

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
    public SmeDetailModel getResponse(SmeDetailResponse model) {

        SmeDetailModel smeModel = new SmeDetailModel();
        smeModel.setBiller(model.getResponse().getBiller());
        smeModel.setCcName(model.getResponse().getCcName());
        smeModel.setTotalConnection(model.getResponse().getTotalConnection());
        smeModel.setFBConnection(model.getResponse().getFBConnection());
        smeModel.setMico(model.getResponse().getMico());
        smeModel.setMicoCode(model.getResponse().getMicoCode());
        smeModel.setMiName(model.getResponse().getMiName());
        smeModel.setZone(model.getResponse().getZone());
        smeModel.setSegment(model.getResponse().getSegment());
        smeModel.setBegdate(model.getResponse().getBegdate());
        smeModel.setActReason(model.getResponse().getActReason());
        smeModel.setTmcode(model.getResponse().getTmcode());
        smeModel.setRatePlan(model.getResponse().getRatePlan());
        smeModel.setCr_limit(model.getResponse().getCr_limit());
        smeModel.setBill1(model.getResponse().getBill1());
        smeModel.setBill2(model.getResponse().getBill2());
        smeModel.setBill3(model.getResponse().getBill3());
        smeModel.setBill4(model.getResponse().getBill4());
        smeModel.setBill5(model.getResponse().getBill5());
        smeModel.setLandLine2(model.getResponse().getLandLine2());
        smeModel.setType(model.getResponse().getType());
        smeModel.setVisitStatus(model.getResponse().getVisitStatus());
        smeModel.setLocation(model.getResponse().getLocation());
        smeModel.setOther(model.getResponse().getOther());
        smeModel.setEsclate(model.getResponse().getEsclate());
        smeModel.setFeedback(model.getResponse().getFeedback());
        smeModel.setReason(model.getResponse().getReason());
        smeModel.setFromZsm(model.getResponse().getFromZsm());
        smeModel.setFromMico(model.getResponse().getFromMico());
        smeModel.setFromExecutive(model.getResponse().getFromExecutive());

        return smeModel;
    }
}
