package tech.libin.rahul.ideaproject.views.credentialviews.fragments;

import android.app.ProgressDialog;
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
import tech.libin.rahul.ideaproject.views.basecomponents.FOSBaseFragment;
import tech.libin.rahul.ideaproject.views.detailsview.dialogs.InfoDialog;
import tech.libin.rahul.ideaproject.views.widgets.button.FOSButton;
import tech.libin.rahul.ideaproject.views.widgets.edittext.FOSEditText;
import tech.libin.rahul.ideaproject.views.widgets.textview.FOSTextView;

/**
 * Created by libin on 24/03/17.
 */

public class OtpFragment extends FOSBaseFragment {

    public static final String USER_ID = "userId";
    public static final String OTP_FRAGMENT = "OTP_FRAGMENT";
    private View view;
    private FOSEditText editTextOtp;
    private FOSButton buttonVerify;
    private FOSTextView textViewTime;
    private FOSTextView textViewResendOtp;
    private Long userId;
    private FOSFacade fosFacade;

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

    private void parseBundle() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            userId = bundle.getLong(USER_ID);
        }
    }

    private void initComponents() {
        fosFacade = new FOSFacadeImpl();
        editTextOtp = (FOSEditText) view.findViewById(R.id.editTextOtp);
        textViewTime = (FOSTextView) view.findViewById(R.id.textViewTimer);
        textViewResendOtp = (FOSTextView) view.findViewById(R.id.textViewResendOtp);
        buttonVerify = (FOSButton) view.findViewById(R.id.buttonOtpVerify);
    }

    private void startTimer() {
        new CountDownTimer(30000, 1000) {

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

    private void verifyOTP(String otp) {
        final ProgressDialog dialog = ProgressDialog.show(getActivity(), null, getResources().getString(R.string.requesting), true, true);
        dialog.show();
        fosFacade.submitOTP(userId, otp, new ServiceCallback<OTPResponse>() {
            @Override
            public void onResponse(OTPResponse response) {
                dialog.dismiss();
                getActivity().finish();
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

    private void showMessage(String title, String message, Constants.MessageType type) {
        InfoDialog infoDialog = InfoDialog.newInstance(title, message, type);
        infoDialog.show(getChildFragmentManager(), OTP_FRAGMENT);
    }
}
