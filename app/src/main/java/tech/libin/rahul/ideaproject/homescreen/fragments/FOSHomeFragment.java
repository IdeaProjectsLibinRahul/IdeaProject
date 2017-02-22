package tech.libin.rahul.ideaproject.homescreen.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import tech.libin.rahul.ideaproject.R;
import tech.libin.rahul.ideaproject.components.FOSBaseFragment;

/**
 * Created by libin on 21/02/17.
 */

public class FOSHomeFragment extends FOSBaseFragment {

    private View mView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_home, container, false);
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setCollectionsHome();
    }

    private void setCollectionsHome() {
        FOSCollectionFragment collectionFragment = new FOSCollectionFragment();
        addFragment(R.id.home_container, collectionFragment);
    }
}
