package tech.libin.rahul.ideaproject.service.responses;

import java.util.ArrayList;

import tech.libin.rahul.ideaproject.configurations.Constants;
import tech.libin.rahul.ideaproject.service.models.DetailFromSMERoleModel;
import tech.libin.rahul.ideaproject.service.models.DetailOtherData;
import tech.libin.rahul.ideaproject.service.models.LocationModel;
import tech.libin.rahul.ideaproject.service.models.SpinnerData;
import tech.libin.rahul.ideaproject.service.responses.base.FOSBaseResponse;

/**
 * Created by rahul on 3/5/2017.
 */

public class SmeDetailResponse extends FOSBaseResponse {
    Response response;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public class Response {

        String biller;
        String ccName;
        String totalConnection;
        String FBConnection;
        String mico;
        String micoCode;
        String miName;
        String zone;
        String segment;
        String begdate;
        String actReason;
        String tmcode;
        String ratePlan;
        String cr_limit;
        String bill1;
        String bill2;
        String bill3;
        String bill4;
        String bill5;
        String zip;
        String landLine2;
        String type;

        private LocationModel location;
        private ArrayList<DetailOtherData> other;
        private Constants.EsclateStatus esclate;
        private ArrayList<SpinnerData> visitStatus;
        private ArrayList<SpinnerData> feedback;
        private ArrayList<SpinnerData> reason;

        private DetailFromSMERoleModel fromZsm;
        private DetailFromSMERoleModel fromMico;
        private DetailFromSMERoleModel fromExecutive;


        public String getBiller() {
            return biller;
        }

        public void setBiller(String biller) {
            this.biller = biller;
        }

        public String getCcName() {
            return ccName;
        }

        public void setCcName(String ccName) {
            this.ccName = ccName;
        }

        public String getTotalConnection() {
            return totalConnection;
        }

        public void setTotalConnection(String totalConnection) {
            this.totalConnection = totalConnection;
        }

        public String getFBConnection() {
            return FBConnection;
        }

        public void setFBConnection(String FBConnection) {
            this.FBConnection = FBConnection;
        }

        public String getMico() {
            return mico;
        }

        public void setMico(String mico) {
            this.mico = mico;
        }

        public String getMicoCode() {
            return micoCode;
        }

        public void setMicoCode(String micoCode) {
            this.micoCode = micoCode;
        }

        public String getMiName() {
            return miName;
        }

        public void setMiName(String miName) {
            this.miName = miName;
        }

        public String getZone() {
            return zone;
        }

        public void setZone(String zone) {
            this.zone = zone;
        }

        public String getSegment() {
            return segment;
        }

        public void setSegment(String segment) {
            this.segment = segment;
        }

        public String getBegdate() {
            return begdate;
        }

        public void setBegdate(String begdate) {
            this.begdate = begdate;
        }

        public String getActReason() {
            return actReason;
        }

        public void setActReason(String actReason) {
            this.actReason = actReason;
        }

        public String getTmcode() {
            return tmcode;
        }

        public void setTmcode(String tmcode) {
            this.tmcode = tmcode;
        }

        public String getRatePlan() {
            return ratePlan;
        }

        public void setRatePlan(String ratePlan) {
            this.ratePlan = ratePlan;
        }

        public String getCr_limit() {
            return cr_limit;
        }

        public void setCr_limit(String cr_limit) {
            this.cr_limit = cr_limit;
        }

        public String getBill1() {
            return bill1;
        }

        public void setBill1(String bill1) {
            this.bill1 = bill1;
        }

        public String getBill2() {
            return bill2;
        }

        public void setBill2(String bill2) {
            this.bill2 = bill2;
        }

        public String getBill3() {
            return bill3;
        }

        public void setBill3(String bill3) {
            this.bill3 = bill3;
        }


        public String getBill4() {
            return bill4;
        }

        public void setBill4(String bill4) {
            this.bill4 = bill4;
        }

        public String getBill5() {
            return bill5;
        }

        public void setBill5(String bill5) {
            this.bill5 = bill5;
        }

        public String getZip() {
            return zip;
        }

        public void setZip(String zip) {
            this.zip = zip;
        }

        public String getLandLine2() {
            return landLine2;
        }

        public void setLandLine2(String landLine2) {
            this.landLine2 = landLine2;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public LocationModel getLocation() {
            return location;
        }

        public void setLocation(LocationModel location) {
            this.location = location;
        }

        public ArrayList<DetailOtherData> getOther() {
            return other;
        }

        public void setOther(ArrayList<DetailOtherData> other) {
            this.other = other;
        }

        public Constants.EsclateStatus getEsclate() {
            return esclate;
        }

        public void setEsclate(Constants.EsclateStatus esclate) {
            this.esclate = esclate;
        }

        public ArrayList<SpinnerData> getVisitStatus() {
            return visitStatus;
        }

        public void setVisitStatus(ArrayList<SpinnerData> visitStatus) {
            this.visitStatus = visitStatus;
        }

        public ArrayList<SpinnerData> getFeedback() {
            return feedback;
        }

        public void setFeedback(ArrayList<SpinnerData> feedback) {
            this.feedback = feedback;
        }

        public ArrayList<SpinnerData> getReason() {
            return reason;
        }

        public void setReason(ArrayList<SpinnerData> reason) {
            this.reason = reason;
        }

        public DetailFromSMERoleModel getFromZsm() {
            return fromZsm;
        }

        public void setFromZsm(DetailFromSMERoleModel fromZsm) {
            this.fromZsm = fromZsm;
        }

        public DetailFromSMERoleModel getFromMico() {
            return fromMico;
        }

        public void setFromMico(DetailFromSMERoleModel fromMico) {
            this.fromMico = fromMico;
        }

        public DetailFromSMERoleModel getFromExecutive() {
            return fromExecutive;
        }

        public void setFromExecutive(DetailFromSMERoleModel fromExecutive) {
            this.fromExecutive = fromExecutive;
        }
    }
}
