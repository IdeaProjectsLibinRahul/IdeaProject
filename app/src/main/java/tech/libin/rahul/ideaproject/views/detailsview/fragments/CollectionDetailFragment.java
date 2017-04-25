package tech.libin.rahul.ideaproject.views.detailsview.fragments;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Switch;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

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
import tech.libin.rahul.ideaproject.views.detailsview.dialogs.FOSMapExploreDialog;
import tech.libin.rahul.ideaproject.views.detailsview.dialogs.InfoDialog;
import tech.libin.rahul.ideaproject.views.detailsview.viewmodels.CollectionDetailModel;
import tech.libin.rahul.ideaproject.views.detailsview.viewmodels.FormSubmitModel;
import tech.libin.rahul.ideaproject.views.models.ActivityDetailRequestModel;
import tech.libin.rahul.ideaproject.views.utils.GPSTracker;
import tech.libin.rahul.ideaproject.views.utils.MakeCall;
import tech.libin.rahul.ideaproject.views.utils.SpinnerOperations;
import tech.libin.rahul.ideaproject.views.widgets.textview.FOSTextView;

import static tech.libin.rahul.ideaproject.views.detailsview.fragments.SMEDetailsFragment.DATE_DIALOG;

/**
 * A simple {@link Fragment} subclass.
 */
public class CollectionDetailFragment extends FOSBaseFragment implements OnMapReadyCallback {

    public static final String SUCCESS_DIALOG = "SUCCESS_DIALOG";
    private static final String TAG = CollectionDetailFragment.class.getName();

    //region declarations
    Spinner spnStatus;
    Spinner spnFeedback;
    ScrollView scrollViewDetails;
    ProgressBar progressBarLoading;
    private EditText editTextRemarks;
    private EditText editTextReminder;
    private EditText editTextAmountCollected;
    private EditText editTextLandmark;

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
    private FOSTextView textViewCustNum;
    private FOSTextView textViewLandline1;
    private FOSTextView textViewLandLine2;
    private FOSTextView textViewAddress;
    private FOSTextView textViewReminderDate;

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

    private View view;
    private Button buttonSubmit;
    private Switch switchLocation;
    private Switch switchUpdateLocation;
    private LinearLayout linLayoutReminder;
    private LinearLayout linLayoutPaidAmount;
    private LinearLayout linLayoutFeedback;
    private RecyclerView recViewOther;
    private String objectId;
    private String userName;
    private String userPhone;
    private GoogleMap mMap;
    private Constants.ActivityType activityType;
    private SupportMapFragment mapFragment;
    private GPSTracker gpsTracker;
    private FOSSpinnerAdapter statusAdapter;
    private FOSSpinnerAdapter feedbackAdapter;

    private List visitStatus;
    private List<SpinnerData> feedback;

    CollectionDetailModel detailModel;

    MakeCall makeCall;
    //endregion

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
        view = inflater.inflate(R.layout.fragment_collection_detail, container, false);

        initComponents();
        parseBundle();
        loadCollectionDetails();
        initMap();

        return view;
    }
    //endregion

    //region initComponents
    private void initComponents() {

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
        textViewCustNum = (FOSTextView) view.findViewById(R.id.textViewCustomerNumber);
        textViewLandline1 = (FOSTextView) view.findViewById(R.id.textViewLandLine1);
        textViewLandLine2 = (FOSTextView) view.findViewById(R.id.textViewLandLine2);
        textViewAddress = (FOSTextView) view.findViewById(R.id.textViewAddress);
        textViewReminderDate = (FOSTextView) view.findViewById(R.id.textViewReminderDate);
        editTextAmountCollected = (EditText) view.findViewById(R.id.editTextPaidAmount);

        editTextRemarks = (EditText) view.findViewById(R.id.editTextRemarks);
        textViewAddress = (FOSTextView) view.findViewById(R.id.textViewAddress);
        recViewOther = (RecyclerView) view.findViewById(R.id.recViewOther);

        spnStatus = (Spinner) view.findViewById(R.id.spinnerStatus);
        spnFeedback = (Spinner) view.findViewById(R.id.spinnerFeedback);

        editTextReminder = (EditText) view.findViewById(R.id.editTextReminderDate);
        editTextLandmark = (EditText) view.findViewById(R.id.editTextLandmark);
        switchLocation = (Switch) view.findViewById(R.id.switchLocation);
        switchUpdateLocation = (Switch) view.findViewById(R.id.switchUpdateLocation);
        editTextRemarks = (EditText) view.findViewById(R.id.editTextRemarks);
        buttonSubmit = (Button) view.findViewById(R.id.buttonSubmit);
        linLayoutReminder = (LinearLayout) view.findViewById(R.id.linLayoutReminder);
        linLayoutFeedback = (LinearLayout) view.findViewById(R.id.linLayoutFeedback);
        linLayoutPaidAmount = (LinearLayout) view.findViewById(R.id.linLayoutPaidAmount);

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
    //endregion initComponents

    //region parseBundle
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

    //region loadCollectionDetails
    private void loadCollectionDetails() {
        if (objectId != null) {
            ActivityDetailRequestModel requestModel = new ActivityDetailRequestModel();
            requestModel.setObjectId(objectId);
            requestModel.setRecordType(Constants.RecordType.COLLECTION);

            showProgressBar();
            FOSFacade fosFacade = new FOSFacadeImpl();
            fosFacade.getCollectionDetail(requestModel, new ServiceCallback<CollectionDetailModel>() {
                @Override
                public void onResponse(CollectionDetailModel response) {

                    hideProgressBar();
                    detailModel = response;
                    bindDetails(response);
                    setFomListeners();
                }

                @Override
                public void onRequestTimout() {
                    showMessage(getString(R.string.warn_time_out_title), getResources().getString(R.string.warn_request_timed_out), Constants.MessageType.TIME_OUT);
                }

                @Override
                public void onRequestFail(FOSError error) {
                    showMessage(getString(R.string.warn_server_error), error.getErrorMessage(), Constants.MessageType.ERROR);
                }
            });
        }
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
                            mapFragment.getMapAsync(CollectionDetailFragment.this);
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
                if (((SpinnerData) visitStatus.get(position)).getId() == Constants.VisitStatus.FOLLOW_UP.getValue()) {
                    linLayoutReminder.setVisibility(View.VISIBLE);
                    linLayoutFeedback.setVisibility(View.GONE);
                    linLayoutPaidAmount.setVisibility(View.GONE);

                } else {
                    linLayoutReminder.setVisibility(View.GONE);
                    linLayoutFeedback.setVisibility(View.VISIBLE);
                    if (spnFeedback != null) {
                        spnFeedback.setSelection(0, true);
                        linLayoutPaidAmount.setVisibility(View.VISIBLE);
                    }

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spnFeedback.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int selectedValue = detailModel.getFeedback().get(position).getId();
                if (selectedValue == 2 || selectedValue == 3 || selectedValue == 7) {
                    linLayoutReminder.setVisibility(View.VISIBLE);
                    linLayoutPaidAmount.setVisibility(View.GONE);

                } else if (selectedValue == 1) {
                    linLayoutReminder.setVisibility(View.GONE);
                    linLayoutPaidAmount.setVisibility(View.VISIBLE);
                } else {
                    linLayoutReminder.setVisibility(View.GONE);
                    linLayoutPaidAmount.setVisibility(View.GONE);
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
        requestModel.setRecordType(Constants.RecordType.COLLECTION);
        requestModel.setLandmark(editTextLandmark.getText().toString());

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
        requestModel.setReminder("");
        if (linLayoutReminder.getVisibility() == View.VISIBLE) {
            requestModel.setReminder(editTextReminder.getText().toString().trim());
        }
        if (linLayoutFeedback.getVisibility() == View.VISIBLE) {
            requestModel.setFeedback(((SpinnerData) spnFeedback.getSelectedItem()).getId());
        }
        requestModel.setAmountPaid("0");
        if (linLayoutPaidAmount.getVisibility() == View.VISIBLE) {
            requestModel.setAmountPaid(editTextAmountCollected.getText().toString().trim());
        }

        final ProgressDialog dialog = ProgressDialog.show(getActivity(), null, getResources().getString(R.string.requesting), true, true);
        FOSFacade fosFacade = new FOSFacadeImpl();
        fosFacade.doSubmitVisitDetails(requestModel, new ServiceCallback<String>() {
            @Override
            public void onResponse(String response) {
                if (dialog != null) {
                    dialog.cancel();
                }
                showMessage(getString(R.string.warn_success), response, Constants.MessageType.TIME_OUT);
            }

            @Override
            public void onRequestTimout() {
                if (dialog != null) {
                    dialog.cancel();
                }
                showMessage(getString(R.string.warn_time_out_title), getResources().getString(R.string.warn_request_timed_out), Constants.MessageType.TIME_OUT);
            }

            @Override
            public void onRequestFail(FOSError error) {
                if (dialog != null) {
                    dialog.cancel();
                }
                showMessage(getString(R.string.warn_server_error), error.getErrorMessage(), Constants.MessageType.ERROR);
            }
        });
    }
    //endregion

    //region bindDetails
    private void bindDetails(CollectionDetailModel model) {

        textViewName.setText(model.getBill2());
        textViewMobile.setText(model.getMobile());
        textViewCurBalance.setText(model.getCurBalance());
        textViewCustType.setText(model.getCustomerType());
        textViewBal00.setText(model.getBal());
        textViewBal30.setText(model.getBal30());
        textViewBalOther.setText(model.getBalOther());
        textViewCategory.setText(model.getCategory());
        textViewRatePlan.setText(model.getRatePlan());
        textViewBiller.setText(model.getBiller());
        textViewDefaultHabit.setText(model.getDefaultHabit());
        textViewCurrentStatus.setText(model.getCrmStatus());
        textViewSer.setText(model.getSer());
        textViewBucket.setText(model.getBucket());
        textViewCustNum.setText(model.getCustNum());
        textViewLandline1.setText(model.getLandLine1());
        textViewLandLine2.setText(model.getLandLine2());

        textViewAddress.setText(model.getBill1() + "\n" + model.getBill2() + "\n" + model.getBill3() + "\n" + model.getBill4() + "\n" + model.getZip());

        visitStatus = model.getVisitStatus();
        statusAdapter = new FOSSpinnerAdapter(getActivity(), android.R.layout.simple_spinner_item, model.getVisitStatus());
        spnStatus.setAdapter(statusAdapter);

        feedback = model.getFeedback();
        feedbackAdapter = new FOSSpinnerAdapter(getActivity(), android.R.layout.simple_spinner_item, model.getFeedback());
        spnFeedback.setAdapter(feedbackAdapter);

        if (model.getLocation() != null && model.getLocation().getLatitude() != null && !model.getLocation().getLatitude().isEmpty()) {
            switchLocation.setVisibility(View.VISIBLE);
        }

        if (model.getLocation() != null && model.getLocation().getLandmark() != null) {
            editTextLandmark.setText(model.getLocation().getLandmark());
        }

        makeCall = new MakeCall(getActivity());
        makeCall.setCallClick(textViewMobile);
        makeCall.setCallClick(textViewLandline1);
        makeCall.setCallClick(textViewLandLine2);

        loadPreviousData();
    }
    //endregion

    //region loadPreviousData
    private void loadPreviousData() {
        //if its a new activity then not need to load anything based on role,its default settings

        if (activityType == Constants.ActivityType.REMINDER) {
            if (Config.getInstance().getUser().getRole() == Constants.Role.EXECUTIVE) {
                // only need to load executive data to form submit card
                loadReminderData(detailModel.getFromExecutive());
            }
            if (Config.getInstance().getUser().getRole() == Constants.Role.MICO) {
                //need to load  mico data to from submit and executive data from executive card
                loadFromExecutiveData();
                loadReminderData(detailModel.getFromMico());
            }

            if (Config.getInstance().getUser().getRole() == Constants.Role.ZSM) {
                //load all data from server
                loadFromMicoData();
                loadFromExecutiveData();
                loadReminderData(detailModel.getFromZsm());
            }
        }

        if (activityType == Constants.ActivityType.ACTIVITY) {
            cardViewFromSubmit.setVisibility(View.GONE);
            loadFromExecutiveData();
            if (Config.getInstance().getUser().getRole() == Constants.Role.MICO)
                loadFromMicoData();

            if (Config.getInstance().getUser().getRole() == Constants.Role.ZSM) {
                loadFromMicoData();
                loadFromZsmData();
            }
        }

        if (activityType == Constants.ActivityType.NEW_ACTIVITY) {
            if (Config.getInstance().getUser().getRole() == Constants.Role.MICO)
                loadFromExecutiveData();

            if (Config.getInstance().getUser().getRole() == Constants.Role.ZSM) {
                loadFromMicoData();
                loadFromExecutiveData();
            }
        }

    }
    //endregion

    //region loadReminderData
    private void loadReminderData(DetailFromUPCRoleModel reminderData) {
        try {
            String reminder = detailModel.getReminderDate();
            if (reminderData != null) {
                if (reminderData.getVisitStatus() != 0 && spnStatus != null) {
                    int position = statusAdapter.findElementPosition(reminderData.getVisitStatus());
                    spnStatus.setSelection(position, false);
                }

                linLayoutPaidAmount.setVisibility(View.GONE);
                linLayoutReminder.setVisibility(View.GONE);
                if (reminderData.getFeedback() != 0 && spnFeedback != null) {
                    linLayoutFeedback.setVisibility(View.VISIBLE);
                    if (reminderData.getVisitStatus() != 0 && spnStatus != null) {
                        int position = feedbackAdapter.findElementPosition(reminderData.getFeedback());
                        spnFeedback.setSelection(position, false);
                    }
                    int selectedFeedback = ((SpinnerData) spnFeedback.getSelectedItem()).getId();
                    //if amount paid
                    if (selectedFeedback == 1) {
                        linLayoutPaidAmount.setVisibility(View.VISIBLE);
                        linLayoutReminder.setVisibility(View.GONE);
                        editTextAmountCollected.setText(reminderData.getAmountPaid());

                    } else if (selectedFeedback == 2 || selectedFeedback == 3 || selectedFeedback == 7) {
                        linLayoutPaidAmount.setVisibility(View.GONE);
                        linLayoutReminder.setVisibility(View.VISIBLE);
                        editTextReminder.setText(reminder);
                    }
                }
                if ((reminder != null || !reminder.isEmpty()) && reminderData.getVisitStatus() == Constants.VisitStatus.FOLLOW_UP.getValue()) {
                    linLayoutReminder.setVisibility(View.VISIBLE);
                    textViewReminderDate.setText(getResources().getString(R.string.follow_up_date));
                    editTextReminder.setText(reminder);
                }
                editTextRemarks.setText(reminderData.getRemarks());
            }
            //if not visited
            if (((SpinnerData) spnStatus.getSelectedItem()).getId() == Constants.VisitStatus.FOLLOW_UP.getValue()) {
                linLayoutFeedback.setVisibility(View.GONE);
                linLayoutPaidAmount.setVisibility(View.GONE);
                linLayoutReminder.setVisibility(View.VISIBLE);
                textViewReminderDate.setText(getResources().getString(R.string.follow_up_date));
            } else {
                linLayoutFeedback.setVisibility(View.VISIBLE);
                linLayoutReminder.setVisibility(View.GONE);
            }

            if (linLayoutFeedback.getVisibility() == View.VISIBLE) {
                //if amount paid
                if (((SpinnerData) spnFeedback.getSelectedItem()).getId() == 1) {
                    linLayoutPaidAmount.setVisibility(View.VISIBLE);
                    linLayoutReminder.setVisibility(View.GONE);

                } else if (((SpinnerData) spnFeedback.getSelectedItem()).getId() == 2) {
                    linLayoutPaidAmount.setVisibility(View.GONE);
                    linLayoutReminder.setVisibility(View.VISIBLE);
                }
            }
        } catch (Exception ex) {
            Log.e(TAG, ex.toString());
        }
    }
    //endregion

    //region loadExecutiveOwnData
    private void loadFromExecutiveData() {
        try {
            cardViewFromExe.setVisibility(View.VISIBLE);
            DetailFromUPCRoleModel fromExecutive = detailModel.getFromExecutive();
            if (fromExecutive != null) {
                textViewFromExeName.setText(fromExecutive.getName());
                textViewFromExeMobileNum.setText(fromExecutive.getPhoneNum());
                textViewFromExeMyIdea.setText(fromExecutive.getMyIdea());
                textViewFromExeMyIdeaCode.setText(fromExecutive.getMyIdeaCode());
                if (fromExecutive.getTotalVisit() > 0) {
                    textViewFromExeVisitStatus.setText(SpinnerOperations.getSpinnerItem(fromExecutive.getVisitStatus(), visitStatus));
                    textViewFromExeVisitFeedback.setText(SpinnerOperations.getSpinnerItem(fromExecutive.getFeedback(), feedback));
                    if (fromExecutive.getFeedback() == 2) {
                        //payment collected
                        textViewFromExeAmountCollected.setVisibility(View.VISIBLE);
                        textViewFromExeAmountCollectedTitle.setVisibility(View.VISIBLE);
                        textViewFromExeAmountCollected.setText(fromExecutive.getAmountPaid());
                    }
                    textViewFromExeVisitedDate.setText(fromExecutive.getVisitedDate());
                    textViewExeRemarks.setText(fromExecutive.getRemarks());
                } else {
                    llFromExeVisitDetails.setVisibility(View.GONE);
                    textViewFromExeEscalateNoVisit.setVisibility(View.VISIBLE);
                    textViewFromExeEscalateNoVisit.setText(getString(R.string.warn_not_visited));
                }
                makeCall.setCallClick(textViewFromExeMobileNum);

            }

        } catch (Exception ex) {
            Log.e(TAG, ex.toString());
        }
    }
    //endregion

    //region loadFromMicoData
    private void loadFromMicoData() {
        try {
            cardViewFromMico.setVisibility(View.VISIBLE);
            DetailFromUPCRoleModel fromMico = detailModel.getFromMico();
            if (fromMico != null) {
                textViewFromMicoName.setText(fromMico.getName());
                textViewFromMicoMobileNum.setText(fromMico.getPhoneNum());
                if (fromMico.getTotalVisit() > 0) {
                    textViewFromMicoVisitStatus.setText(SpinnerOperations.getSpinnerItem(fromMico.getVisitStatus(), visitStatus));
                    textViewFromMicoVisitFeedback.setText(SpinnerOperations.getSpinnerItem(fromMico.getFeedback(), feedback));
                    if (fromMico.getFeedback() == 2) {
                        //payment collected
                        textViewFromMicoAmountCollected.setVisibility(View.VISIBLE);
                        textViewFromMicoAmountCollectedTitle.setVisibility(View.VISIBLE);
                        textViewFromMicoAmountCollected.setText(fromMico.getAmountPaid());
                    }
                    textViewFromMicoVisitedDate.setText(fromMico.getVisitedDate());
                    textViewMicoRemarks.setText(fromMico.getRemarks());
                } else {
                    llFromMicoVisitDetails.setVisibility(View.GONE);
                    textViewFromMicoEscalateNoVisit.setVisibility(View.VISIBLE);
                    textViewFromMicoEscalateNoVisit.setText(getString(R.string.warn_not_visited));
                }
                makeCall.setCallClick(textViewFromMicoMobileNum);
            }

        } catch (Exception ex) {
            Log.e("Exception", ex.toString());
        }
    }
    //endregion

    //region loadFromZsmData
    private void loadFromZsmData() {
        try {
            cardViewFromZsm.setVisibility(View.VISIBLE);
            DetailFromUPCRoleModel fromZsm = detailModel.getFromZsm();
            if (fromZsm != null) {
                textViewFromZsmName.setText(fromZsm.getName());
                textViewFromZsmMobileNum.setText(fromZsm.getPhoneNum());
                if (fromZsm.getTotalVisit() > 0) {
                    textViewFromZsmVisitStatus.setText(SpinnerOperations.getSpinnerItem(fromZsm.getVisitStatus(), visitStatus));
                    textViewFromZsmVisitFeedback.setText(SpinnerOperations.getSpinnerItem(fromZsm.getFeedback(), feedback));
                    if (fromZsm.getFeedback() == 2) {
                        //payment collected
                        textViewFromZsmAmountCollected.setVisibility(View.VISIBLE);
                        textViewFromZsmAmountCollectedTitle.setVisibility(View.VISIBLE);
                        textViewFromZsmAmountCollected.setText(fromZsm.getAmountPaid());
                    }
                    textViewFromZsmVisitedDate.setText(fromZsm.getVisitedDate());
                    textViewZsmRemarks.setText(fromZsm.getRemarks());
                } else {
                    llFromZsmVisitDetails.setVisibility(View.GONE);
                    textViewFromZsmEscalateNoVisit.setVisibility(View.VISIBLE);
                    textViewFromZsmEscalateNoVisit.setText(getString(R.string.warn_not_visited));
                }
                makeCall.setCallClick(textViewFromZsmMobileNum);
            }
        } catch (Exception ex) {
            Log.e("Exception", ex.toString());
        }
    }
    //endregion

    //region initMap
    private void initMap() {
        mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        View mapView = mapFragment.getView();
        if (mapView != null && switchLocation.getVisibility() == View.VISIBLE) {
            if (!switchLocation.isChecked()) {
                mapView.setVisibility(View.GONE);
            } else {
                mapView.setVisibility(View.VISIBLE);
                mapFragment.getMapAsync(this);
            }
        } else
            mapView.setVisibility(View.GONE);
    }
    //endregion

    //region onMapReady
    @Override
    public void onMapReady(GoogleMap googleMap) {
        try {
            mMap = googleMap;
            LatLng latLng;
            if (detailModel != null && !detailModel.getLocation().getLatitude().isEmpty() && !detailModel.getLocation().getLongitude().isEmpty()) {
                double latitude = Double.parseDouble(detailModel.getLocation().getLatitude());
                double longitude = Double.parseDouble(detailModel.getLocation().getLongitude());
                latLng = new LatLng(latitude, longitude);
            } else {
                latLng = new LatLng(0.0, 0.0);
            }

            googleMap.addMarker(new MarkerOptions().position(latLng)
                    .title("My Idea"));
            CameraUpdate location = CameraUpdateFactory.newLatLngZoom(latLng, 12);
            mMap.animateCamera(location);

            googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                @Override
                public void onMapClick(LatLng arg0) {
                    try {
                        if (detailModel != null) {
                            Bundle bundle = new Bundle();
                            bundle.putString("name", detailModel.getBill2());
                            bundle.putString("latitude", detailModel.getLocation().getLatitude());
                            bundle.putString("longitude", detailModel.getLocation().getLatitude());
                            FOSMapExploreDialog dialog = new FOSMapExploreDialog();
                            dialog.setArguments(bundle);
                            dialog.show(getFragmentManager(), "MapExplorer");
                        }
                    } catch (Exception ex) {

                    }
                }
            });

        } catch (Exception ex) {
            Log.e("Error", ex.toString());
        }
    }
    //endregion

    //region showMessage
    private void showMessage(String title, String message, Constants.MessageType type) {
        InfoDialog infoDialog = InfoDialog.newInstance(title, message, type);
        infoDialog.show(getChildFragmentManager(), SUCCESS_DIALOG);
    }
    //endregion

}
