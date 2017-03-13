package tech.libin.rahul.ideaproject.views.homescreen.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import tech.libin.rahul.ideaproject.R;
import tech.libin.rahul.ideaproject.configurations.Config;
import tech.libin.rahul.ideaproject.configurations.Constants;
import tech.libin.rahul.ideaproject.views.basecomponents.FOSBaseFragment;
import tech.libin.rahul.ideaproject.views.homescreen.adapters.HomeTabAdapter;
import tech.libin.rahul.ideaproject.views.homescreen.dialogs.FOSSearchDialog;
import tech.libin.rahul.ideaproject.views.models.User;

/**
 * Created by libin on 21/02/17.
 */

public class FOSCollectionFragment extends FOSBaseFragment {
    private static final String TAG = FOSCollectionFragment.class.getSimpleName();
    private View mView;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private LinearLayout bottomBar;
    private RelativeLayout tabRetention;
    private RelativeLayout tabCollection;
    private HomeTabAdapter tabAdapter;
    private User user;
    private Constants.Type userSelectionType;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_collection, container, false);
        setHasOptionsMenu(true);

        initComponents();
        setListeners();
        initTab();

        return mView;
    }

    private void initComponents() {
        mTabLayout = (TabLayout) mView.findViewById(R.id.tab_layout_collections);
        mViewPager = (ViewPager) mView.findViewById(R.id.view_pager_collections);

        bottomBar = (LinearLayout) mView.findViewById(R.id.bottom_bar);
        tabRetention = (RelativeLayout) mView.findViewById(R.id.tabRetention);
        tabCollection = (RelativeLayout) mView.findViewById(R.id.tabCollection);

        user = Config.getInstance().getUser();
        if (user.getType() != Constants.Type.BOTH) {
//            bottomBar.setVisibility(View.GONE);
            userSelectionType = Constants.Type.RETENSION;
            Config.getInstance().setTabSelected(Constants.Type.RETENSION);
        } else {
            userSelectionType = Constants.Type.COLLECTION;
            Config.getInstance().setTabSelected(Constants.Type.COLLECTION);
        }
    }

    private void setListeners() {
        final int selectedColor = ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark);
        final int normalColor = ContextCompat.getColor(getActivity(), R.color.colorPrimary);

        tabRetention.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tabRetention.setBackgroundColor(selectedColor);
                tabCollection.setBackgroundColor(normalColor);
                userSelectionType = Constants.Type.RETENSION;
                Config.getInstance().setTabSelected(Constants.Type.RETENSION);
                initTab();
            }
        });

        tabCollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tabCollection.setBackgroundColor(selectedColor);
                tabRetention.setBackgroundColor(normalColor);
                userSelectionType = Constants.Type.COLLECTION;
                Config.getInstance().setTabSelected(Constants.Type.COLLECTION);
                initTab();
            }
        });
    }

    private void initTab() {
        tabAdapter = new HomeTabAdapter(getActivity(), getChildFragmentManager(), userSelectionType);
        mViewPager.setAdapter(tabAdapter);
        mTabLayout.setupWithViewPager(mViewPager);

        Log.i(TAG, "initTab: done");
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        getActivity().getMenuInflater().inflate(R.menu.menu_search, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_search) {
            FOSSearchDialog dialog = new FOSSearchDialog();
            dialog.show(getFragmentManager(), "TAG");
        }

        return true;
    }
}
