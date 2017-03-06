package tech.libin.rahul.ideaproject.service.responses;

import java.util.List;

import tech.libin.rahul.ideaproject.configurations.Constants;
import tech.libin.rahul.ideaproject.service.responses.base.FOSBaseResponse;

/**
 * Created by libin on 01/03/17.
 */

public class ActivityResponse extends FOSBaseResponse {

    private List<Response> response;

    public List<Response> getResponse() {
        return response;
    }

    public void setResponse(List<Response> response) {
        this.response = response;
    }

    public class Response {
        private Constants.RecordType type;

        private String objectId;

        private String name;

        private String mobileNo;

        private String centerBottom;

        private String topRight;

        private String bottomRight;

        private Constants.EsclateStatus esclateStatus;

        public Constants.RecordType getType() {
            return type;
        }

        public void setType(Constants.RecordType type) {
            this.type = type;
        }

        public String getObjectId() {
            return objectId;
        }

        public void setObjectId(String objectId) {
            this.objectId = objectId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getMobileNo() {
            return mobileNo;
        }

        public void setMobileNo(String mobileNo) {
            this.mobileNo = mobileNo;
        }

        public String getCenterBottom() {
            return centerBottom;
        }

        public void setCenterBottom(String centerBottom) {
            this.centerBottom = centerBottom;
        }

        public String getTopRight() {
            return topRight;
        }

        public void setTopRight(String topRight) {
            this.topRight = topRight;
        }

        public String getBottomRight() {
            return bottomRight;
        }

        public void setBottomRight(String bottomRight) {
            this.bottomRight = bottomRight;
        }

        public Constants.EsclateStatus getEsclateStatus() {
            return esclateStatus;
        }

        public void setEsclateStatus(Constants.EsclateStatus esclateStatus) {
            this.esclateStatus = esclateStatus;
        }
    }

}
