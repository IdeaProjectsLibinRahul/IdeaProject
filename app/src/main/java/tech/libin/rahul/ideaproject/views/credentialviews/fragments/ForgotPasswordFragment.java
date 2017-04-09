package tech.libin.rahul.ideaproject.views.credentialviews.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;

import tech.libin.rahul.ideaproject.R;
import tech.libin.rahul.ideaproject.events.OTPEvent;
import tech.libin.rahul.ideaproject.facade.FOSFacade;
import tech.libin.rahul.ideaproject.facade.FOSFacadeImpl;
import tech.libin.rahul.ideaproject.service.handlers.ServiceCallback;
import tech.libin.rahul.ideaproject.service.responses.base.FOSError;
import tech.libin.rahul.ideaproject.views.basecomponents.FOSBaseFragment;
import tech.libin.rahul.ideaproject.views.credentialviews.viewmodels.ForgotPasswordModel;
import tech.libin.rahul.ideaproject.views.widgets.button.FOSButton;
import tech.libin.rahul.ideaproject.views.widgets.edittext.FOSIconEditText;

/**
 * Created by libin on 24/03/17.
 */

public class ForgotPasswordFragment extends FOSBaseFragment {

    private View view;
    private FOSIconEditText editTextMiCode;
    private FOSIconEditText editTextMoblieNum;
    private FOSButton buttonVerify;
    private FOSFacade fosFacade;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_forgot_password, container, false);

        initComponents();
        setListeners();

        return view;
    }

    private void initComponents() {
        fosFacade = new FOSFacadeImpl();

        editTextMiCode = (FOSIconEditText) view.findViewById(R.id.editTextForgotMICode);
        editTextMoblieNum = (FOSIconEditText) view.findViewById(R.id.editTextForgotMobileNo);
        buttonVerify = (FOSButton) view.findViewById(R.id.buttonForgotVerify);
    }

    private void setListeners() {
        buttonVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(new OTPEvent());

                String miCode = editTextMiCode.getText();
                String mobileNum = editTextMoblieNum.getText();

                fosFacade.forgotPassword(miCode, mobileNum, new ServiceCallback<ForgotPasswordModel>() {
                    @Override
                    public void onResponse(ForgotPasswordModel response) {

                    }

                    @Override
                    public void onRequestTimout() {

                    }

                    @Override
                    public void onRequestFail(FOSError error) {

                    }
                });
            }
        });
    }
}
