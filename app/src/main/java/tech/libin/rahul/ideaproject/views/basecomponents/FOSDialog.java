package tech.libin.rahul.ideaproject.views.basecomponents;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import tech.libin.rahul.ideaproject.R;
import tech.libin.rahul.ideaproject.views.widgets.button.FOSButton;
import tech.libin.rahul.ideaproject.views.widgets.textview.FOSTextView;

/**
 * Created by libin on 26/02/17.
 */

public class FOSDialog extends DialogFragment {
    private View view;
    private FOSButton buttonOK;
    private FOSTextView textViewTitle;
    private FOSTextView textViewMessage;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout_dialog, container, false);

        initComponents();
        return view;
    }

    private void initComponents() {
        buttonOK = (FOSButton) view.findViewById(R.id.button_dialog_ok);
        textViewTitle = (FOSTextView) view.findViewById(R.id.dialogTitle);
        textViewMessage = (FOSTextView) view.findViewById(R.id.dialogMessage);
    }

    public void setTitle(String title) {
        textViewTitle.setText(title);
    }

    public void setMessage(String message) {
        textViewMessage.setText(message);
    }

    public void setButtonOKClick(View.OnClickListener clickListener) {
        buttonOK.setOnClickListener(clickListener);
    }

}
