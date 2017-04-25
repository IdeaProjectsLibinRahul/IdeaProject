package tech.libin.rahul.ideaproject.views;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;

import tech.libin.rahul.ideaproject.R;
import tech.libin.rahul.ideaproject.configurations.Config;
import tech.libin.rahul.ideaproject.configurations.Constants;
import tech.libin.rahul.ideaproject.facade.FOSFacade;
import tech.libin.rahul.ideaproject.facade.FOSFacadeImpl;
import tech.libin.rahul.ideaproject.service.handlers.ServiceCallback;
import tech.libin.rahul.ideaproject.service.responses.base.FOSError;
import tech.libin.rahul.ideaproject.views.basecomponents.FOSBaseActivity;
import tech.libin.rahul.ideaproject.views.models.Login;
import tech.libin.rahul.ideaproject.views.models.User;
import tech.libin.rahul.ideaproject.views.widgets.button.FOSButton;
import tech.libin.rahul.ideaproject.views.widgets.dialogs.FOSDialog;
import tech.libin.rahul.ideaproject.views.widgets.edittext.FOSIconEditText;

public class LoginActivity extends FOSBaseActivity {

    private static final int MY_PERMISSIONS_REQUEST_READ_PHONE_STATE = 1;
    //region declaration
    private FOSIconEditText editTextUserName;
    private FOSIconEditText editTextPassword;
    private FOSButton buttonSignIn;
    private FOSButton buttonForgotPassword;
    private FOSButton buttonSignUp;

    //endregion
    private FOSDialog fosDialog;

    //region onCreate
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editTextUserName = (FOSIconEditText) findViewById(R.id.editTextUsernameLogin);
        editTextPassword = (FOSIconEditText) findViewById(R.id.editTextPasswordLogin);
        buttonSignIn = (FOSButton) findViewById(R.id.buttonSignInLogin);
        buttonForgotPassword = (FOSButton) findViewById(R.id.buttonForgotPasswordLogin);
        buttonSignUp = (FOSButton) findViewById(R.id.buttonSignUpLogin);

        //region buttonClicks
        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  checkPhoneStatePermission();
                doLogin();
            }
        });

        buttonForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putSerializable(LoginCredentialsActivity.VIEW_MODE, Constants.CredentialsMode.FORGOT_PASSWORD);

                Intent intent = new Intent(LoginActivity.this, LoginCredentialsActivity.class);
                intent.putExtras(bundle);

                startActivity(intent);
            }
        });

        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        //endregion
    }

    @Override
    protected void setToolbarElevation() {
        setToolbarElevation(0);
    }
    //endregion

    @Override
    protected void setHasToolBar() {
        setHasToolBar(false);
    }

    private void doLogin() {
        try {
            if (isValid()) {
                FOSFacade facade = new FOSFacadeImpl();
                Login loginData = new Login();

//                TelephonyInfo telephonyInfo = TelephonyInfo.getInstance(this);
//                loginData.setImei1(telephonyInfo.getImsiSIM1());
//                loginData.setImei2(telephonyInfo.getImsiSIM2());

                loginData.setUsername(editTextUserName.getText());
                loginData.setPassword(editTextPassword.getText());
                loginData.setImei1("00000");
                loginData.setImei2("00000");

                final ProgressDialog dialog = ProgressDialog.show(this, null, getResources().getString(R.string.requesting), true, true);

                facade.doLogin(loginData, new ServiceCallback<User>() {
                    @Override
                    public void onResponse(User response) {
                        dialog.cancel();
                        if (response.isFirstTimeLogin()) {
                            Long userId = response.getUserId();

                            Bundle bundle = new Bundle();
                            bundle.putSerializable(LoginCredentialsActivity.VIEW_MODE, Constants.CredentialsMode.OTP);
                            bundle.putSerializable(Constants.KEY.OTP_TYPE, Constants.OtpVerificationType.FIRST_TIME_LOGIN);
                            bundle.putLong(LoginCredentialsActivity.USER_ID, userId);

                            Intent intent = new Intent(LoginActivity.this, LoginCredentialsActivity.class);
                            intent.putExtras(bundle);

                            startActivity(intent);
                        } else {
                            Config.getInstance().setUser(response);
                            navigateToHome();
                        }

                    }

                    @Override
                    public void onRequestTimout() {
                        dialog.cancel();
                        String title = "Request Fail";
                        fosDialog = FOSDialog.newInstance(LoginActivity.this, title, getResources().getString(R.string.warn_request_timed_out), false);
                        fosDialog.show(getSupportFragmentManager(), "tag");
                    }

                    @Override
                    public void onRequestFail(FOSError error) {
                        dialog.cancel();
                        String message = error.getErrorMessage();
                        String title = "Authentication Fail";
                        fosDialog = FOSDialog.newInstance(LoginActivity.this, title, message, false);

                        fosDialog.show(getSupportFragmentManager(), "tag");
//                        navigateToHome();
//                        fosDialog.setMessage(error.getErrorMessage());
                    }
                });
            }
        } catch (Exception ex) {
            Log.e("Login", ex.toString());
        }
    }

    private void navigateToHome() {
        Intent intent = new Intent(LoginActivity.this, FOSHomeActivity.class);
        startActivity(intent);
    }

    private boolean isValid() {
        boolean status = true;
        if (editTextPassword.getText().trim().isEmpty()) {
            editTextPassword.setError(getResources().getString(R.string.lbl_warn_password));
            status = false;
        }
        if (editTextUserName.getText().trim().isEmpty()) {
            editTextUserName.setError(getResources().getString(R.string.lbl_warn_user_name));
            status = false;
        }
        return status;
    }

    private void checkPhoneStatePermission() {
        try {
            // Here, thisActivity is the current activity
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.READ_PHONE_STATE)
                    != PackageManager.PERMISSION_GRANTED) {

                // Should we show an explanation?
                if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.READ_PHONE_STATE)) {

                    // Show an explanation to the user *asynchronously* -- don't block
                    // this thread waiting for the user's response! After the user
                    // sees the explanation, try again to request the permission.

                } else {

                    // No explanation needed, we can request the permission.

                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.READ_PHONE_STATE},
                            MY_PERMISSIONS_REQUEST_READ_PHONE_STATE);

                    // MY_PERMISSIONS_REQUEST_READ_PHONE_STATE is an
                    // app-defined int constant. The callback method gets the
                    // result of the request.
                }
            } else {
                doLogin();
            }
        } catch (Exception ex) {

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_PHONE_STATE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {


                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    doLogin();
                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;

            }


            // other 'case' lines to check for other
            // permissions this app might request
        }
    }


}
