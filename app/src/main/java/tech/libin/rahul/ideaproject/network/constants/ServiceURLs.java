package tech.libin.rahul.ideaproject.network.constants;

/**
 * Created by 10945 on 10/27/2016.
 */

public class ServiceURLs {
//    public static final String REGISTER = "/Account/RegisterDummy";
    public static final String REGISTER = "/Account/Register";
    public static final String LOGIN = "/Account/Login";
    public static final String ACTIVITY = "/AppData/Activity";
    public static final String COLLECTION_DETAIL = "/AppData/UPCDetailActivity";
    public static final String TD_DETAIL = "/AppData/UPCDetailActivity";
    public static final String UPC_DETAIL = "/AppData/UPCDetailActivity";
    public static final String SME_DETAIL = "/AppData/UPCDetailActivity";
    public static final String FORM_SUBMIT = "/AppData/UpdateUPC_TDSME";
    public static final String LOGOUT = "/Account/Logout";
    public static final String FORGOT_PASSWORD = "/Account/ForgotPassword";
    public static final String RESET_PASSWORD = "/Account/ResetPassword";

    //   private static final String BASE_URL = "https://ibs.ideacellular.com/IFOST/api";//live
    private static final String BASE_URL = "http://122.166.96.206/isft/api";//local testing
//    private static final String BASE_URL = "http://192.9.200.183/IBS.Mvc4/api";//local testing

    public static String build(String subURL) {
        return BASE_URL + subURL;
    }
}
