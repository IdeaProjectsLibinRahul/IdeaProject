package tech.libin.rahul.ideaproject.views.detailsview.adapters;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

import tech.libin.rahul.ideaproject.service.models.SpinnerData;
import tech.libin.rahul.ideaproject.views.widgets.textview.FOSTextView;

/**
 * Created by libin on 10/03/17.
 */

public class FOSSpinnerAdapter extends ArrayAdapter<SpinnerData> {

    ArrayList<SpinnerData> spinnerModels;

    public FOSSpinnerAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull ArrayList<SpinnerData> objects) {
        super(context, resource, objects);
        this.spinnerModels = objects;
    }


    @Override
    public int getCount() {
        return spinnerModels.size();
    }

    @Nullable
    @Override
    public SpinnerData getItem(int position) {
        return spinnerModels.get(position);
    }

    private View getCustomView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        SpinnerData model = spinnerModels.get(position);
        FOSTextView textView = new FOSTextView(parent.getContext());
        textView.setPadding(16, 16, 16, 16);
        textView.setText(model.getValue());
        return textView;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }
}
