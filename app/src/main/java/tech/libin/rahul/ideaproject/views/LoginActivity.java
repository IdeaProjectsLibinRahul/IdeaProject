package tech.libin.rahul.ideaproject.views;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import tech.libin.rahul.ideaproject.R;
import tech.libin.rahul.ideaproject.configurations.Config;
import tech.libin.rahul.ideaproject.facade.FOSFacade;
import tech.libin.rahul.ideaproject.facade.FOSFacadeImpl;
import tech.libin.rahul.ideaproject.service.handlers.ServiceCallback;
import tech.libin.rahul.ideaproject.service.models.UserModel;
import tech.libin.rahul.ideaproject.service.responses.base.FOSError;
import tech.libin.rahul.ideaproject.views.basecomponents.FOSBaseActivity;
import tech.libin.rahul.ideaproject.views.models.Login;
import tech.libin.rahul.ideaproject.views.models.User;
import tech.libin.rahul.ideaproject.views.utils.ApplicationContextHolder;
import tech.libin.rahul.ideaproject.views.utils.TelephonyInfo;
import tech.libin.rahul.ideaproject.views.widgets.button.FOSButton;
import tech.libin.rahul.ideaproject.views.widgets.edittext.FOSIconEditText;

public class LoginActivity extends FOSBaseActivity {

    //region declaration
    FOSIconEditText editTextUserName;
    FOSIconEditText editTextPassword;
    FOSButton buttonSignIn;
    FOSButton buttonForgotPassword;
    FOSButton buttonSignUp;
    //endregion

    //region onCreate
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editTextUserName=(FOSIconEditText)findViewById(R.id.editTextUsernameLogin);
        editTextPassword=(FOSIconEditText)findViewById(R.id.editTextPasswordLogin);
        buttonSignIn =(FOSButton)findViewById(R.id.buttonSignInLogin);
        buttonForgotPassword =(FOSButton)findViewById(R.id.buttonForgotPasswordLogin);
        buttonSignUp =(FOSButton)findViewById(R.id.buttonSignUpLogin);

        //region buttonClicks
        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doLogin();
            }
        });

        buttonForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //endregion
    }

    @Override
    protected void setToolbarElevation() {
        setToolbarElevation(0);
    }

    @Override
    protected void setHasToolBar() {
        setHasToolBar(false);
    }
    //endregion

    private void doLogin()
    {
        try {
            if (isValid()) {
                FOSFacade facade = new FOSFacadeImpl();
                Login login = new Login();
                TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
                telephonyManager.getSimOperatorName();

                TelephonyInfo telephonyInfo = TelephonyInfo.getInstance(this);
                login.setUsername(editTextUserName.getText());
                login.setPassword(editTextPassword.getText());
                login.setImei1( telephonyInfo.getImsiSIM1());
                login.setImei2(telephonyInfo.getImsiSIM2());
                final ProgressDialog dialog = ProgressDialog.show(this,null, getResources().getString(R.string.requesting), true, true);

                facade.doLogin(login, new ServiceCallback<User>() {
                    @Override
                    public void onResponse(User response) {
                        Config.getInstance().setUser(response);
                        dialog.cancel();

                    }

                    @Override
                    public void onRequestTimout() {
                        dialog.cancel();
                    }

                    @Override
                    public void onRequestFail(FOSError error) {
                        dialog.cancel();

                    }
                });
            }
        }
        catch (Exception ex) {
            Log.e("Login", ex.toString());
        }
    }

    private boolean isValid()
    {
        boolean status=true;
        if(editTextPassword.getText().trim().isEmpty())
        {editTextPassword.setError(getResources().getString(R.string.lbl_warn_password));
            status=false;
        }
        if(editTextUserName.getText().trim().isEmpty())
        {editTextUserName.setError(getResources().getString(R.string.lbl_warn_user_name));
            status=false;
        }
        return  status;
    }

}
