package tech.libin.rahul.ideaproject.homescreen.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import tech.libin.rahul.ideaproject.R;
import tech.libin.rahul.ideaproject.components.FOSBaseFragment;
import tech.libin.rahul.ideaproject.homescreen.adapters.HomeTabAdapter;

/**
 * Created by libin on 21/02/17.
 */

public class FOSCollectionFragment extends FOSBaseFragment {
    private static final String TAG = FOSCollectionFragment.class.getSimpleName();
    private View mView;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_collection, container, false);

        initComponents();
        initTab();

        return mView;
    }

    private void initComponents() {
        mTabLayout = (TabLayout) mView.findViewById(R.id.tab_layout_collections);
        mViewPager = (ViewPager) mView.findViewById(R.id.view_pager_collections);
    }

    private void initTab() {
        HomeTabAdapter tabAdapter = new HomeTabAdapter(getActivity(), getChildFragmentManager());
        mViewPager.setAdapter(tabAdapter);
        mTabLayout.setupWithViewPager(mViewPager);

        Log.i(TAG, "initTab: done");
    }
}
