package tech.libin.rahul.ideaproject.views.detailsview.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
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

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import tech.libin.rahul.ideaproject.R;
import tech.libin.rahul.ideaproject.configurations.Config;
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
    Spinner spnFeedback;
    private List feedbackList;

    FOSSpinnerAdapter statusAdapter;
    FOSSpinnerAdapter feedbackAdapter;
    private FOSTextView textViewCustNum;
    private FOSTextView textViewMobile;
    private FOSTextView textViewUpc;
    private FOSTextView textViewUPCDate;
    private FOSTextView textViewSegment;
    private FOSTextView textViewCustomerType;
    private FOSTextView textViewAlternateNumber;
    private FOSTextView textViewServSeg;
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
    private Constants.ActivityType activityType;
    private SupportMapFragment mapFragment;
    private UpcDetailModel detailModel;
    private GPSTracker gpsTracker;
    String userName;
    String userPhone;
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
    /*
    * Initialize components
    * */
    private void initComponents() {

        //to get current location from device
        gpsTracker = new GPSTracker(getActivity());

        textViewCustNum = (FOSTextView) view.findViewById(R.id.textViewName);
        textViewMobile = (FOSTextView) view.findViewById(R.id.textViewPhoneNum);
        textViewUpc = (FOSTextView) view.findViewById(R.id.textViewUpc);

        textViewUPCDate = (FOSTextView) view.findViewById(R.id.textViewBegeningDate);
        textViewSegment = (FOSTextView) view.findViewById(R.id.textViewSegment);

        textViewCustomerType = (FOSTextView) view.findViewById(R.id.textViewCustomerType);
        textViewAlternateNumber = (FOSTextView) view.findViewById(R.id.textViewAlternateNum);
        textViewServSeg = (FOSTextView) view.findViewById(R.id.textViewServSeg);

        textViewMicoName = (FOSTextView) view.findViewById(R.id.textViewFromMicoName);
        textViewMicoMobileNum = (FOSTextView) view.findViewById(R.id.textViewFromMicoMobileNum);
        textViewMicoMyIdea = (FOSTextView) view.findViewById(R.id.textViewFromMicoMyIdea);
        textViewMicoMyIdeaCode = (FOSTextView) view.findViewById(R.id.textViewFromMicoMyIdeaCode);
        textViewMicoVisitStatus = (FOSTextView) view.findViewById(R.id.textViewFromMicoVisitStatus);
        textViewMicoVisitedDate = (FOSTextView) view.findViewById(R.id.textViewFromMicoVisitedDate);
        textViewMicoRemarks = (FOSTextView) view.findViewById(R.id.textViewFromMicoRemarks);

        textViewExeName = (FOSTextView) view.findViewById(R.id.textViewFromExeName);
        textViewExeMobileNum = (FOSTextView) view.findViewById(R.id.textViewFromExeMobileNum);
        textViewExeMyIdea = (FOSTextView) view.findViewById(R.id.textViewFromExeMyIdea);
        textViewExeMyIdeaCode = (FOSTextView) view.findViewById(R.id.textViewFromExeMyIdeaCode);
        textViewExeVisitStatus = (FOSTextView) view.findViewById(R.id.textViewFromExeVisitStatus);
        textViewExeVisitedDate = (FOSTextView) view.findViewById(R.id.textViewFromExeVisitedDate);
        textViewExeRemarks = (FOSTextView) view.findViewById(R.id.textViewFromExeRemarks);

        editTextRemarks = (EditText) view.findViewById(R.id.editTextRemarks);
        textViewAddress = (FOSTextView) view.findViewById(R.id.textViewAddress);
        recViewOther = (RecyclerView) view.findViewById(R.id.recViewOther);

        spnStatus = (Spinner) view.findViewById(R.id.spinnerStatus);
        spnFeedback = (Spinner) view.findViewById(R.id.spinnerFeedback);
        editTextReminder = (EditText) view.findViewById(R.id.editTextReminderDate);
        switchLocation = (Switch) view.findViewById(R.id.switchLocation);
        switchUpdateLocation = (Switch) view.findViewById(R.id.switchUpdateLocation);
        buttonSubmit = (Button) view.findViewById(R.id.buttonSubmit);
        linLayoutReminder = (LinearLayout) view.findViewById(R.id.linLayoutReminder);
    }
    //endregion

    //region parseBundle
    //get information from previous layout click
    private void parseBundle() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            objectId = bundle.getString(Constants.PARAMS.DETAILS_OBJECT_ID);
            userName = bundle.getString(Constants.PARAMS.DETAILS_OBJECT_NAME);
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

            final ProgressDialog dialog = ProgressDialog.show(getActivity(), null, getResources().getString(R.string.requesting), true, true);
            FOSFacade fosFacade = new FOSFacadeImpl();
            fosFacade.getUpcDetail(requestModel, new ServiceCallback<UpcDetailModel>() {
                @Override
                public void onResponse(UpcDetailModel response) {
                    if (dialog != null) {
                        dialog.cancel();
                    }
                    detailModel = response;
                    bindData(response);
                    setFomListeners();

                }

                @Override
                public void onRequestTimout() {
                    if (dialog != null) {
                        dialog.cancel();
                    }
                    showSuccessInfo(getResources().getString(R.string.warn_request_timed_out));
                }

                @Override
                public void onRequestFail(FOSError error) {
                    if (dialog != null) {
                        dialog.cancel();
                    }
                    showSuccessInfo(error.getErrorMessage());
                }
            });
        }
    }
    //endregion

    //region bindData
    private void bindData(UpcDetailModel model) {
        textViewCustNum.setText(model.getCustomerName());
        textViewMobile.setText(model.getMsisdn());
        textViewUpc.setText(model.getUpc());
        textViewUPCDate.setText(model.getCreatedDateTime());
        textViewSegment.setText(model.getSegment());
        textViewCustomerType.setText(model.getCustomerType());
        textViewAlternateNumber.setText(model.getAlternateNumber());
        textViewServSeg.setText(model.getServSeg());
        textViewAddress.setText(model.getAddress1() + "\n" + model.getAddress2() + "\n" + model.getAddress3() + "\n" + model.getZip());

        statusAdapter = new FOSSpinnerAdapter(getActivity(), android.R.layout.simple_spinner_item, model.getVisitStatus());
        spnStatus.setAdapter(statusAdapter);

        //if not retained
        if (((SpinnerData) spnStatus.getSelectedItem()).getId() == 2)
            loadFeedback(model.getFeedbackNotRetained());
            //if retained
        else
            loadFeedback(model.getFeedbackRetained());

        //Show view location switch only if location already saved
        if (model.getLocation() != null && model.getLocation().getLatitude() != null && !model.getLocation().getLatitude().isEmpty()) {
            switchLocation.setVisibility(View.VISIBLE);
        }

        //load executive details if user is executive
        //need not to be load from executive if the record is new activity
        if ((Config.getInstance().getUser().getRole() == Constants.Role.EXECUTIVE) && (activityType != Constants.ActivityType.NEW_ACTIVITY)) {
            loadExecutiveOwnData();
        }
    }

    //region loadExecutiveOwnData
    private void loadExecutiveOwnData() {
        try {
            DetailFromUPCRoleModel fromExecutive = detailModel.getFromExecutive();
            if (fromExecutive != null) {
                //find out visit status and set spinner selection
                if (fromExecutive.getStatus() != 0 && spnStatus != null) {
                    int position = statusAdapter.findElementPosition(fromExecutive.getStatus());
                    spnStatus.setSelection(position, false);
                }

                int visitStatus = ((SpinnerData) spnStatus.getSelectedItem()).getId();
                if (fromExecutive.getFeedback() != 0) {
                    //if not retained
                    if (visitStatus == 2) {
                        loadFeedback(detailModel.getFeedbackNotRetained());
                    } else {
                        loadFeedback(detailModel.getFeedbackRetained());
                    }

                    //find out feedback and set feedback spinner selection
                    int position = feedbackAdapter.findElementPosition(fromExecutive.getFeedback());
                    if (position != 0) {
                        spnFeedback.setSelection(position,false);
                    }
                }
                String reminder = detailModel.getReminderDate();
                if (reminder != null && !reminder.isEmpty()) {
                    linLayoutReminder.setVisibility(View.VISIBLE);
                    editTextReminder.setText(reminder);
                }
                editTextRemarks.setText(fromExecutive.getRemarks());
            }
        } catch (Exception ex) {
            Log.e(TAG, ex.toString());
        }
    }
    //endregion

    //region loadFeedback
    //load feedback spinner
    private void loadFeedback(ArrayList<SpinnerData> feedback) {
        feedbackAdapter = new FOSSpinnerAdapter(getActivity(), android.R.layout.simple_spinner_item, feedback);
        spnFeedback.setAdapter(feedbackAdapter);
        feedbackList=feedback;
    }
    //endregion

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

        editTextReminder.setInputType(InputType.TYPE_NULL);
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
                if (detailModel.getVisitStatus().get(position).getId() == 2) {
                    loadFeedback(detailModel.getFeedbackNotRetained());
                    linLayoutReminder.setVisibility(View.VISIBLE);
                } else {
                    loadFeedback(detailModel.getFeedbackRetained());
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
        requestModel.setFeedback(((SpinnerData) spnFeedback.getSelectedItem()).getId());
        requestModel.setRemarks(editTextRemarks.getText().toString().trim());
        requestModel.setRecordType(Constants.RecordType.UPC);

        GPSTracker gpsTracker = new GPSTracker(getActivity());
        if (switchUpdateLocation.isChecked()) {
            if (gpsTracker != null) {
                double latitude = gpsTracker.getLatitude();
                double longitude = gpsTracker.getLongitude();
                requestModel.setLatitude(latitude + "");
                requestModel.setLongitude(longitude + "");
            }
        }

        requestModel.setReminder("");
        if (linLayoutReminder.getVisibility() == View.VISIBLE) {
            requestModel.setReminder(editTextReminder.getText().toString().trim());
        }

        requestModel.setAmountPaid("0");

        final ProgressDialog dialog = ProgressDialog.show(getActivity(), null, getResources().getString(R.string.requesting), true, true);
        FOSFacade fosFacade = new FOSFacadeImpl();
        fosFacade.doSubmitVisitDetails(requestModel, new ServiceCallback<String>() {
            @Override
            public void onResponse(String response) {
                if (dialog != null) {
                    dialog.cancel();
                }
                showSuccessInfo();
            }

            @Override
            public void onRequestTimout() {
                if (dialog != null) {
                    dialog.cancel();
                }
            }

            @Override
            public void onRequestFail(FOSError error) {
                Log.e("Submit Fail", error.getErrorMessage());
                showSuccessInfo();
                if (dialog != null) {
                    dialog.cancel();
                }
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

        LatLng latLng;
        if (detailModel != null && !detailModel.getLocation().getLatitude().isEmpty() && !detailModel.getLocation().getLongitude().isEmpty()) {
            double latitude = Double.parseDouble(detailModel.getLocation().getLatitude());
            double longitude = Double.parseDouble(detailModel.getLocation().getLongitude());
            latLng = new LatLng(latitude, longitude);
        } else if (gpsTracker != null) {
            double latitude = gpsTracker.getLatitude();
            double longitude = gpsTracker.getLongitude();
            latLng = new LatLng(latitude, longitude);
            Log.d(TAG, "onMapReady: (lat, long) - (" + latitude + ", " + longitude + ")");
        } else {
            latLng = new LatLng(9.977052, 76.317974);
        }

        googleMap.addMarker(new MarkerOptions().position(latLng)
                .title("My Idea"));
        CameraUpdate location = CameraUpdateFactory.newLatLngZoom(latLng, 12);
        mMap.animateCamera(location);
    }
    //endregion

    //region initMap
    private void initMap() {
        try {

            mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
            View mapView = mapFragment.getView();
            if (mapView != null && switchLocation.getVisibility()==View.VISIBLE) {
                if (!switchLocation.isChecked()) {
                    mapView.setVisibility(View.GONE);
                } else {
                    mapView.setVisibility(View.VISIBLE);
                    mapFragment.getMapAsync(this);
                }
            }
            else
            {
                mapView.setVisibility(View.GONE);
            }
        } catch (Exception ex) {
            Log.e("Error", ex.toString());
        }
    }
    //endregion

    //region showSuccessInfo
    private void showSuccessInfo(String message) {
        String title = "Info";
        InfoDialog infoDialog = InfoDialog.newInstance(title, message);
        infoDialog.show(getChildFragmentManager(), SUCCESS_DIALOG);
    }
    //endregion

}
