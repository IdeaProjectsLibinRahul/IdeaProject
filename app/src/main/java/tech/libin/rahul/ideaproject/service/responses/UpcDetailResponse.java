package tech.libin.rahul.ideaproject.service.responses;

import java.util.ArrayList;

import tech.libin.rahul.ideaproject.configurations.Constants;
import tech.libin.rahul.ideaproject.service.models.DetailFromUPCRoleModel;
import tech.libin.rahul.ideaproject.service.models.DetailOtherData;
import tech.libin.rahul.ideaproject.service.models.LocationModel;
import tech.libin.rahul.ideaproject.service.models.SpinnerData;
import tech.libin.rahul.ideaproject.service.responses.base.FOSBaseResponse;

/**
 * Created by rahul on 3/5/2017.
 */

public class UpcDetailResponse extends FOSBaseResponse {
    Response response;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public class Response {
        private String upc;
        private String msisdn;
        private String subscriberType;
        private String createdDateTime;
        private String segment;
        private String customerSubsType;
        private String csCreditCode;
        private String customerType;
        private String customerName;
        private String address1;
        private String address2;
        private String address3;
        private String zip;
        private String alternateNumber;
        private String servSeg;

        private LocationModel location;
        private DetailOtherData other;
        private Constants.EsclateStatus esclate;
        private DetailFromUPCRoleModel fromZsm;
        private DetailFromUPCRoleModel fromMico;
        private DetailFromUPCRoleModel fromExecutive;
        private ArrayList<SpinnerData> visitStatus;

        public String getUpc() {
            return upc;
        }

        public void setUpc(String upc) {
            this.upc = upc;
        }

        public String getMsisdn() {
            return msisdn;
        }

        public void setMsisdn(String msisdn) {
            this.msisdn = msisdn;
        }

        public String getSubscriberType() {
            return subscriberType;
        }

        public void setSubscriberType(String subscriberType) {
            this.subscriberType = subscriberType;
        }

        public String getCreatedDateTime() {
            return createdDateTime;
        }

        public void setCreatedDateTime(String createdDateTime) {
            this.createdDateTime = createdDateTime;
        }

        public String getSegment() {
            return segment;
        }

        public void setSegment(String segment) {
            this.segment = segment;
        }

        public String getCustomerSubsType() {
            return customerSubsType;
        }

        public void setCustomerSubsType(String customerSubsType) {
            this.customerSubsType = customerSubsType;
        }

        public String getCsCreditCode() {
            return csCreditCode;
        }

        public void setCsCreditCode(String csCreditCode) {
            this.csCreditCode = csCreditCode;
        }

        public String getCustomerType() {
            return customerType;
        }

        public void setCustomerType(String customerType) {
            this.customerType = customerType;
        }

        public String getCustomerName() {
            return customerName;
        }

        public void setCustomerName(String customerName) {
            this.customerName = customerName;
        }

        public String getAddress1() {
            return address1;
        }

        public void setAddress1(String address1) {
            this.address1 = address1;
        }

        public String getAddress2() {
            return address2;
        }

        public void setAddress2(String address2) {
            this.address2 = address2;
        }

        public String getAddress3() {
            return address3;
        }

        public void setAddress3(String address3) {
            this.address3 = address3;
        }

        public String getZip() {
            return zip;
        }

        public void setZip(String zip) {
            this.zip = zip;
        }

        public String getAlternateNumber() {
            return alternateNumber;
        }

        public void setAlternateNumber(String alternateNumber) {
            this.alternateNumber = alternateNumber;
        }

        public String getServSeg() {
            return servSeg;
        }

        public void setServSeg(String servSeg) {
            this.servSeg = servSeg;
        }

        public LocationModel getLocation() {
            return location;
        }

        public void setLocation(LocationModel location) {
            this.location = location;
        }

        public DetailOtherData getOther() {
            return other;
        }

        public void setOther(DetailOtherData other) {
            this.other = other;
        }

        public Constants.EsclateStatus getEsclate() {
            return esclate;
        }

        public void setEsclate(Constants.EsclateStatus esclate) {
            this.esclate = esclate;
        }

        public DetailFromUPCRoleModel getFromZsm() {
            return fromZsm;
        }

        public void setFromZsm(DetailFromUPCRoleModel fromZsm) {
            this.fromZsm = fromZsm;
        }

        public DetailFromUPCRoleModel getFromMico() {
            return fromMico;
        }

        public void setFromMico(DetailFromUPCRoleModel fromMico) {
            this.fromMico = fromMico;
        }

        public DetailFromUPCRoleModel getFromExecutive() {
            return fromExecutive;
        }

        public void setFromExecutive(DetailFromUPCRoleModel fromExecutive) {
            this.fromExecutive = fromExecutive;
        }

        public ArrayList<SpinnerData> getVisitStatus() {
            return visitStatus;
        }

        public void setVisitStatus(ArrayList<SpinnerData> visitStatus) {
            this.visitStatus = visitStatus;
        }
    }
}
