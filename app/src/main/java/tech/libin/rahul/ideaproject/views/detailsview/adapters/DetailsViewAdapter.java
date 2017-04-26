package tech.libin.rahul.ideaproject.views.detailsview.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import tech.libin.rahul.ideaproject.R;
import tech.libin.rahul.ideaproject.service.models.DetailOtherData;
import tech.libin.rahul.ideaproject.views.widgets.textview.FOSTextView;

/**
 * Created by libin on 26/04/17.
 */

public class DetailsViewAdapter extends RecyclerView.Adapter<DetailsViewAdapter.DetailsViewHolder> {

    private ArrayList<DetailOtherData> data;

    public DetailsViewAdapter(ArrayList<DetailOtherData> data) {
        this.data = data;
    }

    @Override
    public DetailsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.details_other_view, parent, false);
        return new DetailsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DetailsViewHolder holder, int position) {
        DetailOtherData item = data.get(position);
        holder.textViewKey.setText(item.getKey());
        holder.textViewValue.setText(item.getValue());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class DetailsViewHolder extends RecyclerView.ViewHolder {
        private FOSTextView textViewKey;
        private FOSTextView textViewValue;

        public DetailsViewHolder(View itemView) {
            super(itemView);
        }
    }
}
