package tech.libin.rahul.ideaproject.views.detailsview.fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
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
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Spinner;
import android.widget.EditText;
import android.widget.Button;
import java.util.*;

import javax.security.auth.Subject;

import tech.libin.rahul.ideaproject.R;
import tech.libin.rahul.ideaproject.configurations.Constants;
import tech.libin.rahul.ideaproject.facade.FOSFacade;
import tech.libin.rahul.ideaproject.facade.FOSFacadeImpl;
import tech.libin.rahul.ideaproject.service.handlers.ServiceCallback;
import tech.libin.rahul.ideaproject.service.models.SpinnerData;
import tech.libin.rahul.ideaproject.service.requests.SmeFormSubmitRequest;
import tech.libin.rahul.ideaproject.service.responses.base.FOSError;
import tech.libin.rahul.ideaproject.views.basecomponents.FOSBaseFragment;
import tech.libin.rahul.ideaproject.views.detailsview.viewmodels.SmeDetailModel;
import tech.libin.rahul.ideaproject.views.detailsview.viewmodels.SmeFormSubmitModel;
import tech.libin.rahul.ideaproject.views.detailsview.viewmodels.TdDetailModel;
import tech.libin.rahul.ideaproject.views.detailsview.viewmodels.UpcDetailModel;
import tech.libin.rahul.ideaproject.views.models.ActivityDetailRequestModel;
import tech.libin.rahul.ideaproject.views.models.SpinnerModel;

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
    private TextView textViewSegment;
    private TextView textViewBeginningDate;
    private TextView textViewActiveReason;
    private TextView textViewTmCode;
    private TextView textViewRatePlan;
    private TextView textViewCRLimit;
    private TextView textViewAddress;
    private TextView textViewLandLine;
    private TextView textViewType;
    private EditText editTextRemarks;
    private static EditText editTextReminder;
    private Button buttonSubmit;

    private LinearLayout linLayoutFeedback;
    private LinearLayout linLayoutReason;
    private LinearLayout linLayoutReminder;




    Spinner spnStatus;
    Spinner spnFeedback;
    Spinner spnReason;

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
        textViewSegment = (TextView) view.findViewById(R.id.textViewSegment);
        textViewBeginningDate = (TextView) view.findViewById(R.id.textViewBegeningDate);
        textViewActiveReason = (TextView) view.findViewById(R.id.textViewActiveReason);
        textViewTmCode = (TextView) view.findViewById(R.id.textViewTmCode);
        textViewRatePlan = (TextView) view.findViewById(R.id.textViewRatePlan);
        textViewCRLimit = (TextView) view.findViewById(R.id.textViewCrLimit);
        textViewLandLine = (TextView) view.findViewById(R.id.textViewLandLine);
        textViewType = (TextView) view.findViewById(R.id.textViewType);
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
                textViewMobileNum.setVisibility(View.GONE);
                textViewBiller.setText(response.getBiller());
                textViewCompanyName.setText(response.getCcName());
                textViewTotAlConnections.setText(response.getTotalConnection());
                textViewFbConnections.setText(response.getFBConnection());
                textViewMico.setText(response.getMico());
                textViewMicoCode.setText(response.getMicoCode());
                textViewMicoName.setText(response.getMiName());
                textViewZone.setText(response.getZone());
                textViewSegment.setText(response.getSegment());
                textViewBeginningDate.setText(response.getBegdate());
                textViewActiveReason.setText(response.getActReason()==null ? "Nill" : response.getActReason());
                textViewTmCode.setText(response.getTmcode());
                textViewRatePlan.setText(response.getRatePlan());
                textViewCRLimit.setText(response.getCr_limit());
                               textViewLandLine.setText(response.getLandLine2());
                if (response.getLandLine2()==null)
                    textViewLandLine.setVisibility(view.GONE);
                textViewType.setText(response.getType());

                textViewAddress.setText(response.getBill1() + "\n" + response.getBill2() + "\n" + response.getBill3() + "\n" + response.getBill4() + "\n" + response.getBill5() + "\n" + response.getZip());



                setLinearLayoutVisible();
                final List visitStatus= response.getVisitStatus();
                final List visitFeedback= response.getFeedback();

                ArrayAdapter<SpinnerModel> spinnerAdapter = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_dropdown_item_1line,
                        (List) response.getVisitStatus()  );
                spnStatus.setAdapter(spinnerAdapter);

                spinnerAdapter = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_dropdown_item_1line,
                        (List) response.getReason()  );
                spnReason.setAdapter(spinnerAdapter);


               spinnerAdapter = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_dropdown_item_1line,
                        (List) response.getFeedback()  );
                spnFeedback.setAdapter(spinnerAdapter);

                spnStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        if(((SpinnerData)visitStatus.get(position)).getId()==2)
                        {
                            linLayoutReminder.setVisibility(View.VISIBLE);
                            linLayoutFeedback.setVisibility(View.GONE);
                            linLayoutReason.setVisibility(View.GONE);

                        }
                        else
                        {
                            linLayoutReminder.setVisibility(View.GONE);
                            linLayoutFeedback.setVisibility(View.VISIBLE);
                            linLayoutReason.setVisibility(View.VISIBLE);
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

//                spnFeedback.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                    @Override
//                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                        if(((SpinnerData)visitFeedback.get(position)).getId()!=5)
//                        {
//                            linLayoutReason.setVisibility(View.VISIBLE);
//                        }
//                        else
//                        {
//                            linLayoutReason.setVisibility(View.GONE);
//                        }
//                    }
//
//                    @Override
//                    public void onNothingSelected(AdapterView<?> parent) {
//
//                    }
//                });


                editTextReminder.setOnTouchListener(new View.OnTouchListener() {

                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (event.getAction() == MotionEvent.ACTION_UP) {
                            //CREATE YOUR DATE PICKER DIALOG HERE.
                            DialogFragment newFragment = new SelectDobFragment();
                            newFragment.show(getActivity().getFragmentManager(), "DATE PICKER");
                        }
                        return false;
                    }
                });


                buttonSubmit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        SmeFormSubmitModel requestModel = new SmeFormSubmitModel();
                        requestModel.setObjectId(17L);
                        requestModel.setStatus(((SpinnerData)spnStatus.getSelectedItem()).getId());
                        requestModel.setRemarks(editTextRemarks.getText().toString().trim());
                        requestModel.setLongitude("12.12");
                        requestModel.setLongitude("12.12");
                        if(linLayoutFeedback.getVisibility()==View.VISIBLE) {
                            requestModel.setFeedback(((SpinnerData) spnFeedback.getSelectedItem()).getId());
                        }
                        if(linLayoutReason.getVisibility()==View.VISIBLE) {
                            requestModel.setReason(((SpinnerData) spnReason.getSelectedItem()).getId());
                        }
                        if(linLayoutReminder.getVisibility()==View.VISIBLE) {
                            requestModel.setReminder(editTextReminder.getText().toString().trim());
                        }
                        requestModel.setRecordType(Constants.RecordType.SME);

                        FOSFacade fosFacade = new FOSFacadeImpl();
                        fosFacade.doSubmitSmeVisitDetails(requestModel, new ServiceCallback<String>() {
                            @Override
                            public void onResponse(String response) {

                            }

                            @Override
                            public void onRequestTimout() {

                            }

                            @Override
                            public void onRequestFail(FOSError error) {

                            }
                        });
                    }
                });



                setLinearLayoutGone();
            }

            @Override
            public void onRequestTimout() {

            }

            @Override
            public void onRequestFail(FOSError error) {

            }
        });
    }

    private void setLinearLayoutVisible()
    {
        linLayoutFeedback.setVisibility(View.VISIBLE);
        linLayoutReason.setVisibility(View.VISIBLE);
        linLayoutReason.setVisibility(View.VISIBLE);
    }

    private void setLinearLayoutGone()
    {
        linLayoutFeedback.setVisibility(View.GONE);
        linLayoutReason.setVisibility(View.GONE);
        linLayoutReason.setVisibility(View.GONE);
    }

    private boolean isValid()
    {
        if(((SpinnerData)spnStatus.getSelectedItem()).getId()==0)
        {
            return  false;
        }

        return false;
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


    // region Date Picker
    public static class SelectDobFragment extends DialogFragment implements
            DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_YEAR, -1);
            int yy = calendar.get(Calendar.YEAR);
            int mm = calendar.get(Calendar.MONTH);
            int dd = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog dfrom = new DatePickerDialog(getActivity(), this, yy, mm, dd);
            dfrom.getDatePicker().setMaxDate(new Date().getTime());
            //return new DatePickerDialog(getActivity(), this, yy, mm, dd);
            return dfrom;
        }

        public void onDateSet(DatePicker view, int yy, int mm, int dd) {
            populateSetDate(yy, mm + 1, dd);
        }

        public void populateSetDate(int year, int month, int day) {
            editTextReminder.setText(month + "/" + day + "/" + year);
            editTextReminder.clearFocus();
            editTextReminder.setError(null);
        }
    }
    //endregion
}
