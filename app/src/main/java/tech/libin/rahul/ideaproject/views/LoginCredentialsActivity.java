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
import tech.libin.rahul.ideaproject.views.credentialviews.fragments.ResetPasswordFragment;

public class LoginCredentialsActivity extends FOSBaseActivity {


    public static String VIEW_MODE = "VIEWMODE";
    public static String USER_ID = "USERID";
    private Constants.CredentialsMode viewMode;
    private Constants.OtpVerificationType otpType;
    private Long userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_credentials);

        parseBundle();
        handleView();
    }

    @Override
    protected void onResume() {
        super.onResume();

        configToolbar();
    }

    @Override
    protected void setToolbarElevation() {
        setToolbarElevation(0);
    }

    @Override
    protected void setHasToolBar() {
        setHasToolBar(true);
    }

    private void configToolbar() {
        if (viewMode == Constants.CredentialsMode.FORGOT_PASSWORD) {
            setToolbarUsername(getString(R.string.forgot_password_text));
        } else if (viewMode == Constants.CredentialsMode.RESET_PASSWORD) {
            setToolbarUsername(getString(R.string.reset_password));
        }
        showToolbarPhoneNo(false);
        showToolbarPhoto(false);
    }

    private void parseBundle() {
        Bundle bundle = getIntent().getExtras();
        viewMode = (Constants.CredentialsMode) bundle.getSerializable(VIEW_MODE);
        userId = bundle.getLong(USER_ID);
        try {
            otpType = (Constants.OtpVerificationType) bundle.getSerializable(Constants.KEY.OTP_TYPE);
        } catch (Exception ex) {
        }
    }

    private void handleView() {
        if (viewMode == Constants.CredentialsMode.FORGOT_PASSWORD) {
            showForgotView();
        } else if (viewMode == Constants.CredentialsMode.RESET_PASSWORD) {
            showResetView(userId);
        } else if (viewMode == Constants.CredentialsMode.OTP) {
            showOtpView(userId,otpType);
        }
    }

    private void showForgotView() {
        Fragment fragment = new ForgotPasswordFragment();
        addFragment(R.id.credential_container, fragment);
    }

    private void showResetView(Long userId) {
        Fragment fragment = new ResetPasswordFragment();
        Bundle bundle = new Bundle();
        bundle.putLong(OtpFragment.USER_ID, userId);
        fragment.setArguments(bundle);
        addFragment(R.id.credential_container, fragment);
    }

    private void showOtpView(Long userId, Constants.OtpVerificationType otpVerType) {
        Fragment fragment = new OtpFragment();
        Bundle bundle = new Bundle();
        bundle.putLong(OtpFragment.USER_ID, userId);
        bundle.putSerializable(Constants.KEY.OTP_TYPE, otpVerType);
        fragment.setArguments(bundle);
        addFragment(R.id.credential_container, fragment);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(OTPEvent event) {
        showOtpView(event.getUserId(),event.getOtpType());
    }
}
