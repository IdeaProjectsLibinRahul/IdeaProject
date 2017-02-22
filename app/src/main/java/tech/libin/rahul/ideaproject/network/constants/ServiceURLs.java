package tech.libin.rahul.ideaproject.network.constants;

/**
 * Created by 10945 on 10/27/2016.
 */

public class ServiceURLs {
    public static final String REGISTER = "/user/register";
    public static final String UPDATE_USER_PROFILE = "/user/updateprofile";
    public static final String UPDATE_PROFILE_PIC = "/saveProfilePic";

    private static final String BASE_URL = "http://192.168.37.53:8080/carpool";

    public static String build(String subURL) {
        return BASE_URL + subURL;
    }
}