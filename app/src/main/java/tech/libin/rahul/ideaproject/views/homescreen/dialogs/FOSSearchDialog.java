package tech.libin.rahul.ideaproject.views.homescreen.dialogs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import org.greenrobot.eventbus.EventBus;

import tech.libin.rahul.ideaproject.R;
import tech.libin.rahul.ideaproject.configurations.Constants;
import tech.libin.rahul.ideaproject.events.SearchEvent;
import tech.libin.rahul.ideaproject.views.widgets.button.FOSButton;
import tech.libin.rahul.ideaproject.views.widgets.edittext.FOSEditText;

/**
 * Created by libin on 05/03/17.
 */

public class FOSSearchDialog extends DialogFragment {
    private View view;
    private ImageView imageViewBackButton;
    private FOSButton buttonSearch;
    private FOSEditText editTextName;
    private FOSEditText editTextMsisdn;
    private FOSEditText editTextZip;
    private Constants.Type userSelectionType;
    private RadioGroup rbGroup;
    private RadioButton rbAll;
    private RadioButton rbSME;
    private RadioButton rbTD;
    private RadioButton rbUPC;

    public void setUserSelectionType(Constants.Type userSelectionType) {
        this.userSelectionType = userSelectionType;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.dialog_search, container, false);

        initComponents();
        setListeners();

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, android.R.style.Theme_Material_Light_NoActionBar_Fullscreen);
    }

    private void initComponents() {
        imageViewBackButton = (ImageView) view.findViewById(R.id.imageViewBackButton);
        buttonSearch = (FOSButton) view.findViewById(R.id.buttonSearch);
        editTextName = (FOSEditText) view.findViewById(R.id.editTextSearchName);
        editTextMsisdn = (FOSEditText) view.findViewById(R.id.editTextSearchMsisdn);
        editTextZip = (FOSEditText) view.findViewById(R.id.editTextSearchZip);

        rbGroup = (RadioGroup) view.findViewById(R.id.rbGroup);
        rbAll = (RadioButton) view.findViewById(R.id.radioAll);
        rbUPC = (RadioButton) view.findViewById(R.id.radioUPC);
        rbSME = (RadioButton) view.findViewById(R.id.radioSME);
        rbTD = (RadioButton) view.findViewById(R.id.radioTD);

        if (userSelectionType == Constants.Type.COLLECTION) {
            rbGroup.setVisibility(View.INVISIBLE);
        }
    }

    private void setListeners() {
        imageViewBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Constants.RecordType recordType;
                if (userSelectionType == Constants.Type.RETENSION) {
                    if (rbAll.isChecked()) {
                        recordType = Constants.RecordType.ALL;
                    } else if (rbSME.isChecked()) {
                        recordType = Constants.RecordType.SME;
                    } else if (rbUPC.isChecked()) {
                        recordType = Constants.RecordType.UPC;
                    } else {
                        recordType = Constants.RecordType.TD;
                    }
                } else {
                    recordType = Constants.RecordType.ALL;
                }
                String name = editTextName.getText().toString();
                String msisdn = editTextMsisdn.getText().toString();
                String zip = editTextZip.getText().toString();

                isValid(name, msisdn, zip);

                SearchEvent event = new SearchEvent();
                event.setName(name);
                event.setMsisdn(msisdn);
                event.setZip(zip);
                event.setRecordType(recordType);

                EventBus.getDefault().post(event);

                dismiss();
            }
        });
    }

    private boolean isValid(String name, String msisdn, String zip) {
        if (name.equals("") && msisdn.equals("") && zip.equals("")) {
            return false;
        }
        return true;
    }
}
