package tech.libin.rahul.ideaproject.configurations;

import com.google.gson.annotations.SerializedName;

/**
 * Created by rahul on 2/26/2017.
 */

public class Constants {
    public enum Role {
        @SerializedName("1")
        EXECUTIVE(1),
        @SerializedName("2")
        MICO(2),
        @SerializedName("3")
        ZSM(3);

        private int value;

        Role(int value) {
            this.value = value;
        }
    }

    public enum Type {
        @SerializedName("1")
        RETENSION(1),
        @SerializedName("2")
        COLLECTION(2),
        @SerializedName("3")
        BOTH(3);

        private int value;

        Type(int value) {
            this.value = value;
        }
    }

    public enum Status {
        @SerializedName("0")
        ERROR(0),
        @SerializedName("1")
        SUCCESS(1),
        @SerializedName("2")
        FIRST_TIME_LOGIN(2),
        @SerializedName("3")
        INVALID_USER(3),
        @SerializedName("4")
        SESSION_EXIST(4);

        private int value;

        Status(int value) {
            this.value = value;
        }
    }

    public enum ActivityType {

        @SerializedName("1")
        NEW_ACTIVITY(1),
        @SerializedName("2")
        ACTIVITY(2),
        @SerializedName("3")
        REMINDER(3);

        private int value;

        ActivityType(int value) {
            this.value = value;
        }
    }

    public enum RecordType {
        @SerializedName("1")
        SME(1),
        @SerializedName("2")
        UPC(2),
        @SerializedName("3")
        TD(3),
        @SerializedName("4")
        COLLECTION(4);

        private int value;

        private RecordType(int value) {
            this.value = value;
        }
    }

    public enum EsclateStatus {
        @SerializedName("0")
        NOT_ESCLATED(0),
        @SerializedName("1")
        ESCLATED(1);

        private int value;

        EsclateStatus(int value) {
            this.value = value;
        }
    }

    public enum CredentialsMode {
        FORGOT_PASSWORD,
        RESET_PASSWORD,
        OTP
    }

    public class PARAMS {
        public static final String DETAILS_OBJECT_ID = "detailsObjectId";
        public static final String DETAILS_OBJECT_NAME = "detailsObjectName";
        public static final String DETAILS_OBJECT_PHONE = "detailsObjectPhone";
        public static final String DETAILS_OBJECT_TAB = "detailsObjectTab";
    }

    public enum VisitStatus {
        @SerializedName("1")
        VISITED(1),
        @SerializedName("2")
        RETAINED(2),
        @SerializedName("3")
        NOT_RETAINED(3),
        @SerializedName("4")
        FOLLOW_UP(4);

        private int value;

        private VisitStatus(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

}
