package tech.libin.rahul.ideaproject.views.detailsview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import tech.libin.rahul.ideaproject.R;
import tech.libin.rahul.ideaproject.views.basecomponents.FOSBaseFragment;

/**
 * Created by libin on 06/03/17.
 */

public class UPCDetailsFragment extends FOSBaseFragment {

    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_upc_details, container, false);
        return view;
    }
}
