package com.tels.androidassignmentexercise.Fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.tels.androidassignmentexercise.Adapter.CustomAdapter;
import com.tels.androidassignmentexercise.Application.MyApplication;
import com.tels.androidassignmentexercise.Model.CountryResponse;
import com.tels.androidassignmentexercise.Model.Row;
import com.tels.androidassignmentexercise.Network.Service.NetworkService;
import com.tels.androidassignmentexercise.R;
import com.tels.androidassignmentexercise.Utils.DividerItemDecoration;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by saurabhgarg on 20/02/17.
 */
public class ListFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView recyclerView;
    private ProgressDialog pbDialog;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private NetworkService mNetworkService;
    private MyApplication myApplication;
    private TaskListener listener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (TaskListener) context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_fragment, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initView();

        myApplication = (MyApplication) getActivity().getApplicationContext();
        if (null == mNetworkService) {
            mNetworkService = myApplication.getNetworkProvider().getNetworkService();
        }

        fetchResponse();
    }

    /**
     * This method is used to initialize view.
     */
    private void initView() {
        recyclerView = (RecyclerView) getActivity().findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),LinearLayoutManager.VERTICAL));
        mSwipeRefreshLayout = (SwipeRefreshLayout) getActivity().findViewById(R.id.swipeRefreshLayout);
        mSwipeRefreshLayout.setOnRefreshListener(this);
    }

    /**
     * Calling web service and rendering service response
     */
    private void getServerResponse() {
        pbDialog = ProgressDialog.show(getActivity(), getResources().getString(R.string.loading), getResources().getString(R.string.please_wait));
        Call<CountryResponse> androidResponseCall = mNetworkService.getResponse();
        androidResponseCall.enqueue(new Callback<CountryResponse>() {
            @Override
            public void onResponse(Call<CountryResponse> call, retrofit2.Response<CountryResponse> response) {
                pbDialog.dismiss();
                int statusCode = response.code();
                if (statusCode == 200) {
                    CountryResponse countryResponse = response.body();

                    listener.onTaskFinished(countryResponse.getTitle());
                    List<Row> rowData = countryResponse.getRows();

                    /***
                     * Skip null row from the response
                     */
                    for (int i = 0; i < rowData.size(); i++) {
                        if (rowData.get(i).getTitle() == null && rowData.get(i).getDescription() == null && rowData.get(i).getImageHref() == null) {
                            rowData.remove(rowData.get(i));
                        }
                    }

                    recyclerView.setAdapter(new CustomAdapter(rowData, getActivity()));
                    mSwipeRefreshLayout.setRefreshing(false);
                } else {
                    mSwipeRefreshLayout.setRefreshing(false);
                    Toast.makeText(getActivity(), getResources().getString(R.string.server_error), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CountryResponse> call, Throwable t) {
                mSwipeRefreshLayout.setRefreshing(false);
                pbDialog.dismiss();
                Toast.makeText(getActivity(), getResources().getString(R.string.server_error), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onRefresh() {
        fetchResponse();
    }

    /**
     * This interface to set Action bar title after response
     */
    public interface TaskListener {
        void onTaskFinished(String title);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_action, menu);

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Take appropriate action for action item click
        switch (item.getItemId()) {
            case R.id.action_refresh:
                fetchResponse();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * This method is use to fetch data from URL.
     */
    private void fetchResponse() {
        if (myApplication.isNetworkConnectionAvailable()) {
            getServerResponse();
        } else {
            //Setting title of screen in case of Network failure
            listener.onTaskFinished(getResources().getString(R.string.network_error));
            Toast.makeText(getActivity(), getResources().getString(R.string.check_network), Toast.LENGTH_SHORT).show();
        }
    }
}
