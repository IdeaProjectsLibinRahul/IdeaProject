package tech.libin.rahul.ideaproject;

import android.os.Bundle;
import android.view.Menu;

import tech.libin.rahul.ideaproject.homescreen.fragments.FOSHomeFragment;
import tech.libin.rahul.ideaproject.views.basecomponents.FOSBaseActivity;

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
}
