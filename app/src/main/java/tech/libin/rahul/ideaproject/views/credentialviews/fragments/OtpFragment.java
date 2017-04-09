package tech.libin.rahul.ideaproject.views.credentialviews.fragments;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Locale;

import tech.libin.rahul.ideaproject.R;
import tech.libin.rahul.ideaproject.views.basecomponents.FOSBaseFragment;
import tech.libin.rahul.ideaproject.views.widgets.button.FOSButton;
import tech.libin.rahul.ideaproject.views.widgets.edittext.FOSEditText;
import tech.libin.rahul.ideaproject.views.widgets.textview.FOSTextView;

/**
 * Created by libin on 24/03/17.
 */

public class OtpFragment extends FOSBaseFragment {

    private View view;
    private FOSEditText editTextOtp;
    private FOSButton buttonVerify;
    private FOSTextView textViewTime;
    private FOSTextView textViewResendOtp;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_otp, container, false);

        initComponents();
        startTimer();
        setListeners();

        return view;
    }

    private void initComponents() {
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
}
