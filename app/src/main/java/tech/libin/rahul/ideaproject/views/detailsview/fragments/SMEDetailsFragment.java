package tech.libin.rahul.ideaproject.views.detailsview.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import tech.libin.rahul.ideaproject.R;
import tech.libin.rahul.ideaproject.configurations.Constants;
import tech.libin.rahul.ideaproject.facade.FOSFacade;
import tech.libin.rahul.ideaproject.facade.FOSFacadeImpl;
import tech.libin.rahul.ideaproject.service.handlers.ServiceCallback;
import tech.libin.rahul.ideaproject.service.requests.ActivityDetailRequest;
import tech.libin.rahul.ideaproject.service.responses.base.FOSError;
import tech.libin.rahul.ideaproject.views.basecomponents.FOSBaseFragment;
import tech.libin.rahul.ideaproject.views.detailsview.viewmodels.SmeDetailModel;
import tech.libin.rahul.ideaproject.views.detailsview.viewmodels.TdDetailModel;
import tech.libin.rahul.ideaproject.views.detailsview.viewmodels.UpcDetailModel;
import tech.libin.rahul.ideaproject.views.models.ActivityDetailRequestModel;

/**
 * Created by libin on 05/03/17.
 */

public class SMEDetailsFragment extends FOSBaseFragment {
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

    private RecyclerView recViewOther;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_sme_details, container, false);
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
        loadDetails();
     //   loadTdDetails();
//       loadUpcDetails();
        return view;
    }

    private void loadDetails() {
        ActivityDetailRequestModel requestModel = new ActivityDetailRequestModel();
        requestModel.setObjectId("17");
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
                textViewCustomerType.setText(response.getCustType());
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


    private void loadUpcDetails() {
        ActivityDetailRequestModel requestModel = new ActivityDetailRequestModel();
        requestModel.setObjectId("169");
        requestModel.setRecordType(Constants.RecordType.UPC);

        FOSFacade fosFacade = new FOSFacadeImpl();
        fosFacade.getUpcDetail(requestModel, new ServiceCallback<UpcDetailModel>() {
            @Override
            public void onResponse(UpcDetailModel response) {
Log.e("UPCDETAILS",response.toString());
            }

            @Override
            public void onRequestTimout() {

            }

            @Override
            public void onRequestFail(FOSError error) {

            }
        });
    }


    private void loadTdDetails() {
        ActivityDetailRequestModel requestModel = new ActivityDetailRequestModel();
        requestModel.setObjectId("12");
        requestModel.setRecordType(Constants.RecordType.TD);

        FOSFacade fosFacade = new FOSFacadeImpl();
        fosFacade.getTdDetail(requestModel, new ServiceCallback<TdDetailModel>() {
            @Override
            public void onResponse(TdDetailModel response) {
                Log.e("TDDETAILS",response.toString());
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
