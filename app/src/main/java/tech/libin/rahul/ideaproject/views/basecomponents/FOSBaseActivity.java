package tech.libin.rahul.ideaproject.views.basecomponents;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import tech.libin.rahul.ideaproject.R;
import tech.libin.rahul.ideaproject.views.utils.ApplicationContextHolder;

/**
 * Created by libin on 21/02/17.
 */

public abstract class FOSBaseActivity extends AppCompatActivity {
    protected boolean hasToolBar = false;
    protected Toolbar toolbar;
    protected float toolbarElevation = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void onResume() {
        super.onResume();

        setHasToolBar();
        setToolbarElevation();
        setToolBar();

        ApplicationContextHolder contextHolder = ApplicationContextHolder.getInstance();
        contextHolder.setContext(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        ApplicationContextHolder contextHolder = ApplicationContextHolder.getInstance();
        contextHolder.setContext(this);
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);

        ApplicationContextHolder contextHolder = ApplicationContextHolder.getInstance();
        contextHolder.setContext(this);
    }

    protected abstract void setToolbarElevation();

    protected abstract void setHasToolBar();

    private void setToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if(toolbar != null) {
            if (hasToolBar) {
                if (toolbar != null) {
                    toolbar.setTitle("");
                    toolbar.setElevation(toolbarElevation);
                    setSupportActionBar(toolbar);
                }
            } else {
                toolbar.setVisibility(View.GONE);
            }
        }
    }

    protected void setMainView(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_container, fragment).commit();
    }

    protected void setMainViewWithBackStack(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_container, fragment).addToBackStack(null).commit();
    }

    protected void addFragment(@IdRes int containerId, Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(containerId, fragment).commit();
    }

    public void setHasToolBar(boolean hasToolBar) {
        this.hasToolBar = hasToolBar;
    }

    public void setToolbarElevation(float toolbarElevation) {
        this.toolbarElevation = toolbarElevation;
    }
}
