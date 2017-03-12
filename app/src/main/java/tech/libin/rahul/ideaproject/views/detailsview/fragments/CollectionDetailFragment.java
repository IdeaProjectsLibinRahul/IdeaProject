package tech.libin.rahul.ideaproject.views.detailsview.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

import tech.libin.rahul.ideaproject.R;
import tech.libin.rahul.ideaproject.configurations.Constants;
import tech.libin.rahul.ideaproject.facade.FOSFacade;
import tech.libin.rahul.ideaproject.facade.FOSFacadeImpl;
import tech.libin.rahul.ideaproject.service.handlers.ServiceCallback;
import tech.libin.rahul.ideaproject.service.responses.base.FOSError;
import tech.libin.rahul.ideaproject.views.basecomponents.FOSBaseFragment;
import tech.libin.rahul.ideaproject.views.detailsview.viewmodels.CollectionDetailModel;
import tech.libin.rahul.ideaproject.views.models.ActivityDetailRequestModel;
import tech.libin.rahul.ideaproject.views.utils.GPSTracker;
import tech.libin.rahul.ideaproject.views.widgets.textview.FOSTextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class CollectionDetailFragment extends FOSBaseFragment implements OnMapReadyCallback {

    public static final String SUCCESS_DIALOG = "SUCCESS_DIALOG";
    private static final String TAG = CollectionDetailFragment.class.getName();

    //region declarations
    Spinner spnStatus;
    private EditText editTextRemarks;
    private EditText editTextReminder;

    private FOSTextView textViewName;
    private FOSTextView textViewMobile;
    private FOSTextView textViewCurBalance;
    private FOSTextView textViewCustType;
    private FOSTextView textViewBal00;
    private FOSTextView textViewBal30;
    private FOSTextView textViewBalOther;
    private FOSTextView textViewCategory;
    private FOSTextView textViewRatePlan;
    private FOSTextView textViewBiller;
    private FOSTextView textViewDefaultHabit;
    private FOSTextView textViewCurrentStatus;
    private FOSTextView textViewSer;
    private FOSTextView textViewBucket;
    private FOSTextView textViewMyIdeaAllocation;
    private FOSTextView textViewCustNum;
    private FOSTextView textViewLandline1;
    private FOSTextView textViewLandLine2;
    private FOSTextView textViewAddress;

    private FOSTextView textViewMicoName;
    private FOSTextView textViewMicoMobileNum;
    private FOSTextView textViewMicoMyIdea;
    private FOSTextView textViewMicoMyIdeaCode;
    private FOSTextView textViewMicoVisitStatus;
    private FOSTextView textViewMicoVisitedDate;
    private FOSTextView textViewMicoRemarks;

    private FOSTextView textViewExeName;
    private FOSTextView textViewExeMobileNum;
    private FOSTextView textViewExeMyIdea;
    private FOSTextView textViewExeMyIdeaCode;
    private FOSTextView textViewExeVisitStatus;
    private FOSTextView textViewExeVisitedDate;
    private FOSTextView textViewExeRemarks;


    private View view;
    private Button buttonSubmit;
    private Switch switchLocation;
    private Switch switchUpdateLocation;
    private LinearLayout linLayoutReminder;
    private RecyclerView recViewOther;
    private String objectId;
    private String userName;
    private String userPhone;
    private Constants.ActivityType activityType;
    private SupportMapFragment mapFragment;
    private GPSTracker gpsTracker;

    //region CollectionDetailFragment
    public CollectionDetailFragment() {
        // Required empty public constructor
    }
    //endregion
    
    //region onCreateView
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_collection_detail, container, false);
    }
    //endregion
    
    //region initComponents
    private void initComponents() {
        gpsTracker = new GPSTracker(getActivity());

        textViewName = (FOSTextView) view.findViewById(R.id.textViewName);
        textViewMobile = (FOSTextView) view.findViewById(R.id.textViewPhoneNum);
        textViewCurBalance = (FOSTextView) view.findViewById(R.id.textViewDueAmount);
        textViewCustType = (FOSTextView) view.findViewById(R.id.textViewCustomerType);
        textViewBal00 = (FOSTextView) view.findViewById(R.id.textViewBal00);
        textViewBal30 = (FOSTextView) view.findViewById(R.id.textViewBal30);
        textViewBalOther = (FOSTextView) view.findViewById(R.id.textViewBalOther);

        textViewCategory = (FOSTextView) view.findViewById(R.id.textViewCategory);
        textViewRatePlan = (FOSTextView) view.findViewById(R.id.textViewRatePlan);
        textViewBiller = (FOSTextView) view.findViewById(R.id.textViewBiller);
        textViewDefaultHabit = (FOSTextView) view.findViewById(R.id.textViewDefaultHabit);
        textViewCurrentStatus = (FOSTextView) view.findViewById(R.id.textViewCurrentStatus);

        textViewSer = (FOSTextView) view.findViewById(R.id.textViewSer);
        textViewBucket = (FOSTextView) view.findViewById(R.id.textViewBucket);
        textViewMyIdeaAllocation = (FOSTextView) view.findViewById(R.id.textViewMyIdeaLocation);
        textViewCustNum = (FOSTextView) view.findViewById(R.id.textViewCustomerNumber);
        textViewLandline1 = (FOSTextView) view.findViewById(R.id.textViewLandLine1);
        textViewLandLine2 = (FOSTextView) view.findViewById(R.id.textViewLandLine2);
        textViewAddress = (FOSTextView) view.findViewById(R.id.textViewAddress);


        editTextRemarks = (EditText) view.findViewById(R.id.editTextRemarks);
        textViewAddress = (FOSTextView) view.findViewById(R.id.textViewAddress);
        recViewOther = (RecyclerView) view.findViewById(R.id.recViewOther);

        spnStatus = (Spinner) view.findViewById(R.id.spinnerStatus);
        editTextReminder = (EditText) view.findViewById(R.id.editTextReminderDate);
        switchLocation = (Switch) view.findViewById(R.id.switchLocation);
        switchUpdateLocation = (Switch) view.findViewById(R.id.switchUpdateLocation);
        editTextRemarks = (EditText) view.findViewById(R.id.editTextRemarks);
        buttonSubmit = (Button) view.findViewById(R.id.buttonSubmit);
        linLayoutReminder = (LinearLayout) view.findViewById(R.id.linLayoutReminder);
    }
    //endregion initComponents

    //region parseBundle
    private void parseBundle() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            objectId = bundle.getString(Constants.PARAMS.DETAILS_OBJECT_ID);
            userName = bundle.getString(Constants.PARAMS.DETAILS_OBJECT_NAME);
            userPhone = bundle.getString(Constants.PARAMS.DETAILS_OBJECT_PHONE);
            userPhone = bundle.getParcelable(Constants.PARAMS.DETAILS_OBJECT_TAB);
            activityType = (Constants.ActivityType) bundle.getSerializable(Constants.PARAMS.DETAILS_OBJECT_TAB);
            //setHeader();
        }
    }
    //endregion

    //region loadCollectionDetails
    private void loadCollectionDetails() {
        if (objectId != null) {
            ActivityDetailRequestModel requestModel = new ActivityDetailRequestModel();
            requestModel.setObjectId(objectId);
            requestModel.setRecordType(Constants.RecordType.COLLECTION);

            FOSFacade fosFacade = new FOSFacadeImpl();
            fosFacade.getCollectionDetail(requestModel, new ServiceCallback<CollectionDetailModel>() {
                @Override
                public void onResponse(CollectionDetailModel response) {
                    Log.e("TDDETAILS", response.toString());
//                    bindDetails(response);
//                    setFomListeners();
                }

                @Override
                public void onRequestTimout() {

                }

                @Override
                public void onRequestFail(FOSError error) {

                }
            });
        }
    }
    //endregion

    //region initMap
    private void initMap() {
        mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        View mapView = mapFragment.getView();
        if (mapView != null) {
            if (!switchLocation.isChecked()) {
                mapView.setVisibility(View.GONE);
            } else {
                mapView.setVisibility(View.VISIBLE);
                mapFragment.getMapAsync(this);
            }
        }
    }
    //endregion

    //region onMapReady
    @Override
    public void onMapReady(GoogleMap googleMap) {
        try {
            mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);
            View mapView = mapFragment.getView();
            if (mapView != null) {
                if (!switchLocation.isChecked()) {
                    mapView.setVisibility(View.GONE);
                } else {
                    mapView.setVisibility(View.VISIBLE);
                }
            }
        } catch (Exception ex) {
            Log.e("Error", ex.toString());
        }
    }
    //endregion


}
