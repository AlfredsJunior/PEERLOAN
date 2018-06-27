package com.alfredtechsystems.myapplication2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.untu.fis.app.untumobile.DataClasses.dbApplications;
import com.untu.fis.app.untumobile.DataClasses.dbOperations;
import com.untu.fis.app.untumobile.ListAdapters.CustomListAdapter;
import com.untu.fis.app.untumobile.ListAdapters.MyAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class LoanApplicationsFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<dbApplications> myDataset = new ArrayList<>();
    String url_status = configValues.url+"/status";
    private dbOperations dboperation;
    SwipeRefreshLayout mSwipeRefreshLayout;
    FloatingActionMenu materialDesignFAM;
    FloatingActionButton floatingActionButton1;

    public LoanApplicationsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_loan_applications, container, false);
        materialDesignFAM = (FloatingActionMenu) view.findViewById(R.id.application_floating_action_menu);
        floatingActionButton1 = (FloatingActionButton) view.findViewById(R.id.application_floating_action_menu_item1);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.activity_main_swipe_refresh_layout);
        dboperation = new dbOperations(getContext());
        dboperation.open();
        final List<dbApplications> mylist = dboperation.getAllApplications();
        final ListView listview = (ListView) view.findViewById(R.id.listview);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshContent();
            }
            private void refreshContent(){

                for (dbApplications application : mylist) {
                    checkStatus(application);
                }
                myDataset = dboperation.getAllApplications();
                CustomListAdapter adapter = new CustomListAdapter(getContext(), myDataset);
                listview.setAdapter(adapter);
                mSwipeRefreshLayout.setRefreshing(false);
            }

        });

        materialDesignFAM.setClosedOnTouchOutside(true);
        floatingActionButton1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(getContext(),
                        LoanApplicationActivity.class);
                startActivity(myIntent);
            }
        });
        myDataset = dboperation.getAllApplications();
        CustomListAdapter adapter = new CustomListAdapter(getContext(), myDataset);
        listview.setAdapter(adapter);

        // Click event for single list row
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {
                final dbApplications item = (dbApplications) parent.getItemAtPosition(position);
                Intent intent = new Intent(getContext(),
                        ApplicationDetailsActivity.class);
                Bundle b = new Bundle();
                b.putLong("id", item.getAppId()); //Your id
                intent.putExtras(b); //Put your id to your next Intent
                startActivity(intent);
            }
        });

        return view;
    }

    private void updateApplication(String status, dbApplications application) {

        application.setAppStatus(status);
        int i = dboperation.updateApplication(application);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                // User chose the "Settings" item, show the app settings UI...
                return true;

            case R.id.action_refresh:
                mSwipeRefreshLayout.setRefreshing(true);
                final List<dbApplications> mylist = dboperation.getAllApplications();
                for (dbApplications application : mylist) {
                    checkStatus(application);
                }
                myDataset = dboperation.getAllApplications();
                mLayoutManager = new LinearLayoutManager(getContext());

                mAdapter = new MyAdapter(myDataset);
                mSwipeRefreshLayout.setRefreshing(false);


                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }

    public void checkStatus(final dbApplications application){
        final Long app_id = application.getAppId();
        JSONObject register = new JSONObject();
        try
        {
            register.put("id",application.getApplcnId());
        }
        catch(Exception e)
        {
            e.printStackTrace();

        }

        RequestQueue queue = Volley.newRequestQueue(getContext());
        JsonObjectRequest string_request = new JsonObjectRequest(Request.Method.POST, url_status,register,new Response.Listener<JSONObject>() {


            @Override
            public void onResponse(JSONObject response) {

                String id ="";
                String status ="";
                try {
                    id = response.getString("id");
                    status = response.getString("status");
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                if(id.equals("0")) {

                }
                else{
                    updateApplication(status,application);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                return headers;
            }
        };
        string_request.setRetryPolicy(new DefaultRetryPolicy(
                0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(string_request);
    }
}
