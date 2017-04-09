package tech.libin.rahul.ideaproject.views.basecomponents;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import tech.libin.rahul.ideaproject.R;
import tech.libin.rahul.ideaproject.events.base.BaseEvent;
import tech.libin.rahul.ideaproject.views.utils.ApplicationContextHolder;

/**
 * Created by libin on 21/02/17.
 */

public abstract class FOSBaseActivity extends AppCompatActivity {
    protected boolean hasToolBar = false;
    protected Toolbar toolbar;
    protected float toolbarElevation = 0;
    private TextView textViewName;
    private TextView textViewPhone;
    private ImageView imageViewProfilePic;

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

        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(BaseEvent event) {

    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        ApplicationContextHolder contextHolder = ApplicationContextHolder.getInstance();
        contextHolder.setContext(this);
    }

    protected abstract void setToolbarElevation();

    protected abstract void setHasToolBar();

    private void setToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            if (hasToolBar) {
                if (toolbar != null) {
                    toolbar.setTitle("");
                    toolbar.setElevation(toolbarElevation);
                    setSupportActionBar(toolbar);
                }
            } else {
                toolbar.setVisibility(View.GONE);
            }

            textViewName = (TextView) toolbar.findViewById(R.id.textViewUserName);
            textViewPhone = (TextView) toolbar.findViewById(R.id.textViewUserPhone);
            imageViewProfilePic = (ImageView) toolbar.findViewById(R.id.imageViewProfilePic);

            setToolbarEvents();
        }


    }

    private void setToolbarEvents() {
        imageViewProfilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }


    protected void setToolbarUsername(String username) {
        textViewName.setText(username);
    }

    protected void setToolbarPhoneNo(String phoneNo) {
        textViewPhone.setText(phoneNo);
    }

    protected void setToolbarProfilePic(String url) {
        if (imageViewProfilePic != null && url != null) {
            Picasso.with(this).load(url).resize(60, 60).into(imageViewProfilePic);
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
