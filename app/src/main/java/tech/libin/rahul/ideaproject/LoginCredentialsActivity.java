package tech.libin.rahul.ideaproject;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import tech.libin.rahul.ideaproject.configurations.Constants;
import tech.libin.rahul.ideaproject.views.basecomponents.FOSBaseActivity;
import tech.libin.rahul.ideaproject.views.credentialviews.fragments.ForgotPasswordFragment;

public class LoginCredentialsActivity extends FOSBaseActivity {


    public static String VIEW_MODE = "VIEWMODE";
    private Constants.CredentialsMode viewMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_credentials);

        parseBundle();
        handleView();
    }

    @Override
    protected void setToolbarElevation() {
        setToolbarElevation(0);
    }

    @Override
    protected void setHasToolBar() {
        setHasToolBar(false);
    }

    private void parseBundle() {
        Bundle bundle = getIntent().getExtras();
        viewMode = (Constants.CredentialsMode) bundle.getSerializable(VIEW_MODE);
    }

    private void handleView() {
        if (viewMode == Constants.CredentialsMode.FORGOT_PASSWORD) {
            Fragment fragment = new ForgotPasswordFragment();
            addFragment(R.id.credential_container, fragment);
        } else if (viewMode == Constants.CredentialsMode.RESET_PASSWORD) {

        } else if (viewMode == Constants.CredentialsMode.OTP) {

        }
    }
}
