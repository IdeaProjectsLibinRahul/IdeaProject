package tech.libin.rahul.ideaproject.views.detailsview.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
import tech.libin.rahul.ideaproject.views.detailsview.viewmodels.SmeDetailModel;
import tech.libin.rahul.ideaproject.views.models.ActivityDetailRequestModel;

/**
 * Created by libin on 05/03/17.
 */

public class SMEDetailsFragment extends FOSBaseFragment implements OnMapReadyCallback {
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
    private TextView textViewCustomerType;
    private TextView textViewSegment;
    private TextView textViewBeginningDate;
    private TextView textViewActiveReason;
    private TextView textViewTmCode;
    private TextView textViewRatePlan;
    private TextView textViewCRLimit;
    private TextView textViewAddress;
    private TextView textViewLandLine;
    private TextView textViewType;

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
        textViewCustomerType = (TextView) view.findViewById(R.id.textViewCustomerType);
        textViewSegment = (TextView) view.findViewById(R.id.textViewSegment);
        textViewBeginningDate = (TextView) view.findViewById(R.id.textViewBegeningDate);
        textViewActiveReason = (TextView) view.findViewById(R.id.textViewActiveReason);
        textViewTmCode = (TextView) view.findViewById(R.id.textViewTmCode);
        textViewRatePlan = (TextView) view.findViewById(R.id.textViewRatePlan);
        textViewCRLimit = (TextView) view.findViewById(R.id.textViewCrLimit);
        textViewLandLine = (TextView) view.findViewById(R.id.textViewLandLine);
        textViewType = (TextView) view.findViewById(R.id.textViewType);
        recViewOther = (RecyclerView) view.findViewById(R.id.recViewOther);
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
        SupportMapFragment mapFragment = (SupportMapFragment) getActivity().getSupportFragmentManager()
                .findFragmentById(R.id.map);
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
                    textViewName.setText(response.getCcName());
//                 textViewMobileNum.setText(response.getM());
                    textViewBiller.setText(response.getBiller());
                    textViewCompanyName.setText(response.getCcName());
                    textViewTotAlConnections.setText(response.getTotalConnection());
                    textViewFbConnections.setText(response.getFBConnection());
                    textViewMico.setText(response.getMiName());
                    textViewMicoCode.setText(response.getMicoCode());
                    textViewMicoName.setText(response.getMiName());
                    textViewZone.setText(response.getZone());
                    // textViewCustomerType.setText(response.getCustType());
                    textViewSegment.setText(response.getSegment());
                    textViewBeginningDate.setText(response.getBegdate());
                    textViewActiveReason.setText(response.getActReason());
                    textViewTmCode.setText(response.getTmcode());
                    textViewRatePlan.setText(response.getRatePlan());
                    textViewCRLimit.setText(response.getCr_limit());
                    textViewLandLine.setText(response.getLandLine2());
                    textViewType.setText(response.getType());
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

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
    }
}
