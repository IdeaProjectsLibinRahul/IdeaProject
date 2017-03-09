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
import android.widget.ArrayAdapter;
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
import tech.libin.rahul.ideaproject.views.detailsview.dialogs.FOSDateDialog;
import tech.libin.rahul.ideaproject.views.detailsview.viewmodels.SmeFormSubmitModel;
import tech.libin.rahul.ideaproject.views.detailsview.viewmodels.TdDetailModel;
import tech.libin.rahul.ideaproject.views.models.ActivityDetailRequestModel;
import tech.libin.rahul.ideaproject.views.models.SpinnerModel;
import tech.libin.rahul.ideaproject.views.utils.GPSTracker;

import static tech.libin.rahul.ideaproject.views.detailsview.fragments.SMEDetailsFragment.DATE_DIALOG;

/**
 * Created by libin on 05/03/17.
 */

public class TDDetailsFragment extends FOSBaseFragment implements OnMapReadyCallback {

    private View view;

    TextView textViewCustNum;
    TextView textViewMobile;
    TextView textViewBiller;
    TextView textViewSer;
    TextView textViewCurBalance;
    TextView textViewSegment;
    TextView textViewBucket;
    TextView textViewType;
    TextView textViewCustomerType;
    TextView textViewCrmStatus;
    TextView textViewMyIdeaCode;
    TextView textViewMyIdeaAllocation;
    TextView textViewActivationMi;
    TextView textViewLandline1;
    TextView textViewLandLine2;
    private EditText editTextRemarks;
    private TextView textViewAddress;
    private Button buttonSubmit;
    private LinearLayout linLayoutReminder;
    private GoogleMap mMap;
    private RecyclerView recViewOther;
    Spinner spnStatus;
    private EditText editTextReminder;

    private String objectId;
    private String userName;
    private String userPhone;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_activity_details, container, false);


        initComponents();
        parseBundle();
        loadTdDetails();
        initMap();

        loadTdDetails();
        return view;
    }

    private void initComponents() {
        textViewCustNum = (TextView) view.findViewById(R.id.textViewName);
        textViewMobile = (TextView) view.findViewById(R.id.textViewPhoneNum);

        textViewBiller= (TextView) view.findViewById(R.id.textViewBiller);
        textViewSer= (TextView) view.findViewById(R.id.textViewSer);
        textViewCurBalance= (TextView) view.findViewById(R.id.textViewDueAmount);
        textViewSegment= (TextView) view.findViewById(R.id.textViewSegment);
        textViewBucket= (TextView) view.findViewById(R.id.textViewBucket);
        textViewType= (TextView) view.findViewById(R.id.textViewType);
        textViewCustomerType= (TextView) view.findViewById(R.id.textViewCustomerType);
        textViewCrmStatus= (TextView) view.findViewById(R.id.textViewCrmStatus);
        textViewMyIdeaCode= (TextView) view.findViewById(R.id.textViewMyIdeaCode);
        textViewMyIdeaAllocation= (TextView) view.findViewById(R.id.textVieMyIdeaLocation);
        textViewActivationMi= (TextView) view.findViewById(R.id.textViewActivationMi);
        textViewLandline1= (TextView) view.findViewById(R.id.textViewLandLine1);
        textViewLandLine2= (TextView) view.findViewById(R.id.textViewLandLine1);

        editTextRemarks = (EditText) view.findViewById(R.id.editTextRemarks);
        textViewAddress = (TextView) view.findViewById(R.id.textViewAddress);
        recViewOther = (RecyclerView) view.findViewById(R.id.recViewOther);

        spnStatus = (Spinner) view.findViewById(R.id.spinnerStatus);
        editTextReminder = (EditText) view.findViewById(R.id.editTextReminderDate);
        editTextRemarks = (EditText) view.findViewById(R.id.editTextRemarks);
        buttonSubmit = (Button) view.findViewById(R.id.buttonSubmit);
        linLayoutReminder = (LinearLayout) view.findViewById(R.id.linLayoutReminder);
    }


    private void parseBundle() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            objectId = bundle.getString(Constants.PARAMS.DETAILS_OBJECT_ID);
            userName = bundle.getString(Constants.PARAMS.DETAILS_OBJECT_NAME);
            userPhone = bundle.getString(Constants.PARAMS.DETAILS_OBJECT_PHONE);
            userPhone = bundle.getParcelable(Constants.PARAMS.DETAILS_OBJECT_TAB);

            //setHeader();
        }
    }

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


    private void bindDetails(TdDetailModel model) {
        textViewCustNum.setText(model.getCustNum());
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
        textViewLandline1.setText(model.getLandLine1());
        textViewLandLine2.setText(model.getLandLine2());

        textViewAddress.setText(model.getBill1() + "\n" + model.getBill2() + "\n" + model.getBill3() + "\n" + model.getBill4() + "\n" + model.getBill5() + "\n" + model.getZip());

        final List visitStatus = model.getVisitStatus();

        ArrayAdapter<SpinnerModel> spinnerAdapter = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_dropdown_item_1line,
                (List) model.getVisitStatus());
        spnStatus.setAdapter(spinnerAdapter);

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
                requestModel.setLatitude(gpsTracker.getLatitude() + "");
                requestModel.setLongitude(gpsTracker.getLongitude() + "");
            } else {
                gpsTracker.showSettingsAlert();
                return;
            }

            requestModel.setLatitude("12.12");
            requestModel.setLongitude("12.12");
            requestModel.setReminder("");
            requestModel.setReason(0);
            requestModel.setFeedback(0);

            if (linLayoutReminder.getVisibility() == View.VISIBLE) {
                requestModel.setReminder(editTextReminder.getText().toString().trim());
            }
            requestModel.setRecordType(Constants.RecordType.TD);

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

    private void initMap() {
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {

    }
}
