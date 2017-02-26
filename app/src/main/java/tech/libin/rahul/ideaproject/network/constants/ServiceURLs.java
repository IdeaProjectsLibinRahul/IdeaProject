package tech.libin.rahul.ideaproject.network.constants;

/**
 * Created by 10945 on 10/27/2016.
 */

public class ServiceURLs {
    public static final String REGISTER = "/user/register";
    public static final String LOGIN = "/postLoginJson";
    public static final String UPDATE_USER_PROFILE = "/user/updateprofile";
    public static final String UPDATE_PROFILE_PIC = "/saveProfilePic";

    private static final String BASE_URL = "http://192.9.200.168/UrlCallerTester/Service1.svc";

    public static String build(String subURL) {
        return BASE_URL + subURL;
    }
}
