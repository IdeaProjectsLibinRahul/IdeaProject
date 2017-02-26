package tech.libin.rahul.ideaproject.views.homescreen.adapters;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.List;

import tech.libin.rahul.ideaproject.R;
import tech.libin.rahul.ideaproject.views.homescreen.viewmodels.ActivityModel;
import tech.libin.rahul.ideaproject.views.widgets.textview.FOSTextView;

/**
 * Created by libin on 26/02/17.
 */

public class ActivityTabAdapter extends RecyclerView.Adapter<ActivityTabAdapter.ActivityTabViewHolder> {

    private List<ActivityModel> modelList;
    private ClickListener listener;

    public ActivityTabAdapter(List<ActivityModel> modelList, ClickListener listener) {
        this.modelList = modelList;
        this.listener = listener;
    }

    @Override
    public ActivityTabViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.row_activity, parent, false);
        return new ActivityTabViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ActivityTabViewHolder holder, int position) {
        final ActivityModel model = modelList.get(position);

        if (position % 2 != 0) {
            holder.layout.setBackgroundColor(Color.argb(10, 0, 0, 0));
        }
        holder.textViewName.setText(model.getName());
        holder.textViewPhone.setText(model.getPhoneNo());
        holder.textViewTopRight.setText(model.getTopRight());
        holder.textViewBottomCenter.setText(model.getBottomCenter());
        holder.textViewBottomRight.setText(model.getBottomRight());
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onClick(model);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public void setModelList(List<ActivityModel> modelList) {
        this.modelList = modelList;
    }

    public interface ClickListener {
        void onClick(ActivityModel model);
    }

    class ActivityTabViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout layout;
        private FOSTextView textViewName;
        private FOSTextView textViewPhone;
        private FOSTextView textViewTopRight;
        private FOSTextView textViewBottomCenter;
        private FOSTextView textViewBottomRight;

        ActivityTabViewHolder(View itemView) {
            super(itemView);

            layout = (LinearLayout) itemView.findViewById(R.id.row_container);
            textViewName = (FOSTextView) itemView.findViewById(R.id.row_name);
            textViewPhone = (FOSTextView) itemView.findViewById(R.id.row_number);
            textViewTopRight = (FOSTextView) itemView.findViewById(R.id.row_top_right);
            textViewBottomCenter = (FOSTextView) itemView.findViewById(R.id.row_bottom_middle);
            textViewBottomRight = (FOSTextView) itemView.findViewById(R.id.row_bottom_right);

        }
    }
}
