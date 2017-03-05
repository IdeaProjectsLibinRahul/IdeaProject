package tech.libin.rahul.ideaproject.views.homescreen.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import tech.libin.rahul.ideaproject.R;
import tech.libin.rahul.ideaproject.configurations.Config;
import tech.libin.rahul.ideaproject.configurations.Constants;
import tech.libin.rahul.ideaproject.events.DetailsEvent;
import tech.libin.rahul.ideaproject.events.SearchEvent;
import tech.libin.rahul.ideaproject.facade.FOSFacade;
import tech.libin.rahul.ideaproject.facade.FOSFacadeImpl;
import tech.libin.rahul.ideaproject.service.handlers.ServiceCallback;
import tech.libin.rahul.ideaproject.service.responses.base.FOSError;
import tech.libin.rahul.ideaproject.views.basecomponents.FOSBaseFragment;
import tech.libin.rahul.ideaproject.views.homescreen.adapters.ActivityTabAdapter;
import tech.libin.rahul.ideaproject.views.homescreen.dummy.ActivityList;
import tech.libin.rahul.ideaproject.views.homescreen.viewmodels.ActivityModel;
import tech.libin.rahul.ideaproject.views.models.ActivityRequestModel;
import tech.libin.rahul.ideaproject.views.models.User;

/**
 * Created by libin on 26/02/17.
 */

public class FOSActivityTab extends FOSBaseFragment {

    private static final int PAGE_SIZE = 20;
    private int maxPages = 100;
    private List<ActivityModel> modelList;
    private ActivityTabAdapter.ClickListener clickListener;
    private LinearLayoutManager layoutManager;
    private View view;
    private RecyclerView recyclerView;
    private ProgressBar progressBarLoading;
    private ActivityTabAdapter adapter;
    private FOSFacade fosFacade;
    private RecyclerView.OnScrollListener recyclerViewOnScrollListener;
    private boolean isLoading;
    private boolean isLastPage;
    private int pageNo = 1;

    private String searchName = "";
    private String searchMsisdn = "";
    private String searchZip = "";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_activity_tab, container, false);
        initComponents();
        setClickListener();
        setScrollListener();
        initRecyclerView();
        sendRequest();
        return view;
    }

    private void initComponents() {
        pageNo = 1;
        maxPages = 100;
        isLastPage = false;
        isLoading = false;

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewActivity);
        progressBarLoading = (ProgressBar) view.findViewById(R.id.progressBarLoading);
    }

    private void initRecyclerView() {
        modelList = new ArrayList<>();
        adapter = new ActivityTabAdapter(modelList, clickListener);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.setOnScrollListener(recyclerViewOnScrollListener);
    }

    private void changeAdapter(List<ActivityModel> models) {
        modelList.addAll(models);
        adapter.setModelList(modelList);
        adapter.notifyDataSetChanged();
    }

    private void sendRequest() {
        if (pageNo == maxPages) {
            isLastPage = true;
        }

        User user = Config.getInstance().getUser();

        ActivityRequestModel requestModel = new ActivityRequestModel();
        requestModel.setName(searchName);
        requestModel.setActivityType(Constants.ActivityType.NEW_ACTIVITY);
        requestModel.setMsisdn(searchMsisdn);
        requestModel.setPageNo(pageNo);
        requestModel.setPageSize(PAGE_SIZE);
        requestModel.setType(Constants.Type.RETENSION);
        requestModel.setUserId(user.getUserId());
        requestModel.setZip(searchZip);

        progressBarLoading.setVisibility(View.VISIBLE);
        isLoading = true;

        fosFacade = new FOSFacadeImpl();
        fosFacade.getActivity(requestModel, new ServiceCallback<List<ActivityModel>>() {
            @Override
            public void onResponse(List<ActivityModel> response) {
                if (response.isEmpty()) {
                    maxPages = pageNo;
                } else {
                    changeAdapter(response);
                    isLoading = false;
                    recyclerView.setVisibility(View.VISIBLE);
                    progressBarLoading.setVisibility(View.GONE);
                    pageNo++;
                }
            }

            @Override
            public void onRequestTimout() {
                Toast.makeText(getActivity(), "TimeOut", Toast.LENGTH_SHORT).show();
                isLoading = false;
                progressBarLoading.setVisibility(View.GONE);
            }

            @Override
            public void onRequestFail(FOSError error) {
                Toast.makeText(getActivity(), "Request Failed", Toast.LENGTH_SHORT).show();
                isLoading = false;
                progressBarLoading.setVisibility(View.GONE);
                maxPages = pageNo;
            }
        });
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

    private void setScrollListener() {
        recyclerViewOnScrollListener = new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int visibleItemCount = layoutManager.getChildCount();
                int totalItemCount = layoutManager.getItemCount();
                int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();

                if (!isLoading && !isLastPage) {
                    if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                            && firstVisibleItemPosition >= 0
                            && totalItemCount >= PAGE_SIZE) {
                        sendRequest();
                    }
                }
            }
        };
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(SearchEvent event) {
        if (event != null) {
            pageNo = 1;
            maxPages = 100;
            isLastPage = false;
            isLoading = false;

            searchName = event.getName();
            searchMsisdn = event.getMsisdn();
            searchZip = event.getZip();

            sendRequest();
        }
    }

}
