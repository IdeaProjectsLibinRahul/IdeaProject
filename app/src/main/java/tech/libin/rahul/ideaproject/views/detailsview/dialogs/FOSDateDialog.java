package tech.libin.rahul.ideaproject.views.detailsview.dialogs;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.DatePicker;

import java.util.Calendar;
import java.util.Date;

import tech.libin.rahul.ideaproject.views.basecomponents.FOSBaseDialog;

/**
 * Created by libin on 09/03/17.
 */

public class FOSDateDialog extends FOSBaseDialog implements DatePickerDialog.OnDateSetListener {

    private DateSetListener dateSetListener;

    public void setDateSetListener(DateSetListener dateSetListener) {
        this.dateSetListener = dateSetListener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        int yy = calendar.get(Calendar.YEAR);
        int mm = calendar.get(Calendar.MONTH);
        int dd = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dfrom = new DatePickerDialog(getActivity(), this, yy, mm, dd);
        dfrom.getDatePicker().setMinDate(new Date().getTime());
        dfrom.getDatePicker().setDescendantFocusability(DatePicker.FOCUS_BLOCK_DESCENDANTS);

        return dfrom;
    }

    @Override
    public void onDateSet(DatePicker view, int yy, int mm, int dd) {
        if (dateSetListener != null) {
            dateSetListener.onDateSet(yy, mm + 1, dd);
        }
    }

    public interface DateSetListener {
        void onDateSet(int year, int month, int day);
    }
}
