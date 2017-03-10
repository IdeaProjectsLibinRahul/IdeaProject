package tech.libin.rahul.ideaproject.network.constants;

/**
 * Created by 10945 on 10/27/2016.
 */

public class ServiceURLs {
    public static final String REGISTER = "/user/register";
    public static final String LOGIN = "/Account/Login";
    public static final String ACTIVITY = "/AppData/Activity";
    public static final String COLLECTION_DETAIL = "/AppData/UPCDetailActivity";
    public static final String TD_DETAIL = "/AppData/UPCDetailActivity";
    public static final String UPC_DETAIL = "/AppData/UPCDetailActivity";
    public static final String SME_DETAIL = "/AppData/UPCDetailActivity";
    public static final String FORM_SUBMIT = "/AppData/UpdateUPC_TDSME";
    public static final String LOGOUT = "";

    private static final String BASE_URL = "http://122.166.96.206/isft/api";

    public static String build(String subURL) {
        return BASE_URL + subURL;
    }
}
