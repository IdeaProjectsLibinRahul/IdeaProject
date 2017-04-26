package tech.libin.rahul.ideaproject.views.credentialviews.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Locale;

import tech.libin.rahul.ideaproject.R;
import tech.libin.rahul.ideaproject.configurations.Constants;
import tech.libin.rahul.ideaproject.facade.FOSFacade;
import tech.libin.rahul.ideaproject.facade.FOSFacadeImpl;
import tech.libin.rahul.ideaproject.service.handlers.ServiceCallback;
import tech.libin.rahul.ideaproject.service.responses.OTPResponse;
import tech.libin.rahul.ideaproject.service.responses.base.FOSError;
import tech.libin.rahul.ideaproject.views.LoginActivity;
import tech.libin.rahul.ideaproject.views.LoginCredentialsActivity;
import tech.libin.rahul.ideaproject.views.basecomponents.FOSBaseFragment;
import tech.libin.rahul.ideaproject.views.detailsview.dialogs.InfoDialog;
import tech.libin.rahul.ideaproject.views.widgets.button.FOSButton;
import tech.libin.rahul.ideaproject.views.widgets.dialogs.FOSDialog;
import tech.libin.rahul.ideaproject.views.widgets.edittext.FOSEditText;
import tech.libin.rahul.ideaproject.views.widgets.textview.FOSTextView;

/**
 * Created by libin on 24/03/17.
 */

public class OtpFragment extends FOSBaseFragment {

    //region declaration
    public static final String USER_ID = "userId";
    public static final String OTP_FRAGMENT = "OTP_FRAGMENT";
    private View view;
    private FOSEditText editTextOtp;
    private FOSButton buttonVerify;
    private FOSTextView textViewTime;
    private FOSTextView textViewResendOtp;
    private Long userId;
    private FOSFacade fosFacade;
    private Constants.OtpVerificationType otpType;
    private FOSDialog fosDialog;
    //endregion

    //region onCreateView
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_otp, container, false);

        parseBundle();
        initComponents();
        startTimer();
        setListeners();

        return view;
    }
    //endregion

    //region parseBundle
    private void parseBundle() {
        Bundle bundle = getArguments();
        otpType = Constants.OtpVerificationType.FORGOT_PASSWORD;
        if (bundle != null) {
            userId = bundle.getLong(USER_ID);
            otpType = (Constants.OtpVerificationType) bundle.getSerializable(Constants.KEY.OTP_TYPE);
        }
    }
    //endregion

    //region initComponents
    private void initComponents() {
        fosFacade = new FOSFacadeImpl();
        editTextOtp = (FOSEditText) view.findViewById(R.id.editTextOtp);
        textViewTime = (FOSTextView) view.findViewById(R.id.textViewTimer);
        textViewResendOtp = (FOSTextView) view.findViewById(R.id.textViewResendOtp);
        buttonVerify = (FOSButton) view.findViewById(R.id.buttonOtpVerify);
    }
    //endregion

    //region startTimer
    private void startTimer() {
        new CountDownTimer(120000, 1000) {

            public void onTick(long millisUntilFinished) {
                textViewTime.setText(String.format(Locale.UK, "%02d s", millisUntilFinished / 1000));
            }

            public void onFinish() {
                editTextOtp.setEnabled(false);
                textViewTime.setText("00 s");
                textViewResendOtp.setVisibility(View.VISIBLE);
            }

        }.start();
    }
    //endregion

    //region setListeners
    private void setListeners() {
        buttonVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String otp = editTextOtp.getText().toString();
                verifyOTP(otp);
            }
        });

        textViewResendOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editTextOtp.setEnabled(true);
                textViewResendOtp.setVisibility(View.INVISIBLE);
                startTimer();
            }
        });
    }
    //endregion

    //region verifyOTP
    private void verifyOTP(String otp) {
        final ProgressDialog dialog = ProgressDialog.show(getActivity(), null, getResources().getString(R.string.requesting), true, true);
        dialog.show();
        fosFacade.submitOTP(userId, otpType, otp, new ServiceCallback<OTPResponse>() {
            @Override
            public void onResponse(OTPResponse response) {
                dialog.dismiss();
                if (otpType != Constants.OtpVerificationType.FORGOT_PASSWORD) {
                    String title = "OTP Verified";
                    fosDialog = FOSDialog.newInstance(getActivity(), title, response.getMessage(), true);
                    fosDialog.show(getChildFragmentManager(), "tag");
                    //getActivity().finish();
                } else {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(LoginCredentialsActivity.VIEW_MODE, Constants.CredentialsMode.RESET_PASSWORD);
                    bundle.putLong(LoginCredentialsActivity.USER_ID, userId);
                    Intent intent = new Intent(getContext(), LoginCredentialsActivity.class);
                    intent.putExtras(bundle);

                    startActivity(intent);
                }
            }

            @Override
            public void onRequestTimout() {
                dialog.dismiss();
                String title = "Request Timed Out";
                showMessage(title, getResources().getString(R.string.warn_request_timed_out), Constants.MessageType.TIME_OUT);
            }

            @Override
            public void onRequestFail(FOSError error) {
                dialog.dismiss();
                String title = "Request Fail";
                showMessage(title, error.getErrorMessage(), Constants.MessageType.ERROR);
            }
        });
    }
    //endregion

    //region showMessage
    private void showMessage(String title, String message, Constants.MessageType type) {
        InfoDialog infoDialog = InfoDialog.newInstance(title, message, type);
        infoDialog.show(getChildFragmentManager(), OTP_FRAGMENT);
    }
    //endregion
}
