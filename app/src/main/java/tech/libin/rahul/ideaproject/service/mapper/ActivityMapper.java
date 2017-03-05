package tech.libin.rahul.ideaproject.service.mapper;

import java.util.ArrayList;
import java.util.List;

import tech.libin.rahul.ideaproject.configurations.Config;
import tech.libin.rahul.ideaproject.service.mapper.base.FOSBaseMapper;
import tech.libin.rahul.ideaproject.service.requests.ActivityRequest;
import tech.libin.rahul.ideaproject.service.responses.ActivityResponse;
import tech.libin.rahul.ideaproject.views.homescreen.viewmodels.ActivityModel;
import tech.libin.rahul.ideaproject.views.models.ActivityRequestModel;
import tech.libin.rahul.ideaproject.views.models.User;

/**
 * Created by libin on 01/03/17.
 */

public class ActivityMapper implements FOSBaseMapper<ActivityRequestModel, ActivityRequest, ActivityResponse, List<ActivityModel>> {


    @Override
    public ActivityRequest getRequest(ActivityRequestModel model) {
        User user = Config.getInstance().getUser();
        String sessionKey = user.getSessionKey();

        ActivityRequest request = new ActivityRequest();
        request.setUserId(model.getUserId());
        request.setSessionKey(sessionKey);
        request.setPageNo(model.getPageNo());
        request.setPageSize(model.getPageSize());
        request.setActivityType(model.getActivityType());
        request.setType(model.getType());
        request.setMsisdn(model.getMsisdn());
        request.setZip(model.getZip());
        request.setName(model.getName());
        return request;
    }

    @Override
    public List<ActivityModel> getResponse(ActivityResponse model) {
        List<ActivityModel> models = new ArrayList<>();
        List<ActivityResponse.Response> responses = model.getResponse();

        for (ActivityResponse.Response response : responses) {
            ActivityModel activityModel = new ActivityModel();
            activityModel.setId(response.getObjectId());
            activityModel.setName(response.getName());
            activityModel.setTopRight(response.getTopRight());
            activityModel.setBottomCenter(response.getCenterBottom());
            activityModel.setBottomRight(response.getBottomRight());
            activityModel.setPhoneNo(response.getMobileNo());

            models.add(activityModel);
        }
        return models;
    }
}
