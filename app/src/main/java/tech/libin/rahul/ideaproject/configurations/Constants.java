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

        public int getValue() {
            return value;
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
        SESSION_EXIST(4),

        @SerializedName("7")
        INVALID_OTP(7),

        @SerializedName("8")
        OTP_TIME_OUT(8);

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
        @SerializedName("0")
        ALL(0),
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

    public enum MessageType {
        @SerializedName("0")
        ERROR(0),
        @SerializedName("1")
        SUCCESS(1),
        @SerializedName("2")
        TIME_OUT(2);

        private int value;

        MessageType(int value) {
            this.value = value;
        }
    }

    public enum OtpVerificationType {
        @SerializedName("1")
        FORGOT_PASSWORD(1),
        @SerializedName("2")
        FIRST_TIME_LOGIN(2);

        private int value;

        OtpVerificationType(int value) {
            this.value = value;
        }
    }

    public class PARAMS {
        public static final String DETAILS_OBJECT_ID = "detailsObjectId";
        public static final String DETAILS_OBJECT_NAME = "detailsObjectName";
        public static final String DETAILS_OBJECT_PHONE = "detailsObjectPhone";
        public static final String DETAILS_OBJECT_TAB = "detailsObjectTab";
    }

    public class KEY {
        public static final String OTP_TYPE = "otpType";
    }


}
