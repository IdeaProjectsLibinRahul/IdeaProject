package tech.libin.rahul.ideaproject.views.widgets.dialogs;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import tech.libin.rahul.ideaproject.R;
import tech.libin.rahul.ideaproject.views.basecomponents.FOSBaseDialog;
import tech.libin.rahul.ideaproject.views.widgets.button.FOSButton;
import tech.libin.rahul.ideaproject.views.widgets.textview.FOSTextView;

/**
 * Created by libin on 26/02/17.
 */

public class FOSDialog extends FOSBaseDialog {
    public static final String TITLE = "title";
    public static final String MESSAGE = "message";
    private View view;
    private FOSButton buttonOK;
    private FOSTextView textViewTitle;
    private FOSTextView textViewMessage;
    private boolean isRegister;
    private Activity activity;

    public static FOSDialog newInstance(Activity activity, String title, String message, boolean isRegister) {
        Bundle bundle = new Bundle();
        bundle.putString(TITLE, title);
        bundle.putString(MESSAGE, message);

        FOSDialog fosDialog = new FOSDialog();
        fosDialog.setArguments(bundle);
        fosDialog.isRegister = isRegister;
        fosDialog.activity = activity;

        return fosDialog;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout_dialog, container, false);

        initComponents();
        parseBundle();

        return view;
    }

    private void initComponents() {
        buttonOK = (FOSButton) view.findViewById(R.id.button_dialog_ok);
        textViewTitle = (FOSTextView) view.findViewById(R.id.dialogTitle);
        textViewMessage = (FOSTextView) view.findViewById(R.id.dialogMessage);

        buttonOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().dismiss();
                if (isRegister) {
                    activity.finish();
                }
            }
        });
    }

    private void parseBundle() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            String title = bundle.getString(TITLE);
            String message = bundle.getString(MESSAGE);

            textViewTitle.setText(title);
            textViewMessage.setText(message);
        }
    }

}
