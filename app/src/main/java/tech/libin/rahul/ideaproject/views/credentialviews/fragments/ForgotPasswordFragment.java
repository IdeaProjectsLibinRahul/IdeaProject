package tech.libin.rahul.ideaproject.views.credentialviews.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;

import tech.libin.rahul.ideaproject.R;
import tech.libin.rahul.ideaproject.configurations.Constants;
import tech.libin.rahul.ideaproject.events.OTPEvent;
import tech.libin.rahul.ideaproject.facade.FOSFacade;
import tech.libin.rahul.ideaproject.facade.FOSFacadeImpl;
import tech.libin.rahul.ideaproject.service.handlers.ServiceCallback;
import tech.libin.rahul.ideaproject.service.responses.base.FOSError;
import tech.libin.rahul.ideaproject.views.basecomponents.FOSBaseFragment;
import tech.libin.rahul.ideaproject.views.credentialviews.viewmodels.ForgotPasswordModel;
import tech.libin.rahul.ideaproject.views.detailsview.dialogs.InfoDialog;
import tech.libin.rahul.ideaproject.views.widgets.button.FOSButton;
import tech.libin.rahul.ideaproject.views.widgets.edittext.FOSIconEditText;

/**
 * Created by libin on 24/03/17.
 */

public class ForgotPasswordFragment extends FOSBaseFragment {

    public static final String SUCCESS_DIALOG = "SUCCESS_DIALOG";
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

                if (!isValid()) {

                    String miCode = editTextMiCode.getText().trim();
                    String mobileNum = editTextMoblieNum.getText().trim();

                    requestOtp(miCode, mobileNum);
                }
            }
        });
    }

    private void requestOtp(String miCode, String mobileNum) {
        final ProgressDialog dialog = ProgressDialog.show(getActivity(), null, getResources().getString(R.string.requesting), true, true);
        dialog.show();
        fosFacade.forgotPassword(miCode, mobileNum, new ServiceCallback<ForgotPasswordModel>() {
            @Override
            public void onResponse(ForgotPasswordModel response) {
                dialog.cancel();
                if (response.getStatus() == Constants.Status.SUCCESS) {
                    OTPEvent event = new OTPEvent();
                    event.setUserId(response.getResponse().getUserId());
                    EventBus.getDefault().post(event);
                } else {
                    showMessage("Error", response.getMessage(), Constants.MessageType.ERROR);
                }
            }

            @Override
            public void onRequestTimout() {
                dialog.cancel();
                String title = "Request Timed Out";
                showMessage(title, getResources().getString(R.string.warn_request_timed_out), Constants.MessageType.TIME_OUT);
            }

            @Override
            public void onRequestFail(FOSError error) {
                dialog.cancel();
                String title = "Request Fail";
                showMessage(title, error.getErrorMessage(), Constants.MessageType.ERROR);
            }
        });
    }

    //region showMessage
    private void showMessage(String title, String message, Constants.MessageType type) {
        InfoDialog infoDialog = InfoDialog.newInstance(title, message, type);
        infoDialog.show(getChildFragmentManager(), SUCCESS_DIALOG);
    }
    //endregion

    private boolean isValid() {
        editTextMoblieNum.setError(null);
        if (editTextMoblieNum.getText().trim().isEmpty()) {
            editTextMoblieNum.setError(getResources().getString(R.string.warn_mobile));
            return true;
        }
        return false;
    }
}
