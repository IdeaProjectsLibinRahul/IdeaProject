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
import tech.libin.rahul.ideaproject.views.detailsview.viewmodels.TdDetailModel;
import tech.libin.rahul.ideaproject.views.models.ActivityDetailRequestModel;
import tech.libin.rahul.ideaproject.views.utils.GPSTracker;
import tech.libin.rahul.ideaproject.views.widgets.textview.FOSTextView;

import static tech.libin.rahul.ideaproject.views.detailsview.fragments.SMEDetailsFragment.DATE_DIALOG;

/**
 * Created by libin on 05/03/17.
 */

public class TDDetailsFragment extends FOSBaseFragment implements OnMapReadyCallback {

    public static final String SUCCESS_DIALOG = "SUCCESS_DIALOG";
    private static final String TAG = TDDetailsFragment.class.getName();

    //region declarations
    Spinner spnStatus;
    private EditText editTextRemarks;
    private EditText editTextReminder;
    private FOSTextView textViewCustNum;
    private FOSTextView textViewMobile;
    private FOSTextView textViewBiller;
    private FOSTextView textViewSer;
    private FOSTextView textViewCurBalance;
    private FOSTextView textViewSegment;
    private FOSTextView textViewBucket;
    private FOSTextView textViewType;
    private FOSTextView textViewCustomerType;
    private FOSTextView textViewCrmStatus;
    private FOSTextView textViewMyIdeaCode;
    private FOSTextView textViewMyIdeaAllocation;
    private FOSTextView textViewActivationMi;
    private FOSTextView textViewLandline1;
    private FOSTextView textViewLandLine2;
    private FOSTextView textViewAddress;
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

    //endregion

    //region onCreateView
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_activity_details, container, false);

        initComponents();
        parseBundle();
        loadTdDetails();
        initMap();

        return view;
    }
    //endregion

    //region initComponents
    private void initComponents() {
        gpsTracker = new GPSTracker(getActivity());

        textViewCustNum = (FOSTextView) view.findViewById(R.id.textViewName);
        textViewMobile = (FOSTextView) view.findViewById(R.id.textViewPhoneNum);

        textViewBiller = (FOSTextView) view.findViewById(R.id.textViewBiller);
        textViewSer = (FOSTextView) view.findViewById(R.id.textViewSer);
        textViewCurBalance = (FOSTextView) view.findViewById(R.id.textViewDueAmount);
        textViewSegment = (FOSTextView) view.findViewById(R.id.textViewSegment);
        textViewBucket = (FOSTextView) view.findViewById(R.id.textViewBucket);
        textViewType = (FOSTextView) view.findViewById(R.id.textViewType);
        textViewCustomerType = (FOSTextView) view.findViewById(R.id.textViewCustomerType);
        textViewCrmStatus = (FOSTextView) view.findViewById(R.id.textViewCrmStatus);
        textViewMyIdeaCode = (FOSTextView) view.findViewById(R.id.textViewMyIdeaCode);
        textViewMyIdeaAllocation = (FOSTextView) view.findViewById(R.id.textVieMyIdeaLocation);
        textViewActivationMi = (FOSTextView) view.findViewById(R.id.textViewActivationMi);
        textViewLandline1 = (FOSTextView) view.findViewById(R.id.textViewLandLine1);
        textViewLandLine2 = (FOSTextView) view.findViewById(R.id.textViewLandLine2);

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

    //region loadTdDetails
    private void loadTdDetails() {
        if (objectId != null) {
            ActivityDetailRequestModel requestModel = new ActivityDetailRequestModel();
            requestModel.setObjectId(objectId);
            requestModel.setRecordType(Constants.RecordType.TD);

            FOSFacade fosFacade = new FOSFacadeImpl();
            fosFacade.getTdDetail(requestModel, new ServiceCallback<TdDetailModel>() {
                @Override
                public void onResponse(TdDetailModel response) {
                    Log.e("TDDETAILS", response.toString());
                    bindDetails(response);
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

    //region bindDetails
    private void bindDetails(TdDetailModel model) {
        textViewCustNum.setText(model.getBill2());
        textViewMobile.setText(model.getMobile());
        textViewBiller.setText(model.getBiller());
        textViewSer.setText(model.getSer());
        textViewCurBalance.setText(model.getCurBalance());
        textViewSegment.setText(model.getSegment());
        textViewBucket.setText(model.getBucket());
        textViewType.setText(model.getType());
        textViewCustomerType.setText(model.getCustomerType());
        textViewCrmStatus.setText(model.getCrmStatus());
        textViewMyIdeaCode.setText(model.getMyIdeaCode());
        textViewMyIdeaAllocation.setText(model.getMyIdeaAllocation());
        textViewActivationMi.setText(model.getActivatiomMi());
        textViewActivationMi.setText("Nill");
        textViewLandline1.setText(model.getLandLine1());
        if (textViewLandline1.getText().toString().trim().isEmpty())
            textViewLandline1.setText("Nilll");
//        textViewLandLine2.setText(model.getLandLine2());
        textViewLandLine2.setText("Nilll");

        textViewAddress.setText(model.getBill1() + "\n" + model.getBill2() + "\n" + model.getBill3() + "\n" + model.getBill4() + "\n" + model.getBill5() + "\n" + model.getZip());

        final List<SpinnerData> visitStatus = model.getVisitStatus();

        FOSSpinnerAdapter statusAdapter = new FOSSpinnerAdapter(getActivity(), android.R.layout.simple_spinner_item, model.getVisitStatus());
        spnStatus.setAdapter(statusAdapter);

        if (activityType != Constants.ActivityType.NEW_ACTIVITY) {
            DetailFromUPCRoleModel fromExecutive = model.getFromExecutive();
            if (fromExecutive != null) {
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
    }
    //endregion

    //region findSpinnerElementPosition
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
                            mapFragment.getMapAsync(TDDetailsFragment.this);
                        }
                    }
                }

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

            GPSTracker gpsTracker = new GPSTracker(getActivity());

            if (gpsTracker.getIsGPSTrackingEnabled()) {
                requestModel.setLatitude(gpsTracker.getLatitude() + "");
                requestModel.setLongitude(gpsTracker.getLongitude() + "");
            } else {
                gpsTracker.showSettingsAlert();
                return;
            }

            requestModel.setReminder("");
            requestModel.setReason(0);
            requestModel.setFeedback(0);

            if (linLayoutReminder.getVisibility() == View.VISIBLE) {
                requestModel.setReminder(editTextReminder.getText().toString().trim());
            }
            requestModel.setRecordType(Constants.RecordType.TD);

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
    }
    //endregion

    private void showSuccessInfo() {
        String message = "Form submitted successfully";
        String title = "Info";

        InfoDialog infoDialog = InfoDialog.newInstance(title, message);
        infoDialog.show(getChildFragmentManager(), SUCCESS_DIALOG);
    }

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