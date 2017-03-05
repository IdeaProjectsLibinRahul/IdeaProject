package tech.libin.rahul.ideaproject.network.constants;

/**
 * Created by 10945 on 10/27/2016.
 */

public class ServiceURLs {
    public static final String REGISTER = "/user/register";
    public static final String LOGIN = "/postLoginJson";
    public static final String ACTIVITY = "/postLoginJson";

    private static final String BASE_URL = "http://122.166.96.206/isft/api/";

    public static String build(String subURL) {
        return BASE_URL + subURL;
    }
}
