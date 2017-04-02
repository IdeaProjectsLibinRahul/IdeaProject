package tech.libin.rahul.ideaproject.views.detailsview.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
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
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Switch;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import tech.libin.rahul.ideaproject.R;
import tech.libin.rahul.ideaproject.configurations.Config;
import tech.libin.rahul.ideaproject.configurations.Constants;
import tech.libin.rahul.ideaproject.facade.FOSFacade;
import tech.libin.rahul.ideaproject.facade.FOSFacadeImpl;
import tech.libin.rahul.ideaproject.service.handlers.ServiceCallback;
import tech.libin.rahul.ideaproject.service.models.DetailFromSMERoleModel;
import tech.libin.rahul.ideaproject.service.models.DetailFromUPCRoleModel;
import tech.libin.rahul.ideaproject.service.models.SpinnerData;
import tech.libin.rahul.ideaproject.service.responses.base.FOSError;
import tech.libin.rahul.ideaproject.views.basecomponents.FOSBaseFragment;
import tech.libin.rahul.ideaproject.views.detailsview.adapters.FOSSpinnerAdapter;
import tech.libin.rahul.ideaproject.views.detailsview.dialogs.FOSDateDialog;
import tech.libin.rahul.ideaproject.views.detailsview.dialogs.InfoDialog;
import tech.libin.rahul.ideaproject.views.detailsview.viewmodels.FormSubmitModel;
import tech.libin.rahul.ideaproject.views.detailsview.viewmodels.SmeDetailModel;
import tech.libin.rahul.ideaproject.views.models.ActivityDetailRequestModel;
import tech.libin.rahul.ideaproject.views.utils.GPSTracker;
import tech.libin.rahul.ideaproject.views.widgets.textview.FOSTextView;

/**
 * Created by libin on 05/03/17.
 */

public class SMEDetailsFragment extends FOSBaseFragment implements OnMapReadyCallback {

    //region declaration
    public static final String DATE_DIALOG = "DATE_DIALOG";
    public static final String SUCCESS_DIALOG = "SUCCESS_DIALOG";
    private static final String TAG = SMEDetailsFragment.class.getName();
    private Spinner spnStatus;
    private Spinner spnReason;
    ScrollView scrollViewDetails;
    ProgressBar progressBarLoading;
    private EditText editTextLandmark;

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
    private FOSTextView textViewBeginningDate;
    private FOSTextView textViewActiveReason;
    private FOSTextView textViewRatePlan;
    private FOSTextView textViewCRLimit;
    private FOSTextView textViewAddress;
    private FOSTextView textViewLandLine;
    private FOSTextView textViewLandLineHead;
    private FOSTextView textViewType;
    private FOSTextView textViewTypeHead;
    private FOSTextView textViewFbConnections;
    private Button buttonSubmit;
    private Switch switchLocation;
    private Switch switchUpdateLocation;
    private GoogleMap mMap;
    private RecyclerView recViewOther;
    private String objectId;
    private Constants.ActivityType activityType;
    private SupportMapFragment mapFragment;
    private Marker mMarker;
    private GPSTracker gpsTracker;
    private SmeDetailModel detailModel;
    private RatingBar ratingBar;
    private FOSTextView textViewRatingInfo;
    private FOSSpinnerAdapter statusAdapter;
    private FOSSpinnerAdapter reasonAdapter;



    private FOSTextView textViewFromZsmName;
    private FOSTextView textViewFromZsmMobileNum;
    private FOSTextView textViewFromZsmVisitStatus;
    private FOSTextView textViewFromZsmVisitedDate;
    private FOSTextView textViewFromZsmVisitFeedback;
    private FOSTextView textViewFromZsmAmountCollected;
    private FOSTextView textViewZsmRemarks;
    private FOSTextView textViewFromZsmEscalateNoVisit;
    private FOSTextView textViewFromZsmAmountCollectedTitle;

    private FOSTextView textViewFromMicoName;
    private FOSTextView textViewFromMicoMobileNum;
    private FOSTextView textViewFromMicoVisitStatus;
    private FOSTextView textViewFromMicoVisitedDate;
    private FOSTextView textViewFromMicoVisitFeedback;
    private FOSTextView textViewFromMicoAmountCollected;
    private FOSTextView textViewMicoRemarks;
    private FOSTextView textViewFromMicoEscalateNoVisit;
    private FOSTextView textViewFromMicoAmountCollectedTitle;

    private FOSTextView textViewFromExeName;
    private FOSTextView textViewFromExeMobileNum;
    private FOSTextView textViewFromExeMyIdea;
    private FOSTextView textViewFromExeMyIdeaCode;
    private FOSTextView textViewFromExeVisitStatus;
    private FOSTextView textViewFromExeVisitedDate;
    private FOSTextView textViewFromExeVisitFeedback;
    private FOSTextView textViewFromExeAmountCollected;
    private FOSTextView textViewExeRemarks;
    private FOSTextView textViewFromExeEscalateNoVisit;
    private FOSTextView textViewFromExeAmountCollectedTitle;

    LinearLayout llFromMicoVisitDetails;
    LinearLayout llFromZsmVisitDetails;
    LinearLayout llFromExeVisitDetails;

    CardView cardViewFromExe;
    CardView cardViewFromMico;
    CardView cardViewFromZsm;
    CardView cardViewFromSubmit;

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
        gpsTracker = new GPSTracker(getActivity());

        textViewName = (FOSTextView) view.findViewById(R.id.textViewName);
        textViewMobileNum = (FOSTextView) view.findViewById(R.id.textViewPhoneNum);
        textViewBiller = (FOSTextView) view.findViewById(R.id.textViewBiller);
        textViewCompanyName = (FOSTextView) view.findViewById(R.id.textViewCompanyName);
        textViewTotAlConnections = (FOSTextView) view.findViewById(R.id.textViewTotalCollection);
        textViewFbConnections = (FOSTextView) view.findViewById(R.id.textViewFbCollection);
        textViewBeginningDate = (FOSTextView) view.findViewById(R.id.textViewBegeningDate);
        textViewActiveReason = (FOSTextView) view.findViewById(R.id.textViewActiveReason);
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

        switchLocation = (Switch) view.findViewById(R.id.switchLocation);
        switchUpdateLocation = (Switch) view.findViewById(R.id.switchUpdateLocation);

        editTextReminder = (EditText) view.findViewById(R.id.editTextReminderDate);
        editTextRemarks = (EditText) view.findViewById(R.id.editTextRemarks);
        buttonSubmit = (Button) view.findViewById(R.id.buttonSubmit);

        linLayoutFeedback = (LinearLayout) view.findViewById(R.id.linLayoutFeedback);
        linLayoutReason = (LinearLayout) view.findViewById(R.id.linLayoutReason);
        linLayoutReminder = (LinearLayout) view.findViewById(R.id.linLayoutReminder);

        ratingBar = (RatingBar) view.findViewById(R.id.ratingBar);
        textViewRatingInfo = (FOSTextView) view.findViewById(R.id.ratingInfo);

        editTextLandmark = (EditText) view.findViewById(R.id.editTextLandmark);


        //role wise

        textViewFromExeName = (FOSTextView) view.findViewById(R.id.textViewFromExeName);
        textViewFromExeMobileNum = (FOSTextView) view.findViewById(R.id.textViewFromExeMobileNum);
        textViewFromExeMyIdea = (FOSTextView) view.findViewById(R.id.textViewFromExeMyIdea);
        textViewFromExeMyIdeaCode = (FOSTextView) view.findViewById(R.id.textViewFromExeMyIdeaCode);
        textViewFromExeVisitStatus = (FOSTextView) view.findViewById(R.id.textViewFromExeVisitStatus);
        textViewFromExeVisitedDate = (FOSTextView) view.findViewById(R.id.textViewFromExeVisitedDate);
        textViewFromExeVisitFeedback = (FOSTextView) view.findViewById(R.id.textViewFromExeVisitFeedback);
        textViewFromExeAmountCollected = (FOSTextView) view.findViewById(R.id.textViewFromExeAmountCollected);
        textViewExeRemarks = (FOSTextView) view.findViewById(R.id.textViewFromExeRemarks);
        textViewFromExeEscalateNoVisit = (FOSTextView) view.findViewById(R.id.textViewFromExeEscalateNoVisit);
        textViewFromExeAmountCollectedTitle = (FOSTextView) view.findViewById(R.id.textViewFromExeAmountCollectedTitle);

        textViewFromMicoName = (FOSTextView) view.findViewById(R.id.textViewFromMicoName);
        textViewFromMicoMobileNum = (FOSTextView) view.findViewById(R.id.textViewFromMicoMobileNum);
        textViewFromMicoVisitStatus = (FOSTextView) view.findViewById(R.id.textViewFromMicoVisitStatus);
        textViewFromMicoVisitedDate = (FOSTextView) view.findViewById(R.id.textViewFromMicoVisitedDate);
        textViewFromMicoVisitFeedback = (FOSTextView) view.findViewById(R.id.textViewFromMicoVisitFeedback);
        textViewFromMicoAmountCollected = (FOSTextView) view.findViewById(R.id.textViewFromMicoAmountCollected);
        textViewMicoRemarks = (FOSTextView) view.findViewById(R.id.textViewFromMicoRemarks);
        textViewFromMicoEscalateNoVisit = (FOSTextView) view.findViewById(R.id.textViewFromMicoEscalateNoVisit);
        textViewFromMicoAmountCollectedTitle = (FOSTextView) view.findViewById(R.id.textViewFromMicoAmountCollectedTitle);

        textViewFromZsmName = (FOSTextView) view.findViewById(R.id.textViewFromZsmName);
        textViewFromZsmMobileNum = (FOSTextView) view.findViewById(R.id.textViewFromZsmMobileNum);
        textViewFromZsmVisitStatus = (FOSTextView) view.findViewById(R.id.textViewFromZsmVisitStatus);
        textViewFromZsmVisitedDate = (FOSTextView) view.findViewById(R.id.textViewFromZsmVisitedDate);
        textViewFromZsmVisitFeedback = (FOSTextView) view.findViewById(R.id.textViewFromZsmVisitFeedback);
        textViewFromZsmAmountCollected = (FOSTextView) view.findViewById(R.id.textViewFromZsmAmountCollected);
        textViewZsmRemarks = (FOSTextView) view.findViewById(R.id.textViewFromZsmRemarks);
        textViewFromZsmEscalateNoVisit = (FOSTextView) view.findViewById(R.id.textViewFromZsmEscalateNoVisit);
        textViewFromZsmAmountCollectedTitle = (FOSTextView) view.findViewById(R.id.textViewFromZsmAmountCollectedTitle);

        llFromMicoVisitDetails = (LinearLayout) view.findViewById(R.id.llFromMicoVisitDetails);
        llFromZsmVisitDetails = (LinearLayout) view.findViewById(R.id.llFromZsmVisitDetails);
        llFromExeVisitDetails = (LinearLayout) view.findViewById(R.id.llFromExeVisitDetails);

        cardViewFromExe = (CardView) view.findViewById(R.id.cardViewFromExe);
        cardViewFromMico = (CardView) view.findViewById(R.id.cardViewFromMico);
        cardViewFromZsm = (CardView) view.findViewById(R.id.cardViewFromZsm);
        cardViewFromSubmit = (CardView) view.findViewById(R.id.cardViewFormSubmit);

        scrollViewDetails = (ScrollView) view.findViewById(R.id.scrollViewDetails);
        progressBarLoading = (ProgressBar) view.findViewById(R.id.progressBarLoading);



    }
    //endregion

    //region parseBundle
    private void parseBundle() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            objectId = bundle.getString(Constants.PARAMS.DETAILS_OBJECT_ID);
            activityType = (Constants.ActivityType) bundle.getSerializable(Constants.PARAMS.DETAILS_OBJECT_TAB);
        }
    }
    //endregion

    //region loadDetails
    private void loadDetails() {
        if (objectId != null) {
            ActivityDetailRequestModel requestModel = new ActivityDetailRequestModel();
            requestModel.setObjectId(objectId);
            requestModel.setRecordType(Constants.RecordType.SME);
            showProgressBar();

            FOSFacade fosFacade = new FOSFacadeImpl();
            fosFacade.getSmeDetail(requestModel, new ServiceCallback<SmeDetailModel>() {
                @Override
                public void onResponse(SmeDetailModel response) {

                    hideProgressBar();
                    detailModel = response;
                    bindDetails(response);
                    setFomListeners();
                }

                @Override
                public void onRequestTimout() {
                    showMessage(getString(R.string.warn_time_out_title), getString(R.string.warn_time_out_message));
                }

                @Override
                public void onRequestFail(FOSError error) {
                    showMessage(getString(R.string.warn_server_error), error.getErrorMessage());
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
                            mapFragment.getMapAsync(SMEDetailsFragment.this);
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
                if ((detailModel.getVisitStatus().get(position)).getId() == Constants.VisitStatus.FOLLOW_UP.getValue()) {
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

            GPSTracker gpsTracker = new GPSTracker(getActivity());
            if (switchUpdateLocation.isChecked()) {
                if (gpsTracker.getIsGPSTrackingEnabled()) {
                    requestModel.setLatitude(gpsTracker.getLatitude() + "");
                    requestModel.setLongitude(gpsTracker.getLongitude() + "");
                } else {
                    gpsTracker.showSettingsAlert();
                    return;
                }
            }
            requestModel.setAmountPaid("0");
            if (linLayoutFeedback.getVisibility() == View.VISIBLE) {
                int feedback = (int) ratingBar.getRating();
                if (feedback > 0) {
                    SpinnerData data = detailModel.getFeedback().get(feedback - 1);
                    requestModel.setFeedback(data.getId());
                }
            }
            if (linLayoutReason.getVisibility() == View.VISIBLE) {
                requestModel.setReason(((SpinnerData) spnReason.getSelectedItem()).getId());
            }
            requestModel.setReminder("");
            if (linLayoutReminder.getVisibility() == View.VISIBLE) {
                requestModel.setReminder(editTextReminder.getText().toString().trim());
            }
            requestModel.setRecordType(Constants.RecordType.SME);

            final ProgressDialog dialog = ProgressDialog.show(getActivity(), null, getResources().getString(R.string.requesting), true, true);
            FOSFacade fosFacade = new FOSFacadeImpl();
            fosFacade.doSubmitVisitDetails(requestModel, new ServiceCallback<String>() {
                @Override
                public void onResponse(String response) {
                    if (dialog != null) {
                        dialog.cancel();
                    }
                    showSuccessInfo(response);
                }

                @Override
                public void onRequestTimout() {
                    if (dialog != null) {
                        dialog.cancel();
                    }
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

    //region showSuccessInfo
    private void showSuccessInfo(String message) {
        String title = "Info";
        InfoDialog infoDialog = InfoDialog.newInstance(title, message);
        infoDialog.show(getChildFragmentManager(), SUCCESS_DIALOG);
    }
    //endregion

    //region bindDetails
    private void bindDetails(final SmeDetailModel model) {
        textViewName.setText(model.getCcName());
        textViewMobileNum.setVisibility(View.GONE);
        textViewBiller.setText(model.getBiller());
        textViewCompanyName.setText(model.getCcName());
        textViewTotAlConnections.setText(model.getTotalConnection());
        textViewBeginningDate.setText(model.getBegdate());
        textViewActiveReason.setText(model.getActReason());
        textViewRatePlan.setText(model.getRatePlan());
        textViewCRLimit.setText(model.getCr_limit());
        textViewLandLine.setText(model.getLandLine2());

//        if (model.getType() == null || model.getType().isEmpty()) {
//            textViewTypeHead.setVisibility(view.GONE);
//            textViewType.setVisibility(view.GONE);
//        }
        textViewAddress.setText(model.getBill1() + "\n" + model.getBill2() + "\n" + model.getBill3() + "\n" + model.getBill4() + "\n" + model.getBill5());

        setLinearLayoutVisible();
        statusAdapter = new FOSSpinnerAdapter(getActivity(), android.R.layout.simple_spinner_item, model.getVisitStatus());
        spnStatus.setAdapter(statusAdapter);

        reasonAdapter = new FOSSpinnerAdapter(getActivity(), android.R.layout.simple_spinner_item, model.getReason());
        spnReason.setAdapter(reasonAdapter);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float value, boolean b) {
                ArrayList<SpinnerData> feedback = model.getFeedback();
                for (SpinnerData data : feedback) {
                    if (data.getId() == (int) value) {
                        if (data.getId() != 5) {
                            linLayoutReason.setVisibility(View.VISIBLE);
                        } else {
                            linLayoutReason.setVisibility(View.GONE);
                        }
                        String text = data.getValue();
                        textViewRatingInfo.setText(text);
                        textViewRatingInfo.setVisibility(View.VISIBLE);
                    }
                }
            }
        });

        ratingBar.setRating(1);
        if (model.getLocation() != null && model.getLocation().getLatitude() != null && !model.getLocation().getLatitude().isEmpty()) {
            switchLocation.setVisibility(View.VISIBLE);
        }

//        //load executive details if user is executive
//        //need not to be load from executive if the record is new activity
//        if ((Config.getInstance().getUser().getRole() == Constants.Role.EXECUTIVE) && (activityType != Constants.ActivityType.NEW_ACTIVITY)) {
//            loadExecutiveOwnData();
//        }

    }
    //endregion


    //region loadReminderData
    private void loadReminderData(DetailFromSMERoleModel reminderData)
    {
        //bind data to submit from
        if (reminderData != null) {
            if (reminderData.getVisitStatus() != 0) {
                int position = statusAdapter.findElementPosition(reminderData.getVisitStatus());
                spnStatus.setSelection(position);
            }

            if (reminderData.getFeedback() != 0 && ((SpinnerData) spnStatus.getSelectedItem()).getId() == 1) {
                linLayoutFeedback.setVisibility(View.VISIBLE);
                ratingBar.setRating(reminderData.getFeedback());
            }

            if (reminderData.getReason() != 0 && reminderData.getFeedback() != 5) {
                linLayoutReason.setVisibility(View.VISIBLE);
                int position = reasonAdapter.findElementPosition(reminderData.getReason());
                spnReason.setSelection(position, false);
            }

            editTextRemarks.setText(reminderData.getRemarks());
            if (detailModel.getReminderDate() != null && !detailModel.getReminderDate().isEmpty()) {
                linLayoutReminder.setVisibility(View.VISIBLE);
                editTextReminder.setText(detailModel.getReminderDate());
            }
        }
    }
    //endregion

//    //region loadExecutiveOwnData
//    private void loadExecutiveOwnData()
//    {
//        DetailFromSMERoleModel fromExecutive = detailModel.getFromExecutive();
//        //bind data to submit from
//        if (fromExecutive != null) {
//            if (fromExecutive.getVisitStatus() != 0) {
//                int position = statusAdapter.findElementPosition(fromExecutive.getVisitStatus());
//                spnStatus.setSelection(position);
//            }
//
//            if (fromExecutive.getFeedback() != 0 && ((SpinnerData) spnStatus.getSelectedItem()).getId() == 1) {
//                linLayoutFeedback.setVisibility(View.VISIBLE);
//                ratingBar.setRating(fromExecutive.getFeedback());
//            }
//
//            if (fromExecutive.getReason() != 0 && fromExecutive.getFeedback() != 5) {
//                linLayoutReason.setVisibility(View.VISIBLE);
//                int position = reasonAdapter.findElementPosition(fromExecutive.getReason());
//                spnReason.setSelection(position, false);
//            }
//
//            editTextRemarks.setText(fromExecutive.getRemarks());
//            if (detailModel.getReminderDate() != null && !detailModel.getReminderDate().isEmpty()) {
//                linLayoutReminder.setVisibility(View.VISIBLE);
//                editTextReminder.setText(detailModel.getReminderDate());
//            }
//        }
//    }
//    //endregion

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

    //region setLinearLayoutVisible
    private void setLinearLayoutVisible() {
        linLayoutFeedback.setVisibility(View.VISIBLE);
        linLayoutReason.setVisibility(View.VISIBLE);
        linLayoutReason.setVisibility(View.VISIBLE);
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
            latLng = new LatLng(0.0, 0.0);
        }

        googleMap.addMarker(new MarkerOptions().position(latLng)
                .title("My Idea"));
        CameraUpdate location = CameraUpdateFactory.newLatLngZoom(latLng, 12);
        mMap.animateCamera(location);
    }
    //endregion

    //region initMap
    private void initMap() {
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
            mapView.setVisibility(View.GONE);

    }
    //endregion

    //region ProgressBar
    private void showProgressBar() {
        progressBarLoading.setVisibility(View.VISIBLE);
        scrollViewDetails.setVisibility(View.GONE);
    }

    private void hideProgressBar() {
        progressBarLoading.setVisibility(View.GONE);
        scrollViewDetails.setVisibility(View.VISIBLE);
    }
    //endregion

    //region showMessage
    private void showMessage(String title, String message) {
        InfoDialog infoDialog = InfoDialog.newInstance(title, message);
        infoDialog.show(getChildFragmentManager(), SUCCESS_DIALOG);
    }
    //endregion

}
