package com.alfredtechsystems.myapplication2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.untu.fis.app.untumobile.DataClasses.dbOperations;
import com.untu.fis.app.untumobile.DataClasses.dbSchedules;
import com.untu.fis.app.untumobile.ListAdapters.ScheduleListAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoanScheduleFragment extends Fragment {
    SwipeRefreshLayout mSwipeRefreshLayout;
    private List<dbSchedules> myDataset = new ArrayList<>();
    private dbOperations dboperation;
    private dbSchedules newSchedule;
    String url_schedule = configValues.url + "/getSchedule";

    public LoanScheduleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_loan_schedule, container, false);
        Bundle b = this.getArguments();
        String ln_value = ""; // or other values
        if(b != null)
            ln_value = b.getString("loan_id");
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.activity_main_swipe_refresh_layout);
        dboperation = new dbOperations(getContext());
        dboperation.open();
        final ListView listview = (ListView) view.findViewById(R.id.listview4);
        final String finalValue = ln_value;
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshContent();
            }
            private void refreshContent(){

                    checkStatus(finalValue);

                myDataset = dboperation.getAllSchedule(finalValue);
                ScheduleListAdapter adapter = new ScheduleListAdapter(getActivity(), myDataset);
                listview.setAdapter(adapter);
                mSwipeRefreshLayout.setRefreshing(false);
            }

        });
         checkStatus(ln_value);
         myDataset = dboperation.getAllSchedule(ln_value);
         ScheduleListAdapter adapter = new ScheduleListAdapter(getActivity(), myDataset);
         listview.setAdapter(adapter);
        return view;
    }

    private void checkStatus(String loan_id) {

        List<dbSchedules> mylist = dboperation.getAllSchedule(loan_id);
        if (mylist.size() > 0) {
        } else {
            JSONObject request = new JSONObject();
            try {
                request.put("id", loan_id);
            } catch (Exception e) {
                e.printStackTrace();

            }

            JSONArray request_array = new JSONArray();
            request_array.put(request);
            RequestQueue queue = Volley.newRequestQueue(getContext());
            JsonArrayRequest string_request = new JsonArrayRequest(Request.Method.POST, url_schedule, request_array, new Response.Listener<JSONArray>() {

                @Override
                public void onResponse(JSONArray response) {

                    // Process the JSON
                    try {
                        // Loop through the array elements
                        for (int i = 0; i < response.length(); i++) {
                            // Get current json object
                            JSONObject loan = response.getJSONObject(i);
                            String id = loan.getString("ln_account");
                            if (id.equals("0")) {
                                Toast.makeText(getContext(), "Error Submitting", Toast.LENGTH_SHORT).show();
                            } else {
                                if(dboperation.ScheduleCount(loan.getString("ln_account"),loan.getString("dueDate")))
                                {
                                    newSchedule = new dbSchedules();
                                    newSchedule.setAmount_due(loan.getString("amountDue"));
                                    newSchedule.setAmount_paid(loan.getString("amountlPaid"));
                                    newSchedule.setDue_date(loan.getString("dueDate"));
                                    newSchedule.setState(loan.getString("state"));
                                    newSchedule.setLn_account(loan.getString("ln_account"));
                                    dboperation.addSchedule(newSchedule);
                                }
                                else {

                                    newSchedule = dboperation.getSchedule(loan.getString("ln_account"),loan.getString("dueDate"));
                                    newSchedule.setAmount_due(loan.getString("amountDue"));
                                    newSchedule.setAmount_paid(loan.getString("amountlPaid"));
                                    newSchedule.setState(loan.getString("state"));
                                    dboperation.updateSchedule(newSchedule);
                                }

                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
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
                    5000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            queue.add(string_request);

        }
    }
}
