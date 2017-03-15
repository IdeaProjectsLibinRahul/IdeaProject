package tech.libin.rahul.ideaproject.views.homescreen.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import tech.libin.rahul.ideaproject.R;
import tech.libin.rahul.ideaproject.views.basecomponents.FOSBaseFragment;

/**
 * Created by libin on 26/02/17.
 */

public class FOSActivityDetailsFragment extends FOSBaseFragment {

    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_td_details, container, false);
        return view;
    }
}
