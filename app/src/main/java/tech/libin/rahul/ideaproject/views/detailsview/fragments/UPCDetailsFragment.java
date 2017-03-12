package tech.libin.rahul.ideaproject.views.detailsview.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import tech.libin.rahul.ideaproject.R;
import tech.libin.rahul.ideaproject.configurations.Constants;
import tech.libin.rahul.ideaproject.facade.FOSFacade;
import tech.libin.rahul.ideaproject.facade.FOSFacadeImpl;
import tech.libin.rahul.ideaproject.service.handlers.ServiceCallback;
import tech.libin.rahul.ideaproject.service.models.DetailFromUPCRoleModel;
import tech.libin.rahul.ideaproject.service.models.SpinnerData;
import tech.libin.rahul.ideaproject.service.responses.base.FOSError;
import tech.libin.rahul.ideaproject.views.basecomponents.FOSBaseFragment;
import tech.libin.rahul.ideaproject.views.detailsview.adapters.FOSSpinnerAdapter;
import tech.libin.rahul.ideaproject.views.detailsview.dialogs.FOSDateDialog;
import tech.libin.rahul.ideaproject.views.detailsview.dialogs.InfoDialog;
import tech.libin.rahul.ideaproject.views.detailsview.viewmodels.FormSubmitModel;
import tech.libin.rahul.ideaproject.views.detailsview.viewmodels.UpcDetailModel;
import tech.libin.rahul.ideaproject.views.models.ActivityDetailRequestModel;
import tech.libin.rahul.ideaproject.views.utils.GPSTracker;
import tech.libin.rahul.ideaproject.views.widgets.textview.FOSTextView;

import static tech.libin.rahul.ideaproject.views.detailsview.fragments.SMEDetailsFragment.DATE_DIALOG;

/**
 * Created by libin on 06/03/17.
 */

public class UPCDetailsFragment extends FOSBaseFragment implements OnMapReadyCallback {

    //region declarations

    public static final String SUCCESS_DIALOG = "SUCCESS_DIALOG";
    private static final String TAG = UPCDetailsFragment.class.getName();
    Spinner spnStatus;
    private FOSTextView textViewCustNum;
    private FOSTextView textViewMobile;
    private FOSTextView textViewUpc;
    private FOSTextView textViewSubscriberType;
    private FOSTextView textViewCreatedDateTime;
    private FOSTextView textViewSegment;
    private FOSTextView textViewCustomerSubsType;
    private FOSTextView textViewCsCreditCode;
    private FOSTextView textViewCustomerType;
    private FOSTextView textViewAlternateNumber;
    private FOSTextView textViewServSeg;
    private FOSTextView textViewAddress;
    private EditText editTextReminder;
    private EditText editTextRemarks;
    private View view;

    private Button buttonSubmit;

    private LinearLayout linLayoutReminder;

    private GoogleMap mMap;

    private RecyclerView recViewOther;

    private Switch switchLocation;
    private Switch switchUpdateLocation;

    private String objectId;
    private String userName;
    private String userPhone;
    private List visitStatus;
    private Constants.ActivityType activityType;
    private SupportMapFragment mapFragment;

    private GPSTracker gpsTracker;
    //endregion

    //region onCreateView
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_upc_details, container, false);

        initComponents();
        parseBundle();
        loadUpcDetails();
        initMap();

        return view;
    }
    //endregion

    //region initComponents
    private void initComponents() {
        gpsTracker = new GPSTracker(getActivity());

        textViewCustNum = (FOSTextView) view.findViewById(R.id.textViewName);
        textViewMobile = (FOSTextView) view.findViewById(R.id.textViewPhoneNum);
        textViewUpc = (FOSTextView) view.findViewById(R.id.textViewUpc);

        textViewSubscriberType = (FOSTextView) view.findViewById(R.id.textViewSubType);
        textViewCreatedDateTime = (FOSTextView) view.findViewById(R.id.textViewBegeningDate);
        textViewSegment = (FOSTextView) view.findViewById(R.id.textViewSegment);

        textViewCustomerSubsType = (FOSTextView) view.findViewById(R.id.textViewCustSubType);
        textViewCsCreditCode = (FOSTextView) view.findViewById(R.id.textViewCsCreditCode);
        textViewCustomerType = (FOSTextView) view.findViewById(R.id.textViewCustomerType);
        textViewAlternateNumber = (FOSTextView) view.findViewById(R.id.textViewAlternateNum);
        textViewServSeg = (FOSTextView) view.findViewById(R.id.textViewServSeg);

        editTextRemarks = (EditText) view.findViewById(R.id.editTextRemarks);
        textViewAddress = (FOSTextView) view.findViewById(R.id.textViewAddress);
        recViewOther = (RecyclerView) view.findViewById(R.id.recViewOther);

        spnStatus = (Spinner) view.findViewById(R.id.spinnerStatus);
        editTextReminder = (EditText) view.findViewById(R.id.editTextReminderDate);
        switchLocation = (Switch) view.findViewById(R.id.switchLocation);
        switchUpdateLocation = (Switch) view.findViewById(R.id.switchUpdateLocation);
        buttonSubmit = (Button) view.findViewById(R.id.buttonSubmit);
        linLayoutReminder = (LinearLayout) view.findViewById(R.id.linLayoutReminder);
    }
    //endregion

    //region parseBundle
    private void parseBundle() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            objectId = bundle.getString(Constants.PARAMS.DETAILS_OBJECT_ID);
            userName = bundle.getString(Constants.PARAMS.DETAILS_OBJECT_NAME);
            userPhone = bundle.getString(Constants.PARAMS.DETAILS_OBJECT_PHONE);
            userPhone = bundle.getString(Constants.PARAMS.DETAILS_OBJECT_PHONE);
            activityType = (Constants.ActivityType) bundle.getSerializable(Constants.PARAMS.DETAILS_OBJECT_TAB);
        }
    }
    //endregion

    //region loadUpcDetails
    private void loadUpcDetails() {
        if (objectId != null) {
            ActivityDetailRequestModel requestModel = new ActivityDetailRequestModel();
            requestModel.setObjectId(objectId);
            requestModel.setRecordType(Constants.RecordType.UPC);

            FOSFacade fosFacade = new FOSFacadeImpl();
            fosFacade.getUpcDetail(requestModel, new ServiceCallback<UpcDetailModel>() {
                @Override
                public void onResponse(UpcDetailModel response) {
                    bindData(response);
                    setFomListeners();
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

    //region bindData
    private void bindData(UpcDetailModel response) {
        textViewCustNum.setText(response.getCustomerName());
        textViewMobile.setText(response.getMsisdn());
        textViewUpc.setText(response.getUpc());
        textViewSubscriberType.setText(response.getSubscriberType());
        textViewCreatedDateTime.setText(response.getCreatedDateTime());
        textViewSegment.setText(response.getSegment());
        textViewCustomerSubsType.setText(response.getCustomerSubsType());
        textViewCsCreditCode.setText(response.getCsCreditCode());
        textViewCustomerType.setText(response.getCustomerType());
        textViewAlternateNumber.setText(response.getAlternateNumber());
        textViewServSeg.setText(response.getServSeg());
        textViewAddress.setText(response.getAddress1() + "\n" + response.getAddress2() + "\n" + response.getAddress3() + "\n" + response.getZip());
        visitStatus = response.getVisitStatus();
        FOSSpinnerAdapter statusAdapter = new FOSSpinnerAdapter(getActivity(), android.R.layout.simple_spinner_item, response.getVisitStatus());
        spnStatus.setAdapter(statusAdapter);

        try {
            if (activityType != Constants.ActivityType.NEW_ACTIVITY) {
                DetailFromUPCRoleModel fromExecutive = response.getFromExecutive();
                if (fromExecutive != null) {
                    if (fromExecutive.getStatus() == 2) {
                        spnStatus.setSelection(1);
                        linLayoutReminder.setVisibility(View.VISIBLE);
                        editTextReminder.setText(response.getReminderDate());
                    } else
                        spnStatus.setSelection(0);
                    if (fromExecutive.getStatus() != 0) {
                        SpinnerData spinnerElement = findSpinnerElementPosition(fromExecutive.getStatus(), visitStatus);
                        if (spinnerElement != null) {
                            int position = statusAdapter.getPosition(spinnerElement);
                            spnStatus.setSelection(position);
                        }
                    }

                    editTextRemarks.setText(fromExecutive.getRemarks());
                }
            }
        } catch (Exception ex) {
            Log.e("Testing", ex.toString());
        }
    }

    private SpinnerData findSpinnerElementPosition(int value, List<SpinnerData> spinnerData) {
        for (SpinnerData spnData : spinnerData) {
            if (spnData.getId() == value) {
                return spnData;
            }
        }
        return null;
    }

    //endregion

    //region setFomListeners
    private void setFomListeners() {

        switchLocation.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                if (mapFragment != null) {
                    View mapView = mapFragment.getView();
                    if (mapView != null) {
                        if (!switchLocation.isChecked()) {
                            mapView.setVisibility(View.GONE);
                        } else {
                            mapView.setVisibility(View.VISIBLE);
                            mapFragment.getMapAsync(UPCDetailsFragment.this);
                        }
                    }
                }

            }
        });


        editTextReminder.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    //CREATE YOUR DATE PICKER DIALOG HERE.
                    FOSDateDialog dateDialog = new FOSDateDialog();
                    dateDialog.setDateSetListener(new FOSDateDialog.DateSetListener() {
                        @Override
                        public void onDateSet(int year, int month, int day) {
                            editTextReminder.setText(month + "/" + day + "/" + year);
                            editTextReminder.clearFocus();
                            editTextReminder.setError(null);
                        }
                    });

                    dateDialog.show(getChildFragmentManager(), DATE_DIALOG);
                }
                return false;
            }
        });


        spnStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (((SpinnerData) visitStatus.get(position)).getId() == 2) {
                    linLayoutReminder.setVisibility(View.VISIBLE);
                } else {
                    linLayoutReminder.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                submitFormData();
            }
        });
    }
    //endregion

    //region submitFormData
    private void submitFormData() {

        FormSubmitModel requestModel = new FormSubmitModel();
        requestModel.setObjectId(Long.parseLong(objectId));
        requestModel.setStatus(((SpinnerData) spnStatus.getSelectedItem()).getId());
        requestModel.setRemarks(editTextRemarks.getText().toString().trim());

        GPSTracker gpsTracker = new GPSTracker(getActivity());

        if (gpsTracker.getIsGPSTrackingEnabled()) {
            requestModel.setLatitude(gpsTracker.getLatitude() + "");
            requestModel.setLongitude(gpsTracker.getLongitude() + "");
        } else {
            gpsTracker.showSettingsAlert();
            return;
        }

        requestModel.setLongitude("9.977052");
        requestModel.setLatitude("76.317974");
        requestModel.setReminder("");
        requestModel.setFeedback(0);
        requestModel.setReason(0);

        if (linLayoutReminder.getVisibility() == View.VISIBLE) {
            requestModel.setReminder(editTextReminder.getText().toString().trim());
        }
        requestModel.setRecordType(Constants.RecordType.UPC);

        FOSFacade fosFacade = new FOSFacadeImpl();
        fosFacade.doSubmitVisitDetails(requestModel, new ServiceCallback<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("Submit", response);
                showSuccessInfo();
            }

            @Override
            public void onRequestTimout() {

            }

            @Override
            public void onRequestFail(FOSError error) {
                Log.e("Submit Fail", error.getErrorMessage());
                showSuccessInfo();
            }
        });
    }

    private void showSuccessInfo() {
        String message = "Form submitted successfully";
        String title = "Info";

        InfoDialog infoDialog = InfoDialog.newInstance(title, message);
        infoDialog.show(getChildFragmentManager(), SUCCESS_DIALOG);
    }
    //endregion

    //region onMapReady
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng sydney = new LatLng(9.977052, 76.317974);
        googleMap.addMarker(new MarkerOptions().position(sydney)
                .title("Marker in Sydney"));
        //googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));


//        LatLngBounds.Builder builder = new LatLngBounds.Builder();
//        builder.include(sydney);
//        LatLngBounds bounds = builder.build();
//
//        googleMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, 150));

    }
    //endregion

    //region initMap
    private void initMap() {
        try {


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
        } catch (Exception ex) {
            Log.e("Error", ex.toString());
        }
    }
    //endregion

}
