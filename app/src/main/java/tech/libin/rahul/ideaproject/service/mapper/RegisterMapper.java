package tech.libin.rahul.ideaproject.service.mapper;

import tech.libin.rahul.ideaproject.configurations.Config;
import tech.libin.rahul.ideaproject.service.mapper.base.FOSBaseMapper;
import tech.libin.rahul.ideaproject.service.requests.RegisterRequest;
import tech.libin.rahul.ideaproject.service.requests.SmeFormSubmitRequest;
import tech.libin.rahul.ideaproject.views.detailsview.viewmodels.FormSubmitModel;
import tech.libin.rahul.ideaproject.views.models.RegisterModel;
import tech.libin.rahul.ideaproject.views.models.User;

/**
 * Created by libin on 01/03/17.
 */

public class RegisterMapper implements FOSBaseMapper<RegisterModel, RegisterRequest, String, String> {

    @Override
    public RegisterRequest getRequest(RegisterModel model) {

        RegisterRequest request = new RegisterRequest();
        request.setName(model.getName());
        request.setDateOfJoining(model.getDateOfJoining());
        request.setDob(model.getDob());
        request.setRole(model.getRole());
        request.setZip(model.getZip());
        request.setFatherName(model.getFatherName());
        request.setMiCode(model.getMiCode());
        request.setPassword(model.getPassword());

        request.setAddress1(model.getAddress1());
        request.setAddress2(model.getAddress2());
        request.setAddress3(model.getAddress3());
        request.setPassword(model.getPassword());
        request.setMobileNum(model.getMobileNum());

        return request;
    }

    @Override
    public String getResponse(String model) {

        return model;
    }
}
