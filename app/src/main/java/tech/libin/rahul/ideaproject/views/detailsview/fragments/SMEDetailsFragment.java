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
import tech.libin.rahul.ideaproject.views.detailsview.viewmodels.SmeDetailModel;
import tech.libin.rahul.ideaproject.views.detailsview.viewmodels.SmeFormSubmitModel;
import tech.libin.rahul.ideaproject.views.models.ActivityDetailRequestModel;
import tech.libin.rahul.ideaproject.views.utils.GPSTracker;

/**
 * Created by libin on 05/03/17.
 */

public class SMEDetailsFragment extends FOSBaseFragment implements OnMapReadyCallback {
    public static final String DATE_DIALOG = "DATE_DIALOG";
    Spinner spnStatus;
    Spinner spnFeedback;
    Spinner spnReason;
    private EditText editTextReminder;
    private View view;
    private TextView textViewName;
    private TextView textViewMobileNum;
    private TextView textViewBiller;
    private TextView textViewCompanyName;
    private TextView textViewTotAlConnections;
    private TextView textViewFbConnections;
    private TextView textViewMico;
    private TextView textViewMicoCode;
    private TextView textViewMicoName;
    private TextView textViewZone;
    private TextView textViewSegment;
    private TextView textViewBeginningDate;
    private TextView textViewActiveReason;
    private TextView textViewTmCode;
    private TextView textViewRatePlan;
    private TextView textViewCRLimit;
    private TextView textViewAddress;
    private TextView textViewLandLine;
    private TextView textViewLandLineHead;
    private TextView textViewType;
    private TextView textViewTypeHead;
    private EditText editTextRemarks;
    private Button buttonSubmit;
    private LinearLayout linLayoutFeedback;
    private LinearLayout linLayoutReason;
    private LinearLayout linLayoutReminder;
    private GoogleMap mMap;
    private RecyclerView recViewOther;

    private String objectId;
    private String userName;
    private String userPhone;


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

    private void initComponents() {
        textViewName = (TextView) view.findViewById(R.id.textViewName);
        textViewMobileNum = (TextView) view.findViewById(R.id.textViewPhoneNum);
        textViewBiller = (TextView) view.findViewById(R.id.textViewBiller);
        textViewCompanyName = (TextView) view.findViewById(R.id.textViewCompanyName);
        textViewTotAlConnections = (TextView) view.findViewById(R.id.textViewTotalCollection);
        textViewFbConnections = (TextView) view.findViewById(R.id.textViewFbCollection);
        textViewMico = (TextView) view.findViewById(R.id.textViewMico);
        textViewMicoCode = (TextView) view.findViewById(R.id.textViewMicoCode);
        textViewMicoName = (TextView) view.findViewById(R.id.textViewMicoName);
        textViewZone = (TextView) view.findViewById(R.id.textViewZone);
        textViewSegment = (TextView) view.findViewById(R.id.textViewSegment);
        textViewBeginningDate = (TextView) view.findViewById(R.id.textViewBegeningDate);
        textViewActiveReason = (TextView) view.findViewById(R.id.textViewActiveReason);
        textViewTmCode = (TextView) view.findViewById(R.id.textViewTmCode);
        textViewRatePlan = (TextView) view.findViewById(R.id.textViewRatePlan);
        textViewCRLimit = (TextView) view.findViewById(R.id.textViewCrLimit);
        textViewLandLine = (TextView) view.findViewById(R.id.textViewLandLine);
        textViewLandLineHead = (TextView) view.findViewById(R.id.textViewLandLine1);
        textViewType = (TextView) view.findViewById(R.id.textViewType);
        textViewTypeHead = (TextView) view.findViewById(R.id.textViewTypeHead);
        editTextRemarks = (EditText) view.findViewById(R.id.editTextRemarks);
        textViewAddress = (TextView) view.findViewById(R.id.textViewAddress);
        recViewOther = (RecyclerView) view.findViewById(R.id.recViewOther);

        spnStatus = (Spinner) view.findViewById(R.id.spinnerStatus);
        spnReason = (Spinner) view.findViewById(R.id.spinnerReason);
        spnFeedback = (Spinner) view.findViewById(R.id.spinnerFeedBack);

        editTextReminder = (EditText) view.findViewById(R.id.editTextReminderDate);
        editTextRemarks = (EditText) view.findViewById(R.id.editTextRemarks);
        buttonSubmit = (Button) view.findViewById(R.id.buttonSubmit);

        linLayoutFeedback = (LinearLayout) view.findViewById(R.id.linLayoutFeedback);
        linLayoutReason = (LinearLayout) view.findViewById(R.id.linLayoutReason);
        linLayoutReminder = (LinearLayout) view.findViewById(R.id.linLayoutReminder);
    }

    private void parseBundle() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            objectId = bundle.getString(Constants.PARAMS.DETAILS_OBJECT_ID);
            userName = bundle.getString(Constants.PARAMS.DETAILS_OBJECT_NAME);
            userPhone = bundle.getString(Constants.PARAMS.DETAILS_OBJECT_PHONE);

            setHeader();
        }
    }

    private void setHeader() {
        textViewName.setText(userName);
        textViewMobileNum.setText(userPhone);
    }

    private void initMap() {
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

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
    }

    private void submitFormData() {
        if (objectId != null) {
            SmeFormSubmitModel requestModel = new SmeFormSubmitModel();
            requestModel.setObjectId(Long.parseLong(objectId));
            requestModel.setStatus(((SpinnerData) spnStatus.getSelectedItem()).getId());
            requestModel.setRemarks(editTextRemarks.getText().toString().trim());

            GPSTracker gpsTracker = new GPSTracker(getActivity());
            if (gpsTracker.getIsGPSTrackingEnabled()) {
                requestModel.setLongitude(gpsTracker.getLatitude() + "");
                requestModel.setLongitude(gpsTracker.getLongitude() + "");
            } else {
                gpsTracker.showSettingsAlert();
                return;
            }

            requestModel.setLongitude("12.12");
            requestModel.setLatitude("12.12");
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
            fosFacade.doSubmitSmeVisitDetails(requestModel, new ServiceCallback<String>() {
                @Override
                public void onResponse(String response) {
                    Log.e("Submit", response);
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
    }

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
        textViewActiveReason.setText(model.getActReason() == null ? "Nill" : model.getActReason());
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

    private void setLinearLayoutVisible() {
        linLayoutFeedback.setVisibility(View.VISIBLE);
        linLayoutReason.setVisibility(View.VISIBLE);
        linLayoutReason.setVisibility(View.VISIBLE);
    }

    private void setLinearLayoutGone() {
        linLayoutFeedback.setVisibility(View.GONE);
        linLayoutReason.setVisibility(View.GONE);
        linLayoutReason.setVisibility(View.GONE);
    }

    private boolean isValid() {
        if (((SpinnerData) spnStatus.getSelectedItem()).getId() == 0) {
            return false;
        }

        return false;
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
    }
    //endregion

}
