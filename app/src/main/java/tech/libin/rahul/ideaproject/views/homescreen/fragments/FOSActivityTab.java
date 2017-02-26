package tech.libin.rahul.ideaproject.views.homescreen.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import tech.libin.rahul.ideaproject.R;
import tech.libin.rahul.ideaproject.events.DetailsEvent;
import tech.libin.rahul.ideaproject.views.basecomponents.FOSBaseFragment;
import tech.libin.rahul.ideaproject.views.homescreen.adapters.ActivityTabAdapter;
import tech.libin.rahul.ideaproject.views.homescreen.dummy.ActivityList;
import tech.libin.rahul.ideaproject.views.homescreen.viewmodels.ActivityModel;

/**
 * Created by libin on 26/02/17.
 */

public class FOSActivityTab extends FOSBaseFragment {

    ActivityTabAdapter.ClickListener clickListener;
    private View view;
    private RecyclerView recyclerView;
    private ActivityTabAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_activity_tab, container, false);
        initComponents();
        setClickListener();
        initRecyclerView();
        mocRecyclerView();
        return view;
    }

    private void initComponents() {
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewActivity);

    }

    private void initRecyclerView() {
        List<ActivityModel> modelList = new ArrayList<>();
        adapter = new ActivityTabAdapter(modelList, clickListener);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void changeAdapter(List<ActivityModel> models) {
        adapter.setModelList(models);
        adapter.notifyDataSetChanged();

    }

    private void mocRecyclerView() {
        List<ActivityModel> models = ActivityList.getModels();
        changeAdapter(models);
    }

    private void setClickListener() {
        clickListener = new ActivityTabAdapter.ClickListener() {
            @Override
            public void onClick(ActivityModel model) {
                DetailsEvent event = new DetailsEvent();
                event.setModel(model);

                EventBus.getDefault().post(event);
            }
        };
    }
}
