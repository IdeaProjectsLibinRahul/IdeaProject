package tech.libin.rahul.ideaproject.service.mapper;

import tech.libin.rahul.ideaproject.configurations.Config;
import tech.libin.rahul.ideaproject.service.mapper.base.FOSBaseMapper;
import tech.libin.rahul.ideaproject.service.requests.ActivityDetailRequest;
import tech.libin.rahul.ideaproject.service.responses.CollectionDetailResponse;
import tech.libin.rahul.ideaproject.views.detailsview.viewmodels.CollectionDetailModel;
import tech.libin.rahul.ideaproject.views.models.ActivityDetailRequestModel;
import tech.libin.rahul.ideaproject.views.models.User;

/**
 * Created by libin on 01/03/17.
 */

public class CollectionDetailMapper implements FOSBaseMapper<ActivityDetailRequestModel, ActivityDetailRequest, CollectionDetailResponse, CollectionDetailModel> {

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
    public CollectionDetailModel getResponse(CollectionDetailResponse model) {

        CollectionDetailModel models = new CollectionDetailModel();

        models.setCustNum(model.getResponse().getCustNum());
        models.setMobile(model.getResponse().getMobile());
        models.setBiller(model.getResponse().getBiller());
        models.setSer(model.getResponse().getSer());
        models.setCustomerType(model.getResponse().getCustomerType());
        models.setSegment(model.getResponse().getSegment());

        models.setMyIdeaCode(model.getResponse().getMyIdeaCode());
        models.setCurBalance(model.getResponse().getCurBalance());
        models.setBucket(model.getResponse().getBucket());
        models.setMyIdeaAllocation(model.getResponse().getMyIdeaAllocation());

        models.setBill1(model.getResponse().getBill1());
        models.setBill2(model.getResponse().getBill2());
        models.setBill3(model.getResponse().getBill3());
        models.setBill4(model.getResponse().getBill4());
        models.setBill5(model.getResponse().getBill5());

        models.setLandLine1(model.getResponse().getLandLine1());
        models.setLandLine2(model.getResponse().getLandLine2());
        models.setCrmStatus(model.getResponse().getCrmStatus());
        models.setType(model.getResponse().getType());
        models.setActivatiomMi(model.getResponse().getActivatiomMi());
        models.setZip(model.getResponse().getZip());

        models.setLocation(model.getResponse().getLocation());
        models.setOther(model.getResponse().getOther());
        models.setEsclate(model.getResponse().getEsclate());
        models.setVisitStatus(model.getResponse().getVisitStatus());
        models.setFromExecutive(model.getResponse().getFromExecutive());
        models.setFromMico(model.getResponse().getFromMico());
        models.setFromZsm(model.getResponse().getFromZsm());

        return models;
    }
}
