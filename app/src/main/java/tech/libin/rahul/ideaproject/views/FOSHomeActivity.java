package tech.libin.rahul.ideaproject.views;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import tech.libin.rahul.ideaproject.R;
import tech.libin.rahul.ideaproject.configurations.Config;
import tech.libin.rahul.ideaproject.facade.FOSFacade;
import tech.libin.rahul.ideaproject.facade.FOSFacadeImpl;
import tech.libin.rahul.ideaproject.service.handlers.ServiceCallback;
import tech.libin.rahul.ideaproject.service.responses.base.FOSError;
import tech.libin.rahul.ideaproject.views.basecomponents.FOSBaseActivity;
import tech.libin.rahul.ideaproject.views.homescreen.fragments.FOSHomeFragment;
import tech.libin.rahul.ideaproject.views.models.User;

public class FOSHomeActivity extends FOSBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foshome);

        setHomeView();
    }

    @Override
    protected void setToolbarElevation() {
        setToolbarElevation(0);
    }

    @Override
    protected void setHasToolBar() {
        setHasToolBar(true);
    }

    private void setHomeView() {
        FOSHomeFragment homeFragment = new FOSHomeFragment();
        setMainView(homeFragment);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_logout) {
            FOSFacade fosFacade = new FOSFacadeImpl();
            User user = Config.getInstance().getUser();
            fosFacade.doLogout(user.getUserId() + "", new ServiceCallback<String>() {
                @Override
                public void onResponse(String response) {
                    finish();
                }

                @Override
                public void onRequestTimout() {

                }

                @Override
                public void onRequestFail(FOSError error) {

                }
            });

            finish();
        }
        return true;
    }
}
