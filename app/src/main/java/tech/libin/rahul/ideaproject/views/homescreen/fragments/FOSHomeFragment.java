package tech.libin.rahul.ideaproject.views.homescreen.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import tech.libin.rahul.ideaproject.R;
import tech.libin.rahul.ideaproject.configurations.Constants;
import tech.libin.rahul.ideaproject.events.DetailsEvent;
import tech.libin.rahul.ideaproject.views.basecomponents.FOSBaseFragment;
import tech.libin.rahul.ideaproject.views.detailsview.fragments.SMEDetailsFragment;
import tech.libin.rahul.ideaproject.views.detailsview.fragments.TDDetailsFragment;
import tech.libin.rahul.ideaproject.views.detailsview.fragments.UPCDetailsFragment;
import tech.libin.rahul.ideaproject.views.homescreen.viewmodels.ActivityModel;

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

        showCollectionsHome();

    }

    private void showCollectionsHome() {
        FOSCollectionFragment collectionFragment = new FOSCollectionFragment();
        addFragment(R.id.home_container, collectionFragment);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(DetailsEvent event) {
        ActivityModel model = event.getModel();
        Fragment detailsFragment;

        Bundle bundle = new Bundle();
        bundle.putString(Constants.PARAMS.DETAILS_OBJECT_ID, model.getId());
        bundle.putString(Constants.PARAMS.DETAILS_OBJECT_NAME, model.getName());
        bundle.putString(Constants.PARAMS.DETAILS_OBJECT_PHONE, model.getPhoneNo());
        bundle.putSerializable(Constants.PARAMS.DETAILS_OBJECT_TAB,event.getActivityType());

        switch (model.getType()) {
            case SME:
                detailsFragment = new SMEDetailsFragment();
                break;
            case TD:
                detailsFragment = new TDDetailsFragment();
                break;
            case UPC:
                detailsFragment = new UPCDetailsFragment();
                break;
            case COLLECTION:
                detailsFragment = new Fragment();
                break;
            default:
                detailsFragment = new SMEDetailsFragment();
        }

        detailsFragment.setArguments(bundle);
        showDetailsPage(detailsFragment);
    }

    private void showDetailsPage(Fragment fragment) {
        addFragmentWithBackStack(R.id.home_container, fragment);
    }
}
