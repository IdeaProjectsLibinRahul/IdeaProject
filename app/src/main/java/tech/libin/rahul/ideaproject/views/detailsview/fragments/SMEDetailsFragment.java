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

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import tech.libin.rahul.ideaproject.R;
import tech.libin.rahul.ideaproject.configurations.Constants;
import tech.libin.rahul.ideaproject.facade.FOSFacade;
import tech.libin.rahul.ideaproject.facade.FOSFacadeImpl;
import tech.libin.rahul.ideaproject.service.handlers.ServiceCallback;
import tech.libin.rahul.ideaproject.service.models.DetailFromSMERoleModel;
import tech.libin.rahul.ideaproject.service.models.SpinnerData;
import tech.libin.rahul.ideaproject.service.responses.base.FOSError;
import tech.libin.rahul.ideaproject.views.basecomponents.FOSBaseFragment;
import tech.libin.rahul.ideaproject.views.detailsview.adapters.FOSSpinnerAdapter;
import tech.libin.rahul.ideaproject.views.detailsview.dialogs.FOSDateDialog;
import tech.libin.rahul.ideaproject.views.detailsview.viewmodels.SmeDetailModel;
import tech.libin.rahul.ideaproject.views.detailsview.viewmodels.FormSubmitModel;
import tech.libin.rahul.ideaproject.views.models.ActivityDetailRequestModel;
import tech.libin.rahul.ideaproject.views.widgets.textview.FOSTextView;

/**
 * Created by libin on 05/03/17.
 */

public class SMEDetailsFragment extends FOSBaseFragment implements OnMapReadyCallback {

    //region declaration
    public static final String DATE_DIALOG = "DATE_DIALOG";
    private Spinner spnStatus;
    private Spinner spnFeedback;
    private Spinner spnReason;

    private LinearLayout linLayoutFeedback;
    private LinearLayout linLayoutReason;
    private LinearLayout linLayoutReminder;

    private View view;

    private EditText editTextReminder;
    private EditText editTextRemarks;

    private FOSTextView textViewName;
    private FOSTextView textViewMobileNum;
    private FOSTextView textViewBiller;
    private FOSTextView textViewCompanyName;
    private FOSTextView textViewTotAlConnections;
    private FOSTextView textViewFbConnections;
    private FOSTextView textViewMico;
    private FOSTextView textViewMicoCode;
    private FOSTextView textViewMicoName;
    private FOSTextView textViewZone;
    private FOSTextView textViewSegment;
    private FOSTextView textViewBeginningDate;
    private FOSTextView textViewActiveReason;
    private FOSTextView textViewTmCode;
    private FOSTextView textViewRatePlan;
    private FOSTextView textViewCRLimit;
    private FOSTextView textViewAddress;
    private FOSTextView textViewLandLine;
    private FOSTextView textViewLandLineHead;
    private FOSTextView textViewType;
    private FOSTextView textViewTypeHead;

    private Button buttonSubmit;

    private Switch switchLocation;
    private Switch switchUpdateLocation;

    private GoogleMap mMap;

    private RecyclerView recViewOther;

    private String objectId;
    private String userName;
    private String userPhone;
    private Constants.ActivityType activityType;
    private SupportMapFragment mapFragment;
    private Marker mMarker;

    //endregion

    //region onCreateView
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_sme_details, container, false);

        initComponents();
        parseBundle();
        loadDetails();
        initMap();

        return view;
    }
    //endregion

    //region initComponents
    private void initComponents() {
        textViewName = (FOSTextView) view.findViewById(R.id.textViewName);
        textViewMobileNum = (FOSTextView) view.findViewById(R.id.textViewPhoneNum);
        textViewBiller = (FOSTextView) view.findViewById(R.id.textViewBiller);
        textViewCompanyName = (FOSTextView) view.findViewById(R.id.textViewCompanyName);
        textViewTotAlConnections = (FOSTextView) view.findViewById(R.id.textViewTotalCollection);
        textViewFbConnections = (FOSTextView) view.findViewById(R.id.textViewFbCollection);
        textViewMico = (FOSTextView) view.findViewById(R.id.textViewMico);
        textViewMicoCode = (FOSTextView) view.findViewById(R.id.textViewMicoCode);
        textViewMicoName = (FOSTextView) view.findViewById(R.id.textViewMicoName);
        textViewZone = (FOSTextView) view.findViewById(R.id.textViewZone);
        textViewSegment = (FOSTextView) view.findViewById(R.id.textViewSegment);
        textViewBeginningDate = (FOSTextView) view.findViewById(R.id.textViewBegeningDate);
        textViewActiveReason = (FOSTextView) view.findViewById(R.id.textViewActiveReason);
        textViewTmCode = (FOSTextView) view.findViewById(R.id.textViewTmCode);
        textViewRatePlan = (FOSTextView) view.findViewById(R.id.textViewRatePlan);
        textViewCRLimit = (FOSTextView) view.findViewById(R.id.textViewCrLimit);
        textViewLandLine = (FOSTextView) view.findViewById(R.id.textViewLandLine);
        textViewLandLineHead = (FOSTextView) view.findViewById(R.id.textViewLandLine1);
        textViewType = (FOSTextView) view.findViewById(R.id.textViewType);
        textViewTypeHead = (FOSTextView) view.findViewById(R.id.textViewTypeHead);
        editTextRemarks = (EditText) view.findViewById(R.id.editTextRemarks);
        textViewAddress = (FOSTextView) view.findViewById(R.id.textViewAddress);
        recViewOther = (RecyclerView) view.findViewById(R.id.recViewOther);

        spnStatus = (Spinner) view.findViewById(R.id.spinnerStatus);
        spnReason = (Spinner) view.findViewById(R.id.spinnerReason);
        spnFeedback = (Spinner) view.findViewById(R.id.spinnerFeedBack);

        switchLocation = (Switch) view.findViewById(R.id.switchLocation);
        switchUpdateLocation = (Switch) view.findViewById(R.id.switchUpdateLocation);

        editTextReminder = (EditText) view.findViewById(R.id.editTextReminderDate);
        editTextRemarks = (EditText) view.findViewById(R.id.editTextRemarks);
        buttonSubmit = (Button) view.findViewById(R.id.buttonSubmit);

        linLayoutFeedback = (LinearLayout) view.findViewById(R.id.linLayoutFeedback);
        linLayoutReason = (LinearLayout) view.findViewById(R.id.linLayoutReason);
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
            activityType = (Constants.ActivityType) bundle.getSerializable(Constants.PARAMS.DETAILS_OBJECT_TAB);

            setHeader();
        }
    }
    //endregion

    //region setHeader
    private void setHeader() {
        textViewName.setText(userName);
        textViewMobileNum.setText(userPhone);
    }
    //endregion

    //region loadDetails
    private void loadDetails() {
        if (objectId != null) {
            ActivityDetailRequestModel requestModel = new ActivityDetailRequestModel();
            requestModel.setObjectId(objectId);
            requestModel.setRecordType(Constants.RecordType.SME);

            FOSFacade fosFacade = new FOSFacadeImpl();
            fosFacade.getSmeDetail(requestModel, new ServiceCallback<SmeDetailModel>() {
                @Override
                public void onResponse(SmeDetailModel response) {

                    bindDetails(response);
                    setFomListeners();
                    setLinearLayoutGone();
                }

                @Override
                public void onRequestTimout() {
                    toastMessage("Request timeout");
                }

                @Override
                public void onRequestFail(FOSError error) {
                    toastMessage("Request failed - " + error.getErrorMessage());
                }
            });
        }
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
        if (objectId != null) {
            FormSubmitModel requestModel = new FormSubmitModel();
            requestModel.setObjectId(Long.parseLong(objectId));
            requestModel.setStatus(((SpinnerData) spnStatus.getSelectedItem()).getId());
            requestModel.setRemarks(editTextRemarks.getText().toString().trim());

//            GPSTracker gpsTracker = new GPSTracker(getActivity());
//
//            if (gpsTracker.getIsGPSTrackingEnabled()) {
//                requestModel.setLatitude(gpsTracker.getLatitude() + "");
//                requestModel.setLongitude(gpsTracker.getLongitude() + "");
//            } else {
//                gpsTracker.showSettingsAlert();
//                return;
//            }

            requestModel.setLongitude("9.977052");
            requestModel.setLatitude("76.317974");

            if (linLayoutFeedback.getVisibility() == View.VISIBLE) {
                requestModel.setFeedback(((SpinnerData) spnFeedback.getSelectedItem()).getId());
            }
            if (linLayoutReason.getVisibility() == View.VISIBLE) {
                requestModel.setReason(((SpinnerData) spnReason.getSelectedItem()).getId());
            }
            requestModel.setReminder("");
            if (linLayoutReminder.getVisibility() == View.VISIBLE) {
                requestModel.setReminder(editTextReminder.getText().toString().trim());
            }
            requestModel.setRecordType(Constants.RecordType.SME);

            FOSFacade fosFacade = new FOSFacadeImpl();
            fosFacade.doSubmitVisitDetails(requestModel, new ServiceCallback<String>() {
                @Override
                public void onResponse(String response) {
                    Log.e("Submit", response);
                    getActivity().getSupportFragmentManager().popBackStack();
                }

                @Override
                public void onRequestTimout() {

                }

                @Override
                public void onRequestFail(FOSError error) {
                    Log.e("Submit Fail", error.getErrorMessage());
                    getActivity().getSupportFragmentManager().popBackStack();

                }
            });
        }
    }
    //endregion

    //region bindDetails
    private void bindDetails(SmeDetailModel model) {
        textViewName.setText(model.getCcName());
        textViewMobileNum.setVisibility(View.GONE);
        textViewBiller.setText(model.getBiller());
        textViewCompanyName.setText(model.getCcName());
        textViewTotAlConnections.setText(model.getTotalConnection());
        textViewFbConnections.setText(model.getFBConnection());
        textViewMico.setText(model.getMico());
        textViewMicoCode.setText(model.getMicoCode());
        textViewMicoName.setText(model.getMiName());
        textViewZone.setText(model.getZone());
        textViewSegment.setText(model.getSegment());
        textViewBeginningDate.setText(model.getBegdate());
        textViewActiveReason.setText(model.getActReason());
        textViewTmCode.setText(model.getTmcode());
        textViewRatePlan.setText(model.getRatePlan());
        textViewCRLimit.setText(model.getCr_limit());
        textViewLandLine.setText(model.getLandLine2());
        if (model.getLandLine2() == null) {
            textViewLandLine.setVisibility(view.GONE);
            textViewLandLineHead.setVisibility(view.GONE);
        }
        if (model.getType() == null || model.getType().isEmpty()) {
            textViewTypeHead.setVisibility(view.GONE);
            textViewType.setVisibility(view.GONE);
        }
        textViewAddress.setText(model.getBill1() + "\n" + model.getBill2() + "\n" + model.getBill3() + "\n" + model.getBill4() + "\n" + model.getBill5());

        setLinearLayoutVisible();
        final List visitStatus = model.getVisitStatus();
        final List visitFeedback = model.getFeedback();

        FOSSpinnerAdapter statusAdapter = new FOSSpinnerAdapter(getActivity(), android.R.layout.simple_spinner_item, model.getVisitStatus());
        spnStatus.setAdapter(statusAdapter);

        FOSSpinnerAdapter reasonAdapter = new FOSSpinnerAdapter(getActivity(), android.R.layout.simple_spinner_item, model.getReason());
        spnReason.setAdapter(reasonAdapter);

        FOSSpinnerAdapter feedbackAdapter = new FOSSpinnerAdapter(getActivity(), android.R.layout.simple_spinner_item, model.getFeedback());
        spnFeedback.setAdapter(feedbackAdapter);


        if(activityType!=Constants.ActivityType.NEW_ACTIVITY) {
            DetailFromSMERoleModel fromExecutive = model.getFromExecutive();
//bind data to submit from
            if (fromExecutive != null) {
                if (fromExecutive.getVisitStatus() != 0)
                    spnStatus.setSelection(findSpinnerElementPosition(fromExecutive.getVisitStatus(), visitStatus));

                if (fromExecutive.getFeedback() != 0) {
                    linLayoutFeedback.setVisibility(View.VISIBLE);
                    spnFeedback.setSelection(findSpinnerElementPosition(fromExecutive.getFeedback(), visitFeedback));
                }

                if (fromExecutive.getReason() != 0) {
                    linLayoutReason.setVisibility(View.VISIBLE);
                    spnFeedback.setSelection(findSpinnerElementPosition(fromExecutive.getReason(), model.getReason()));
                }

                editTextRemarks.setText(fromExecutive.getRemarks());
            }
        }

        spnStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (((SpinnerData) visitStatus.get(position)).getId() == 2) {
                    linLayoutReminder.setVisibility(View.VISIBLE);
                    linLayoutFeedback.setVisibility(View.GONE);
                    linLayoutReason.setVisibility(View.GONE);

                } else {
                    linLayoutReminder.setVisibility(View.GONE);
                    linLayoutFeedback.setVisibility(View.VISIBLE);
                    linLayoutReason.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spnFeedback.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (((SpinnerData) visitFeedback.get(position)).getId() != 5) {
                    linLayoutReason.setVisibility(View.VISIBLE);
                } else {
                    linLayoutReason.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    //endregion

    //region findSpinnerElementPosition
    private int findSpinnerElementPosition(int value, List<SpinnerData> spinnerData) {
        int position = 0;
        for (SpinnerData spnData : spinnerData
                ) {
            if (spnData.getId() == value)
                return position;
            position++;
        }
        return 0;
    }
    //endregion

    //region setLinearLayoutVisible
    private void setLinearLayoutVisible() {
        linLayoutFeedback.setVisibility(View.VISIBLE);
        linLayoutReason.setVisibility(View.VISIBLE);
        linLayoutReason.setVisibility(View.VISIBLE);
    }
    //endregion

    //region setLinearLayoutGone
    private void setLinearLayoutGone() {
        linLayoutFeedback.setVisibility(View.GONE);
        linLayoutReason.setVisibility(View.GONE);
        linLayoutReason.setVisibility(View.GONE);
    }
    //endregion

    //region isValid
    private boolean isValid() {
        if (((SpinnerData) spnStatus.getSelectedItem()).getId() == 0) {
            return false;
        }

        return false;
    }
    //endregion

    //region onMapReady
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng sydney = new LatLng(9.977052, 76.317974);
        googleMap.addMarker(new MarkerOptions().position(sydney)
                .title("My Idea"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
    //endregion


    //region initMap
    private void initMap() {
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
    }
    //endregion

}
