package tech.libin.rahul.ideaproject.views;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import tech.libin.rahul.ideaproject.R;
import tech.libin.rahul.ideaproject.configurations.Constants;
import tech.libin.rahul.ideaproject.events.OTPEvent;
import tech.libin.rahul.ideaproject.views.basecomponents.FOSBaseActivity;
import tech.libin.rahul.ideaproject.views.credentialviews.fragments.ForgotPasswordFragment;
import tech.libin.rahul.ideaproject.views.credentialviews.fragments.OtpFragment;

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
            showForgotView();
        } else if (viewMode == Constants.CredentialsMode.RESET_PASSWORD) {

        } else if (viewMode == Constants.CredentialsMode.OTP) {

        }
    }

    private void showForgotView() {
        Fragment fragment = new ForgotPasswordFragment();
        addFragment(R.id.credential_container, fragment);
    }

    private void showOtpView() {
        Fragment fragment = new OtpFragment();
        addFragment(R.id.credential_container, fragment);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(OTPEvent event) {
        showOtpView();
    }
}
