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
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

import java.util.List;

import tech.libin.rahul.ideaproject.R;
import tech.libin.rahul.ideaproject.configurations.Constants;
import tech.libin.rahul.ideaproject.facade.FOSFacade;
import tech.libin.rahul.ideaproject.facade.FOSFacadeImpl;
import tech.libin.rahul.ideaproject.service.handlers.ServiceCallback;
import tech.libin.rahul.ideaproject.service.models.SpinnerData;
import tech.libin.rahul.ideaproject.service.responses.base.FOSError;
import tech.libin.rahul.ideaproject.views.basecomponents.FOSBaseFragment;
import tech.libin.rahul.ideaproject.views.detailsview.adapters.FOSSpinnerAdapter;
import tech.libin.rahul.ideaproject.views.detailsview.dialogs.FOSDateDialog;
import tech.libin.rahul.ideaproject.views.detailsview.viewmodels.SmeFormSubmitModel;
import tech.libin.rahul.ideaproject.views.detailsview.viewmodels.UpcDetailModel;
import tech.libin.rahul.ideaproject.views.models.ActivityDetailRequestModel;
import tech.libin.rahul.ideaproject.views.utils.GPSTracker;

import static tech.libin.rahul.ideaproject.views.detailsview.fragments.SMEDetailsFragment.DATE_DIALOG;

/**
 * Created by libin on 06/03/17.
 */

public class UPCDetailsFragment extends FOSBaseFragment implements OnMapReadyCallback {
    TextView textViewCustNum;
    TextView textViewMobile;
    TextView textViewUpc;
    TextView textViewSubscriberType;
    TextView textViewCreatedDateTime;
    TextView textViewSegment;
    TextView textViewCustomerSubsType;
    TextView textViewCsCreditCode;
    TextView textViewCustomerType;
    TextView textViewAlternateNumber;
    TextView textViewServSeg;
    Spinner spnStatus;
    Constants.ActivityType tab;
    private View view;
    private EditText editTextRemarks;
    private TextView textViewAddress;
    private Button buttonSubmit;
    private LinearLayout linLayoutReminder;
    private GoogleMap mMap;
    private RecyclerView recViewOther;
    private EditText editTextReminder;
    private String objectId;
    private String userName;
    private String userPhone;
    private List visitStatus;
    private Constants.ActivityType activityType;

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

    //region initComponents
    private void initComponents() {

        textViewCustNum = (TextView) view.findViewById(R.id.textViewName);
        textViewMobile = (TextView) view.findViewById(R.id.textViewPhoneNum);
        textViewUpc = (TextView) view.findViewById(R.id.textViewUpc);

        textViewSubscriberType = (TextView) view.findViewById(R.id.textViewSubType);
        textViewCreatedDateTime = (TextView) view.findViewById(R.id.textViewBegeningDate);
        textViewSegment = (TextView) view.findViewById(R.id.textViewSegment);

        textViewCustomerSubsType = (TextView) view.findViewById(R.id.textViewCustSubType);
        textViewCsCreditCode = (TextView) view.findViewById(R.id.textViewCsCreditCode);
        textViewCustomerType = (TextView) view.findViewById(R.id.textViewCustomerType);
        textViewAlternateNumber = (TextView) view.findViewById(R.id.textViewAlternateNum);
        textViewServSeg = (TextView) view.findViewById(R.id.textViewServSeg);

        editTextRemarks = (EditText) view.findViewById(R.id.editTextRemarks);
        textViewAddress = (TextView) view.findViewById(R.id.textViewAddress);
        recViewOther = (RecyclerView) view.findViewById(R.id.recViewOther);

        spnStatus = (Spinner) view.findViewById(R.id.spinnerStatus);
        editTextReminder = (EditText) view.findViewById(R.id.editTextReminderDate);
        editTextRemarks = (EditText) view.findViewById(R.id.editTextRemarks);
        buttonSubmit = (Button) view.findViewById(R.id.buttonSubmit);
        linLayoutReminder = (LinearLayout) view.findViewById(R.id.linLayoutReminder);
    }
    //endregion

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

        //bind data to submit from
//        DetailFromUPCRoleModel fromExecutive = response.getFromExecutive();
//        if (fromExecutive != null) {
//            spnStatus.setSelection(findSpinnerElementPosition(fromExecutive.getStatus(), visitStatus));
//            editTextRemarks.setText(fromExecutive.getRemarks());
//        }

    }

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

        SmeFormSubmitModel requestModel = new SmeFormSubmitModel();
        //OtherFormSubmitModel requestModel = new OtherFormSubmitModel();
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

        requestModel.setLongitude("12.12");
        requestModel.setLatitude("12.12");
        requestModel.setReminder("");
        requestModel.setFeedback(0);
        requestModel.setReason(0);

        if (linLayoutReminder.getVisibility() == View.VISIBLE) {
            requestModel.setReminder(editTextReminder.getText().toString().trim());
        }
        requestModel.setRecordType(Constants.RecordType.UPC);

        FOSFacade fosFacade = new FOSFacadeImpl();
        fosFacade.doSubmitSmeVisitDetails(requestModel, new ServiceCallback<String>() {
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

            }
        });
    }
    //endregion

    //region onMapReady
    @Override
    public void onMapReady(GoogleMap googleMap) {

    }
    //endregion

    //region initMap
    private void initMap() {
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }
    //endregion

}
