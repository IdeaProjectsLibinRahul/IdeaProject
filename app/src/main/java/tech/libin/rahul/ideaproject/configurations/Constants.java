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

        private Role(int value) {
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

        private Type(int value) {
            this.value = value;
        }
    }

    public enum Status {
        @SerializedName("0")
        ERROR(0),
        @SerializedName("1")
        SUCCESS(1),
        @SerializedName("2")
        INVALID_USER(2),
        @SerializedName("3")
        SESSION_EXIST(3);

        private int value;

        private Status(int value) {
            this.value = value;
        }
    }

    public enum ActivityType {
        @SerializedName("0")
        ACTIVITY(0),
        @SerializedName("1")
        NEW_ACTIVITY(1),
        @SerializedName("2")
        REMINDER(2);

        private int value;

        private ActivityType(int value) {
            this.value = value;
        }
    }

    public enum EsclateStatus {
        @SerializedName("0")
        NOT_ESCLATED(0),
        @SerializedName("1")
        ESCLATED(1);

        private int value;

        private EsclateStatus(int value) {
            this.value = value;
        }
    }
}
