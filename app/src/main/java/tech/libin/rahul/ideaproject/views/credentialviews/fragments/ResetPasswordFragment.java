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
import tech.libin.rahul.ideaproject.views.RegisterActivity;
import tech.libin.rahul.ideaproject.views.basecomponents.FOSBaseFragment;
import tech.libin.rahul.ideaproject.views.credentialviews.viewmodels.ForgotPasswordModel;
import tech.libin.rahul.ideaproject.views.detailsview.dialogs.InfoDialog;
import tech.libin.rahul.ideaproject.views.widgets.button.FOSButton;
import tech.libin.rahul.ideaproject.views.widgets.dialogs.FOSDialog;
import tech.libin.rahul.ideaproject.views.widgets.edittext.FOSIconEditText;

/**
 * Created by libin on 24/03/17.
 */

public class ResetPasswordFragment extends FOSBaseFragment {

    private View view;
    private FOSIconEditText editTextPassword;
    private FOSIconEditText editTextConfirmPassword;
    private FOSButton buttonVerify;
    private FOSFacade fosFacade;
    private FOSDialog fosDialog;
    public static final String SUCCESS_DIALOG = "SUCCESS_DIALOG";
    private Long userId;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_reset_password, container, false);

        parseBundle();
        initComponents();
        setListeners();

        return view;
    }


    private void initComponents() {
        fosFacade = new FOSFacadeImpl();

        editTextPassword = (FOSIconEditText) view.findViewById(R.id.editTextPassword);
        editTextConfirmPassword = (FOSIconEditText) view.findViewById(R.id.editTextForgotMobileNo);
        buttonVerify = (FOSButton) view.findViewById(R.id.buttonForgotVerify);
    }
    private void parseBundle() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            userId = bundle.getLong(OtpFragment.USER_ID);
        }
    }
    private void setListeners() {
        buttonVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isValid()) {

                    String password = editTextPassword.getText().trim();

                    final ProgressDialog dialog = ProgressDialog.show(getActivity(), null, getResources().getString(R.string.requesting), true, true);
                    fosFacade.resetPassword(userId,password, new ServiceCallback<String>() {
                        @Override
                        public void onResponse(String response) {
                            if (dialog != null) {
                                dialog.cancel();
                            }

                            String title = "Success";
                            fosDialog = FOSDialog.newInstance(getActivity(), title, response, true);
                            fosDialog.show(getChildFragmentManager(), "tag");
                        }

                        @Override
                        public void onRequestTimout() {
                            if (dialog != null) {
                                dialog.cancel();
                            }
                            String title = "Info";
                            fosDialog = FOSDialog.newInstance(getActivity(), title, getResources().getString(R.string.warn_request_timed_out), false);
                            fosDialog.show(getChildFragmentManager(), "tag");
                        }

                        @Override
                        public void onRequestFail(FOSError error) {
                            if (dialog != null) {
                                dialog.cancel();
                            }
                            String message = error.getErrorMessage();
                            String title = "Info";
                            fosDialog = FOSDialog.newInstance(getActivity(), title, message, false);
                            fosDialog.show(getChildFragmentManager(), "tag");
                        }
                    });

                }
            }
        });
    }

    //region showMessage
    private void showMessage(String title, String message, Constants.MessageType type) {
        InfoDialog infoDialog = InfoDialog.newInstance(title, message, type);
        infoDialog.show(getChildFragmentManager(), SUCCESS_DIALOG);
    }
    //endregion

    //region isValid
    private boolean isValid() {
        editTextPassword.setError(null);
        editTextConfirmPassword.setError(null);
        boolean status = true;
        if (editTextPassword.getText().trim().isEmpty()) {
            status = false;
            editTextPassword.setError(getResources().getString(R.string.warn_password));
        } else if (editTextPassword.getText().trim().length() < 6) {
            status = false;
            editTextPassword.setError(getResources().getString(R.string.warn_password_minim));
        } else if (!editTextConfirmPassword.getText().trim().equals(editTextConfirmPassword.getText().trim())) {
            status = false;
            editTextConfirmPassword.setError(getResources().getString(R.string.warn_password_miss_match));
        }
        return status;
    }
    //endregion

}
